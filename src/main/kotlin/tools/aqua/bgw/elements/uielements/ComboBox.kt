@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package tools.aqua.bgw.elements.uielements

import tools.aqua.bgw.observable.ObjectProperty
import tools.aqua.bgw.observable.ObservableArrayList
import tools.aqua.bgw.observable.ObservableList
import tools.aqua.bgw.util.Font

/**
 * A standard ComboBox that may be populated with items of specified type parameter.
 * The formatFunction is used to gain a String representation of each item.
 * If no formatFunction is specified the toString function gets used instead.
 *
 * Whenever the user selects an item, the selectedItemProperty gets updated.
 *
 * @param height height for this Button. Default: 0.
 * @param width width for this Button. Default: 0.
 * @param posX horizontal coordinate for this Button. Default: 0.
 * @param posY vertical coordinate for this Button. Default: 0.
 * @param prompt Prompt for this ComboBox.
 *        This gets displayed as a prompt to the user whenever the selectedItemsProperty value is null.
 *        Default: empty string.
 * @param font font to be used for the label. Default: default Font constructor.
 * @param items the initial selection of items. Default: empty list.
 * @param selectedItem the initial selected item. Null denotes no initial selection. Default: null.
 * @param formatFunction the formatFunction that is used to represent the items. Default: null.
 */
open class ComboBox<T>(
	height: Number = 0,
	width: Number = 0,
	posX: Number = 0,
	posY: Number = 0,
	@Suppress("UNUSED_PARAMETER") val prompt: String = "",
	font: Font = Font(),
	items: List<T> = listOf(),
	selectedItem: T? = null,
	formatFunction: ((T) -> String)? = null, //TODO: implement
) : UIElementView(
	height = height,
	width = width,
	posX = posX,
	posY = posY,
	font = font
) { //TODO: Selected Item not working
	
	/**
	 * Property for the items list for this ComboBox.
	 */
	val observableItemsList: ObservableList<T> = ObservableArrayList()
	
	/**
	 * Items list for this ComboBox.
	 * @see observableItemsList
	 */
	var items: MutableList<T>
		get() = observableItemsList.list
		set(value) {
			observableItemsList.clear()
			observableItemsList.addAll(value)
		}
	
	/**
	 * Property for the selected item.
	 * Value may be null if no item is selected.
	 */
	val selectedItemProperty: ObjectProperty<T?> = ObjectProperty(null)
	
	/**
	 * The selected item.
	 * May be null if no item is selected.
	 * @see selectedItemProperty
	 */
	var selectedItem: T?
		get() = selectedItemProperty.value
		set(value) {
			require(items.contains(value)) { "Items list does not contain element to select: $value" }
			
			selectedItemProperty.value = value
		}

	/**
	 * Property for the formatFunction that gets used to obtain a String representation for each item.
	 * If the value is null, the toString function of the item is used instead.
	 */
	var formatFunctionProperty: ObjectProperty<((T) -> String)?> = ObjectProperty(formatFunction)

	/**
	 * The formatFunction that gets used to obtain a String representation for each item.
	 * If the value is null, the toString function of the item is used instead.
	 * @see formatFunctionProperty
	 */
	var formatFunction: ((T) -> String)?
		get() = formatFunctionProperty.value
		set(value) {
			formatFunctionProperty.value = value
		}

	init {
		observableItemsList.addAll(items)
		
		if (selectedItem != null) {
			require(items.contains(selectedItem)) { "Items list does not contain element to select." }
			
			selectedItemProperty.value = selectedItem
		}
	}
}