---
layout: about
---

### Why creating schedules can give us headaches:

This application originated from a request of an employee to switch from a full time job to part time in a branch with continuous hours of a retail shop with a certain regulation of personnel. The question was whether it would still be possible to fulfill a schedule that is compatible with regulation constraints. Several employee shift management applications, while offering valuable additional features, were not able to address the feasibility of that request. Many of the shortcomings that emerged mainly concerned the automated part of such software: the ability to self-generate shift rosters without human intervention. In fact, the request proved to be complex.

- A typical weekly employee schedule for a retail shop open from 8:30 AM to 22:30 PM with nine employees has:

> 2 possible states for a half hour time unit (employee present or not)... **to the power of**... 9 employees × 7 days per week × 14 hours per day × 2 in half hours

...which means 1,039710311×10⁵³¹ possible solutions. This is a lot more than the minimal number of atoms in the known universe (1×10⁸⁰).

- It is impossible to evaluate every possible solution even if we compute with the fastest machine for billion of years. This is why we jokingly say that *creating schedules can give us headaches* and more seriously why traditional planners do not even try to **really automatically** make a schedule.

```
There are situations in which no traditional planner can automatically address a complex problem, no spreadsheet, in fact no one can. Auto Shift Planner will try to give you a possible solution if it exists.
```

Further information on this topic can be found on the [Optaplanner](https://www.optaplanner.org/) website. This application uses Optaplanner, a professional grade constraint satisfaction solver, as a planning engine.

[back to Main page](./)
