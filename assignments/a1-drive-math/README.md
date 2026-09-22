# Assignment 1 - Arcade drive math

**Out:** Sunday, September 21
**Due:** Wednesday, September 24 at 6:00 pm

## Goal

Compute one step of drivetrain math and report the robot's state. You will
practice variables, data types, and operators. You do not need `if`
statements, loops, or your own methods - those come next meeting, and this
assignment is solvable without them.

## Scenario

Your robot has a tank drivetrain: one motor on the left, one on the right.
The driver uses **arcade drive**, pushing one stick forward and another
sideways, and the code converts those two inputs into two motor powers. The
robot also has an encoder on the left wheel that counts how far it has
travelled.

Write a program that takes the driver inputs and the encoder count, and
prints a status report.

## Your task

1. Declare the input variables at the top of `main`, each with the right type
2. Compute the left and right motor speeds from `forward` and `turn`
3. Clamp both speeds so they stay between -1.0 and 1.0
4. Convert each speed into volts using the battery voltage
5. Convert the encoder count into wheel revolutions, then into centimetres
6. Compute a `boolean` that says whether the robot is moving
7. Print a status report using every value above

## Input variables

| Variable               | Type     | Meaning                     | Example        |
| ---------------------- | -------- | --------------------------- | -------------- |
| `name`                 | `String` | Subsystem name              | `"Drivetrain"` |
| `forward`              | `double` | Forward stick, -1.0 to 1.0  | `0.8`          |
| `turn`                 | `double` | Turn stick, -1.0 to 1.0     | `0.9`          |
| `batteryVolts`         | `double` | Current battery voltage     | `12.4`         |
| `encoderTicks`         | `int`    | Ticks counted by the encoder| `1024`         |
| `ticksPerRev`          | `int`    | Ticks in one full wheel turn| `2048`         |
| `wheelCircumferenceCm` | `double` | Distance in one wheel turn  | `47.88`        |

## Explanation of the concepts

- **Arcade drive**: `leftSpeed = forward + turn` and
  `rightSpeed = forward - turn`. Straight ahead means `turn` is zero and both
  motors match. A positive `turn` makes the left motor run faster, which
  steers the robot right.
- **Clamping**: a motor accepts a power from -1.0 to 1.0 and nothing else.
  `forward = 0.8` with `turn = 0.9` gives 1.7, which is not a legal power, so
  it must be pulled back to 1.0. Clamp with
  `Math.min(1.0, Math.max(-1.0, value))`.
- **Motor volts**: a speed of 1.0 means full battery voltage, 0.5 means half.
  Multiply the clamped speed by `batteryVolts`.
- **Encoder ticks**: an encoder counts fixed steps as the wheel turns.
  `ticksPerRev` ticks means one full revolution, and one revolution moves the
  robot forward by the wheel's circumference.
- **Integer division**: `encoderTicks / ticksPerRev` with two `int` values
  throws the fraction away, so 1024 over 2048 gives 0 instead of 0.5. Make
  one side a decimal to keep the fraction.

## Expected output

With the example inputs above:

```
=== Drivetrain ===
Inputs: forward=0.80, turn=0.90, battery=12.40 V
Motor speeds: left=1.00, right=-0.10
Motor volts: left=12.40 V, right=-1.24 V
Encoder: 1024 ticks = 0.50 rev = 23.94 cm
Moving: true
```

## Test cases to run before submitting

Change the inputs, run it again, and check each result. Paste all three
outputs into your pull request description.

| Case           | forward | turn | battery | ticks | Left  | Right | Distance | Moving |
| -------------- | ------- | ---- | ------- | ----- | ----- | ----- | -------- | ------ |
| Clamped turn   | 0.8     | 0.9  | 12.4    | 1024  | 1.00  | -0.10 | 23.94 cm | true   |
| Straight ahead | 0.5     | 0.0  | 12.6    | 4096  | 0.50  | 0.50  | 95.76 cm | true   |
| Stopped        | 0.0     | 0.0  | 11.8    | 0     | 0.00  | 0.00  | 0.00 cm  | false  |

## Acceptance criteria

- [ ] Runs with `java DriveMath.java` in a fresh Codespace, with no errors
- [ ] Uses at least one `int`, one `double`, one `boolean`, and one `String`
- [ ] Both motor speeds are clamped, not just the one that overflowed in the
      first test
- [ ] The encoder conversion keeps the fraction: 1024 ticks reads as 0.50 rev,
      not 0
- [ ] `isMoving` is computed with operators, with no `if` statement anywhere
- [ ] All three test cases produce the values in the table
- [ ] Numbers print with two decimals

## Submitting

1. Create a branch named `a1-drive-math-<yourname>`
2. Put your file at `students/<yourname>/assignments/a1-drive-math/DriveMath.java`
3. Commit, push, and open a pull request titled `A1 - Your Name`
4. In the description, answer: what does it do, how did you test it, what got
   you stuck
5. Add a line declaring any AI use: what you asked, what you used, what you
   changed

Using AI to understand something is expected. Submitting code you cannot
explain is not - you will be asked to walk a mentor through this code.

## Stuck?

Post in GitHub Discussions. Say what you expected, what happened, and what
you already tried, and paste the code and the full error message.
