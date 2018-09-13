---
layout: default
---

# Presentation

Auto Shift Planner has an *unique* feature: it can design and automatically make a detailed schedule (roster) while using **heuristic** and **metaheuristic** algorithms. A shift is fine tuned with half hour time units instead as just morning shifts or night shifts. This feature allows the application to manage situations where *traditional planners fail* because it is just impossible to define exactly the desired scenario, for example retail shops with continuous hours with strict staff regulation. Precise **rules** and **preferences** can also be defined as constraints and the application will **automatically generate a schedule** accordingly.

[Tell me why is this important and why should I use this application...](./about.html).

```
The application is not a general purpose factotum planner.
It is a detailed rule based schedule generator.
```

###### Let's see how Auto Shift Planner looks like in action...

![AutoShiftPlanner_screenshot_animated](/images/AutoShiftPlanner_screenshot_animated.gif)

* * *

# Main Features

- easy to use
  - simple and intuitive user interface
    - be productive immediately
- automated planning
  - available rules (constraints)
    - respect the opening and closing time
    - do the required number of hours per week
    - do not exceed the expected daily hours
    - do not exceed the number of shifts per day
    - have some eventual breaks of the desired break duration
    - minimum and maximum quantity of consecutive hours
    - minimum amount of employees present at the same time
    - minimum amount of hours for overnight rest
    - all mandatory shifts must be satisfied
    - not too much or not enough staff present
- no installation needed
  - portable, easy and clean
    - for Linux, Mac and Windows
- free and open source
  - no malicious features
    - you can modify the application as you need

* * *

# An Example

#### As a manager of a store staff you have to organize shifts for the next week as follows:

*values ​​written in **bold** can be replaced or removed as per needs...*

> The shop opens at **nine AM** and closes at **half past nine PM**. There are **seven** employees, **five** of whom work **forty** hours a week and **two** have **twenty-one** hours. **Each** employee has **two** free days per week. The **supply truck** arrives at **half past eight** on **Monday**, **Wednesday** and **Friday**. **Only** in these days there must be already present staff. An employee asked to have an **extra free** afternoon on Friday. The same employee **must be** in the shop in the morning from **half past nine AM** to **half past ten AM**. No employee can do more than **eight** total hours per day while no one can do less than **three** consecutive hours or more than **six** consecutive hours per day. Each employee may possibly take **only one** break which must however be **one** hour long. In the shop there must always be at least **two** people. Some specific employees must be present during **opening** or **closing** of the shop. There **must not** be times when there is **too much** staff present or **too little**.

[Tell me how to use this application...](./usage.html).

* * *

# Screenshots

![AutoShiftPlanner_screenshot_1](/images/AutoShiftPlanner_screenshot_1.png)

![AutoShiftPlanner_screenshot_2](/images/AutoShiftPlanner_screenshot_2.png)

* * *

# Download

1. You need to have **Java** which you can find [here](https://java.com)
1. Click "download application" (at the top of the screen)
1. Download the latest file named: AutoShiftPlanner-X.X.X-jar-with-dependencies.jar (e.g. AutoShiftPlanner-0.2.0-jar-with-dependencies.jar)
1. Double click the icon or type "java -jar AutoShiftPlanner-X.X.X-jar-with-dependencies.jar" in a terminal (or the Command Prompt)
1. How to open the Command Prompt in Windows: click Start, scroll down and expand the “Windows System” folder, click “Command Prompt”

* * *

# Are you a developer?

The application is a **Maven** project: just clone the repository and open the **pom** file in your preferred IDE. Please consider forking the repository on GitHub, contributions are welcome! The application is also an easy example of **Optaplanner** usage with simple code.

```
AutoShiftPlanner uses Optaplanner and it's dependencies. Many thanks to Optaplanner developers.
Released under: Gnu Public License, version 3
```

* * *

# Roadmap

- Known issues
  - The solving process could be slow in some situations: restarting the solver may help.
  - Enabling "Uniformly distributed employees" (the only soft score constraint) will slow down the solving speed.

- To do 
  - Domain Model could be further improved.
  - A shift is fine tuned with half hour time units: this length should be made configurable in GUI.
  - Implement score calculation using the Drools rule engine.
  - Please contact me if you are in any way interested in this project. Thanks.