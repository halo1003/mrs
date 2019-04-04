<%@page import="reasoner.history"%>
<%@page import="reasoner.tag"%>
<%@page import="java.util.Collection"%>
<%@page import="reasoner.artist"%>
<%@page import="reasoner.prepare_data.evaluating_song"%>
<%@page import="java.util.ArrayList"%>
<%@page import="reasoner.song"%>
<%@page import="reasoner.Reasoner"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Music Recommender System</title>
		<link rel="shortcut icon" type="image/png" href="img/MRS_favicon.png" class="mrs_icon"/>
		<link rel="stylesheet" type="text/css" href="style.css">
                <script src="jquery-3.2.1.min.js"></script>
                <script>
                    $(document).ready(function(){
                        setInterval(function(){
                            if($("#not-nhac").attr("class") === "not-nhac") {
                                $("#not-nhac").attr('class', 'not-nhac-2');
                            } else {
                                $("#not-nhac").attr('class', 'not-nhac');
                            }
                        }, 200);
                    });
                    
                    function check_history() {
                        var check = $("#check_login_history").attr("value");
                        if(check === "true") {
                            alert("Login to be more supported.");
                            return false;
                        }
                        return true;
                    }
                    
                    function check_mr() {
                        var check = $("#check_login_mr").attr("value");
                        if(check === "true") {
                            alert("Login to be more supported.");
                            return false;
                        }
                        return true;
                    }
                </script>
</head>
<body>
<%
	Reasoner controlAccount = (Reasoner) session.getAttribute("controlAccount");
        String display = (String) session.getAttribute("display");
        boolean check_login = controlAccount.get_user_id() == null;
        int count ;
%>
	<div class="top">
                    <img src="img/MRS_icon_1.png" alt="Music Recommender System" class="top-icon">
                    <div class="top-center">
                        <form action="/Check" method="post" style="text-align: center;">
                            <div class="search-tool dropdown">
                                <input type="hidden" value="search_tool" name="action">
                                <input class="search-tool-input" type="text" placeholder="Search: Song name" name="name">
                                <div class="dropdown-content">
                                    <div class="search-tool-dropdown">
                                        <input class="search-tool-input" type="text" placeholder="Search: Artist" name="artist">
                                    </div>
                                    <div class="search-tool-dropdown">
                                        <input class="search-tool-input" type="text" placeholder="Search: Category" name="category">
                                    </div>
                                    <div class="search-tool-dropdown" style="background-color: transparent; text-align: center;">
                                        <input class="search-submit-button" type="submit" value="Find">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="top-right">
                    <%
                        if(controlAccount.get_user_id() == null) {
                    %>
                        <div class="login dropdown">
                            <span>Login</span>
                            <div class="dropdown-content login-dropdown">
                                <form action="/Check" method="post">
                                    <input type="hidden" value="login" name="action">
                                    <input class="login-input" type="text" name="user_id" placeholder="User ID">
                                    <input class="search-submit-button login-button" type="submit" value="login">
                                </form>
                            </div>
                        </div>

                        <div style="display: inline-block">|</div>
                        <div class="register dropdown">
                            <span>Register</span>
                            <div class="dropdown-content register-dropdown">
                                <form action="/Check">
                                    <!--<div class="user_id_input">User ID:<input class="login-right" type="text" name="user_id"></div>-->
                                    <input type="hidden" value="register" name="action">
                                    <input class="login-input" type="text" name="user_id" placeholder="User ID">
                                    <input class="search-submit-button login-button" type="submit" value="Submit">
                                </form>
                            </div>
                        </div>
                    
                        
                    <%} else {%>
                        <div class="login dropdown">
                            <span></span>
                        </div>
                        <div class="login dropdown">
                            <span>
                                <%=controlAccount.get_user_id()%>
                            </span>
                            <div class="dropdown-content login-dropdown">
                                <form action="/Check" method="post">
                                    <input type="hidden" value="logout" name="action">
                                    <input class="search-submit-button login-button" type="submit" value="Log out">
                                </form>
                            </div>
                        </div>
                    <%}%>
                    </div>
		</div>
                <div class="not-nhac" id="not-nhac" check="true"></div>
                <div class="body">
                    <div class="categories">
                        <form action="/Check" method="post">
                            <input type="hidden" value="top_play" name="action">
                            <button class="category">
                                <div class="category_text">
                                    Top Play
                                </div>
                                <div class="category_circle_1">
                                    <div class="category_circle_2">
                                        <div class="category_circle_3">
                                        </div>
                                    </div>
                                </div>
                            </button>
                        </form>
                        <form action="/Check" method="post">
                            <input type="hidden" value="mr" name="action">
                            <input type="hidden" value=<%=check_login%> id="check_login_mr">
                            <button class="category" onclick="return check_mr();">
                                <div class="category_text">
                                    Recommend
                                </div>
                                <div class="category_circle_1">
                                    <div class="category_circle_2">
                                        <div class="category_circle_3">
                                        </div>
                                    </div>
                                </div>
                            </button>
                        </form>
                        <form action="/Check" method="post">
                            <input type="hidden" value="category" name="action">
                            <button class="category">
                                <div class="category_text">
                                    Category
                                </div>
                                <div class="category_circle_1">
                                    <div class="category_circle_2">
                                        <div class="category_circle_3">
                                        </div>
                                    </div>
                                </div>
                            </button>
                        </form>
                        <form action="/Check" method="post">
                            <input type="hidden" value="artist" name="action">
                            <button class="category">
                                <div class="category_text">
                                    Artist
                                </div>
                                <div class="category_circle_1">
                                    <div class="category_circle_2">
                                        <div class="category_circle_3">
                                        </div>
                                    </div>
                                </div>
                            </button>
                        </form>
                        <form action="/Check" method="post">
                            <input type="hidden" value="history" name="action">
                            <input type="hidden" value=<%=check_login%> id="check_login_history">
                            <button class="category" onclick="return check_history();">
                                <div class="category_text">
                                    History
                                </div>
                                <div class="category_circle_1">
                                    <div class="category_circle_2">
                                        <div class="category_circle_3">
                                        </div>
                                    </div>
                                </div>
                            </button>
                        </form>
                    </div>
                    <div class="main">
                        <%
                            if("top_play".equals(display)) {
                                count = 0;
                                for(song s : controlAccount.get_top(9)) {
                        %>
                        <form action="/Check" method="post" class="main_song_form">
                            <input type="hidden" value="action_listen" name="action">
                            <input type="hidden" value="<%=s.getLocalName()%>" name="track_id">
                            <button class="main_song" type="submit">
                                <div class="include_song_index_info">
                                    <div class="song_index top_<%=(++count)%>">
                                        <div class="song_index_index">
                                            <%=count%>
                                        </div>
                                    </div>
                                    <div class="song_info">
                                        <div>
                                            <p class="song-info-title"><%=s.getTitle()%></p>
                                            <p class="song-info-artist">
                                                <%=controlAccount.get_artist_name_from_track_id(s.getLocalName())%>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </button>
                        </form>
                        <%
                                }
                            } else if("artist".equals(display)) {
                                for(artist a: controlAccount.get_arists()){
                        %>
                        <form action="/Check" method="post" class="main_tag_form">
                            <input type="hidden" value="action_get_song_from_artist" name="action">
                            <input type="hidden" value="<%=a.getLocalName()%>" name="artist_id">
                            <button class="main_tag main_artist" type="submit">
                                    <p style="background: #0000001a;"><%=controlAccount.get_display_name(a.getLocalName())%></p>
                            </button>
                        </form>
                        <%
                                }
                            } else if("category".equals(display)) {
                                for(tag t: controlAccount.get_tags()){
                        %>
                            <form action="/Check" method="post" class="main_tag_form">
                                <input type="hidden" value="action_get_song_from_tag" name="action">
                                <input type="hidden" value="<%=t.getLocalName()%>" name="tag_id">
                                <button class="main_tag" type="submit">
                                    <p style="background: #0000001a; padding: 10px;"><%=controlAccount.get_display_name(t.getLocalName())%></p>
                                </button>
                            </form>
                            
                        <%
                                }
                            } else if("history".equals(display)) {
                                for(history h: controlAccount.get_histories()){
                                   song s = controlAccount.get_song_from_track_id((h.getLocalName().split("_"))[1]);
                        %>
                        <form action="/Check" method="post" class="main_song_form">
                                <input type="hidden" value="action_listen" name="action">
                                <input type="hidden" value="<%=s.getLocalName()%>" name="track_id">
                                <button class="main_song" type="submit">
                                    <div class="include_song_index_info">
                                        <div class="song_info_history">
                                                <p class="song-info-title"><%=s.getTitle()%></p>
                                                <p class="song-info-artist">
                                                    <%=controlAccount.get_artist_name_from_track_id(s.getLocalName())%>
                                                </p>
                                        </div>
                                    </div>
                                </button>
                            </form>
                        <%
                                }
                            } else if("mr".equals(display)) {
                                count = 0;
                                try {
                                for(evaluating_song s : controlAccount.get_recommended_songs()){
                        %>
                            <form action="/Check" method="post" class="main_song_form">
                                <input type="hidden" value="action_listen" name="action">
                                <input type="hidden" value="<%=s.get_track_id()%>" name="track_id">
                                <button class="main_song" type="submit">
                                    <div class="include_song_index_info">
                                        <div class="song_index top_<%=(++count)%>">
                                            <div class="song_index_index">
                                                <%
                                                    if(count <= 9) out.print(count); else out.print("X");
                                                %>
                                            </div>
                                        </div>
                                        <div class="song_info">
                                            <div>
                                                <p class="song-info-title"><%=s.getTitle()%></p>
                                                <p class="song-info-artist">
                                                    <%=s.get_display_artist()%>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </button>
                            </form>
                        <%
                                    }
                                } catch (NullPointerException e) {}
                            } else if("action_get_song_from_tag".equals(display)) {
                                String tag_id = (String) session.getAttribute("main_id");
                                out.print("<div class='main_id'>" + controlAccount.get_display_name(tag_id) + "</div>");
                                for(song s : controlAccount.get_song_from_tag_id(tag_id)){
                        %>
                            <form action="/Check" method="post" class="main_song_form">
                                <input type="hidden" value="action_listen" name="action">
                                <input type="hidden" value="<%=s.getLocalName()%>" name="track_id">
                                <button class="main_song" type="submit">
                                    <div class="include_song_index_info">
                                        <div class="song_info_artist_tag">
                                            <div>
                                                <p class="song-info-title"><%=controlAccount.get_song_title_from_track_id(s.getLocalName())%></p>
                                                <p class="song-info-artist">
                                                    <%=controlAccount.get_artist_name_from_track_id(s.getLocalName())%>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </button>
                            </form>
                        <%
                                }
                            } else if("action_get_song_from_search".equals(display)) {
                                String song_name = (String) session.getAttribute("main_id");
                                out.print("<div class='main_id'>" + song_name + "</div>");
                                try {
                                    for(song s : controlAccount.get_song_from_search(song_name)){
                            %>
                                <form action="/Check" method="post" class="main_song_form">
                                    <input type="hidden" value="action_listen" name="action">
                                    <input type="hidden" value="<%=s.getLocalName()%>" name="track_id">
                                    <button class="main_song" type="submit">
                                        <div class="include_song_index_info">
                                            <div class="song_info_artist_tag">
                                                <div>
                                                    <p class="song-info-title"><%=controlAccount.get_song_title_from_track_id(s.getLocalName())%><%=s.getLocalName()%></p>
                                                    <p class="song-info-artist">
                                                        <%=controlAccount.get_artist_name_from_track_id(s.getLocalName())%>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </button>
                                </form>
                            <%
                                    }
                                } catch (NullPointerException e) {}
                            } else if("action_get_song_from_artist".equals(display)) {
                                String artist_id = (String) session.getAttribute("main_id");
                                out.print("<div class='main_id'>" + controlAccount.get_display_name(artist_id) + "</div>");
                                for(song s : controlAccount.get_song_from_artist_id(artist_id)){
                        %>
                            <form action="/Check" method="post" class="main_song_form">
                                <input type="hidden" value="action_listen" name="action">
                                <input type="hidden" value="<%=s.getLocalName()%>" name="track_id">
                                <button class="main_song" type="submit">
                                    <div class="include_song_index_info">
                                        <div class="song_info_artist_tag">
                                            <div>
                                                <p class="song-info-title"><%=controlAccount.get_song_title_from_track_id(s.getLocalName())%></p>
                                                <p class="song-info-artist">
                                                    <%=controlAccount.get_artist_name_from_track_id(s.getLocalName())%>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </button>
                            </form>
                        <%
                                }
                            } else if("action_listen".equals(display)) {
                                String track_id = (String) session.getAttribute("main_id");
                                song s = controlAccount.get_song_from_track_id(track_id);
                                int size = 40;
                                if(s.getTitle().length() > 20) size = 33;
                                String like = "like";
                                String dislike = "dislike";
                                if(controlAccount.get_user_id() != null){
                                    String get_like = controlAccount.get_like(track_id);
                                    if(get_like.equals("liked")) {
                                        like = "liked";
                                    } else if(get_like.equals("disliked")) {
                                        dislike = "disliked";
                                    }
                                }
                        %>
                            <div style="width: 100%; font-size: 0;">
                                <div class="main_title_and_artist">
                                    <p style="font-size: <%=size%>px;"><%=s.getTitle()%></p>
                                    <p style="font-size: 20px;"><%=controlAccount.get_artist_name_from_track_id(track_id)%></p>
                                </div>
                                <div class="main_tags">
                                    <p style="font-size: 20px;"><%=controlAccount.get_tag_name_from_track_id(track_id)%></p>
                                </div>
                            </div>
                            <div style="width: 100%; font-size: 0; height: 340px;">
                                <div class="speaker white_speaker"></div>
                                <div class="player"></div>
                                <div class="speaker yellow_speaker"></div>
                            </div>
                            <div style="width: 100%; font-size: 0; font-family: cursive;">
                                <div class="times_like_dislike">
                                    <p style="margin-left: 15px;"><%=s.getListening_times()%> listens</p>
                                    <p style="margin-left: 15px;"><%=s.getLike()%> <%if(s.getLike() > 1) out.print("likes"); else out.print("like");%></p>
                                    <p style="margin-left: 15px;"><%=s.getDislike()%> <%if(s.getDislike() > 1) out.print("dislikes"); else out.print("dislike");%></p>
                                </div>
                                <form action="/Check" method="post" class="like_form">
                                    <input type="hidden" value="action_like" name="action" >
                                    <button type="submit" class="button_like_dislike">
                                        <%=like%>
                                    </button>
                                </form>
                                <form action="/Check" method="post" class="dislike_form">
                                    <input type="hidden" value="action_dislike" name="action">
                                    <button type="submit" class="button_like_dislike">
                                        <%=dislike%>
                                    </button>
                                </form>
                            </div>                        
                        <%
                            }
                        %>
                    </div>
                    <div class="recommend_bar">
                        <div class='most-suitable'>Most Suitable</div>
                        <%
                            if(controlAccount.get_user_id() != null){
                                if(!"mr".equals(display)) {
                                    count = 0;
                                    try{
                                        for(evaluating_song s : controlAccount.get_recommended_songs()){
                        %>
                        <form action="/Check" method="post" class="recommend-song-form">
                            <input type="hidden" value="action_listen" name="action">
                            <input type="hidden" value="<%=s.get_track_id()%>" name="track_id">
                            <button class="recommend-song">
                                <div class="group-index-artist-icon">
                                    <div class="recommend-index"><%=++count%></div>
                                    <img src="img/present-artist-icon.png" class="present-artist-icon">
                                </div>
                                <div class="group-name-artist">
                                    <div class="recommend-name">
                                        <div class="recommend-name-name"><%=s.getTitle()%></div>
                                    </div>
                                    <div class="recommend-artist"><%=s.get_display_artist()%></div>
                                </div>
                            </button>
                        </form>
                                <%      if(count == 5) break;
                                        }
                                    if(count == 0) {
                                    %>
                                    <div class="recommend-song recommend-song-form">
                                        <p class="suggest_login">Waiting for the next listen.</p>
                                    </div>
                                    <%
                                    }
                                    } catch (NullPointerException e) {
                                    %>
                                    <div class="recommend-song recommend-song-form">
                                        <p class="suggest_login">Waiting for the first listen</p>
                                    </div>
                        
                            <%
                                    }
                                } else {
                                    count = 0;
                                    for(song s : controlAccount.get_top(5)) {                            %>
                            <form action="/Check" method="post" class="recommend-song-form">
                                <input type="hidden" value="action_listen" name="action">
                                <input type="hidden" value="<%=s.getLocalName()%>" name="track_id">
                                <button class="recommend-song">
                                    <div class="group-index-artist-icon">
                                        <div class="recommend-index"><%=++count%></div>
                                        <img src="img/present-artist-icon.png" class="present-artist-icon">
                                    </div>
                                    <div class="group-name-artist">
                                        <div class="recommend-name">
                                            <div class="recommend-name-name"><%=s.getTitle()%></div>
                                        </div>
                                        <div class="recommend-artist"><%=controlAccount.get_artist_name_from_track_id(s.getLocalName())%></div>
                                    </div>
                                </button>
                            </form>
                            <%
                                    }
                                }
                            %>
                        <%} else {%>
                            <div class="recommend-song recommend-song-form">
                                <p class="suggest_login">Login to get the best support</p>
                            </div>
                        <%
                            }
                        %>
                        <div class="recommend-string-left"></div>
                        <div class="recommend-string-right"></div>
                    </div>
                </div>
</body>
</html>