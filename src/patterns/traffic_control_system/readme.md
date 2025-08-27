## Requirements:
1. Traffic signal system = control flow of traffic at intersection of roads.
2. Signal = RED, GREEN, YELLOW
3. Configurable = Duration of signal and adjustable(??) based on traffic conditions(??)
4. Smooth transition b/w signals
5. Should detect and handle emergency situation = Ambulance, Fire truck approaching intersection.
6. Should be scalable and extensible to support additional features and functionality.

## Thought Process:
- The idea is to keep simple design where the traffic will flow from east to west or vice versa and north to south or
  vice versa.
- Therefore, we will focus on concept of intersections. Where we are going to control the traffic lights based on
  what intersection they belong to.
- So we will have a TrafficControlSystem which will contains list of intersections.
- Each intersection will have unique id to differentiate themselves. And each intersection will have
  one traffic light in each direction.
- We can control the duration of signals from the IntersectionController class itself as it will keep the duration 
  same/sync for all the lights in that intersection.
- For smooth transition we will transition from Green light to yellow and then to red.
- The Duration will be configurable for Green and Yellow light as the duration of Red light will be equal to the sum of 
  green light and yellow light duration.
- We will have a CentralMonitor class to observe the change in the traffic lights of the intersection. To do this we
  will be using Observer pattern.
- We will be using State pattern for IntersectionController and TrafficLight. Because of these reasons:

## Why State Pattern is used for IntersectionController and TrafficLight:
The **state design pattern** is used in this UML diagram to **model how the behavior of objects (mainly intersections and traffic lights) changes depending on their internal state**. It brings clarity, scalability, and flexibility to managing complex, state-dependent logic—essential for systems like traffic control.

### Where is the State Pattern Applied?
- **IntersectionController** uses `IntersectionState` (interface) with concrete states like `EastWestGreenState` and `NorthSouthGreenState`.
- **TrafficLight** uses `SignalState` (interface) with concrete states like `GreenState`, `YellowState`, and `RedState`.

***

### **Why Use State Pattern Here?**

#### 1. **Encapsulate State-Dependent Logic**
- Each state (e.g., GreenState, YellowState, RedState) encapsulates the rules and actions for that specific color.
- Intersection states (EastWestGreenState, NorthSouthGreenState) determine which direction has the green light and how transitions occur.

#### 2. **Clean Transitions Between States**
- Instead of large conditional statements (if-else/switch), the object simply delegates behavior to the current state object.
- When a state transition happens (e.g., green to yellow), the context (TrafficLight or IntersectionController) switches its current state object.

#### 3. **Extensible and Maintainable Design**
- New states or behaviors can be added easily (e.g., a future blinking state, pedestrian signal).
- State logic is separate from the context class, making it easier to test and update.

#### 4. **Improves Readability and Testability**
- Complexity is isolated to each state class.
- The main controller or light stays simple—just holds a reference to the current state and delegates work.

#### 5. **Real-World Modeling**
- Traffic systems are inherently state-driven:
    - Lights cycle between GREEN → YELLOW → RED.
    - Intersection cycles between North-South green → East-West green.
- The state pattern mirrors how real systems transition and enforce rules.

***

### **Summary Table**

| Class                | State Interface      | Concrete States            | Purpose                                           |
|----------------------|---------------------|----------------------------|---------------------------------------------------|
| IntersectionController| IntersectionState   | EastWestGreenState,<br>NorthSouthGreenState | Controls traffic flow direction, timing, & transition |
| TrafficLight         | SignalState         | GreenState,<br>YellowState,<br>RedState | Controls color/time/observers for a single light      |

***

**In summary:**  
The state design pattern lets traffic lights and intersections change behavior cleanly when their state changes, reflecting actual traffic rules and cycles in a robust, maintainable way. It's particularly useful for time-based, reactive, and cyclic systems like traffic control.

[1](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/images/10752736/ae74f58e-9d7e-47c1-bf15-5032dd8da311/trafficcontrolsystem-class-diagram.jpg)

## Entities

### ENUM
LightColor = GREEN, YELLOW, RED;
Direction = EAST, WEST, NORTH, SOUTH;

### SignalState (Interface)
- handle(TrafficLight): void

RedState, GreenState, YellowState

### TrafficLight
- signalColor: SignalColor
- currentState: SignalState
- nextState: SignalState
- intersectionId: int
- observers: List<TrafficObservers>
- direction: Direction
- 
- startGreen(): void
- addObserver(): void
- removeObserver(): void
- notifyObserver(): void
- transition(): void

### TrafficObserver (Interface)
- update(int,Direction,SignalColor)

CentralMonitor: TrafficObserver

### IntersectionState
handle(IntersectionController): void

EaseWestGreenState, NorthSouthGreenState

### IntersectionController
- id: int
- running: boolean
- currentState: IntersectionState
- greenDuration: int
- yellowDuration: int
- trafficLights: Map<Direction,TrafficLight>

- start(): void
- stop(): void
- run(): void
- getLight(Direction): TrafficLight

### TrafficControlSystem
- executorService: ExecutorService
- instance: TrafficControlSystem
- intersections: List<IntersectionController>

- getInstance(): TrafficControlSystem
- addIntersection(int,int,int): void
- startSystem(): void
- stopSystem(): void

## What, when and how handle function works inside these state function?
In the context of this UML diagram, the **handle** function is fundamental to the **State design pattern**; it defines the behavior that occurs when transitioning or operating within a particular state for both intersections and traffic lights.

***

### **Where Is `handle` Used?**

- **IntersectionState** (interface):
    - `handle(IntersectionController): void`
    - Implemented by `EastWestGreenState` and `NorthSouthGreenState`

- **SignalState** (interface):
    - `handle(TrafficLight): void`
    - Implemented by `GreenState`, `YellowState`, `RedState`

***

### **What Does `handle` Do?**

**1. In Intersection States:**
- The `handle` function determines how an intersection behaves in a given traffic direction state (East-West or North-South green).
- **What?**
    - It checks timers, changes the current green/yellow light, decides when to transition to the next state, and manages timing for each state.
- **When?**
    - Called at each cycle of traffic lights, or at specific time triggers (e.g., when green duration has elapsed).
- **Example:**
    - If the intersection is in `EastWestGreenState`, the `handle` will keep East-West lights green and North-South lights red, and after the configured time, transition to the next state.

**2. In Signal States:**
- The `handle` function defines what a specific traffic light does when in a given color state (Green, Yellow, or Red).
- **What?**
    - Manages the light's color transition, notifies observers, and determines when to move to the next color.
- **When?**
    - Called when the light enters a particular signal state, after a timer expires, or when a transition is needed.
- **Example:**
    - In `GreenState`, `handle` might set the light color to GREEN, start a timer for green duration, notify any observers, and then transition to `YellowState` when time is up.

***

### **Why Is This Useful?**

- **Encapsulates state-specific logic:** Each state knows how to behave and when to cause transitions, avoiding large conditionals elsewhere.
- **Flexible and maintainable:** Adding or modifying states only requires changes to individual state classes.
- **Triggers actions at the right moments:** Timers and events lead to `handle` being called at key cycle points for realistic, cyclic control.

***

### **Summary Table**

| State Type      | Class/Interface         | What `handle` Does                           | When Called                               |
|-----------------|------------------------|----------------------------------------------|-------------------------------------------|
| Intersection    | IntersectionState,<br>EastWestGreenState,<br>NorthSouthGreenState | Manages which direction is green/red,<br> transitions to next intersection state | When intersection updates,<br>at start, when timer triggers|
| Signal/Light    | SignalState,<br>GreenState,<br>YellowState,<br>RedState | Changes light color,<br> starts timer,<br> notifies observers,<br>moves to next color state | On light transition, after timer, at cycle events         |

***

**In short:**  
The `handle` method is invoked at every meaningful transition or update in intersection and light cycles. It carries out state-specific actions, ensuring each part of the system behaves correctly according to its current state and timings.

[1](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/images/10752736/ae74f58e-9d7e-47c1-bf15-5032dd8da311/trafficcontrolsystem-class-diagram.jpg)