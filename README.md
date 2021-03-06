# Expression Language like variant demo

## Tech Stack
Maven
Java
Spring Expression Language
RegEx

## Use Case : Allow content creators add placeholders for actual contextual data.

Given a UI that uses a Content Service API to get formatted json for web page content.  The Content API aggregates multiple systems to get content for the web page.  One of the Content Service backend providers is a content manager system.

Non programmers build content for web pages, alerts, and links for a site.  What if the content manager can add tokens to get dynamic data inserted at run time?

This repo has some building blocks to get that done.  I am a fan of JSF Expression Language.  What if the content creator can use #{field.field...} syntax for place holders in the content for actual data?

The benefit is when content managers want to reword and use other existing fields of domain objects, we do not have to make code changes to map the token to some data and avoid a synchronized deployments.  What if we can just resolve the expression to a Java Class Object field?

## Overview:

<p>We have a Object called Vehicle and it has fields and another Object.</p>
<p>We use #{xxxxx} for place holders in the content.  These are the field names in the Object.</p>

### Output
<p>"The vehicle is #{color} and it was designed in the year #{year}.  We started shipping it in #{buildDates.start}. This one does not #{exist} and should come back."</p>

<p>After running the content becomes:</p>
<p>"The vehicle is Black and it was designed in the year 2016.  We started shipping it in July, 16 2022. This one does not #{exist} and should come back."</p>

<p>#{color} becomes 'Black'</p>
<p>#{year} becomes '2016'</p>
<p>#{buildDates.start} becomes 'July, 16 2022'</p>

<p>Notice #{exist}, the Vehicle object does not have such a field so it is passed through.</p>
<p>Notice Vehicle has another object called BuildDates.java which has two fields 'start' and 'end' which are java.util.Date types.</p>
