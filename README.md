# Learn-Servlet-JSP-
## Tomcat Configuration in Eclipse: 
1. Install the Tomcat Apache server : File > New > Other > Server > Next > Apache > Select the latest version of Tomcat
2. Wizard will ask to configure the server runtime environment > specify the location of the Tomcat Installationdirectory > Next > Finish
3. Windown > Show View >Other > Server > we can configure the server using the provided UI : 
* server name - this is the name that will apear in the server view
* config path - where the files we see in the Project Explorer reside
* server location -  where we config the locationof the server installation. Also set the application deploy location here
* module publishing - where we configure how the web modules are published
* timeouts -  for starting/stoping the server
* ports- set the various server ports
* MIME mappings- 
* server launch config - config the VM arguments, classpath, etc.
* server options- enable/disable features like security, auto reload of modules by default, etc.
## Start the server
![alt text](image.png)
Right click on the server > Start
## Some issues need to be noted
1. If testing localhost:8081 ( or any 80 port) it shows 404 error > Right click on Server> Properties> Switch Location
2. Open the Server UI and check if the correct Server Locations is selected ( the second one - user Tomcat installation)
## Create simple servlet application
1. Right click on Server folder> new > other> Web > Dynamic Web Project > Project name: SimpleServletApplication > Generate web.xml deployment descriptor checked > Finish > Yes: this kind of project is associated with the Java EE perspective. Do you want to open this perspective now 
2. Deployment Descriptor selected > Window > Show View > Navigator : we can see all files in the file system
3. WEB-INF> Web.xml> Select Source tab : this configs the file that the applicaiton should show as welcome page
4. Right click on WebApp > New> Other> HTML > make sure it is created inside the WebApp folder with file name: index.html as shown on web.xml > Next>Finish 
5. Adjust title and body of html file 
6. Delete all and leave only index.html as welcome file in web.xml
7. On SimpleServletProject right click> Run on server > select the configured project > Finish
![alt text](image-1.png)
## Create a servlet
1. Tomcat is a container, servlet is a class. So Servlet is run on server (VM) that the Tomcat is deployed. It is pretty similar to how you run main method on Java IDE, but in this case it run on different server where Tomcat is deployed. Servlet does not have main method but it has default method that can be executed
2. On ServletSimpleProject> New>Servlet> enter Java pakage + Class name. Notice the superclass of this servlet class we are creating should always be javax.servlet.http.HttpServlet
![alt text](image-2.png)
3. Next > select no constructor > select methods doGet only or other method that HttpServlet has as default methods

![alt text](image-3.png)
4. response.setContentType("text/html") > response.getWriter().println(""<h3> Hello Thuy </h3>"") > Right click on SimpleServlet.java > run on server 

![alt text](image-4.png)

![alt text](image-5.png)