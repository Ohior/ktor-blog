<#-- @ftlvariable name="article" type="com.example.models.Articles" -->
<#import "_layout.ftl" as layout />
<@layout.header>
<div>
    <h3>Edit article</h3>
    <form action="/articles/${article.id}" method="post">
        <p>
            <input type="text" name="title" value="${article.title}">
        </p>
        <p>
            <textarea name="body">${article.body}</textarea>
        </p>
        <div>
            <strong><input type="submit" name="_action" value="update"></strong>
            <strong><input type="submit" name="_action" value="delete"></strong>
        </div>
    </form>
</div>
<div>
    <form action="/articles/${article.id}" method="post">
        <p>
            <input type="submit" name="_action" value="delete">
        </p>
    </form>
</div>
</@layout.header>