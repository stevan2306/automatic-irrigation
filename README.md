# automatic-irrigation
This is for automatic irrigation service.
1. Pre-requisite:

   1. Java
   2. MySQL
 connection url: jdbc:mysql://localhost:3306/irrigation
 user: root
 password: root
 
 2. How to Run and Build Project
     1. mvn clean install - run this command in project folder for build jar file
     2. go to target-file run below command
         java -jar ${jar_name}
         Service up and run in port 8080;
  
         check service using below curl command
         curl --location --request GET 'http://localhost:8080/api/v1/plot' - will return empty array (no data there initially)
  
 3. How Sensor works:
 
   When application starts, timer task(practically this will be sensor and monitor plot) will be executed for each plot.
   This timer task monitor plot and irrigate based on a configuration. 
   
   Monitor will be added when create a plot and removed while delete the plot.
 
 4. REST APIs:
      1. Add plot
      2. Update Plot
      3. Delete Plot
      4. Get Plots
      5. Get Plots By ID
   
   
   
