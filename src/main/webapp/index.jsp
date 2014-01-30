<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<form action="web/consume/" method="post">

	<textarea name="rss" rows="20" cols="50"></textarea>
	<br/>
	<input type="submit" value="Send">
	
	<hr>
	<p>Title: ${feed.title}</p>
	<ul>
		<c:forEach var="i" items="${feed.items}">
		   <li>Item <a href="${i.link}">${i.title}</a>
		</c:forEach>	
	</ul>
</form>
</html>