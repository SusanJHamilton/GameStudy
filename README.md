# Introduction


This is a study of machine learning algorithms, specifically reinforcement learning. Machine has been a considerable interest within the past X years due to its wide-ranging applications such as...



In this study in particular, we’re interested in an application of machine learning in game theory, in order to utilize the methodology of learning within a simple game in order to investigate aspects of the theory and associated algorithms in a concrete example. 

We discovered that our agent was able to effectively learn to perform better in the game. We also investigated the convergence of the agent’s learning progress. As well as gradual learning, we found some instances where the agent discovered methods for surviving indefinitely within the game i.e., in addition to gradual learning, the algorithms also led to a process of discrete discovery of winning strategies. 

# Game Description

The game we designed contained the following rules: Within our game, our character had simple options for what to do, such as go to the wild and pick up resources which they then had to travel to the market to in order to sell and buy food. They could also merge items and sell the new item for more money. The objective of the game was to survive, and a character would die the day after their hunger bar fell all the way to zero. After writing the game, we created an agent to control the player in the game and used the RL method to develop a strategy that would encourage it to win, making it assess a unique “score” for each potential move, a higher score meaning a better chance at surviving. After each game, the score values were updated based on what the machine learned that game. We also created a strategy where it updated the score after each move, then compared the learning between the two.

# Data and Methods
  Then, we observed the agent's learning by looking at the average number of moves done after a set amount of games, gradually increasing the number. We recorded the data and did this multiple times, averaging what we got then plotting the data to observe the trend, finding that the data increased before reaching a convergence.

# Results


 