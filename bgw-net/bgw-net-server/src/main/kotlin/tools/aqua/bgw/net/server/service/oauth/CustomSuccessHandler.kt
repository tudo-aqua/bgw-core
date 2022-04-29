/*
 * Copyright 2022 The BoardGameWork Authors
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tools.aqua.bgw.net.server.service.oauth

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomSuccessHandler(
    var accountRepository: AccountRepository,
) : AuthenticationSuccessHandler {
  private val logger = LoggerFactory.getLogger(javaClass)

  override fun onAuthenticationSuccess(
      request: HttpServletRequest?,
      response: HttpServletResponse?,
      authentication: Authentication?
  ) {
    if (authentication != null && authentication.principal is OidcUser) {
      val user = authentication.principal as OidcUser
      val account = accountRepository.findBySub(user.name)
      if (account.isEmpty) {
        user.getAttribute<String>("name")?.let {
          createAccount(it, user.name, user.getAttribute<ArrayList<String>>("groups"))
        }
      } else {
        user.getAttribute<String>("name")?.let {
          updateAccount(
              account.get(), it, user.name, user.getAttribute<ArrayList<String>>("groups"))
        }
      }
    }
    response?.sendRedirect(request?.contextPath + "/")
  }

  fun updateAccount(account: Account, name: String, sub: String, groups: ArrayList<String>?) {
    accountRepository.save(
        account.apply {
          this.accountName = name
          this.role = getRoleFromGroups(groups)
        })
  }

  fun getRoleFromGroups(groups: ArrayList<String>?): String {
    if (groups != null && groups.isNotEmpty()) {
      if (groups.contains("tutorengruppe")) {
        return "admin"
      }
    }
    return "user"
  }

  fun createAccount(name: String, sub: String, groups: ArrayList<String>?) {
    accountRepository.save(Account(sub = sub, accountName = name, role = getRoleFromGroups(groups)))
  }
}
