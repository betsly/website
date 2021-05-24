<%-- 
    Document   : jCreateGroupPage
    Created on : 21.01.2021, 12:36:32
    Author     : nico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gruppe erstellen</title>
        <script src="betslyScript.js" type="text/javascript"></script>
        <link href="styleCreateGroup.css" rel="stylesheet" type="text/css"/>
    </head>
    <body  style="background-color: rgba(43,49,52,1)">
        
        <form  action="./BetslyServlet" method="POST" onsubmit="return validateCreateGroup()">
            <div id="football">
                    <div id="Group_36">
                        <div id="Group_55">
                            <div id="Group_47">
                                <svg class="Rectangle_2233">
                                <rect id="Rectangle_2233" rx="3.5" ry="3.5" x="0" y="0" width="156" height="7">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234">
                                <rect id="Rectangle_2234" rx="0" ry="0" x="0" y="0" width="156" height="7">
                                </rect>
                                </svg>
                            </div>
                            <div id="Group_48">
                                <svg class="Rectangle_2233_t">
                                <rect id="Rectangle_2233_t" rx="3.5" ry="3.5" x="0" y="0" width="157" height="7">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234_u">
                                <rect id="Rectangle_2234_u" rx="0" ry="0" x="0" y="0" width="157" height="7">
                                </rect>
                                </svg>
                            </div>
                        </div>
                        <div id="Group_53">
                            <div id="Group_49">
                                <svg class="Rectangle_2233_x">
                                <rect id="Rectangle_2233_x" rx="3.848804235458374" ry="3.848804235458374" x="0" y="0" width="156.511" height="7.698">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234_y">
                                <rect id="Rectangle_2234_y" rx="0" ry="0" x="0" y="0" width="156.511" height="7.698">
                                </rect>
                                </svg>
                            </div>
                            <div id="Group_50">
                                <svg class="Rectangle_2233_">
                                <rect id="Rectangle_2233_" rx="3.8487727642059326" ry="3.8487727642059326" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234_">
                                <rect id="Rectangle_2234_" rx="0" ry="0" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                            </div>
                            <div id="Group_51">
                                <svg class="Rectangle_2233_ba">
                                <rect id="Rectangle_2233_ba" rx="3.848804235458374" ry="3.848804235458374" x="0" y="0" width="156.511" height="7.698">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234_ba">
                                <rect id="Rectangle_2234_ba" rx="0" ry="0" x="0" y="0" width="156.511" height="7.698">
                                </rect>
                                </svg>
                            </div>
                            <div id="Group_52">
                                <svg class="Rectangle_2233_bb">
                                <rect id="Rectangle_2233_bb" rx="3.8487727642059326" ry="3.8487727642059326" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234_bb">
                                <rect id="Rectangle_2234_bb" rx="0" ry="0" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                            </div>
                        </div>
                        <div id="Group_54">
                            <div id="Group_49_">
                                <svg class="Rectangle_2233_bc">
                                <rect id="Rectangle_2233_bc" rx="3.8487892150878906" ry="3.8487892150878906" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234_bc">
                                <rect id="Rectangle_2234_bc" rx="0" ry="0" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                            </div>
                            <div id="Group_50_bc">
                                <svg class="Rectangle_2233_bd">
                                <rect id="Rectangle_2233_bd" rx="3.848785161972046" ry="3.848785161972046" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234_be">
                                <rect id="Rectangle_2234_be" rx="0" ry="0" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                            </div>
                            <div id="Group_51_bf">
                                <svg class="Rectangle_2233_bg">
                                <rect id="Rectangle_2233_bg" rx="3.8487892150878906" ry="3.8487892150878906" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234_bh">
                                <rect id="Rectangle_2234_bh" rx="0" ry="0" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                            </div>
                            <div id="Group_52_bi">
                                <svg class="Rectangle_2233_bj">
                                <rect id="Rectangle_2233_bj" rx="3.848785161972046" ry="3.848785161972046" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                                <svg class="Rectangle_2234_bk">
                                <rect id="Rectangle_2234_bk" rx="0" ry="0" x="0" y="0" width="156.51" height="7.698">
                                </rect>
                                </svg>
                            </div>
                        </div>
                        <svg class="Polygon_5" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_5" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                        <svg class="Polygon_4" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_4" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                    </div>
                    <div id="Group_38">
                        <svg class="Polygon_5_bo" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_5_bo" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                        <svg class="Polygon_4_bp" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_4_bp" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                    </div>
                    <div id="Group_39">
                        <svg class="Polygon_5_br" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_5_br" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                        <svg class="Polygon_4_bs" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_4_bs" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                    </div>
                    <div id="Group_40">
                        <svg class="Polygon_5_bu" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_5_bu" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                        <svg class="Polygon_4_bv" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_4_bv" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                    </div>
                    <div id="Group_43">
                        <svg class="Polygon_5_bx" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_5_bx" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                        <svg class="Polygon_4_by" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_4_by" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                    </div>
                    <div id="Group_45">
                        <div id="Group_43_b">
                            <svg class="Polygon_5_b" viewBox="0 0 284.798 246.312">
                            <path id="Polygon_5_b" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                            </path>
                            </svg>
                            <svg class="Polygon_4_b" viewBox="0 0 284.798 246.312">
                            <path id="Polygon_4_b" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                            </path>
                            </svg>
                        </div>
                    </div>
                    <div id="Group_46">
                        <svg class="Polygon_5_ca" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_5_ca" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                        <svg class="Polygon_4_ca" viewBox="0 0 284.798 246.312">
                        <path id="Polygon_4_ca" d="M 213.5985717773438 0 L 284.798095703125 123.1559219360352 L 213.5985717773438 246.3118438720703 L 71.19951629638672 246.3118438720703 L 0 123.1559143066406 L 71.19953918457031 0 Z">
                        </path>
                        </svg>
                    </div>
                </div>
            
                <img id="default-user-image" src="default-user-image.png" srcset="default-user-image.png 1x, default-user-image@2x.png 2x">
                 <div id="Group_8">
                    <img id="Group_7" src="Group_7.png" srcset="Group_7.png 1x, Group_7@2x.png 2x">

                    </svg>
                </div>
                <p id="title">Neue Gruppe erstellen</p>
                <div id="divi">
                <div class="inputs"><input class="input" type="text" name="createGroupName" id="createGroupName" value="" placeholder="   Name"/></div>
                <div class="inputs"><input class="input" type="text" name="createGroupPassword" id="createGroupPassword" value="" placeholder="   Password"/></div>
                <div class="inputs"><input class="input" type="text" name="createGroupDes" id="createGroupDes" value="" placeholder="  Beschreibung"/></div></div>
                <div id="divb">
                <div class="buttons"><input class="bt" type="submit" value="Gruppe erstellen" name="createGroup" /></div>
                <div class="buttons"><input class="bt" type="submit" value="Zurück" name="backCreate" /></div></div>
        </form>
    </body>
</html>
