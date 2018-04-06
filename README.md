## AutoShiftPlanner is used to solve problems similar to the following:
*(replace or remove the values ​​written in **bold** as per needs).*

![Screenshot settings](data/Screenshot_1_ASP.png)

> You are responsible for managing the store staff. You have to organize shifts for the next week. The shop opens at **nine AM** and closes at **nine PM**. There are **seven** employees, **five** of whom work **forty** hours a week and **two** have **twenty-one**. **Each** employee has **two** days free per week. The **supply truck** arrives at **half past eight** on **Monday**, **Wednesday** and **Friday**. **Only** in these days there must be already present staff. An employee asked to have an **extra free** afternoon on Friday. The same employee **must be** in the shop in the morning from **half past nine** to **half past ten**. No employee can do more than **eight** hours a day. You can not do less than **three** consecutive hours or more than **six** hours. Each employee may possibly take **only one** break which must however be **one** hour long. In the shop there must always be at least **two** people. Some specific employees must be present during **opening** or **closing** of the shop. There **must not** be times when there is **too much** staff present or **too little**.

![Screenshot editor](data/Screenshot_2_ASP.png)

Do you need to solve problems like this? First you need to have Java which you can find [here](https://java.com). Then you just have to download the AutoShiftPlanner's jar file [here](AutoShiftPlanner-0.1.0-jar-with-dependencies.jar) and run it. The apllication does not need installation.


###For developers:
The application is a **Maven** project: just clone the repository and open the **pom** file. The application is a trivial example of **Optaplanner** usage. There is no connection with the Optaplanner project. Many thanks to Optaplanner developers. **GPL v3**