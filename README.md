# What does it do?
The goal of the program is to statistically generate a test to determine if a player in a game of chance has a hypothesized boosted probability of winning. \
Since false positives and false negatives are inevitable we preset the desired maximum proportion of each.
### For example
You suspect some people are using dice that roll 6's twice as often as normal (1/3 to roll a 6, 2/15 for any other number)\
We want to catch at least 80% of cheaters on average and falsely accuse a maximum of 5% of fair players\
So we can input this information into our program and reach our desired cheating test.\
More than 10 6's in 39 rolls means we can accuse them of cheating under the desired parameters.
![image](https://github.com/user-attachments/assets/f27418e7-17bf-4468-a594-a9ed91569cf5)

# How does it work?
The program uses a normal distribution if statistically relevant and otherwise a binomial distribution to create distribution arrays for both cheaters and fair players\
The goal should be to have the smallest test possible that follows our parameters so we work from the bottom creating distributions for each type of player and comparing them.\
![graph](https://github.com/user-attachments/assets/0bddc1ec-4695-4f85-9b0c-c93efaa0f2d9)\
As seen in the graph, the peak of the cheaters distribution will be further along the distribution range due to the boosted odds of success. This fact is the core premise for the functionality of the program, 
we go along checking if our parameters are met until we find something that satisfies our input.\
![image](https://github.com/user-attachments/assets/5b104507-c856-4e28-a471-0b18ae2d5f27)\
If the line does not represent a test that follows our parameters, we move the line forward checking again until we reach the end, where we then increment our test trial size and try again. As the trial size increases we have more and more possible values
thus the peaks of the two distributions will seperate themselves, always leading to a test eventually.\
It is worth noting that with extremely small differences between cheaters odds and fair players odds, the program can take much longer to output a result due to the nature of the stats being used and the comparison method, especially with tight parameters.

