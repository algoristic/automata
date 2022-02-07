# automata

## Examples

### Elementary Cellular Automata
See classes under `src/test` `de.algoristic.automata.evolution` for further exaples.

The `automata` project offers various possibilities of creating, monitoring and printing your automata. You can hook into every stage of the transition process of the automaton which enables the shown (build-in) printing functions.

```java
Automaton automaton = Automaton.Builder.wolframsUniverse(110) // binary representation of rule to be applied
    .chaotic(51) // generates a random pattern of 51 dead or alive cells
    .withRuntime(51) // ...and defines a running time of 51 generations
    .build();
automaton.run();
```

### Conway's Game Of Life (and any other Life-like Cellular Automata)

```java
Seed seed = new RandomSeed(25, 25); // will create a random pattern of dead or alive cells on a 25x25 grid
Automaton automaton = Automaton.Builder.gameOfLife("B2/S23") // literal representation of the life-like rule to be applied
    .withSeed(seed) // use the defined seed as start
    .withRuntime(25) // ...defines a running time of 25 generations for the simulation
    .build();
automaton.run();
```

### Langton's Ant (and some other Turmites)


```java
Turmites rule = Turmites.getInstance("LLRR"); // create a generic turmite rule from its' literal representation
TurmitesRuleMetadata metadata = rule.getMetadata();

// define a simple printer that compiles the complete lifecycle of the automaton into a single GIF file
Path directory = Paths.get("path/to/your/output/directory");
LifeCyclePrinter printer = new LifeCyclePrinter(directory.resolve(supplier + ".gif")); // printer for the compilation of the final GIF
Printer<FinishBreedingEvent> automationStepPrinter = new Printer.Builder(directory) // printer for the single generations
  .withCallback(printer::addFile) // notify printer for every file t be compiled into GIF
  .withColorMapping(ColorModel.TURMITES(metadata)) // use a predefined color model for printing
  .buildEvolutionStepPrinter();

Automaton automaton = Automaton.Builder
  .turmites(rule) // use the defined rule
  .withSeed(new AntSeed(5)) // starts with an empty 5x5 grid, populated by a single ant at the center
  .withRuntime(10) // define a running time of 10 generations
  .build();
automaton.registerFinishBreedingListener(automationStepPrinter); // register printer for the single generations
automaton.run();
printer.print(25); // compile the generation-images to a single GIF file with 25 FPS
```

### Wireworld

```java
Seed seed = new TemplateFile("path/to/your/seedfile.txt"); // use a seed file with representation of cell states for predefined non-random  structures
Automaton automaton = Automaton.Builder.wireworld()
  .withSeed(seed) // use the defined seed
  .withRuntime(17) // define a running time of 17 generations
  .build();
automaton.run();
```

### More complex printing example
See classes under `src/test` `de.algoristic.automata.prod` for further exaples.

```java
Path destination = Paths.get("path/to/your/directory");
ColorModel colors = new Coolors()
  .withMapping(BinaryState.DEAD, Coolors.richBlackFOGRA29)
  .withMapping(BinaryState.ALIVE, Coolors.platinum)
  .withBackground(Coolors.richBlackFOGRA29)
  .withFrameColor(Coolors.richBlackFOGRA29)
  .build();
IntStream.range(0, 256).forEach(rule -> {
  Printer<FinishAutomationEvent> printer = new Printer.Builder(destination)
    .withColorMapping(colors)
    .withCellSize(8)
    .withScaling(2)
    .withFrameWidth(8*20)
    .withBorder(2)
    .buildElementaryPrinter();
  Automaton automaton = Automaton.Builder
    .wolframsUniverse(rule)
    .chaotic(101)
    .withRuntime(101)
    .build();
  automaton.registerFinishAutomationListener(printer);
  automaton.run();
});
```

### Some image results

#### A simple print of the Rule 110 by Stephen Wolfram
![Simple Rule 110](./doc/cellular-automaton_110.gif)

### Gosper glider gun in Conway's Game of Life
![Gosper glider gun](./doc/gosperglidergun.gif)

### XOR-Gate in Wireworld
![XOR-Gate](./doc/wirworld_xor.gif)

### A more advanced approach to the Rule 110
![Advanced Rule 110](./doc/chaotic_rule_110.gif)
