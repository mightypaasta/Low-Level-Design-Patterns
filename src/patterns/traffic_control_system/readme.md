## Requirements:
1. Traffic signal system = control flow of traffic at intersection of roads.
2. Signal = RED, GREEN, YELLOW
3. Configurable = Duration of signal and adjustable(??) based on traffic conditions(??)
4. Smooth transition b/w signals
5. Should detect and handle emergency situation = Ambulance, Fire truck approaching intersection.
6. Should be scalable and extensible to support additional features and functionality.

ENUM
Signal = GREEN, YELLOW, RED;

<<TrafficSignal>>
- id : String
- signal : Signal
- duration : Duration
- 