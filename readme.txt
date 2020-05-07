Guidelines:

********* To run project **
./gradlew clean build

output of command
------------------------
> Task :test

DroneServiceTest > forwardMoveTest() STANDARD_OUT
    09:10:20.673 [Test worker] INFO com.drone.drone.service.MotionService - Drone in Forward Direction

DroneServiceTest > landTest() STANDARD_OUT
    09:10:20.683 [Test worker] INFO com.drone.drone.service.MotionService - Drone are in down direction

DroneServiceTest > rightTest() STANDARD_OUT
    09:10:20.690 [Test worker] INFO com.drone.drone.service.MotionService - Drone in Right Direction

DroneServiceTest > stabilizeTest() STANDARD_OUT
    09:10:20.693 [Test worker] INFO com.drone.drone.service.MotionService - Drone are in up direction
    09:10:20.694 [Test worker] INFO com.drone.drone.service.MotionService - Drone in hover mode.

DroneServiceTest > takeOffTest() STANDARD_OUT
    09:10:20.701 [Test worker] INFO com.drone.drone.service.MotionService - Drone are in up direction
------------------------------
************end*************



Roll axis -> Back to Front
    rotation about this axis -> Roll
Pitch axis -> Left to right
    rotation about this axis -> Pitch
Yaw axis -> top to bottom (counter/anti clockwise)
    rotation about this axis - yaw

Thrust Formula
F = m * v          F = ma
  = (m/t) * v       = m * (dv/dt) : dv rate of change of velocity
where m is mass, v is velocity

Condition
1> Thrust = weight -> hover condition
2> Thrust > weight -> upward condition
3> Thrust < weight -> downward condition

Direction of Thrust
1> move drone to forward direction :
  - Increase the power of rare motor
  - Reduce the power of front motor
2> move drone to Backward motion
  - Reduce the power of rare motor
  - Increase the power of front motor
3> move drone left
   - Increase the power of right motor
   - reduce the power of left motor
4> move drone to right
   - Reduce the power of right motor
   - increase the power of left motor

Algorithm
take_off()
 - given weight
 - calculate thrust(given velocity)
Thrust(Velocity vel)
 - f = weight * vel
 - compare thrust vs weight
 - if thrust > weight
 - then take of
 - if(thrust < weight){
 - then take down
 - if(thrust == weight){
  - then hover

interface move
void forward(List<Engine> engines);
void back(List<Engine> engine);
void left(List<Engine> engine);
void right(List<Engine> engine);
void up(Gyroscope gyroscope);
void down(Gyroscope gyroscope);

void forward(List<Engine> engines);
- rare engine with power 10
- front engine with power 1

void back(List<Engine> engine);
- rare engine with power 1
- front engine with power 10

void left(List<Engine> engine);
- right engine with power 10
- left engine with power 1

void right(List<Engine> engine);
- right engine with power 1
- left engine with power 10

void down(Gyroscope gyroscope);
- call thrust function with gyroscope velocity (z= -10)


void up(Gyroscope gyroscope);
- call thrust function with gyroscope velocity (z= 10)

stabilize()
- call take_off()
- call status() : will provide the pitch and roll
- call thrust function : thrust == weight

land()
- call take_off()
- call status()
-call thrust function : thrust < weight

status(OrientationSensor sensor)
- return pitch and roll (OrientationSensor);











