# MusicMaker
This was for a team project where we created a program that generates a brief song based on the user’s desired genre input. 
We started with a database of songs lyrics and then performed TF-IDF to get a set of the most meaningful words in each song. I was responsible for
then taking these words and comparing them to a trainer set of sentiments as well as subjecting them to/implementing the
Natural Language Understanding API provided by IBM Watson to classify songs within categories such as love, happy,
breakup, yearning, angst, pain, melancholy, and rage. Once the user selects which type of song they want to generate, our
program fetched lines from that genre category in a randomized fashion and displayed it to the user.
