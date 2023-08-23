import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlinx.serialization.json.Json as KJson

private val module = SerializersModule {
    polymorphic(LayoutViewData::class) {
        subclass(PaneData::class)
    }
    polymorphic(ComponentViewData::class) {
        // UI COMPONENTS
        subclass(ButtonData::class)
        subclass(LabelData::class)
        subclass(TextFieldData::class)
        subclass(TextAreaData::class)
        subclass(ProgressBarData::class)
        subclass(ColorPickerData::class)
        subclass(ToggleButtonData::class)
        subclass(RadioButtonData::class)
        subclass(CheckBoxData::class)
        subclass(BinaryStateButtonData::class)
        subclass(ComboBoxData::class)
        subclass(PasswordFieldData::class)
        subclass(ListViewData::class)
        subclass(TableViewData::class)
        // LAYOUT VIEWS
        subclass(PaneData::class)
        subclass(GridPaneData::class)
        subclass(CameraPaneData::class)
        // GAME COMPONENTS
        subclass(CardViewData::class)
        subclass(DiceViewData::class)
        subclass(HexagonViewData::class)
        subclass(TokenViewData::class)
        // CONTAINER
        subclass(AreaData::class)
        subclass(CardStackData::class)
        subclass(HexagonGridData::class)
        subclass(LinearLayoutData::class)
        subclass(SatchelData::class)
    }
    polymorphic(VisualData::class) {
        subclass(ColorVisualData::class)
        subclass(ImageVisualData::class)
        subclass(TextVisualData::class)
        subclass(CompoundVisualData::class)
    }
}

val mapper = KJson { serializersModule = module }
