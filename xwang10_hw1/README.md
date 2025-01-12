# 5011-Object-Oriented-Concept HW1 Greeter Module

Xiaoyu Wang

1. Is your solution fully working or not? How does your driver program demonstrate this?
- Yes, the solution is fully working. 
- The driver program firstly loads Zippy quotes from the yow.lines file using the ZippyQuote class I created.
- And then uses the Greeter and Template classes to generate a greeting that includes:
  1) The $daypart, $name, and $color variables.
  2) A random Zippy quote inserted at the end of the greeting ($zippy).

2. What extra credit problem(s) did you work on (#1, 2, 3, all)? Briefly describe how do you demonstrate the functionality?
- I worked on all 3 EC problems.
- 1. EC problem: I added a boolean variable isRandomMode to distinguish from sequential mode. In the Driver class I let the user to choose between random and sequential mode.
- 2. EC problem: ($name.): In Template class I search for keys, if found, append the value. Then append whatever comes after the key.
- ($1.25): Because 1.25 is not a key, it will be appended unchanged.
- 3. EC problem: In Greeter class I check in the constructor if a name is given in the inputVars. Otherwise, get the system username.
- Then I implemented a getUserName method to get system username and capitalize the first letter. In the Driver class, I tested this by not giving a name and the console prints out the system username.

3. How much time did you spend on the assignment?
- 10 hours, approximately.

4. Any feedback on the assignment?
- I felt like there are many different solutions to these problem. For example, I found out another way to implement EC 2 problem by using regular expression (Pattern and Matcher method) to find if there's period in the msg. Eventually I'd choose a eaiser/less complicated way to finish the assignment, with which I need to modify some functions from the original solution by the profeesor.