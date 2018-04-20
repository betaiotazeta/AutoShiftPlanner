---
layout: usage
---

### The application usage is intuitive nonetheless here are some clarifications and advices:

- The application produces appreciable results as long as the setup and the constraints (rules) allow it. *An impossible solution can not be found.* The violation of the constraints leads to worse hardscores and softscores.

- All values ​​referring to time measurements are always expressed **in hours**. For example, "8:30 AM" is entered as "8.5".

- By work **shift** we mean the period of time from when the employee starts working until the moment he or she stops. The employee can perform one or more shifts per day. A shift can never be interrupted by breaks. Should the employee stop working, even if for a short period of time, when the work is resumed, it will be considered part of a new shift.

- By **period** we mean the half-hour time frame that can be assigned to one or more employees. For example, a period is the Monday time interval from eight to eight and a half.

- In editor mode you can see the table that is composed of squares that represent units of time lasting half an hour on a given day and at a certain time. *Each square belongs to a single employee.* An unit of time (the square) can be marked as **attributed**, **forbidden** or **mandatory**. To every employee is assigned an unique color. *When for a time unit (square) the presence of the employee is required the square changes color depending on the employee in charge. A prohibited time unit (square) is black. A mandatory time unit (square) contains a white ellipse (a dot).* First of all, define the desired units of time (squares) as forbidden or mandatory. Although it is possible to manually assign the time units as a working time to individual employees, this is not necessary as the application does the attribution automatically.

- In editor mode you can mark the time units (squares) by *clicking* with the mouse, clicking and *dragging* the mouse button or positioning the pointer and then using the *mouse wheel*.

- The application is able to save the proposed solution with all the related settings in a special *xml* file. If you need to export in a different format (pdf, spreadsheet), please leave a comment.

- *Benchmark*: this feature is provided only for developers.

- **Tip**: to assign a *free day* for a particular employee, just mark all units of time (squares) of that day for that particular employee as forbidden. In the same way, to start the work activity later on a particular day, you can mark the entire period as forbidden. To request the presence of a specific employee during opening or closing, simply mark the corresponding unit of time (square) as mandatory.

* * *

### For a standard workflow proceed as follows:

1. Define the *start* and *end* times.
These values ​​do not necessarily have to coincide with the opening or closing time. They indicate the time when employee attendance may be required.

1. Add or remove *employees* and input their *weekly hours* to perform.
Each employee is characterized by a name and the amount of weekly hours he or she has to work.

1. Define which *constraints* you want to activate and their specific *values* when available.
- *Hours per week*:
  - the employee must in any case carry out exactly the weekly amount of hours allocated. The violation of this constraint leads to a worsening of the hardscore.
- *Maximum hours per day*:
  - the maximum amount of working hours that the employee can perform in one day. The amount assigned may be less. The violation of this constraint leads to a worsening of the hardscore.
- *Maximum shifts per day*:
  - the maximum amount of shifts that could be attributed to one employee per day. The violation of this constraint leads to a worsening of the hardscore.
- *Shift lenght*:
  - minimum and maximum duration of a single work shift. The violation of this constraint leads to a worsening of the hardscore.
- *Eventual break length*:
  - the duration of the break between a shift and the next one must correspond exactly to the set value. The time span between two shifts taking place on a single day is considered a break. The violation of this constraint leads to a worsening of the hardscore.
- *Minimum employees per period*:
  - the minimum number of employees present for each period. By period we mean the half-hour time frame that can be assigned to one or more employees. A period is a collection of all time units (rectangles) on a given day and at a certain time. For example, a period is the Monday time interval from eight to eight and a half and contains a time unit (rectangle) for each employee. The violation of this constraint leads to a worsening of the hardscore.
- *Mandatory shifts*:
  - the application takes into account the time units (squares) that are defined as mandatory (contain white ellipses) and provides for the assignment of the working hours. Usually it is not necessary to define all the time units (squares) involved in the work shift as mandatory because the work shift will be assigned also in accordance with the other rules in force. The violation of this constraint leads to a worsening of the hardscore.
- *Uniformly distributed employees*:
  - for every period the application tries to minimize the number of employees present. The violation of this constraint leads to a worsening of the softscore. Since employees must be assigned to periods, softscore can not reach 0. The lower softscore value is the fair distribution of employees between periods. Enabling "Uniformly distributed employees" check (the only soft score constraint) will slow down the solving speed. Avoid this constraint check if not needed.

1. Proceed to the editor page and mark **only** the time units (squares) desired as prohibited or mandatory. The application will automatically generate the best possible solution compatible with the preset constraints.

1. Press the *solve* button. During the first minute the application will make a first pass trying to setup the proposed solution. After the first minute the application changes algorithm providing the *final polishing* of the result within the first two minutes. *Normally the hardscore value should be zero.* This ensures that all hardscore constraints are met. *The softscore value must be as low as possible* even if the minimum value depends on the individual cases and can never be zero. Within the first three minutes a satisfactory softscore value should be achieved.

[back to Main page](./)