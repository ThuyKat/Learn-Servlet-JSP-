<!-- vscode-markdown-toc -->
* 1. [Tomcat Configuration in Eclipse:](#TomcatConfigurationinEclipse:)
* 2. [Start the server](#Starttheserver)
* 3. [Create simple servlet application](#Createsimpleservletapplication)
* 4. [Create a servlet : from servlet class](#Createaservlet:fromservletclass)
* 5. [create a servlet: from normal class then config later in web.xml](#createaservlet:fromnormalclassthenconfiglaterinweb.xml)
* 6. [POST method](#POSTmethod)
* 7. [Passing more parameter](#Passingmoreparameter)
* 8. [Passing parameter for radio type](#Passingparameterforradiotype)
* 9. [JSP](#JSP)
* 10. [MVC patterns](#MVCpatterns)
* 11. [Login function](#Loginfunction)
* 12. [Request Dispatcher](#RequestDispatcher)
* 13. [useBean tag](#useBeantag)
* 14. [getProperty tag:](#getPropertytag:)
* 15. [setProperty tag:](#setPropertytag:)

vscode-markdown-toc-config
	numbering=false
	autoSave=true
	/vscode-markdown-toc-config
/vscode-markdown-toc
# Learn-Servlet-JSP-
##  1. <a name='TomcatConfigurationinEclipse:'></a>Tomcat Configuration in Eclipse: 
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
##  2. <a name='Starttheserver'></a>Start the server
Right click on the server > Start
-->  Some issues need to be noted
1. If testing localhost:8081 ( or any 80 port) it shows 404 error > Right click on Server> Properties> Switch Location
2. Open the Server UI and check if the correct Server Locations is selected ( the second one - user Tomcat installation)
##  3. <a name='Createsimpleservletapplication'></a>Create simple servlet application
1. Right click on Server folder> new > other> Web > Dynamic Web Project > Project name: SimpleServletApplication > Generate web.xml deployment descriptor checked > Finish > Yes: this kind of project is associated with the Java EE perspective. Do you want to open this perspective now 
2. Deployment Descriptor selected > Window > Show View > Navigator : we can see all files in the file system
3. WEB-INF> Web.xml> Select Source tab : this configs the file that the applicaiton should show as welcome page
4. Right click on WebApp > New> Other> HTML > make sure it is created inside the WebApp folder with file name: index.html as shown on web.xml > Next>Finish 
5. Adjust title and body of html file 
6. Delete all and leave only index.html as welcome file in web.xml
7. On SimpleServletProject right click> Run on server > select the configured project > Finish
![alt text](images/image-1.png)
##  4. <a name='Createaservlet:fromservletclass'></a>Create a servlet : from servlet class
1. Tomcat is a container, servlet is a class. So Servlet is run on server (VM) that the Tomcat is deployed. It is pretty similar to how you run main method on Java IDE, but in this case it run on different server where Tomcat is deployed. Servlet does not have main method but it has default method that can be executed
2. On ServletSimpleProject> New>Servlet> enter Java pakage + Class name. Notice the superclass of this servlet class we are creating should always be javax.servlet.http.HttpServlet
![alt text](images/image-2.png)
3. Next > select no constructor > select methods doGet only or other method that HttpServlet has as default methods

![alt text](images/image-3.png)
4. response.setContentType("text/html") > response.getWriter().println("< h3 > Hello Thuy < /h3>") > Right click on SimpleServlet.java > run on server 

![alt text](images/image-4.png)

![alt text](images/image-5.png)
##  5. <a name='createaservlet:fromnormalclassthenconfiglaterinweb.xml'></a>create a servlet: from normal class then config later in web.xml
1. Create a normal class, howerver it should extend from HttpServlet as its super class
2. In web.xml, create a new servlet tag, declare servlet-name and servlet-class 
3. In web.xml, create a new servlet-mapping tag, declare servlet-name and url-pattern
##  6. <a name='POSTmethod'></a>POST method 
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

##  7. <a name='Passingmoreparameter'></a>Passing more parameter

1. HTML file: we add extra variable which is name apart from username.

![alt text](images/image-14.png)

2. Notice that it must match the variable name in java file 

![alt text](images/image-15.png)

3. In browser: 

![alt text](images/image-16.png)

##  8. <a name='Passingparameterforradiotype'></a>Passing parameter for radio type
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

##  9. <a name='JSP'></a>JSP
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

8. get the innit parameter in jsp
Step 1: Define the servlet in web.xml

 <servlet>
    <servlet-name>InitJSP</servlet-name>
    <!--  not specifying servlet class because we are going to do it in jsp -->
    <jsp-file>/initpage.jsp</jsp-file>
    <!-- this will map the servlet-name with the generated servlet in jsp file -->
    <init-param>
    <param-name>defaultUser</param-name>
    <param-value>Default User Name</param-value>
    </init-param>
  </servlet>
<servlet-mapping>
    <servlet-name>InitJSP</servlet-name>
    <url-pattern>/initpage.jsp</url-pattern>
</servlet-mapping>

Step 2: in jsp file we have a script tag
<%=getServletConfig().getInitParameter("defaultUser")%>

9. Override the jspInit() method in jsp using definition tag

![alt text](images/image-27.png)

10. ServletConfig vs ServletConfig: ServletConfig is something Servlet passign to us on the creation of the servlet object. It checks the parameter on the web server, parameter we have set there, bundles them into an object called ServletConfig and passes to us in our methods. ServletContext is an application context( scope object- like request and session) applicable across the the application ( like request is applicaiton for particular request and session is available for a user session). ServletContext is available across the application

##  10. <a name='MVCpatterns'></a>MVC patterns

![alt text](images/image-28.png)

Assumption: The view not going back to controller but directy to user.
Other way is that the view can go to controller and controller go directly to user. 

##  11. <a name='Loginfunction'></a>Login function
1. ControllerServlet - we call it LoginServlet, it has business service - we call it LoginService, and view - login.jsp and success.jsp
2. The LoginServlet takes userId and password from login.jsp page 
(action="/login", method="post" --> LoginServlet),

![alt text](images/image-29.png)

pass it to LoginService  and this business service will return a value of true or false( via doPost method). 

3. If true value is returned, loginServlet directs user to success.jsp. Otherwise, the LoginServlet directs user to login.jsp (via doPost method)

4. Now we want to make changes to success.jsp that we want to get current user's name and print out a hello message with that name. 

* We need to get the user's name from UserName and password  that the user provided. 
* In LoginService, we can get the name of user from the database. In order to do that, we can create a method named " getUserDetail(userId) to get object User using the provided userId. (request.getSession().setAttribute("user",user))
* Next, we get call the method and get user object in LoginServlet( controller) and pass it to Http session. 
     The reason why we choose Http session: when we redirect to another website, we actually create another request. This request is different to the original post request form user. Hence, we will not be able to user the request scope. Meanwhile, application scope can only be used accross multiple users. 
* In success.jsp, we open script tag to get the user object = session.getAttribute. Note that we can use the first script tag to import User entity to jsp file.

Test result: 

![alt text](images/image-30.png)

![alt text](images/image-31.png)

* Not all data can be saved in the session because we call request.getSession in controller. What if we want to save the data we get in the jsp? We need to do the redirect from the server side itself that browser doesnt know that happening to other url or jsp

##  12. <a name='RequestDispatcher'></a>Request Dispatcher

1. Instead of redirect to success.jsp, we can dispatch the control right of controller to success.jsp.

2. In order to do this: 
- In Controller: 

RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
dispatcher.forward(request,response);

Instead of saving user object in session, we can save it in request scope now

- in success.jsp: we can get the user object = request.getAttribute("user");

Test result: 

![alt text](images/image-32.png)

We notice that: the path on browser is still /login, which is different to previously it redirects to /success

# JSTL

##  13. <a name='useBeantag'></a>useBean tag

In LoginServlet, we have setAttribute for user object: 

![alt text](images/image-35.png)

Now we want to get that attribute and display on the view success.jsp. Instead of opening a script tag, we now use useBean tag: 

```java
<jsp:useBean id="user" class=dto.User scope="request"></jsp:useBean>

```
this has meaning similar to opening a script tag in success.jsp and request.getAttribute("user")

![alt text](images/image-34.png)

the variable name is automatically the same as the object id

One good thing is that even without setAttribute("user") in controller, userBean tag can set "user" object itself - it checks if the object "user" is available. If the object is not there, it will create a new bean with the scope from the class declared

Whateve the code you write in between open and close jsp:useBean tag will be executed once bean is created

##  14. <a name='getPropertytag:'></a>getProperty tag:
 
 To get the properties of the bean we already defined in previous step

 It takes 2 parameters: 
 
 - name: what is the name that we need to get property from

 - property: the property we are trying to get

##  15. <a name='setPropertytag:'></a>setProperty tag:: 

 Similar to getProperty, but having new parameter: value.

 The meaning of this is get the property of object (name="user") and pass a value to it

 This tag can be used to pass the parameter from the request directly without going through the controller:

 1. define the bean by useBean tag with three parameters: name = name of object, class=path of the object's class, and method. 

 2. inside the useBean tag, use setProperty tag to look up the parameter in the request and pass it to the object's property. setProperty now has three parameter: property= name of the object's property, name = the object's name, param= name of the parameter in login.jsp form. If the name in param is the same as the name of the object's property, we can obmiss the param parameter because jstl will look for the param that has the same name as the object's property. 

 If all the object's properties have matching names with the parameters' names in login.jsp form, we can use shortcut property ="*" - which mean getting all the values of the user bean's property. 

 3. outside the useBean tag, use getProperty tag to get the value of the property to the screen with two parameters: property = name of the property and name= the object's name

 ![alt text](images/image-33.png)

