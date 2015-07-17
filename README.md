# LeanstartupBar
Step 1: Go to LeanStartupBar directory in your command line and compile the source files using the below command:
        javac -d classes -sourcepath src src/controller/LeanStartUpBar.java
Step 2: To create an executable jar file,Go to classes folder and use the below command:
        jar -cvmf manifest.mf LeanStartupBar.jar *
Step 3: To run the jar file, use the command below:
        java -cp LeanStartupBar.jar controller.LeanStartUpBar
