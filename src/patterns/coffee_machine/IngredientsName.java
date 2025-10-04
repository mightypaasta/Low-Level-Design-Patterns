package patterns.coffee_machine;

public sealed interface IngredientsName permits IngredientsName.COFFEE_BEANS, IngredientsName.MILK, IngredientsName.SUGAR, IngredientsName.WATER, IngredientsName.CREAM{
    enum COFFEE_BEANS implements IngredientsName {
        ARABICA,
        ROBUSTA
    }
    enum MILK implements  IngredientsName {
        ALMOND,
        OAT,
        GOAT,
        COW,
        CAMEL
    }
    enum SUGAR implements IngredientsName {
        NORMAL,
        STEVIA
    }
    enum WATER implements IngredientsName {
        NORMAL
    }
    enum CREAM implements IngredientsName{
        HEAVY
    }
}


