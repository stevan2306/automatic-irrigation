# automatic-irrigation
This is for automatic irrigation service.
Pre-requestics:
   Java
   MySQL
 connection url: jdbc:mysql://localhost:3306/irrigation
 user: root
 password: root
 
 How to Run and Build Project
   1. mvn clean install - run this command in project folder for build jar file
   2. go to target-file run below command
         java -jar ${jar_name}
  Service up and run in port 8080;
  
  check service using below curl command
  curl --location --request GET 'http://localhost:8080/api/v1/plot' - will return empty array (no data there initially)
  
 How Sensor works:
   When application starts, timer task(practically this will be sensor and monitor plot) will be executed for each task.This timer task monitor plot confiuration and irrigate based
 one configuration. 
 
 Functionalities:
   Add plot
   Update Plot
   Delete Plot
   Get Plots
   Get Plots By Id
   
