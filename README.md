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
Right click on the server > Start
-->  Some issues need to be noted
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
![alt text](images/image-1.png)
## Create a servlet : from servlet class
1. Tomcat is a container, servlet is a class. So Servlet is run on server (VM) that the Tomcat is deployed. It is pretty similar to how you run main method on Java IDE, but in this case it run on different server where Tomcat is deployed. Servlet does not have main method but it has default method that can be executed
2. On ServletSimpleProject> New>Servlet> enter Java pakage + Class name. Notice the superclass of this servlet class we are creating should always be javax.servlet.http.HttpServlet
![alt text](images/image-2.png)
3. Next > select no constructor > select methods doGet only or other method that HttpServlet has as default methods

![alt text](images/image-3.png)
4. response.setContentType("text/html") > response.getWriter().println("< h3 > Hello Thuy < /h3>") > Right click on SimpleServlet.java > run on server 

![alt text](images/image-4.png)

![alt text](images/image-5.png)
## create a servlet: from normal class then config later in web.xml
1. Create a normal class, howerver it should extend from HttpServlet as its super class
2. In web.xml, create a new servlet tag, declare servlet-name and servlet-class 
3. In web.xml, create a new servlet-mapping tag, declare servlet-name and url-pattern
## POST method 
1. in doGet method, we observe the parameter request of HttpServletRequest type. In browser, lets say we pass a parameter such as ?userName=Thuy, the browser stays the same because currently all values we pass to the parameter request, we print out the same message

![alt text](images/image-6.png)

2. Now in order to pass ?userName from request variable to response variable, we call .getParameter() method of request and pass it to a new variable so that we can use it in response

![alt text](images/image-8.png)

3. Another option to do the above task is using POST method: First, we need a html form to submit 

![alt text](images/image-7.png)

*Test the html file on browser:

![alt text](images/image-9.png)

![alt text](images/image-10.png)

![alt text](images/image-11.png)

* Now update the method = "post" inside form tag

![alt text](images/image-12.png)

* And create a doPost method in java file, it looks exactly the same as doGet but the method name!

* Test again: we see that everything else is the same, but the url no longer shows the parameter of username anymore

![alt text](images/image-13.png)

## Passing more parameter

1. HTML file: we add extra variable which is name apart from username.

![alt text](images/image-14.png)

2. Notice that it must match the variable name in java file 

![alt text](images/image-15.png)

3. In browser: 

![alt text](images/image-16.png)

## Passing parameter for radio type
1. HTML file:

![alt text](images/image.png)

2. java file:

![alt text](images/image-17.png)

3. Browser:

![alt text](images/image-18.png)

4. In browser, we can select multiple values. In java file, we can pass those multiple values to an array and use for loop to print out each of the value in browser. 

# Request, Session and Context
1. request and response objects are created per access
2. The servlet object ( the servlet class' instance) is created only once
3. HTTP-stateless protocol - user and data are not rememberred
4. In order to remember user'info, we use session object provided by Tomcat to save data value during execution

* HTTPSession session - request.getSession()
* session.setAttribute("savedUserName",userName);

As we can see, even when we erase the ?userName=Thuy , the session still retain the data
![alt text](images/image-19.png)

5. HTTPSession can be applied for Login screens, shopping carts...
6. However, when we move to different browser, the session is changed and unable to use anymore. 
To use across the entire application, share across servlets and users, we use Context object. 
ServletContext context = request.getServletContext();
context.setAttribute("saveUserName",userName)

There are two ways to create Context object.

7. Before a method, lets say doGet, there are methods which run before the doGet run: init() and service(). This are methods inherited from HttpServlet class

![alt text](images/image-20.png)

![alt text](images/image-21.png)

* Option 1: write the parameter inside @WebServlet annotation and get its value:
this. getServletConfig().getInitParameter("parameter_name")
![alt text](images/image-23.png)
* Option 2: Define the parameter inside web.xml

![alt text](images/image-22.png)

# Application 
# pageContext

## JSP
1. Writing method <%! java_method %> ( definition tag )and writing variables and simple fomular with script tag <% variable/fomular %>. 
2. How to write method within script tag : we write separate script-tag for each { and } and we can include html code in between

--> All code inside the script tag <% %> is converted into doGet()/doPost() method by Servlet. That's why we cannot place a method inside the tag because it will be placed inside doGet() method. Looking insde org.apache.jsp folder we can see another java file is generated under the same name as our jsp file in project folder

![alt text](images/image-25.png)
--> HTML in the java file is printed to the screen internally as below: 
![alt text](images/image-24.png)

3. How to write the library method, for example new Date() from java.util.Date : Inside the script tag <%@ import= library_name"%> at the start of jsp file

![alt text](images/image-26.png)

4. Include the response from the other jsp page
In the body part of jsp, insert:
<%@ include file="/hello.jsp">

5. Get the parameter in jsp instead of java file
We can insert inside the script tag: 
<% String userName = request.getParameter ("para_name");%> 
Since this will be transformed into java code, we can use that userName variable in later part of jsp file as normal variable defined inside jsp file

6. Different scope. 2 approaches: 
Inside the script tag, we can write: 
session.setAttribute("sessionUserName",userName);
application.setAttribute("applicationUserName",userName);
pageContext.setAttribute("pageContextUserName",userName);

or 
we can use just one but pass another parameter
pageContext.setAttribute("applicationUserName",userName,pageContext.APPLICATION_SCOPE);

7. pageContext.findAttribute("name") : this will find the variable name in different scope and make it a pageContext.









