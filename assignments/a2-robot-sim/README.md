# Assignment 2 - Drivetrain simulator
**Out:** Saturday, October 3
**Due:** Tuesday, October 6 at 6:00 pm

This one assignment covers everything from all three meetings: variables and data types, operators, if and switch, loops, break and continue, methods you write yourself, and exception handling.

## Goal
Simulate a robot drivetrain for a number of steps and report where it ends up. The simulation runs three times, once for each drive mode, and it has to cope with a battery sensor that fails.

## Scenario

Your robot has two drive motors. The driver can choose a mode:
- **tank** sets the left and right motor speeds directly
- **arcade** takes a forward value and a turn value and works the speeds out: `leftSpeed = forward + turn` and `rightSpeed = forward - turn`
- **turbo** is not a mode your code knows about, and that is deliberate. An unrecognised mode must stop the motors, not carry on with the last value.

Each step the robot moves a little and turns a little, and each step your code reads the battery. The battery reader is written for you, and it fails on step 3 on purpose. Your program must survive that and keep going.

## Your tasks

1. Write **runMode(...)**, a `void` method that runs the whole simulation for one mode, and call it three times from `main` function: once for tank, once for arcade, once for turbo
2. Inside it, pick the motor speeds with a **switch** on the mode, including a `default`
3. Write **clampVoltage(double volts)**, limiting a speed to -1.0 to 1.0
4. Write **wrapHeading(double angle)**, wrapping an angle into 0 to 359
5. Loop the steps with a **for** loop
6. Read the battery inside a **try**, and in the **catch** print the exception and skip the step with **continue**
7. If the battery drops below 11.0 volts, print a warning and stop that mode
   early with **break**
8. Print the state each step, and a final line for each mode

Three of those are methods you write: `runMode`, `clampVoltage` and `wrapHeading`. Two of them hand a value back, and one does not. Think about which is which before you start typing.

## How the robot moves
Each step that actually runs:

```
x        increases by leftSpeed
y        increases by rightSpeed
turnRate = (rightSpeed - leftSpeed) * 45.0
heading  = wrapHeading(heading + turnRate)
```

45 degrees is a fixed constant for how much the robot turns per step.

## wrapHeading
Headings are angles, so they wrap round at 360:

| Input | Output |
| ----- | ------ |
| 200   | 200    |
| 400   | 40     |
| 0     | 0      |
| 360   | 0      |
| -20   | 340    |
| -360  | 0      |

The `%` operator and one `if` is all you need.

## Expected output
With the inputs in the main method, expected exactly would be:

```
=== tank ===
Step 1: x=0.60 y=0.40 heading=351.00 battery=12.20 V
Step 2: x=1.20 y=0.80 heading=342.00 battery=11.80 V
java.lang.ArithmeticException: / by zero
Step 4: x=1.80 y=1.20 heading=333.00 battery=11.00 V
Battery critical at 10.60 V, stopping
Final: x=1.80 y=1.20 heading=333.00

=== arcade ===
Step 1: x=1.00 y=-0.10 heading=310.50 battery=12.20 V
Step 2: x=2.00 y=-0.20 heading=261.00 battery=11.80 V
java.lang.ArithmeticException: / by zero
Step 4: x=3.00 y=-0.30 heading=211.50 battery=11.00 V
Battery critical at 10.60 V, stopping
Final: x=3.00 y=-0.30 heading=211.50

=== turbo ===
Unknown mode, motors stopped
Step 1: x=0.00 y=0.00 heading=0.00 battery=12.20 V
Step 2: x=0.00 y=0.00 heading=0.00 battery=11.80 V
java.lang.ArithmeticException: / by zero
Step 4: x=0.00 y=0.00 heading=0.00 battery=11.00 V
Battery critical at 10.60 V, stopping
Final: x=0.00 y=0.00 heading=0.00
```

Read that output before you start writing. Notice three things:
- Step 3 is missing from every mode. The reader threw, you caught it, and
  `continue` skipped the rest of that step.
- Step 5 never prints. The battery hit 10.60, which is under 11.0, so `break`
  ended the mode early.
- In arcade mode the left motor shows 1.00, not 1.70. `forward + turn` is 1.7,
  which is not a legal motor speed, so `clampVoltage` pulled it back.

## Acceptance criteria
- [ ] Runs with `java RobotSim.java` in a fresh Codespace, with no errors
- [ ] Output matches the expected output above, line for line
- [ ] `main` calls `runMode` three times, and the simulation itself is written once inside that method rather than copied out three times
- [ ] A for loop over the steps
- [ ] A switch with a `default` that stops the motors
- [ ] `clampVoltage` and `wrapHeading` are methods that **return** a value, not methods that print
- [ ] The exception on step 3 is caught, printed, and the program continues
- [ ] `continue` is used for the failed reading, `break` for the flat battery
- [ ] `wrapHeading` gives the right answer for all six rows of the table above
- [ ] Numbers print with two decimals

## Hints
- Build it in pieces. Get tank mode working for one step before you add the loop, the other modes, or the try and catch.
- Write the simulation for one mode first, straight inside `main`, and only move it into `runMode` once it works. Moving working code into a method is easier than writing a method from nothing.
- Write `clampVoltage` and `wrapHeading` first and test them on their own.
- If every heading comes out negative, you missed the `if` in `wrapHeading`.
- If step 3 kills your program, your try does not wrap the battery read.
- If step 5 prints, your battery check is in the wrong place in the loop, or it uses the wrong comparison.
- A method that prints instead of returning is the most common mistake in this assignment. If you cannot write `double s = clampVoltage(1.7);` then yours is printing.

## Submitting
1. Create a branch named `<yourname>-a2-robot-sim`
2. Put your file at `<yourfolder>/Assignments/Assignment2/RobotSim.java`
3. Commit, push, and open a pull request titled `A2 - Your Name`
4. In the description, answer: what does it do, how did you test it, what got you stuck
5. Add a line declaring any AI use: what you asked, what you used, what you changed

Using AI to understand something is expected. Submitting code you cannot explain is not, and you might be asked to walk a mentor through this program.

## Stuck?
Post in GitHub Discussions under Assignment 2. Say what you expected, what happened, and what you already tried, and paste the code and the full error message.
