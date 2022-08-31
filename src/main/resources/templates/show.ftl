<#-- @ftlvariable name="article" type="com.example.models.Articles" -->
<#import "_layout.ftl" as layout />
<@layout.header>
<hr>
<div>
    <h3>
        ${article.title}
    </h3>
    <p>
        ${article.body}
    </p>
    <hr>
    <p>
        <a href="/articles/${article.id}/edit">Edit article</a>
    </p>
</div>
</@layout.header>