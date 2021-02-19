# Albumy

Time spent : 6h<br/>
Chosen architecture : <b>Architecture Components</b><br/>
There is no better architecture... but this one is easy to set up for a little project<br/>
![alt text](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

Library use :<br/>
<ul>
<li>Androidx (ConstraintLayout, Navigation)</li>
<li><b>Hilt</b> for dependency injection (Dagger include)</li>
<li><b>Glide</b> for image loading framework</li>
<li><b>Retrofit + OkHttp</b> for all webservice call</li>
</ul><br/>
The best for practices for me :<br/>
<ul>
<li>id name pattern "viewname_item" for exampe : "album_title"</li>
<li>use scope functions (run, apply, let, also, with, etc...)</li>
<li>Respect kotlin style guide : https://developer.android.com/kotlin/style-guide</li>
<li>Use Clean Architecture for big project</li>
</ul><br/>
Suggestions/Improvements: <br/>
<ul>
<li>Add offline mode with database (Room)</li>
<li>Add transitions & animations (Navigation, MotionLayout)</li>
</ul>

