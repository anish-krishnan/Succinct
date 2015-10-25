# Succinct

#Inspiration
We believe that everybody should be able to advance their learning both inside and outside the classroom. As teens of the 21st century, we find that the classroom environment is built around studying extensive text book material. With the constant distractions of social media and technology, we and many other students are unable to maintain our concentration span during our quotidian readings. As a result, we were inspired to turn the tables by using technology to help accomplish our reading and supplement it with a variety of study resources.

#What it does
Using the Tesseract API provided by Google, our Android application converts a user inputted image into a String Object. Our application then, through a multi-step algorithm, determines key words and phrases based on repetition in the passage and other criteria. The resulting data is used to compile a short summary of the passage, connection to Quizlet study sets, editable notes, and complementary sources to study.

#How we built it
We used Java, XML and JSON to create the backend portion of our application. This includes the use of the Tesseract API for image to text conversion, Chegg API for generating a list of supplementary study material, and the Quizlet API for producing note card sets regarding the passage. The algorithm, written in Java, takes in the converted String and analyses it for the most frequent words and passages. This data reflects the key terms of the passage, and are used to generate the subsequent functions. The summarizing function includes the most important excerpts from the passage based on these key terms. The Quizlet function sends the key terms to Quizlet, generating card sets. The resource function takes the users textbook to find related textbooks based on subject matter, and other characteristics. To create our UI we utilized Adobe Photoshop CC.

#Challenges we ran into
The first challenge we faced was in creating the algorithm that converts the String to primary terms. When brainstorming ideas for the process we missed a few important details which hindered our ability to affectively determine key terms. This issue forced us to reevaluate our code to accommodate for more edge cases. Another challenge we faced was in finding and understanding each of the three API's we used. Most of the API's we found were incompatible with the languages we used, thus extending the process of finding a reliable API.

#Accomplishments that we are proud of
Fast and fairly accurate OCR (Optical Character Recognition)
Identification of key terms and phrases
Creation of relevant study sets on Quizlet
Ability to find pertinent study materials
#What we learned
Implementation of OCR
Utilization of the Chegg API
Remote creation of Quizlet study sets
Natural language processing
#What's next for Succinct
Further improve accuracy of OCR and algorithmic processing, by developing machine learning.
