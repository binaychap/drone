#Run the app
```
 ./gradlew clean build

```
```
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
```

# Roll axis 
###Back to Front
#####rotation about this axis -> Roll
#Pitch axis 
 ###Left to right
 ##### rotation about this axis -> Pitch
 
 #Yaw axis
 ### Top to Bottom (counter/anti clockwise)
 ##### rotation about this axis - yaw
 
 #Thrust Formula
 ```
F = m * v          F = ma
      = (m/t) * v       = m * (dv/dt) : dv rate of change of velocity
    where m is mass, v is velocity
```

# Condition
1. Thrust = weight -> hover condition
2. Thrust > weight -> upward condition
3. Thrust < weight -> downward condition

#Direction of Thrust
1. move drone to forward direction 
    - Increase the power of rare motor
    - Reduce the power of front motor
2. move drone to Backward motion
    - Reduce the power of rare motor
    - Increase the power of front motor
3. move drone left
   - Increase the power of right motor
   - reduce the power of left motor
4. move drone to right
   - Reduce the power of right motor
   - increase the power of left motor


#Algorithm
1. take_off()
    - given weight
    - calculate thrust(given velocity)
2. Thrust(Velocity vel)
    - f = weight * vel
    - compare thrust vs weight
    - if thrust > weight
    - then take of
    - if(thrust < weight){
    - then take down
    - if(thrust == weight){
    - then hover
3. interface drone
    - void takeOff(Drone drone);
    - void stabilize(Drone drone);
    - void land(Drone drone);
    - Drone status(Drone drone);
    - void move(Drone drone);

3. interface motion
    - void forward(Drone drone);
    - void back(Drone drone);
    - void left(Drone drone);
    - void right(Drone drone);
    - void up(Drone drone);
    - void down(Drone drone);

4. void forward(List<Engine> engines);
    - rare engine with power 10
    - front engine with power 1

5. void back(List<Engine> engine);
    - rare engine with power 1
    - front engine with power 10

6. void left(List<Engine> engine);
    - right engine with power 10
    - left engine with power 1

7. void right(List<Engine> engine);
    - right engine with power 1
    - left engine with power 10

8. void down(Gyroscope gyroscope);
    - call thrust function with gyroscope velocity (z= -10)
    
9. void up(Gyroscope gyroscope);
    - call thrust function with gyroscope velocity (z= 10)

10. stabilize()
    - call take_off()
    - call status() : will provide the pitch and roll
    - call thrust function : thrust == weight

11. land()
    - call take_off()
    - call status()
    -call thrust function : thrust < weight

12. status(Grade grade)
    - return pitch and roll (OrientationSensor);
    
    
    
