package tools.aqua.bgw.examples.sudoku.view

import tools.aqua.bgw.examples.sudoku.entity.Sudoku
import tools.aqua.bgw.examples.sudoku.entity.SudokuTuple

interface Refreshable {
	fun refreshInit(initialSudoku: Sudoku)
	fun refreshSetValue(tuple : SudokuTuple)
	fun refreshHint(tuple : Collection<SudokuTuple>)
}