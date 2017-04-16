# What is ABODE?

ABODE is an integrated development environment (IDE) for Behavior Oriented Design (BOD).

The original version was developed for Java 1.5, by Steve Gray of Cobalt Software, followed by a number of subcontractors, undergraduate and postgraduate students of the University of Bath. The last version of ABODE based on the original code, named ABODEStar, was developed and maintained by Swen Gaudl on GitHub.

ABOD3 is the latest iteration of the ABODE , providing new functionalities such as our novel approach to AI-debugging.

Unlike previous updates of ABODE, it was built from scratch, without any usage of the existing source code. In addition to new functionalities, the new version aims at using modular, clean-code, with all the features and performance enhancements of Java8. Moreover, I opted-out of using Swing -as previous versions of ABODE did- in favour of JavaFX8. The new GUI framework, gives the new version a clean, modern look, while maintaining cross-platform compatibility.

#What can I use it for?

It allows the creation and debugging of [POSH](http://www.cs.bath.ac.uk/~jjb/web/posh.html) and [Instinct](http://www.robwortham.com/instinct-planner/) plans. POSH plans can be used in either POSHSharp planner for Starcraft (via BWAPI) or JyPOSH planner for Unreal Tournament 2004. Instinct plans can be currently used by Instinct planner (under development, closed-alpha).
ABOD3-smallplan

#What features does ABOD3 have?

Other than the under the hood and a new UI, ABOD3 aims not only to design AI but also to debug it. The debugger allows both real-time debugging and via existing logfiles. A dedicated video player is built-in, allowing a media capture to be played alongside with the logfile. Best of all? Once you point ABOD3 towards the log and the video, all left for you to do is to press “Start debugging”. ABOD3 will go through the logfile, in real-time, showing elements calls not only in ordered they were used, but also in with the appropriate time differences between them (+-<1 miliseconds!).

Public APIs allow an easy expansion of ABOD3 to include support for other POSH-like plans.

#Why is debugging so important?

At the moment, designing and developing AI is an iterative process. First, you need to create a plan, then run it on your planner, collect logs, and then navigate through -often enormous- logfiles to try find any bugs. Once a bug is found, you need to go back to your plan and essentially repeat the same process. Imagine if you had a tool to help you, not only go through the logs, but also to trace bugs. What if the same tool allowed real-time debugging of your agent? What if there is no need for logs and instead developers can develop, test, and debug their plans all from the same tool? Yes, the process would be faster. This is ABOD3.

ABOD3, also, comes in line line with our current transparency research programme. We are currently evaluating the effects of transparency on end-users of our agents. In more details, we are looking into users’ perception of robots, how they calibrate their trust, and levels of fears towards the robot. Due to ABOD3’s flexible GUI, we can deploy it on a read-only mode, where any plan-development features are disabled.

# Who are the developers?
The lead developer is [Andreas Theodorou](http://recklesscoding.com/), who at time of writing is a PhD student at the [University of Bath](http://bath.ac.uk/). 

[Robbert (Rob) R. Wortham](http://robwortham.com/) and [Joanna J. Bryson](http://www.cs.bath.ac.uk/~jjb/) from University of Bath contributed with their expertises. Rob is the developer of [Instinct, a  transparent planner](http://www.robwortham.com/instinct-planner/). His GitHub can be found [here](https://github.com/rwortham).

We are always happy to accept contributions and feedback by third party users and enthusiast! Get in touch via [Twitter](@recklesscoding) or GitHub if interested.
