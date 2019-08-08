/*
 AngularJS v1.4.6
 (c) 2010-2015 Google, Inc. http://angularjs.org
 License: MIT
*/
(function(Q,X,w){'use strict';function I(b){return function(){var a=arguments[0],c;c="["+(b?b+":":"")+a+"] http://errors.angularjs.org/1.4.6/"+(b?b+"/":"")+a;for(a=1;a<arguments.length;a++){c=c+(1==a?"?":"&")+"p"+(a-1)+"=";var d=encodeURIComponent,e;e=arguments[a];e="function"==typeof e?e.toString().replace(/ \{[\s\S]*$/,""):"undefined"==typeof e?"undefined":"string"!=typeof e?JSON.stringify(e):e;c+=d(e)}return Error(c)}}function Da(b){if(null==b||Za(b))return!1;var a="length"in Object(b)&&b.length;
return b.nodeType===pa&&a?!0:G(b)||J(b)||0===a||"number"===typeof a&&0<a&&a-1 in b}function m(b,a,c){var d,e;if(b)if(x(b))for(d in b)"prototype"==d||"length"==d||"name"==d||b.hasOwnProperty&&!b.hasOwnProperty(d)||a.call(c,b[d],d,b);else if(J(b)||Da(b)){var f="object"!==typeof b;d=0;for(e=b.length;d<e;d++)(f||d in b)&&a.call(c,b[d],d,b)}else if(b.forEach&&b.forEach!==m)b.forEach(a,c,b);else if(lc(b))for(d in b)a.call(c,b[d],d,b);else if("function"===typeof b.hasOwnProperty)for(d in b)b.hasOwnProperty(d)&&
a.call(c,b[d],d,b);else for(d in b)ta.call(b,d)&&a.call(c,b[d],d,b);return b}function mc(b,a,c){for(var d=Object.keys(b).sort(),e=0;e<d.length;e++)a.call(c,b[d[e]],d[e]);return d}function nc(b){return function(a,c){b(c,a)}}function Sd(){return++nb}function oc(b,a){a?b.$$hashKey=a:delete b.$$hashKey}function Mb(b,a,c){for(var d=b.$$hashKey,e=0,f=a.length;e<f;++e){var g=a[e];if(B(g)||x(g))for(var h=Object.keys(g),l=0,k=h.length;l<k;l++){var n=h[l],p=g[n];c&&B(p)?da(p)?b[n]=new Date(p.valueOf()):Oa(p)?
b[n]=new RegExp(p):(B(b[n])||(b[n]=J(p)?[]:{}),Mb(b[n],[p],!0)):b[n]=p}}oc(b,d);return b}function P(b){return Mb(b,ua.call(arguments,1),!1)}function Td(b){return Mb(b,ua.call(arguments,1),!0)}function Y(b){return parseInt(b,10)}function Nb(b,a){return P(Object.create(b),a)}function y(){}function $a(b){return b}function qa(b){return function(){return b}}function pc(b){return x(b.toString)&&b.toString!==Object.prototype.toString}function v(b){return"undefined"===typeof b}function A(b){return"undefined"!==
typeof b}function B(b){return null!==b&&"object"===typeof b}function lc(b){return null!==b&&"object"===typeof b&&!qc(b)}function G(b){return"string"===typeof b}function V(b){return"number"===typeof b}function da(b){return"[object Date]"===va.call(b)}function x(b){return"function"===typeof b}function Oa(b){return"[object RegExp]"===va.call(b)}function Za(b){return b&&b.window===b}function ab(b){return b&&b.$evalAsync&&b.$watch}function bb(b){return"boolean"===typeof b}function rc(b){return!(!b||!(b.nodeName||
b.prop&&b.attr&&b.find))}function Ud(b){var a={};b=b.split(",");var c;for(c=0;c<b.length;c++)a[b[c]]=!0;return a}function wa(b){return F(b.nodeName||b[0]&&b[0].nodeName)}function cb(b,a){var c=b.indexOf(a);0<=c&&b.splice(c,1);return c}function ga(b,a,c,d){if(Za(b)||ab(b))throw Ea("cpws");if(sc.test(va.call(a)))throw Ea("cpta");if(a){if(b===a)throw Ea("cpi");c=c||[];d=d||[];B(b)&&(c.push(b),d.push(a));var e;if(J(b))for(e=a.length=0;e<b.length;e++)a.push(ga(b[e],null,c,d));else{var f=a.$$hashKey;J(a)?
a.length=0:m(a,function(b,c){delete a[c]});if(lc(b))for(e in b)a[e]=ga(b[e],null,c,d);else if(b&&"function"===typeof b.hasOwnProperty)for(e in b)b.hasOwnProperty(e)&&(a[e]=ga(b[e],null,c,d));else for(e in b)ta.call(b,e)&&(a[e]=ga(b[e],null,c,d));oc(a,f)}}else if(a=b,B(b)){if(c&&-1!==(f=c.indexOf(b)))return d[f];if(J(b))return ga(b,[],c,d);if(sc.test(va.call(b)))a=new b.constructor(b);else if(da(b))a=new Date(b.getTime());else if(Oa(b))a=new RegExp(b.source,b.toString().match(/[^\/]*$/)[0]),a.lastIndex=
b.lastIndex;else if(x(b.cloneNode))a=b.cloneNode(!0);else return e=Object.create(qc(b)),ga(b,e,c,d);d&&(c.push(b),d.push(a))}return a}function ja(b,a){if(J(b)){a=a||[];for(var c=0,d=b.length;c<d;c++)a[c]=b[c]}else if(B(b))for(c in a=a||{},b)if("$"!==c.charAt(0)||"$"!==c.charAt(1))a[c]=b[c];return a||b}function ka(b,a){if(b===a)return!0;if(null===b||null===a)return!1;if(b!==b&&a!==a)return!0;var c=typeof b,d;if(c==typeof a&&"object"==c)if(J(b)){if(!J(a))return!1;if((c=b.length)==a.length){for(d=0;d<
c;d++)if(!ka(b[d],a[d]))return!1;return!0}}else{if(da(b))return da(a)?ka(b.getTime(),a.getTime()):!1;if(Oa(b))return Oa(a)?b.toString()==a.toString():!1;if(ab(b)||ab(a)||Za(b)||Za(a)||J(a)||da(a)||Oa(a))return!1;c=ha();for(d in b)if("$"!==d.charAt(0)&&!x(b[d])){if(!ka(b[d],a[d]))return!1;c[d]=!0}for(d in a)if(!(d in c)&&"$"!==d.charAt(0)&&A(a[d])&&!x(a[d]))return!1;return!0}return!1}function db(b,a,c){return b.concat(ua.call(a,c))}function tc(b,a){var c=2<arguments.length?ua.call(arguments,2):[];
return!x(a)||a instanceof RegExp?a:c.length?function(){return arguments.length?a.apply(b,db(c,arguments,0)):a.apply(b,c)}:function(){return arguments.length?a.apply(b,arguments):a.call(b)}}function Vd(b,a){var c=a;"string"===typeof b&&"$"===b.charAt(0)&&"$"===b.charAt(1)?c=w:Za(a)?c="$WINDOW":a&&X===a?c="$DOCUMENT":ab(a)&&(c="$SCOPE");return c}function eb(b,a){if("undefined"===typeof b)return w;V(a)||(a=a?2:null);return JSON.stringify(b,Vd,a)}function uc(b){return G(b)?JSON.parse(b):b}function vc(b,
a){var c=Date.parse("Jan 01, 1970 00:00:00 "+b)/6E4;return isNaN(c)?a:c}function Ob(b,a,c){c=c?-1:1;var d=vc(a,b.getTimezoneOffset());a=b;b=c*(d-b.getTimezoneOffset());a=new Date(a.getTime());a.setMinutes(a.getMinutes()+b);return a}function xa(b){b=C(b).clone();try{b.empty()}catch(a){}var c=C("<div>").append(b).html();try{return b[0].nodeType===Pa?F(c):c.match(/^(<[^>]+>)/)[1].replace(/^<([\w\-]+)/,function(a,b){return"<"+F(b)})}catch(d){return F(c)}}function wc(b){try{return decodeURIComponent(b)}catch(a){}}
function xc(b){var a={};m((b||"").split("&"),function(b){var d,e,f;b&&(e=b=b.replace(/\+/g,"%20"),d=b.indexOf("="),-1!==d&&(e=b.substring(0,d),f=b.substring(d+1)),e=wc(e),A(e)&&(f=A(f)?wc(f):!0,ta.call(a,e)?J(a[e])?a[e].push(f):a[e]=[a[e],f]:a[e]=f))});return a}function Pb(b){var a=[];m(b,function(b,d){J(b)?m(b,function(b){a.push(la(d,!0)+(!0===b?"":"="+la(b,!0)))}):a.push(la(d,!0)+(!0===b?"":"="+la(b,!0)))});return a.length?a.join("&"):""}function ob(b){return la(b,!0).replace(/%26/gi,"&").replace(/%3D/gi,
"=").replace(/%2B/gi,"+")}function la(b,a){return encodeURIComponent(b).replace(/%40/gi,"@").replace(/%3A/gi,":").replace(/%24/g,"$").replace(/%2C/gi,",").replace(/%3B/gi,";").replace(/%20/g,a?"%20":"+")}function Wd(b,a){var c,d,e=Qa.length;for(d=0;d<e;++d)if(c=Qa[d]+a,G(c=b.getAttribute(c)))return c;return null}function Xd(b,a){var c,d,e={};m(Qa,function(a){a+="app";!c&&b.hasAttribute&&b.hasAttribute(a)&&(c=b,d=b.getAttribute(a))});m(Qa,function(a){a+="app";var e;!c&&(e=b.querySelector("["+a.replace(":",
"\\:")+"]"))&&(c=e,d=e.getAttribute(a))});c&&(e.strictDi=null!==Wd(c,"strict-di"),a(c,d?[d]:[],e))}function yc(b,a,c){B(c)||(c={});c=P({strictDi:!1},c);var d=function(){b=C(b);if(b.injector()){var d=b[0]===X?"document":xa(b);throw Ea("btstrpd",d.replace(/</,"&lt;").replace(/>/,"&gt;"));}a=a||[];a.unshift(["$provide",function(a){a.value("$rootElement",b)}]);c.debugInfoEnabled&&a.push(["$compileProvider",function(a){a.debugInfoEnabled(!0)}]);a.unshift("ng");d=fb(a,c.strictDi);d.invoke(["$rootScope",
"$rootElement","$compile","$injector",function(a,b,c,d){a.$apply(function(){b.data("$injector",d);c(b)(a)})}]);return d},e=/^NG_ENABLE_DEBUG_INFO!/,f=/^NG_DEFER_BOOTSTRAP!/;Q&&e.test(Q.name)&&(c.debugInfoEnabled=!0,Q.name=Q.name.replace(e,""));if(Q&&!f.test(Q.name))return d();Q.name=Q.name.replace(f,"");aa.resumeBootstrap=function(b){m(b,function(b){a.push(b)});return d()};x(aa.resumeDeferredBootstrap)&&aa.resumeDeferredBootstrap()}function Yd(){Q.name="NG_ENABLE_DEBUG_INFO!"+Q.name;Q.location.reload()}
function Zd(b){b=aa.element(b).injector();if(!b)throw Ea("test");return b.get("$$testability")}function zc(b,a){a=a||"_";return b.replace($d,function(b,d){return(d?a:"")+b.toLowerCase()})}function ae(){var b;if(!Ac){var a=pb();(ra=v(a)?Q.jQuery:a?Q[a]:w)&&ra.fn.on?(C=ra,P(ra.fn,{scope:Ra.scope,isolateScope:Ra.isolateScope,controller:Ra.controller,injector:Ra.injector,inheritedData:Ra.inheritedData}),b=ra.cleanData,ra.cleanData=function(a){var d;if(Qb)Qb=!1;else for(var e=0,f;null!=(f=a[e]);e++)(d=
ra._data(f,"events"))&&d.$destroy&&ra(f).triggerHandler("$destroy");b(a)}):C=R;aa.element=C;Ac=!0}}function qb(b,a,c){if(!b)throw Ea("areq",a||"?",c||"required");return b}function Sa(b,a,c){c&&J(b)&&(b=b[b.length-1]);qb(x(b),a,"not a function, got "+(b&&"object"===typeof b?b.constructor.name||"Object":typeof b));return b}function Ta(b,a){if("hasOwnProperty"===b)throw Ea("badname",a);}function Bc(b,a,c){if(!a)return b;a=a.split(".");for(var d,e=b,f=a.length,g=0;g<f;g++)d=a[g],b&&(b=(e=b)[d]);return!c&&
x(b)?tc(e,b):b}function rb(b){for(var a=b[0],c=b[b.length-1],d,e=1;a!==c&&(a=a.nextSibling);e++)if(d||b[e]!==a)d||(d=C(ua.call(b,0,e))),d.push(a);return d||b}function ha(){return Object.create(null)}function be(b){function a(a,b,c){return a[b]||(a[b]=c())}var c=I("$injector"),d=I("ng");b=a(b,"angular",Object);b.$$minErr=b.$$minErr||I;return a(b,"module",function(){var b={};return function(f,g,h){if("hasOwnProperty"===f)throw d("badname","module");g&&b.hasOwnProperty(f)&&(b[f]=null);return a(b,f,function(){function a(b,
c,e,f){f||(f=d);return function(){f[e||"push"]([b,c,arguments]);return E}}function b(a,c){return function(b,e){e&&x(e)&&(e.$$moduleName=f);d.push([a,c,arguments]);return E}}if(!g)throw c("nomod",f);var d=[],e=[],r=[],t=a("$injector","invoke","push",e),E={_invokeQueue:d,_configBlocks:e,_runBlocks:r,requires:g,name:f,provider:b("$provide","provider"),factory:b("$provide","factory"),service:b("$provide","service"),value:a("$provide","value"),constant:a("$provide","constant","unshift"),decorator:b("$provide",
"decorator"),animation:b("$animateProvider","register"),filter:b("$filterProvider","register"),controller:b("$controllerProvider","register"),directive:b("$compileProvider","directive"),config:t,run:function(a){r.push(a);return this}};h&&t(h);return E})}})}function ce(b){P(b,{bootstrap:yc,copy:ga,extend:P,merge:Td,equals:ka,element:C,forEach:m,injector:fb,noop:y,bind:tc,toJson:eb,fromJson:uc,identity:$a,isUndefined:v,isDefined:A,isString:G,isFunction:x,isObject:B,isNumber:V,isElement:rc,isArray:J,
version:de,isDate:da,lowercase:F,uppercase:sb,callbacks:{counter:0},getTestability:Zd,$$minErr:I,$$csp:Fa,reloadWithDebugInfo:Yd});Rb=be(Q);Rb("ng",["ngLocale"],["$provide",function(a){a.provider({$$sanitizeUri:ee});a.provider("$compile",Cc).directive({a:fe,input:Dc,textarea:Dc,form:ge,script:he,select:ie,style:je,option:ke,ngBind:le,ngBindHtml:me,ngBindTemplate:ne,ngClass:oe,ngClassEven:pe,ngClassOdd:qe,ngCloak:re,ngController:se,ngForm:te,ngHide:ue,ngIf:ve,ngInclude:we,ngInit:xe,ngNonBindable:ye,
ngPluralize:ze,ngRepeat:Ae,ngShow:Be,ngStyle:Ce,ngSwitch:De,ngSwitchWhen:Ee,ngSwitchDefault:Fe,ngOptions:Ge,ngTransclude:He,ngModel:Ie,ngList:Je,ngChange:Ke,pattern:Ec,ngPattern:Ec,required:Fc,ngRequired:Fc,minlength:Gc,ngMinlength:Gc,maxlength:Hc,ngMaxlength:Hc,ngValue:Le,ngModelOptions:Me}).directive({ngInclude:Ne}).directive(tb).directive(Ic);a.provider({$anchorScroll:Oe,$animate:Pe,$animateCss:Qe,$$animateQueue:Re,$$AnimateRunner:Se,$browser:Te,$cacheFactory:Ue,$controller:Ve,$document:We,$exceptionHandler:Xe,
$filter:Jc,$$forceReflow:Ye,$interpolate:Ze,$interval:$e,$http:af,$httpParamSerializer:bf,$httpParamSerializerJQLike:cf,$httpBackend:df,$location:ef,$log:ff,$parse:gf,$rootScope:hf,$q:jf,$$q:kf,$sce:lf,$sceDelegate:mf,$sniffer:nf,$templateCache:of,$templateRequest:pf,$$testability:qf,$timeout:rf,$window:sf,$$rAF:tf,$$jqLite:uf,$$HashMap:vf,$$cookieReader:wf})}])}function gb(b){return b.replace(xf,function(a,b,d,e){return e?d.toUpperCase():d}).replace(yf,"Moz$1")}function Kc(b){b=b.nodeType;return b===
pa||!b||9===b}function Lc(b,a){var c,d,e=a.createDocumentFragment(),f=[];if(Sb.test(b)){c=c||e.appendChild(a.createElement("div"));d=(zf.exec(b)||["",""])[1].toLowerCase();d=ma[d]||ma._default;c.innerHTML=d[1]+b.replace(Af,"<$1></$2>")+d[2];for(d=d[0];d--;)c=c.lastChild;f=db(f,c.childNodes);c=e.firstChild;c.textContent=""}else f.push(a.createTextNode(b));e.textContent="";e.innerHTML="";m(f,function(a){e.appendChild(a)});return e}function R(b){if(b instanceof R)return b;var a;G(b)&&(b=T(b),a=!0);if(!(this instanceof
R)){if(a&&"<"!=b.charAt(0))throw Tb("nosel");return new R(b)}if(a){a=X;var c;b=(c=Bf.exec(b))?[a.createElement(c[1])]:(c=Lc(b,a))?c.childNodes:[]}Mc(this,b)}function Ub(b){return b.cloneNode(!0)}function ub(b,a){a||vb(b);if(b.querySelectorAll)for(var c=b.querySelectorAll("*"),d=0,e=c.length;d<e;d++)vb(c[d])}function Nc(b,a,c,d){if(A(d))throw Tb("offargs");var e=(d=wb(b))&&d.events,f=d&&d.handle;if(f)if(a)m(a.split(" "),function(a){if(A(c)){var d=e[a];cb(d||[],c);if(d&&0<d.length)return}b.removeEventListener(a,
f,!1);delete e[a]});else for(a in e)"$destroy"!==a&&b.removeEventListener(a,f,!1),delete e[a]}function vb(b,a){var c=b.ng339,d=c&&hb[c];d&&(a?delete d.data[a]:(d.handle&&(d.events.$destroy&&d.handle({},"$destroy"),Nc(b)),delete hb[c],b.ng339=w))}function wb(b,a){var c=b.ng339,c=c&&hb[c];a&&!c&&(b.ng339=c=++Cf,c=hb[c]={events:{},data:{},handle:w});return c}function Vb(b,a,c){if(Kc(b)){var d=A(c),e=!d&&a&&!B(a),f=!a;b=(b=wb(b,!e))&&b.data;if(d)b[a]=c;else{if(f)return b;if(e)return b&&b[a];P(b,a)}}}
function xb(b,a){return b.getAttribute?-1<(" "+(b.getAttribute("class")||"")+" ").replace(/[\n\t]/g," ").indexOf(" "+a+" "):!1}function yb(b,a){a&&b.setAttribute&&m(a.split(" "),function(a){b.setAttribute("class",T((" "+(b.getAttribute("class")||"")+" ").replace(/[\n\t]/g," ").replace(" "+T(a)+" "," ")))})}function zb(b,a){if(a&&b.setAttribute){var c=(" "+(b.getAttribute("class")||"")+" ").replace(/[\n\t]/g," ");m(a.split(" "),function(a){a=T(a);-1===c.indexOf(" "+a+" ")&&(c+=a+" ")});b.setAttribute("class",
T(c))}}function Mc(b,a){if(a)if(a.nodeType)b[b.length++]=a;else{var c=a.length;if("number"===typeof c&&a.window!==a){if(c)for(var d=0;d<c;d++)b[b.length++]=a[d]}else b[b.length++]=a}}function Oc(b,a){return Ab(b,"$"+(a||"ngController")+"Controller")}function Ab(b,a,c){9==b.nodeType&&(b=b.documentElement);for(a=J(a)?a:[a];b;){for(var d=0,e=a.length;d<e;d++)if(A(c=C.data(b,a[d])))return c;b=b.parentNode||11===b.nodeType&&b.host}}function Pc(b){for(ub(b,!0);b.firstChild;)b.removeChild(b.firstChild)}
function Wb(b,a){a||ub(b);var c=b.parentNode;c&&c.removeChild(b)}function Df(b,a){a=a||Q;if("complete"===a.document.readyState)a.setTimeout(b);else C(a).on("load",b)}function Qc(b,a){var c=Bb[a.toLowerCase()];return c&&Rc[wa(b)]&&c}function Ef(b,a){var c=function(c,e){c.isDefaultPrevented=function(){return c.defaultPrevented};var f=a[e||c.type],g=f?f.length:0;if(g){if(v(c.immediatePropagationStopped)){var h=c.stopImmediatePropagation;c.stopImmediatePropagation=function(){c.immediatePropagationStopped=
!0;c.stopPropagation&&c.stopPropagation();h&&h.call(c)}}c.isImmediatePropagationStopped=function(){return!0===c.immediatePropagationStopped};1<g&&(f=ja(f));for(var l=0;l<g;l++)c.isImmediatePropagationStopped()||f[l].call(b,c)}};c.elem=b;return c}function uf(){this.$get=function(){return P(R,{hasClass:function(b,a){b.attr&&(b=b[0]);return xb(b,a)},addClass:function(b,a){b.attr&&(b=b[0]);return zb(b,a)},removeClass:function(b,a){b.attr&&(b=b[0]);return yb(b,a)}})}}function Ga(b,a){var c=b&&b.$$hashKey;
if(c)return"function"===typeof c&&(c=b.$$hashKey()),c;c=typeof b;return c="function"==c||"object"==c&&null!==b?b.$$hashKey=c+":"+(a||Sd)():c+":"+b}function Ua(b,a){if(a){var c=0;this.nextUid=function(){return++c}}m(b,this.put,this)}function Ff(b){return(b=b.toString().replace(Sc,"").match(Tc))?"function("+(b[1]||"").replace(/[\s\r\n]+/," ")+")":"fn"}function fb(b,a){function c(a){return function(b,c){if(B(b))m(b,nc(a));else return a(b,c)}}function d(a,b){Ta(a,"service");if(x(b)||J(b))b=r.instantiate(b);
if(!b.$get)throw Ha("pget",a);return p[a+"Provider"]=b}function e(a,b){return function(){var c=E.invoke(b,this);if(v(c))throw Ha("undef",a);return c}}function f(a,b,c){return d(a,{$get:!1!==c?e(a,b):b})}function g(a){qb(v(a)||J(a),"modulesToLoad","not an array");var b=[],c;m(a,function(a){function d(a){var b,c;b=0;for(c=a.length;b<c;b++){var e=a[b],f=r.get(e[0]);f[e[1]].apply(f,e[2])}}if(!n.get(a)){n.put(a,!0);try{G(a)?(c=Rb(a),b=b.concat(g(c.requires)).concat(c._runBlocks),d(c._invokeQueue),d(c._configBlocks)):
x(a)?b.push(r.invoke(a)):J(a)?b.push(r.invoke(a)):Sa(a,"module")}catch(e){throw J(a)&&(a=a[a.length-1]),e.message&&e.stack&&-1==e.stack.indexOf(e.message)&&(e=e.message+"\n"+e.stack),Ha("modulerr",a,e.stack||e.message||e);}}});return b}function h(b,c){function d(a,e){if(b.hasOwnProperty(a)){if(b[a]===l)throw Ha("cdep",a+" <- "+k.join(" <- "));return b[a]}try{return k.unshift(a),b[a]=l,b[a]=c(a,e)}catch(f){throw b[a]===l&&delete b[a],f;}finally{k.shift()}}function e(b,c,f,h){"string"===typeof f&&(h=
f,f=null);var g=[],k=fb.$$annotate(b,a,h),l,r,n;r=0;for(l=k.length;r<l;r++){n=k[r];if("string"!==typeof n)throw Ha("itkn",n);g.push(f&&f.hasOwnProperty(n)?f[n]:d(n,h))}J(b)&&(b=b[l]);return b.apply(c,g)}return{invoke:e,instantiate:function(a,b,c){var d=Object.create((J(a)?a[a.length-1]:a).prototype||null);a=e(a,d,b,c);return B(a)||x(a)?a:d},get:d,annotate:fb.$$annotate,has:function(a){return p.hasOwnProperty(a+"Provider")||b.hasOwnProperty(a)}}}a=!0===a;var l={},k=[],n=new Ua([],!0),p={$provide:{provider:c(d),
factory:c(f),service:c(function(a,b){return f(a,["$injector",function(a){return a.instantiate(b)}])}),value:c(function(a,b){return f(a,qa(b),!1)}),constant:c(function(a,b){Ta(a,"constant");p[a]=b;t[a]=b}),decorator:function(a,b){var c=r.get(a+"Provider"),d=c.$get;c.$get=function(){var a=E.invoke(d,c);return E.invoke(b,null,{$delegate:a})}}}},r=p.$injector=h(p,function(a,b){aa.isString(b)&&k.push(b);throw Ha("unpr",k.join(" <- "));}),t={},E=t.$injector=h(t,function(a,b){var c=r.get(a+"Provider",b);
return E.invoke(c.$get,c,w,a)});m(g(b),function(a){a&&E.invoke(a)});return E}function Oe(){var b=!0;this.disableAutoScrolling=function(){b=!1};this.$get=["$window","$location","$rootScope",function(a,c,d){function e(a){var b=null;Array.prototype.some.call(a,function(a){if("a"===wa(a))return b=a,!0});return b}function f(b){if(b){b.scrollIntoView();var c;c=g.yOffset;x(c)?c=c():rc(c)?(c=c[0],c="fixed"!==a.getComputedStyle(c).position?0:c.getBoundingClientRect().bottom):V(c)||(c=0);c&&(b=b.getBoundingClientRect().top,
a.scrollBy(0,b-c))}else a.scrollTo(0,0)}function g(a){a=G(a)?a:c.hash();var b;a?(b=h.getElementById(a))?f(b):(b=e(h.getElementsByName(a)))?f(b):"top"===a&&f(null):f(null)}var h=a.document;b&&d.$watch(function(){return c.hash()},function(a,b){a===b&&""===a||Df(function(){d.$evalAsync(g)})});return g}]}function ib(b,a){if(!b&&!a)return"";if(!b)return a;if(!a)return b;J(b)&&(b=b.join(" "));J(a)&&(a=a.join(" "));return b+" "+a}function Gf(b){G(b)&&(b=b.split(" "));var a=ha();m(b,function(b){b.length&&
(a[b]=!0)});return a}function Ia(b){return B(b)?b:{}}function Hf(b,a,c,d){function e(a){try{a.apply(null,ua.call(arguments,1))}finally{if(E--,0===E)for(;K.length;)try{K.pop()()}catch(b){c.error(b)}}}function f(){ia=null;g();h()}function g(){a:{try{u=n.state;break a}catch(a){}u=void 0}u=v(u)?null:u;ka(u,L)&&(u=L);L=u}function h(){if(z!==l.url()||q!==u)z=l.url(),q=u,m(O,function(a){a(l.url(),u)})}var l=this,k=b.location,n=b.history,p=b.setTimeout,r=b.clearTimeout,t={};l.isMock=!1;var E=0,K=[];l.$$completeOutstandingRequest=
e;l.$$incOutstandingRequestCount=function(){E++};l.notifyWhenNoOutstandingRequests=function(a){0===E?a():K.push(a)};var u,q,z=k.href,N=a.find("base"),ia=null;g();q=u;l.url=function(a,c,e){v(e)&&(e=null);k!==b.location&&(k=b.location);n!==b.history&&(n=b.history);if(a){var f=q===e;if(z===a&&(!d.history||f))return l;var h=z&&Ja(z)===Ja(a);z=a;q=e;if(!d.history||h&&f){if(!h||ia)ia=a;c?k.replace(a):h?(c=k,e=a.indexOf("#"),e=-1===e?"":a.substr(e),c.hash=e):k.href=a;k.href!==a&&(ia=a)}else n[c?"replaceState":
"pushState"](e,"",a),g(),q=u;return l}return ia||k.href.replace(/%27/g,"'")};l.state=function(){return u};var O=[],H=!1,L=null;l.onUrlChange=function(a){if(!H){if(d.history)C(b).on("popstate",f);C(b).on("hashchange",f);H=!0}O.push(a);return a};l.$$applicationDestroyed=function(){C(b).off("hashchange popstate",f)};l.$$checkUrlChange=h;l.baseHref=function(){var a=N.attr("href");return a?a.replace(/^(https?\:)?\/\/[^\/]*/,""):""};l.defer=function(a,b){var c;E++;c=p(function(){delete t[c];e(a)},b||0);
t[c]=!0;return c};l.defer.cancel=function(a){return t[a]?(delete t[a],r(a),e(y),!0):!1}}function Te(){this.$get=["$window","$log","$sniffer","$document",function(b,a,c,d){return new Hf(b,d,a,c)}]}function Ue(){this.$get=function(){function b(b,d){function e(a){a!=p&&(r?r==a&&(r=a.n):r=a,f(a.n,a.p),f(a,p),p=a,p.n=null)}function f(a,b){a!=b&&(a&&(a.p=b),b&&(b.n=a))}if(b in a)throw I("$cacheFactory")("iid",b);var g=0,h=P({},d,{id:b}),l={},k=d&&d.capacity||Number.MAX_VALUE,n={},p=null,r=null;return a[b]=
{put:function(a,b){if(!v(b)){if(k<Number.MAX_VALUE){var c=n[a]||(n[a]={key:a});e(c)}a in l||g++;l[a]=b;g>k&&this.remove(r.key);return b}},get:function(a){if(k<Number.MAX_VALUE){var b=n[a];if(!b)return;e(b)}return l[a]},remove:function(a){if(k<Number.MAX_VALUE){var b=n[a];if(!b)return;b==p&&(p=b.p);b==r&&(r=b.n);f(b.n,b.p);delete n[a]}delete l[a];g--},removeAll:function(){l={};g=0;n={};p=r=null},destroy:function(){n=h=l=null;delete a[b]},info:function(){return P({},h,{size:g})}}}var a={};b.info=function(){var b=
{};m(a,function(a,e){b[e]=a.info()});return b};b.get=function(b){return a[b]};return b}}function of(){this.$get=["$cacheFactory",function(b){return b("templates")}]}function Cc(b,a){function c(a,b,c){var d=/^\s*([@&]|=(\*?))(\??)\s*(\w*)\s*$/,e={};m(a,function(a,f){var h=a.match(d);if(!h)throw fa("iscp",b,f,a,c?"controller bindings definition":"isolate scope definition");e[f]={mode:h[1][0],collection:"*"===h[2],optional:"?"===h[3],attrName:h[4]||f}});return e}function d(a){var b=a.charAt(0);if(!b||
b!==F(b))throw fa("baddir",a);if(a!==a.trim())throw fa("baddir",a);}var e={},f=/^\s*directive\:\s*([\w\-]+)\s+(.*)$/,g=/(([\w\-]+)(?:\:([^;]+))?;?)/,h=Ud("ngSrc,ngSrcset,src,srcset"),l=/^(?:(\^\^?)?(\?)?(\^\^?)?)?/,k=/^(on[a-z]+|formaction)$/;this.directive=function r(a,f){Ta(a,"directive");G(a)?(d(a),qb(f,"directiveFactory"),e.hasOwnProperty(a)||(e[a]=[],b.factory(a+"Directive",["$injector","$exceptionHandler",function(b,d){var f=[];m(e[a],function(e,h){try{var g=b.invoke(e);x(g)?g={compile:qa(g)}:
!g.compile&&g.link&&(g.compile=qa(g.link));g.priority=g.priority||0;g.index=h;g.name=g.name||a;g.require=g.require||g.controller&&g.name;g.restrict=g.restrict||"EA";var k=g,l=g,r=g.name,n={isolateScope:null,bindToController:null};B(l.scope)&&(!0===l.bindToController?(n.bindToController=c(l.scope,r,!0),n.isolateScope={}):n.isolateScope=c(l.scope,r,!1));B(l.bindToController)&&(n.bindToController=c(l.bindToController,r,!0));if(B(n.bindToController)){var S=l.controller,E=l.controllerAs;if(!S)throw fa("noctrl",
r);var ca;a:if(E&&G(E))ca=E;else{if(G(S)){var m=Uc.exec(S);if(m){ca=m[3];break a}}ca=void 0}if(!ca)throw fa("noident",r);}var s=k.$$bindings=n;B(s.isolateScope)&&(g.$$isolateBindings=s.isolateScope);g.$$moduleName=e.$$moduleName;f.push(g)}catch(w){d(w)}});return f}])),e[a].push(f)):m(a,nc(r));return this};this.aHrefSanitizationWhitelist=function(b){return A(b)?(a.aHrefSanitizationWhitelist(b),this):a.aHrefSanitizationWhitelist()};this.imgSrcSanitizationWhitelist=function(b){return A(b)?(a.imgSrcSanitizationWhitelist(b),
this):a.imgSrcSanitizationWhitelist()};var n=!0;this.debugInfoEnabled=function(a){return A(a)?(n=a,this):n};this.$get=["$injector","$interpolate","$exceptionHandler","$templateRequest","$parse","$controller","$rootScope","$document","$sce","$animate","$$sanitizeUri",function(a,b,c,d,u,q,z,N,ia,O,H){function L(a,b){try{a.addClass(b)}catch(c){}}function W(a,b,c,d,e){a instanceof C||(a=C(a));m(a,function(b,c){b.nodeType==Pa&&b.nodeValue.match(/\S+/)&&(a[c]=C(b).wrap("<span></span>").parent()[0])});var f=
S(a,b,a,c,d,e);W.$$addScopeClass(a);var h=null;return function(b,c,d){qb(b,"scope");d=d||{};var e=d.parentBoundTranscludeFn,g=d.transcludeControllers;d=d.futureParentElement;e&&e.$$boundTransclude&&(e=e.$$boundTransclude);h||(h=(d=d&&d[0])?"foreignobject"!==wa(d)&&d.toString().match(/SVG/)?"svg":"html":"html");d="html"!==h?C(Xb(h,C("<div>").append(a).html())):c?Ra.clone.call(a):a;if(g)for(var k in g)d.data("$"+k+"Controller",g[k].instance);W.$$addScopeInfo(d,b);c&&c(d,b);f&&f(b,d,d,e);return d}}function S(a,
b,c,d,e,f){function h(a,c,d,e){var f,k,l,r,n,t,O;if(q)for(O=Array(c.length),r=0;r<g.length;r+=3)f=g[r],O[f]=c[f];else O=c;r=0;for(n=g.length;r<n;)if(k=O[g[r++]],c=g[r++],f=g[r++],c){if(c.scope){if(l=a.$new(),W.$$addScopeInfo(C(k),l),t=c.$$destroyBindings)c.$$destroyBindings=null,l.$on("$destroyed",t)}else l=a;t=c.transcludeOnThisElement?ba(a,c.transclude,e):!c.templateOnThisElement&&e?e:!e&&b?ba(a,b):null;c(f,l,k,d,t,c)}else f&&f(a,k.childNodes,w,e)}for(var g=[],k,l,r,n,q,t=0;t<a.length;t++){k=new aa;
l=ca(a[t],[],k,0===t?d:w,e);(f=l.length?D(l,a[t],k,b,c,null,[],[],f):null)&&f.scope&&W.$$addScopeClass(k.$$element);k=f&&f.terminal||!(r=a[t].childNodes)||!r.length?null:S(r,f?(f.transcludeOnThisElement||!f.templateOnThisElement)&&f.transclude:b);if(f||k)g.push(t,f,k),n=!0,q=q||f;f=null}return n?h:null}function ba(a,b,c){return function(d,e,f,h,g){d||(d=a.$new(!1,g),d.$$transcluded=!0);return b(d,e,{parentBoundTranscludeFn:c,transcludeControllers:f,futureParentElement:h})}}function ca(a,b,c,d,e){var h=
c.$attr,k;switch(a.nodeType){case pa:na(b,ya(wa(a)),"E",d,e);for(var l,r,n,q=a.attributes,t=0,O=q&&q.length;t<O;t++){var K=!1,H=!1;l=q[t];k=l.name;r=T(l.value);l=ya(k);if(n=ja.test(l))k=k.replace(Vc,"").substr(8).replace(/_(.)/g,function(a,b){return b.toUpperCase()});var S=l.replace(/(Start|End)$/,"");I(S)&&l===S+"Start"&&(K=k,H=k.substr(0,k.length-5)+"end",k=k.substr(0,k.length-6));l=ya(k.toLowerCase());h[l]=k;if(n||!c.hasOwnProperty(l))c[l]=r,Qc(a,l)&&(c[l]=!0);V(a,b,r,l,n);na(b,l,"A",d,e,K,H)}a=
a.className;B(a)&&(a=a.animVal);if(G(a)&&""!==a)for(;k=g.exec(a);)l=ya(k[2]),na(b,l,"C",d,e)&&(c[l]=T(k[3])),a=a.substr(k.index+k[0].length);break;case Pa:if(11===Wa)for(;a.parentNode&&a.nextSibling&&a.nextSibling.nodeType===Pa;)a.nodeValue+=a.nextSibling.nodeValue,a.parentNode.removeChild(a.nextSibling);Ka(b,a.nodeValue);break;case 8:try{if(k=f.exec(a.nodeValue))l=ya(k[1]),na(b,l,"M",d,e)&&(c[l]=T(k[2]))}catch(E){}}b.sort(M);return b}function za(a,b,c){var d=[],e=0;if(b&&a.hasAttribute&&a.hasAttribute(b)){do{if(!a)throw fa("uterdir",
b,c);a.nodeType==pa&&(a.hasAttribute(b)&&e++,a.hasAttribute(c)&&e--);d.push(a);a=a.nextSibling}while(0<e)}else d.push(a);return C(d)}function s(a,b,c){return function(d,e,f,h,g){e=za(e[0],b,c);return a(d,e,f,h,g)}}function D(a,b,d,e,f,h,g,k,r){function n(a,b,c,d){if(a){c&&(a=s(a,c,d));a.require=D.require;a.directiveName=y;if(u===D||D.$$isolateScope)a=Z(a,{isolateScope:!0});g.push(a)}if(b){c&&(b=s(b,c,d));b.require=D.require;b.directiveName=y;if(u===D||D.$$isolateScope)b=Z(b,{isolateScope:!0});k.push(b)}}
function t(a,b,c,d){var e;if(G(b)){var f=b.match(l);b=b.substring(f[0].length);var h=f[1]||f[3],f="?"===f[2];"^^"===h?c=c.parent():e=(e=d&&d[b])&&e.instance;e||(d="$"+b+"Controller",e=h?c.inheritedData(d):c.data(d));if(!e&&!f)throw fa("ctreq",b,a);}else if(J(b))for(e=[],h=0,f=b.length;h<f;h++)e[h]=t(a,b[h],c,d);return e||null}function O(a,b,c,d,e,f){var h=ha(),g;for(g in d){var k=d[g],l={$scope:k===u||k.$$isolateScope?e:f,$element:a,$attrs:b,$transclude:c},r=k.controller;"@"==r&&(r=b[k.name]);l=q(r,
l,!0,k.controllerAs);h[k.name]=l;ia||a.data("$"+k.name+"Controller",l.instance)}return h}function K(a,c,e,f,h,l){function r(a,b,c){var d;ab(a)||(c=b,b=a,a=w);ia&&(d=ca);c||(c=ia?N.parent():N);return h(a,b,d,c,za)}var n,q,H,E,ca,z,N;b===e?(f=d,N=d.$$element):(N=C(e),f=new aa(N,d));u&&(E=c.$new(!0));h&&(z=r,z.$$boundTransclude=h);ba&&(ca=O(N,f,z,ba,E,c));u&&(W.$$addScopeInfo(N,E,!0,!(L&&(L===u||L===u.$$originalDirective))),W.$$addScopeClass(N,!0),E.$$isolateBindings=u.$$isolateBindings,Y(c,f,E,E.$$isolateBindings,
u,E));if(ca){var Va=u||S,m;Va&&ca[Va.name]&&(q=Va.$$bindings.bindToController,(H=ca[Va.name])&&H.identifier&&q&&(m=H,l.$$destroyBindings=Y(c,f,H.instance,q,Va)));for(n in ca){H=ca[n];var D=H();D!==H.instance&&(H.instance=D,N.data("$"+n+"Controller",D),H===m&&(l.$$destroyBindings(),l.$$destroyBindings=Y(c,f,D,q,Va)))}}n=0;for(l=g.length;n<l;n++)q=g[n],$(q,q.isolateScope?E:c,N,f,q.require&&t(q.directiveName,q.require,N,ca),z);var za=c;u&&(u.template||null===u.templateUrl)&&(za=E);a&&a(za,e.childNodes,
w,h);for(n=k.length-1;0<=n;n--)q=k[n],$(q,q.isolateScope?E:c,N,f,q.require&&t(q.directiveName,q.require,N,ca),z)}r=r||{};for(var H=-Number.MAX_VALUE,S=r.newScopeDirective,ba=r.controllerDirectives,u=r.newIsolateScopeDirective,L=r.templateDirective,z=r.nonTlbTranscludeDirective,N=!1,m=!1,ia=r.hasElementTranscludeDirective,v=d.$$element=C(b),D,y,M,Ka=e,na,I=0,F=a.length;I<F;I++){D=a[I];var P=D.$$start,R=D.$$end;P&&(v=za(b,P,R));M=w;if(H>D.priority)break;if(M=D.scope)D.templateUrl||(B(M)?(Q("new/isolated scope",
u||S,D,v),u=D):Q("new/isolated scope",u,D,v)),S=S||D;y=D.name;!D.templateUrl&&D.controller&&(M=D.controller,ba=ba||ha(),Q("'"+y+"' controller",ba[y],D,v),ba[y]=D);if(M=D.transclude)N=!0,D.$$tlb||(Q("transclusion",z,D,v),z=D),"element"==M?(ia=!0,H=D.priority,M=v,v=d.$$element=C(X.createComment(" "+y+": "+d[y]+" ")),b=v[0],U(f,ua.call(M,0),b),Ka=W(M,e,H,h&&h.name,{nonTlbTranscludeDirective:z})):(M=C(Ub(b)).contents(),v.empty(),Ka=W(M,e));if(D.template)if(m=!0,Q("template",L,D,v),L=D,M=x(D.template)?
D.template(v,d):D.template,M=ga(M),D.replace){h=D;M=Sb.test(M)?Wc(Xb(D.templateNamespace,T(M))):[];b=M[0];if(1!=M.length||b.nodeType!==pa)throw fa("tplrt",y,"");U(f,v,b);F={$attr:{}};M=ca(b,[],F);var If=a.splice(I+1,a.length-(I+1));u&&A(M);a=a.concat(M).concat(If);Xc(d,F);F=a.length}else v.html(M);if(D.templateUrl)m=!0,Q("template",L,D,v),L=D,D.replace&&(h=D),K=Jf(a.splice(I,a.length-I),v,d,f,N&&Ka,g,k,{controllerDirectives:ba,newScopeDirective:S!==D&&S,newIsolateScopeDirective:u,templateDirective:L,
nonTlbTranscludeDirective:z}),F=a.length;else if(D.compile)try{na=D.compile(v,d,Ka),x(na)?n(null,na,P,R):na&&n(na.pre,na.post,P,R)}catch(V){c(V,xa(v))}D.terminal&&(K.terminal=!0,H=Math.max(H,D.priority))}K.scope=S&&!0===S.scope;K.transcludeOnThisElement=N;K.templateOnThisElement=m;K.transclude=Ka;r.hasElementTranscludeDirective=ia;return K}function A(a){for(var b=0,c=a.length;b<c;b++)a[b]=Nb(a[b],{$$isolateScope:!0})}function na(b,d,f,h,g,k,l){if(d===g)return null;g=null;if(e.hasOwnProperty(d)){var n;
d=a.get(d+"Directive");for(var q=0,t=d.length;q<t;q++)try{n=d[q],(v(h)||h>n.priority)&&-1!=n.restrict.indexOf(f)&&(k&&(n=Nb(n,{$$start:k,$$end:l})),b.push(n),g=n)}catch(H){c(H)}}return g}function I(b){if(e.hasOwnProperty(b))for(var c=a.get(b+"Directive"),d=0,f=c.length;d<f;d++)if(b=c[d],b.multiElement)return!0;return!1}function Xc(a,b){var c=b.$attr,d=a.$attr,e=a.$$element;m(a,function(d,e){"$"!=e.charAt(0)&&(b[e]&&b[e]!==d&&(d+=("style"===e?";":" ")+b[e]),a.$set(e,d,!0,c[e]))});m(b,function(b,f){"class"==
f?(L(e,b),a["class"]=(a["class"]?a["class"]+" ":"")+b):"style"==f?(e.attr("style",e.attr("style")+";"+b),a.style=(a.style?a.style+";":"")+b):"$"==f.charAt(0)||a.hasOwnProperty(f)||(a[f]=b,d[f]=c[f])})}function Jf(a,b,c,e,f,h,g,k){var l=[],r,n,q=b[0],t=a.shift(),H=Nb(t,{templateUrl:null,transclude:null,replace:null,$$originalDirective:t}),O=x(t.templateUrl)?t.templateUrl(b,c):t.templateUrl,E=t.templateNamespace;b.empty();d(O).then(function(d){var K,u;d=ga(d);if(t.replace){d=Sb.test(d)?Wc(Xb(E,T(d))):
[];K=d[0];if(1!=d.length||K.nodeType!==pa)throw fa("tplrt",t.name,O);d={$attr:{}};U(e,b,K);var z=ca(K,[],d);B(t.scope)&&A(z);a=z.concat(a);Xc(c,d)}else K=q,b.html(d);a.unshift(H);r=D(a,K,c,f,b,t,h,g,k);m(e,function(a,c){a==K&&(e[c]=b[0])});for(n=S(b[0].childNodes,f);l.length;){d=l.shift();u=l.shift();var N=l.shift(),W=l.shift(),z=b[0];if(!d.$$destroyed){if(u!==q){var za=u.className;k.hasElementTranscludeDirective&&t.replace||(z=Ub(K));U(N,C(u),z);L(C(z),za)}u=r.transcludeOnThisElement?ba(d,r.transclude,
W):W;r(n,d,z,e,u,r)}}l=null});return function(a,b,c,d,e){a=e;b.$$destroyed||(l?l.push(b,c,d,a):(r.transcludeOnThisElement&&(a=ba(b,r.transclude,e)),r(n,b,c,d,a,r)))}}function M(a,b){var c=b.priority-a.priority;return 0!==c?c:a.name!==b.name?a.name<b.name?-1:1:a.index-b.index}function Q(a,b,c,d){function e(a){return a?" (module: "+a+")":""}if(b)throw fa("multidir",b.name,e(b.$$moduleName),c.name,e(c.$$moduleName),a,xa(d));}function Ka(a,c){var d=b(c,!0);d&&a.push({priority:0,compile:function(a){a=
a.parent();var b=!!a.length;b&&W.$$addBindingClass(a);return function(a,c){var e=c.parent();b||W.$$addBindingClass(e);W.$$addBindingInfo(e,d.expressions);a.$watch(d,function(a){c[0].nodeValue=a})}}})}function Xb(a,b){a=F(a||"html");switch(a){case "svg":case "math":var c=X.createElement("div");c.innerHTML="<"+a+">"+b+"</"+a+">";return c.childNodes[0].childNodes;default:return b}}function R(a,b){if("srcdoc"==b)return ia.HTML;var c=wa(a);if("xlinkHref"==b||"form"==c&&"action"==b||"img"!=c&&("src"==b||
"ngSrc"==b))return ia.RESOURCE_URL}function V(a,c,d,e,f){var g=R(a,e);f=h[e]||f;var l=b(d,!0,g,f);if(l){if("multiple"===e&&"select"===wa(a))throw fa("selmulti",xa(a));c.push({priority:100,compile:function(){return{pre:function(a,c,h){c=h.$$observers||(h.$$observers={});if(k.test(e))throw fa("nodomevents");var r=h[e];r!==d&&(l=r&&b(r,!0,g,f),d=r);l&&(h[e]=l(a),(c[e]||(c[e]=[])).$$inter=!0,(h.$$observers&&h.$$observers[e].$$scope||a).$watch(l,function(a,b){"class"===e&&a!=b?h.$updateClass(a,b):h.$set(e,
a)}))}}}})}}function U(a,b,c){var d=b[0],e=b.length,f=d.parentNode,h,g;if(a)for(h=0,g=a.length;h<g;h++)if(a[h]==d){a[h++]=c;g=h+e-1;for(var k=a.length;h<k;h++,g++)g<k?a[h]=a[g]:delete a[h];a.length-=e-1;a.context===d&&(a.context=c);break}f&&f.replaceChild(c,d);a=X.createDocumentFragment();a.appendChild(d);C.hasData(d)&&(C(c).data(C(d).data()),ra?(Qb=!0,ra.cleanData([d])):delete C.cache[d[C.expando]]);d=1;for(e=b.length;d<e;d++)f=b[d],C(f).remove(),a.appendChild(f),delete b[d];b[0]=c;b.length=1}function Z(a,
b){return P(function(){return a.apply(null,arguments)},a,b)}function $(a,b,d,e,f,h){try{a(b,d,e,f,h)}catch(g){c(g,xa(d))}}function Y(a,c,d,e,f,h){var g;m(e,function(e,h){var k=e.attrName,l=e.optional,r,n,q,K;switch(e.mode){case "@":l||ta.call(c,k)||(d[h]=c[k]=void 0);c.$observe(k,function(a){G(a)&&(d[h]=a)});c.$$observers[k].$$scope=a;G(c[k])&&(d[h]=b(c[k])(a));break;case "=":if(!ta.call(c,k)){if(l)break;c[k]=void 0}if(l&&!c[k])break;n=u(c[k]);K=n.literal?ka:function(a,b){return a===b||a!==a&&b!==
b};q=n.assign||function(){r=d[h]=n(a);throw fa("nonassign",c[k],f.name);};r=d[h]=n(a);l=function(b){K(b,d[h])||(K(b,r)?q(a,b=d[h]):d[h]=b);return r=b};l.$stateful=!0;l=e.collection?a.$watchCollection(c[k],l):a.$watch(u(c[k],l),null,n.literal);g=g||[];g.push(l);break;case "&":n=c.hasOwnProperty(k)?u(c[k]):y;if(n===y&&l)break;d[h]=function(b){return n(a,b)}}});e=g?function(){for(var a=0,b=g.length;a<b;++a)g[a]()}:y;return h&&e!==y?(h.$on("$destroy",e),y):e}var aa=function(a,b){if(b){var c=Object.keys(b),
d,e,f;d=0;for(e=c.length;d<e;d++)f=c[d],this[f]=b[f]}else this.$attr={};this.$$element=a};aa.prototype={$normalize:ya,$addClass:function(a){a&&0<a.length&&O.addClass(this.$$element,a)},$removeClass:function(a){a&&0<a.length&&O.removeClass(this.$$element,a)},$updateClass:function(a,b){var c=Yc(a,b);c&&c.length&&O.addClass(this.$$element,c);(c=Yc(b,a))&&c.length&&O.removeClass(this.$$element,c)},$set:function(a,b,d,e){var f=Qc(this.$$element[0],a),h=Zc[a],g=a;f?(this.$$element.prop(a,b),e=f):h&&(this[h]=
b,g=h);this[a]=b;e?this.$attr[a]=e:(e=this.$attr[a])||(this.$attr[a]=e=zc(a,"-"));f=wa(this.$$element);if("a"===f&&"href"===a||"img"===f&&"src"===a)this[a]=b=H(b,"src"===a);else if("img"===f&&"srcset"===a){for(var f="",h=T(b),k=/(\s+\d+x\s*,|\s+\d+w\s*,|\s+,|,\s+)/,k=/\s/.test(h)?k:/(,)/,h=h.split(k),k=Math.floor(h.length/2),l=0;l<k;l++)var r=2*l,f=f+H(T(h[r]),!0),f=f+(" "+T(h[r+1]));h=T(h[2*l]).split(/\s/);f+=H(T(h[0]),!0);2===h.length&&(f+=" "+T(h[1]));this[a]=b=f}!1!==d&&(null===b||v(b)?this.$$element.removeAttr(e):
this.$$element.attr(e,b));(a=this.$$observers)&&m(a[g],function(a){try{a(b)}catch(d){c(d)}})},$observe:function(a,b){var c=this,d=c.$$observers||(c.$$observers=ha()),e=d[a]||(d[a]=[]);e.push(b);z.$evalAsync(function(){e.$$inter||!c.hasOwnProperty(a)||v(c[a])||b(c[a])});return function(){cb(e,b)}}};var da=b.startSymbol(),ea=b.endSymbol(),ga="{{"==da||"}}"==ea?$a:function(a){return a.replace(/\{\{/g,da).replace(/}}/g,ea)},ja=/^ngAttr[A-Z]/;W.$$addBindingInfo=n?function(a,b){var c=a.data("$binding")||
[];J(b)?c=c.concat(b):c.push(b);a.data("$binding",c)}:y;W.$$addBindingClass=n?function(a){L(a,"ng-binding")}:y;W.$$addScopeInfo=n?function(a,b,c,d){a.data(c?d?"$isolateScopeNoTemplate":"$isolateScope":"$scope",b)}:y;W.$$addScopeClass=n?function(a,b){L(a,b?"ng-isolate-scope":"ng-scope")}:y;return W}]}function ya(b){return gb(b.replace(Vc,""))}function Yc(b,a){var c="",d=b.split(/\s+/),e=a.split(/\s+/),f=0;a:for(;f<d.length;f++){for(var g=d[f],h=0;h<e.length;h++)if(g==e[h])continue a;c+=(0<c.length?
" ":"")+g}return c}function Wc(b){b=C(b);var a=b.length;if(1>=a)return b;for(;a--;)8===b[a].nodeType&&Kf.call(b,a,1);return b}function Ve(){var b={},a=!1;this.register=function(a,d){Ta(a,"controller");B(a)?P(b,a):b[a]=d};this.allowGlobals=function(){a=!0};this.$get=["$injector","$window",function(c,d){function e(a,b,c,d){if(!a||!B(a.$scope))throw I("$controller")("noscp",d,b);a.$scope[b]=c}return function(f,g,h,l){var k,n,p;h=!0===h;l&&G(l)&&(p=l);if(G(f)){l=f.match(Uc);if(!l)throw Lf("ctrlfmt",f);
n=l[1];p=p||l[3];f=b.hasOwnProperty(n)?b[n]:Bc(g.$scope,n,!0)||(a?Bc(d,n,!0):w);Sa(f,n,!0)}if(h)return h=(J(f)?f[f.length-1]:f).prototype,k=Object.create(h||null),p&&e(g,p,k,n||f.name),P(function(){var a=c.invoke(f,k,g,n);a!==k&&(B(a)||x(a))&&(k=a,p&&e(g,p,k,n||f.name));return k},{instance:k,identifier:p});k=c.instantiate(f,g,n);p&&e(g,p,k,n||f.name);return k}}]}function We(){this.$get=["$window",function(b){return C(b.document)}]}function Xe(){this.$get=["$log",function(b){return function(a,c){b.error.apply(b,
arguments)}}]}function Yb(b){return B(b)?da(b)?b.toISOString():eb(b):b}function bf(){this.$get=function(){return function(b){if(!b)return"";var a=[];mc(b,function(b,d){null===b||v(b)||(J(b)?m(b,function(b,c){a.push(la(d)+"="+la(Yb(b)))}):a.push(la(d)+"="+la(Yb(b))))});return a.join("&")}}}function cf(){this.$get=function(){return function(b){function a(b,e,f){null===b||v(b)||(J(b)?m(b,function(b,c){a(b,e+"["+(B(b)?c:"")+"]")}):B(b)&&!da(b)?mc(b,function(b,c){a(b,e+(f?"":"[")+c+(f?"":"]"))}):c.push(la(e)+
"="+la(Yb(b))))}if(!b)return"";var c=[];a(b,"",!0);return c.join("&")}}}function Zb(b,a){if(G(b)){var c=b.replace(Mf,"").trim();if(c){var d=a("Content-Type");(d=d&&0===d.indexOf($c))||(d=(d=c.match(Nf))&&Of[d[0]].test(c));d&&(b=uc(c))}}return b}function ad(b){var a=ha(),c;G(b)?m(b.split("\n"),function(b){c=b.indexOf(":");var e=F(T(b.substr(0,c)));b=T(b.substr(c+1));e&&(a[e]=a[e]?a[e]+", "+b:b)}):B(b)&&m(b,function(b,c){var f=F(c),g=T(b);f&&(a[f]=a[f]?a[f]+", "+g:g)});return a}function bd(b){var a;
return function(c){a||(a=ad(b));return c?(c=a[F(c)],void 0===c&&(c=null),c):a}}function cd(b,a,c,d){if(x(d))return d(b,a,c);m(d,function(d){b=d(b,a,c)});return b}function af(){var b=this.defaults={transformResponse:[Zb],transformRequest:[function(a){return B(a)&&"[object File]"!==va.call(a)&&"[object Blob]"!==va.call(a)&&"[object FormData]"!==va.call(a)?eb(a):a}],headers:{common:{Accept:"application/json, text/plain, */*"},post:ja($b),put:ja($b),patch:ja($b)},xsrfCookieName:"XSRF-TOKEN",xsrfHeaderName:"X-XSRF-TOKEN",
paramSerializer:"$httpParamSerializer"},a=!1;this.useApplyAsync=function(b){return A(b)?(a=!!b,this):a};var c=!0;this.useLegacyPromiseExtensions=function(a){return A(a)?(c=!!a,this):c};var d=this.interceptors=[];this.$get=["$httpBackend","$$cookieReader","$cacheFactory","$rootScope","$q","$injector",function(e,f,g,h,l,k){function n(a){function d(a){var b=P({},a);b.data=a.data?cd(a.data,a.headers,a.status,f.transformResponse):a.data;a=a.status;return 200<=a&&300>a?b:l.reject(b)}function e(a,b){var c,
d={};m(a,function(a,e){x(a)?(c=a(b),null!=c&&(d[e]=c)):d[e]=a});return d}if(!aa.isObject(a))throw I("$http")("badreq",a);var f=P({method:"get",transformRequest:b.transformRequest,transformResponse:b.transformResponse,paramSerializer:b.paramSerializer},a);f.headers=function(a){var c=b.headers,d=P({},a.headers),f,h,g,c=P({},c.common,c[F(a.method)]);a:for(f in c){h=F(f);for(g in d)if(F(g)===h)continue a;d[f]=c[f]}return e(d,ja(a))}(a);f.method=sb(f.method);f.paramSerializer=G(f.paramSerializer)?k.get(f.paramSerializer):
f.paramSerializer;var h=[function(a){var c=a.headers,e=cd(a.data,bd(c),w,a.transformRequest);v(e)&&m(c,function(a,b){"content-type"===F(b)&&delete c[b]});v(a.withCredentials)&&!v(b.withCredentials)&&(a.withCredentials=b.withCredentials);return p(a,e).then(d,d)},w],g=l.when(f);for(m(E,function(a){(a.request||a.requestError)&&h.unshift(a.request,a.requestError);(a.response||a.responseError)&&h.push(a.response,a.responseError)});h.length;){a=h.shift();var r=h.shift(),g=g.then(a,r)}c?(g.success=function(a){Sa(a,
"fn");g.then(function(b){a(b.data,b.status,b.headers,f)});return g},g.error=function(a){Sa(a,"fn");g.then(null,function(b){a(b.data,b.status,b.headers,f)});return g}):(g.success=dd("success"),g.error=dd("error"));return g}function p(c,d){function g(b,c,d,e){function f(){k(c,b,d,e)}L&&(200<=b&&300>b?L.put(ba,[b,c,ad(d),e]):L.remove(ba));a?h.$applyAsync(f):(f(),h.$$phase||h.$apply())}function k(a,b,d,e){b=-1<=b?b:0;(200<=b&&300>b?O.resolve:O.reject)({data:a,status:b,headers:bd(d),config:c,statusText:e})}
function p(a){k(a.data,a.status,ja(a.headers()),a.statusText)}function E(){var a=n.pendingRequests.indexOf(c);-1!==a&&n.pendingRequests.splice(a,1)}var O=l.defer(),H=O.promise,L,m,S=c.headers,ba=r(c.url,c.paramSerializer(c.params));n.pendingRequests.push(c);H.then(E,E);!c.cache&&!b.cache||!1===c.cache||"GET"!==c.method&&"JSONP"!==c.method||(L=B(c.cache)?c.cache:B(b.cache)?b.cache:t);L&&(m=L.get(ba),A(m)?m&&x(m.then)?m.then(p,p):J(m)?k(m[1],m[0],ja(m[2]),m[3]):k(m,200,{},"OK"):L.put(ba,H));v(m)&&((m=
ed(c.url)?f()[c.xsrfCookieName||b.xsrfCookieName]:w)&&(S[c.xsrfHeaderName||b.xsrfHeaderName]=m),e(c.method,ba,d,g,S,c.timeout,c.withCredentials,c.responseType));return H}function r(a,b){0<b.length&&(a+=(-1==a.indexOf("?")?"?":"&")+b);return a}var t=g("$http");b.paramSerializer=G(b.paramSerializer)?k.get(b.paramSerializer):b.paramSerializer;var E=[];m(d,function(a){E.unshift(G(a)?k.get(a):k.invoke(a))});n.pendingRequests=[];(function(a){m(arguments,function(a){n[a]=function(b,c){return n(P({},c||{},
{method:a,url:b}))}})})("get","delete","head","jsonp");(function(a){m(arguments,function(a){n[a]=function(b,c,d){return n(P({},d||{},{method:a,url:b,data:c}))}})})("post","put","patch");n.defaults=b;return n}]}function Pf(){return new Q.XMLHttpRequest}function df(){this.$get=["$browser","$window","$document",function(b,a,c){return Qf(b,Pf,b.defer,a.angular.callbacks,c[0])}]}function Qf(b,a,c,d,e){function f(a,b,c){var f=e.createElement("script"),n=null;f.type="text/javascript";f.src=a;f.async=!0;
n=function(a){f.removeEventListener("load",n,!1);f.removeEventListener("error",n,!1);e.body.removeChild(f);f=null;var g=-1,t="unknown";a&&("load"!==a.type||d[b].called||(a={type:"error"}),t=a.type,g="error"===a.type?404:200);c&&c(g,t)};f.addEventListener("load",n,!1);f.addEventListener("error",n,!1);e.body.appendChild(f);return n}return function(e,h,l,k,n,p,r,t){function E(){q&&q();z&&z.abort()}function K(a,d,e,f,h){A(s)&&c.cancel(s);q=z=null;a(d,e,f,h);b.$$completeOutstandingRequest(y)}b.$$incOutstandingRequestCount();
h=h||b.url();if("jsonp"==F(e)){var u="_"+(d.counter++).toString(36);d[u]=function(a){d[u].data=a;d[u].called=!0};var q=f(h.replace("JSON_CALLBACK","angular.callbacks."+u),u,function(a,b){K(k,a,d[u].data,"",b);d[u]=y})}else{var z=a();z.open(e,h,!0);m(n,function(a,b){A(a)&&z.setRequestHeader(b,a)});z.onload=function(){var a=z.statusText||"",b="response"in z?z.response:z.responseText,c=1223===z.status?204:z.status;0===c&&(c=b?200:"file"==Aa(h).protocol?404:0);K(k,c,b,z.getAllResponseHeaders(),a)};e=
function(){K(k,-1,null,null,"")};z.onerror=e;z.onabort=e;r&&(z.withCredentials=!0);if(t)try{z.responseType=t}catch(N){if("json"!==t)throw N;}z.send(v(l)?null:l)}if(0<p)var s=c(E,p);else p&&x(p.then)&&p.then(E)}}function Ze(){var b="{{",a="}}";this.startSymbol=function(a){return a?(b=a,this):b};this.endSymbol=function(b){return b?(a=b,this):a};this.$get=["$parse","$exceptionHandler","$sce",function(c,d,e){function f(a){return"\\\\\\"+a}function g(c){return c.replace(n,b).replace(p,a)}function h(f,
h,n,p){function u(a){try{var b=a;a=n?e.getTrusted(n,b):e.valueOf(b);var c;if(p&&!A(a))c=a;else if(null==a)c="";else{switch(typeof a){case "string":break;case "number":a=""+a;break;default:a=eb(a)}c=a}return c}catch(h){d(La.interr(f,h))}}p=!!p;for(var q,m,N=0,s=[],O=[],H=f.length,L=[],W=[];N<H;)if(-1!=(q=f.indexOf(b,N))&&-1!=(m=f.indexOf(a,q+l)))N!==q&&L.push(g(f.substring(N,q))),N=f.substring(q+l,m),s.push(N),O.push(c(N,u)),N=m+k,W.push(L.length),L.push("");else{N!==H&&L.push(g(f.substring(N)));break}n&&
1<L.length&&La.throwNoconcat(f);if(!h||s.length){var S=function(a){for(var b=0,c=s.length;b<c;b++){if(p&&v(a[b]))return;L[W[b]]=a[b]}return L.join("")};return P(function(a){var b=0,c=s.length,e=Array(c);try{for(;b<c;b++)e[b]=O[b](a);return S(e)}catch(h){d(La.interr(f,h))}},{exp:f,expressions:s,$$watchDelegate:function(a,b){var c;return a.$watchGroup(O,function(d,e){var f=S(d);x(b)&&b.call(this,f,d!==e?c:f,a);c=f})}})}}var l=b.length,k=a.length,n=new RegExp(b.replace(/./g,f),"g"),p=new RegExp(a.replace(/./g,
f),"g");h.startSymbol=function(){return b};h.endSymbol=function(){return a};return h}]}function $e(){this.$get=["$rootScope","$window","$q","$$q",function(b,a,c,d){function e(e,h,l,k){var n=4<arguments.length,p=n?ua.call(arguments,4):[],r=a.setInterval,t=a.clearInterval,E=0,K=A(k)&&!k,u=(K?d:c).defer(),q=u.promise;l=A(l)?l:0;q.then(null,null,n?function(){e.apply(null,p)}:e);q.$$intervalId=r(function(){u.notify(E++);0<l&&E>=l&&(u.resolve(E),t(q.$$intervalId),delete f[q.$$intervalId]);K||b.$apply()},
h);f[q.$$intervalId]=u;return q}var f={};e.cancel=function(b){return b&&b.$$intervalId in f?(f[b.$$intervalId].reject("canceled"),a.clearInterval(b.$$intervalId),delete f[b.$$intervalId],!0):!1};return e}]}function ac(b){b=b.split("/");for(var a=b.length;a--;)b[a]=ob(b[a]);return b.join("/")}function fd(b,a){var c=Aa(b);a.$$protocol=c.protocol;a.$$host=c.hostname;a.$$port=Y(c.port)||Rf[c.protocol]||null}function gd(b,a){var c="/"!==b.charAt(0);c&&(b="/"+b);var d=Aa(b);a.$$path=decodeURIComponent(c&&
"/"===d.pathname.charAt(0)?d.pathname.substring(1):d.pathname);a.$$search=xc(d.search);a.$$hash=decodeURIComponent(d.hash);a.$$path&&"/"!=a.$$path.charAt(0)&&(a.$$path="/"+a.$$path)}function sa(b,a){if(0===a.indexOf(b))return a.substr(b.length)}function Ja(b){var a=b.indexOf("#");return-1==a?b:b.substr(0,a)}function Cb(b){return b.replace(/(#.+)|#$/,"$1")}function bc(b,a,c){this.$$html5=!0;c=c||"";fd(b,this);this.$$parse=function(b){var c=sa(a,b);if(!G(c))throw Db("ipthprfx",b,a);gd(c,this);this.$$path||
(this.$$path="/");this.$$compose()};this.$$compose=function(){var b=Pb(this.$$search),c=this.$$hash?"#"+ob(this.$$hash):"";this.$$url=ac(this.$$path)+(b?"?"+b:"")+c;this.$$absUrl=a+this.$$url.substr(1)};this.$$parseLinkUrl=function(d,e){if(e&&"#"===e[0])return this.hash(e.slice(1)),!0;var f,g;A(f=sa(b,d))?(g=f,g=A(f=sa(c,f))?a+(sa("/",f)||f):b+g):A(f=sa(a,d))?g=a+f:a==d+"/"&&(g=a);g&&this.$$parse(g);return!!g}}function cc(b,a,c){fd(b,this);this.$$parse=function(d){var e=sa(b,d)||sa(a,d),f;v(e)||"#"!==
e.charAt(0)?this.$$html5?f=e:(f="",v(e)&&(b=d,this.replace())):(f=sa(c,e),v(f)&&(f=e));gd(f,this);d=this.$$path;var e=b,g=/^\/[A-Z]:(\/.*)/;0===f.indexOf(e)&&(f=f.replace(e,""));g.exec(f)||(d=(f=g.exec(d))?f[1]:d);this.$$path=d;this.$$compose()};this.$$compose=function(){var a=Pb(this.$$search),e=this.$$hash?"#"+ob(this.$$hash):"";this.$$url=ac(this.$$path)+(a?"?"+a:"")+e;this.$$absUrl=b+(this.$$url?c+this.$$url:"")};this.$$parseLinkUrl=function(a,c){return Ja(b)==Ja(a)?(this.$$parse(a),!0):!1}}function hd(b,
a,c){this.$$html5=!0;cc.apply(this,arguments);this.$$parseLinkUrl=function(d,e){if(e&&"#"===e[0])return this.hash(e.slice(1)),!0;var f,g;b==Ja(d)?f=d:(g=sa(a,d))?f=b+c+g:a===d+"/"&&(f=a);f&&this.$$parse(f);return!!f};this.$$compose=function(){var a=Pb(this.$$search),e=this.$$hash?"#"+ob(this.$$hash):"";this.$$url=ac(this.$$path)+(a?"?"+a:"")+e;this.$$absUrl=b+c+this.$$url}}function Eb(b){return function(){return this[b]}}function id(b,a){return function(c){if(v(c))return this[b];this[b]=a(c);this.$$compose();
return this}}function ef(){var b="",a={enabled:!1,requireBase:!0,rewriteLinks:!0};this.hashPrefix=function(a){return A(a)?(b=a,this):b};this.html5Mode=function(b){return bb(b)?(a.enabled=b,this):B(b)?(bb(b.enabled)&&(a.enabled=b.enabled),bb(b.requireBase)&&(a.requireBase=b.requireBase),bb(b.rewriteLinks)&&(a.rewriteLinks=b.rewriteLinks),this):a};this.$get=["$rootScope","$browser","$sniffer","$rootElement","$window",function(c,d,e,f,g){function h(a,b,c){var e=k.url(),f=k.$$state;try{d.url(a,b,c),k.$$state=
d.state()}catch(h){throw k.url(e),k.$$state=f,h;}}function l(a,b){c.$broadcast("$locationChangeSuccess",k.absUrl(),a,k.$$state,b)}var k,n;n=d.baseHref();var p=d.url(),r;if(a.enabled){if(!n&&a.requireBase)throw Db("nobase");r=p.substring(0,p.indexOf("/",p.indexOf("//")+2))+(n||"/");n=e.history?bc:hd}else r=Ja(p),n=cc;var t=r.substr(0,Ja(r).lastIndexOf("/")+1);k=new n(r,t,"#"+b);k.$$parseLinkUrl(p,p);k.$$state=d.state();var E=/^\s*(javascript|mailto):/i;f.on("click",function(b){if(a.rewriteLinks&&!b.ctrlKey&&
!b.metaKey&&!b.shiftKey&&2!=b.which&&2!=b.button){for(var e=C(b.target);"a"!==wa(e[0]);)if(e[0]===f[0]||!(e=e.parent())[0])return;var h=e.prop("href"),l=e.attr("href")||e.attr("xlink:href");B(h)&&"[object SVGAnimatedString]"===h.toString()&&(h=Aa(h.animVal).href);E.test(h)||!h||e.attr("target")||b.isDefaultPrevented()||!k.$$parseLinkUrl(h,l)||(b.preventDefault(),k.absUrl()!=d.url()&&(c.$apply(),g.angular["ff-684208-preventDefault"]=!0))}});Cb(k.absUrl())!=Cb(p)&&d.url(k.absUrl(),!0);var K=!0;d.onUrlChange(function(a,
b){v(sa(t,a))?g.location.href=a:(c.$evalAsync(function(){var d=k.absUrl(),e=k.$$state,f;k.$$parse(a);k.$$state=b;f=c.$broadcast("$locationChangeStart",a,d,b,e).defaultPrevented;k.absUrl()===a&&(f?(k.$$parse(d),k.$$state=e,h(d,!1,e)):(K=!1,l(d,e)))}),c.$$phase||c.$digest())});c.$watch(function(){var a=Cb(d.url()),b=Cb(k.absUrl()),f=d.state(),g=k.$$replace,r=a!==b||k.$$html5&&e.history&&f!==k.$$state;if(K||r)K=!1,c.$evalAsync(function(){var b=k.absUrl(),d=c.$broadcast("$locationChangeStart",b,a,k.$$state,
f).defaultPrevented;k.absUrl()===b&&(d?(k.$$parse(a),k.$$state=f):(r&&h(b,g,f===k.$$state?null:k.$$state),l(a,f)))});k.$$replace=!1});return k}]}function ff(){var b=!0,a=this;this.debugEnabled=function(a){return A(a)?(b=a,this):b};this.$get=["$window",function(c){function d(a){a instanceof Error&&(a.stack?a=a.message&&-1===a.stack.indexOf(a.message)?"Error: "+a.message+"\n"+a.stack:a.stack:a.sourceURL&&(a=a.message+"\n"+a.sourceURL+":"+a.line));return a}function e(a){var b=c.console||{},e=b[a]||b.log||
y;a=!1;try{a=!!e.apply}catch(l){}return a?function(){var a=[];m(arguments,function(b){a.push(d(b))});return e.apply(b,a)}:function(a,b){e(a,null==b?"":b)}}return{log:e("log"),info:e("info"),warn:e("warn"),error:e("error"),debug:function(){var c=e("debug");return function(){b&&c.apply(a,arguments)}}()}}]}function Xa(b,a){b=B(b)&&b.toString?b.toString():b;if("__defineGetter__"===b||"__defineSetter__"===b||"__lookupGetter__"===b||"__lookupSetter__"===b||"__proto__"===b)throw ea("isecfld",a);return b}
function Ba(b,a){if(b){if(b.constructor===b)throw ea("isecfn",a);if(b.window===b)throw ea("isecwindow",a);if(b.children&&(b.nodeName||b.prop&&b.attr&&b.find))throw ea("isecdom",a);if(b===Object)throw ea("isecobj",a);}return b}function jd(b,a){if(b){if(b.constructor===b)throw ea("isecfn",a);if(b===Sf||b===Tf||b===Uf)throw ea("isecff",a);}}function Vf(b,a){return"undefined"!==typeof b?b:a}function kd(b,a){return"undefined"===typeof b?a:"undefined"===typeof a?b:b+a}function U(b,a){var c,d;switch(b.type){case s.Program:c=
!0;m(b.body,function(b){U(b.expression,a);c=c&&b.expression.constant});b.constant=c;break;case s.Literal:b.constant=!0;b.toWatch=[];break;case s.UnaryExpression:U(b.argument,a);b.constant=b.argument.constant;b.toWatch=b.argument.toWatch;break;case s.BinaryExpression:U(b.left,a);U(b.right,a);b.constant=b.left.constant&&b.right.constant;b.toWatch=b.left.toWatch.concat(b.right.toWatch);break;case s.LogicalExpression:U(b.left,a);U(b.right,a);b.constant=b.left.constant&&b.right.constant;b.toWatch=b.constant?
[]:[b];break;case s.ConditionalExpression:U(b.test,a);U(b.alternate,a);U(b.consequent,a);b.constant=b.test.constant&&b.alternate.constant&&b.consequent.constant;b.toWatch=b.constant?[]:[b];break;case s.Identifier:b.constant=!1;b.toWatch=[b];break;case s.MemberExpression:U(b.object,a);b.computed&&U(b.property,a);b.constant=b.object.constant&&(!b.computed||b.property.constant);b.toWatch=[b];break;case s.CallExpression:c=b.filter?!a(b.callee.name).$stateful:!1;d=[];m(b.arguments,function(b){U(b,a);c=
c&&b.constant;b.constant||d.push.apply(d,b.toWatch)});b.constant=c;b.toWatch=b.filter&&!a(b.callee.name).$stateful?d:[b];break;case s.AssignmentExpression:U(b.left,a);U(b.right,a);b.constant=b.left.constant&&b.right.constant;b.toWatch=[b];break;case s.ArrayExpression:c=!0;d=[];m(b.elements,function(b){U(b,a);c=c&&b.constant;b.constant||d.push.apply(d,b.toWatch)});b.constant=c;b.toWatch=d;break;case s.ObjectExpression:c=!0;d=[];m(b.properties,function(b){U(b.value,a);c=c&&b.value.constant;b.value.constant||
d.push.apply(d,b.value.toWatch)});b.constant=c;b.toWatch=d;break;case s.ThisExpression:b.constant=!1,b.toWatch=[]}}function ld(b){if(1==b.length){b=b[0].expression;var a=b.toWatch;return 1!==a.length?a:a[0]!==b?a:w}}function md(b){return b.type===s.Identifier||b.type===s.MemberExpression}function nd(b){if(1===b.body.length&&md(b.body[0].expression))return{type:s.AssignmentExpression,left:b.body[0].expression,right:{type:s.NGValueParameter},operator:"="}}function od(b){return 0===b.body.length||1===
b.body.length&&(b.body[0].expression.type===s.Literal||b.body[0].expression.type===s.ArrayExpression||b.body[0].expression.type===s.ObjectExpression)}function pd(b,a){this.astBuilder=b;this.$filter=a}function qd(b,a){this.astBuilder=b;this.$filter=a}function Fb(b){return"constructor"==b}function dc(b){return x(b.valueOf)?b.valueOf():Wf.call(b)}function gf(){var b=ha(),a=ha();this.$get=["$filter",function(c){function d(a,b){return null==a||null==b?a===b:"object"===typeof a&&(a=dc(a),"object"===typeof a)?
!1:a===b||a!==a&&b!==b}function e(a,b,c,e,f){var h=e.inputs,g;if(1===h.length){var k=d,h=h[0];return a.$watch(function(a){var b=h(a);d(b,k)||(g=e(a,w,w,[b]),k=b&&dc(b));return g},b,c,f)}for(var l=[],n=[],p=0,m=h.length;p<m;p++)l[p]=d,n[p]=null;return a.$watch(function(a){for(var b=!1,c=0,f=h.length;c<f;c++){var k=h[c](a);if(b||(b=!d(k,l[c])))n[c]=k,l[c]=k&&dc(k)}b&&(g=e(a,w,w,n));return g},b,c,f)}function f(a,b,c,d){var e,f;return e=a.$watch(function(a){return d(a)},function(a,c,d){f=a;x(b)&&b.apply(this,
arguments);A(a)&&d.$$postDigest(function(){A(f)&&e()})},c)}function g(a,b,c,d){function e(a){var b=!0;m(a,function(a){A(a)||(b=!1)});return b}var f,h;return f=a.$watch(function(a){return d(a)},function(a,c,d){h=a;x(b)&&b.call(this,a,c,d);e(a)&&d.$$postDigest(function(){e(h)&&f()})},c)}function h(a,b,c,d){var e;return e=a.$watch(function(a){return d(a)},function(a,c,d){x(b)&&b.apply(this,arguments);e()},c)}function l(a,b){if(!b)return a;var c=a.$$watchDelegate,c=c!==g&&c!==f?function(c,d,e,f){e=a(c,
d,e,f);return b(e,c,d)}:function(c,d,e,f){e=a(c,d,e,f);c=b(e,c,d);return A(e)?c:e};a.$$watchDelegate&&a.$$watchDelegate!==e?c.$$watchDelegate=a.$$watchDelegate:b.$stateful||(c.$$watchDelegate=e,c.inputs=a.inputs?a.inputs:[a]);return c}var k=Fa().noUnsafeEval,n={csp:k,expensiveChecks:!1},p={csp:k,expensiveChecks:!0};return function(d,k,E){var m,u,q;switch(typeof d){case "string":q=d=d.trim();var s=E?a:b;m=s[q];m||(":"===d.charAt(0)&&":"===d.charAt(1)&&(u=!0,d=d.substring(2)),E=E?p:n,m=new ec(E),m=
(new fc(m,c,E)).parse(d),m.constant?m.$$watchDelegate=h:u?m.$$watchDelegate=m.literal?g:f:m.inputs&&(m.$$watchDelegate=e),s[q]=m);return l(m,k);case "function":return l(d,k);default:return y}}}]}function jf(){this.$get=["$rootScope","$exceptionHandler",function(b,a){return rd(function(a){b.$evalAsync(a)},a)}]}function kf(){this.$get=["$browser","$exceptionHandler",function(b,a){return rd(function(a){b.defer(a)},a)}]}function rd(b,a){function c(a,b,c){function d(b){return function(c){e||(e=!0,b.call(a,
c))}}var e=!1;return[d(b),d(c)]}function d(){this.$$state={status:0}}function e(a,b){return function(c){b.call(a,c)}}function f(c){!c.processScheduled&&c.pending&&(c.processScheduled=!0,b(function(){var b,d,e;e=c.pending;c.processScheduled=!1;c.pending=w;for(var f=0,h=e.length;f<h;++f){d=e[f][0];b=e[f][c.status];try{x(b)?d.resolve(b(c.value)):1===c.status?d.resolve(c.value):d.reject(c.value)}catch(g){d.reject(g),a(g)}}}))}function g(){this.promise=new d;this.resolve=e(this,this.resolve);this.reject=
e(this,this.reject);this.notify=e(this,this.notify)}var h=I("$q",TypeError);P(d.prototype,{then:function(a,b,c){if(v(a)&&v(b)&&v(c))return this;var d=new g;this.$$state.pending=this.$$state.pending||[];this.$$state.pending.push([d,a,b,c]);0<this.$$state.status&&f(this.$$state);return d.promise},"catch":function(a){return this.then(null,a)},"finally":function(a,b){return this.then(function(b){return k(b,!0,a)},function(b){return k(b,!1,a)},b)}});P(g.prototype,{resolve:function(a){this.promise.$$state.status||
(a===this.promise?this.$$reject(h("qcycle",a)):this.$$resolve(a))},$$resolve:function(b){var d,e;e=c(this,this.$$resolve,this.$$reject);try{if(B(b)||x(b))d=b&&b.then;x(d)?(this.promise.$$state.status=-1,d.call(b,e[0],e[1],this.notify)):(this.promise.$$state.value=b,this.promise.$$state.status=1,f(this.promise.$$state))}catch(h){e[1](h),a(h)}},reject:function(a){this.promise.$$state.status||this.$$reject(a)},$$reject:function(a){this.promise.$$state.value=a;this.promise.$$state.status=2;f(this.promise.$$state)},
notify:function(c){var d=this.promise.$$state.pending;0>=this.promise.$$state.status&&d&&d.length&&b(function(){for(var b,e,f=0,h=d.length;f<h;f++){e=d[f][0];b=d[f][3];try{e.notify(x(b)?b(c):c)}catch(g){a(g)}}})}});var l=function(a,b){var c=new g;b?c.resolve(a):c.reject(a);return c.promise},k=function(a,b,c){var d=null;try{x(c)&&(d=c())}catch(e){return l(e,!1)}return d&&x(d.then)?d.then(function(){return l(a,b)},function(a){return l(a,!1)}):l(a,b)},n=function(a,b,c,d){var e=new g;e.resolve(a);return e.promise.then(b,
c,d)},p=function t(a){if(!x(a))throw h("norslvr",a);if(!(this instanceof t))return new t(a);var b=new g;a(function(a){b.resolve(a)},function(a){b.reject(a)});return b.promise};p.defer=function(){return new g};p.reject=function(a){var b=new g;b.reject(a);return b.promise};p.when=n;p.resolve=n;p.all=function(a){var b=new g,c=0,d=J(a)?[]:{};m(a,function(a,e){c++;n(a).then(function(a){d.hasOwnProperty(e)||(d[e]=a,--c||b.resolve(d))},function(a){d.hasOwnProperty(e)||b.reject(a)})});0===c&&b.resolve(d);
return b.promise};return p}function tf(){this.$get=["$window","$timeout",function(b,a){var c=b.requestAnimationFrame||b.webkitRequestAnimationFrame,d=b.cancelAnimationFrame||b.webkitCancelAnimationFrame||b.webkitCancelRequestAnimationFrame,e=!!c,f=e?function(a){var b=c(a);return function(){d(b)}}:function(b){var c=a(b,16.66,!1);return function(){a.cancel(c)}};f.supported=e;return f}]}function hf(){function b(a){function b(){this.$$watchers=this.$$nextSibling=this.$$childHead=this.$$childTail=null;
this.$$listeners={};this.$$listenerCount={};this.$$watchersCount=0;this.$id=++nb;this.$$ChildScope=null}b.prototype=a;return b}var a=10,c=I("$rootScope"),d=null,e=null;this.digestTtl=function(b){arguments.length&&(a=b);return a};this.$get=["$injector","$exceptionHandler","$parse","$browser",function(f,g,h,l){function k(a){a.currentScope.$$destroyed=!0}function n(){this.$id=++nb;this.$$phase=this.$parent=this.$$watchers=this.$$nextSibling=this.$$prevSibling=this.$$childHead=this.$$childTail=null;this.$root=
this;this.$$destroyed=!1;this.$$listeners={};this.$$listenerCount={};this.$$watchersCount=0;this.$$isolateBindings=null}function p(a){if(q.$$phase)throw c("inprog",q.$$phase);q.$$phase=a}function r(a,b){do a.$$watchersCount+=b;while(a=a.$parent)}function t(a,b,c){do a.$$listenerCount[c]-=b,0===a.$$listenerCount[c]&&delete a.$$listenerCount[c];while(a=a.$parent)}function E(){}function s(){for(;w.length;)try{w.shift()()}catch(a){g(a)}e=null}function u(){null===e&&(e=l.defer(function(){q.$apply(s)}))}
n.prototype={constructor:n,$new:function(a,c){var d;c=c||this;a?(d=new n,d.$root=this.$root):(this.$$ChildScope||(this.$$ChildScope=b(this)),d=new this.$$ChildScope);d.$parent=c;d.$$prevSibling=c.$$childTail;c.$$childHead?(c.$$childTail.$$nextSibling=d,c.$$childTail=d):c.$$childHead=c.$$childTail=d;(a||c!=this)&&d.$on("$destroy",k);return d},$watch:function(a,b,c,e){var f=h(a);if(f.$$watchDelegate)return f.$$watchDelegate(this,b,c,f,a);var g=this,k=g.$$watchers,l={fn:b,last:E,get:f,exp:e||a,eq:!!c};
d=null;x(b)||(l.fn=y);k||(k=g.$$watchers=[]);k.unshift(l);r(this,1);return function(){0<=cb(k,l)&&r(g,-1);d=null}},$watchGroup:function(a,b){function c(){g=!1;k?(k=!1,b(e,e,h)):b(e,d,h)}var d=Array(a.length),e=Array(a.length),f=[],h=this,g=!1,k=!0;if(!a.length){var l=!0;h.$evalAsync(function(){l&&b(e,e,h)});return function(){l=!1}}if(1===a.length)return this.$watch(a[0],function(a,c,f){e[0]=a;d[0]=c;b(e,a===c?e:d,f)});m(a,function(a,b){var k=h.$watch(a,function(a,f){e[b]=a;d[b]=f;g||(g=!0,h.$evalAsync(c))});
f.push(k)});return function(){for(;f.length;)f.shift()()}},$watchCollection:function(a,b){function c(a){e=a;var b,d,h,g;if(!v(e)){if(B(e))if(Da(e))for(f!==p&&(f=p,t=f.length=0,l++),a=e.length,t!==a&&(l++,f.length=t=a),b=0;b<a;b++)g=f[b],h=e[b],d=g!==g&&h!==h,d||g===h||(l++,f[b]=h);else{f!==r&&(f=r={},t=0,l++);a=0;for(b in e)ta.call(e,b)&&(a++,h=e[b],g=f[b],b in f?(d=g!==g&&h!==h,d||g===h||(l++,f[b]=h)):(t++,f[b]=h,l++));if(t>a)for(b in l++,f)ta.call(e,b)||(t--,delete f[b])}else f!==e&&(f=e,l++);return l}}
c.$stateful=!0;var d=this,e,f,g,k=1<b.length,l=0,n=h(a,c),p=[],r={},q=!0,t=0;return this.$watch(n,function(){q?(q=!1,b(e,e,d)):b(e,g,d);if(k)if(B(e))if(Da(e)){g=Array(e.length);for(var a=0;a<e.length;a++)g[a]=e[a]}else for(a in g={},e)ta.call(e,a)&&(g[a]=e[a]);else g=e})},$digest:function(){var b,f,h,k,n,r,t=a,m,u=[],D,v;p("$digest");l.$$checkUrlChange();this===q&&null!==e&&(l.defer.cancel(e),s());d=null;do{r=!1;for(m=this;z.length;){try{v=z.shift(),v.scope.$eval(v.expression,v.locals)}catch(w){g(w)}d=
null}a:do{if(k=m.$$watchers)for(n=k.length;n--;)try{if(b=k[n])if((f=b.get(m))!==(h=b.last)&&!(b.eq?ka(f,h):"number"===typeof f&&"number"===typeof h&&isNaN(f)&&isNaN(h)))r=!0,d=b,b.last=b.eq?ga(f,null):f,b.fn(f,h===E?f:h,m),5>t&&(D=4-t,u[D]||(u[D]=[]),u[D].push({msg:x(b.exp)?"fn: "+(b.exp.name||b.exp.toString()):b.exp,newVal:f,oldVal:h}));else if(b===d){r=!1;break a}}catch(y){g(y)}if(!(k=m.$$watchersCount&&m.$$childHead||m!==this&&m.$$nextSibling))for(;m!==this&&!(k=m.$$nextSibling);)m=m.$parent}while(m=
k);if((r||z.length)&&!t--)throw q.$$phase=null,c("infdig",a,u);}while(r||z.length);for(q.$$phase=null;N.length;)try{N.shift()()}catch(A){g(A)}},$destroy:function(){if(!this.$$destroyed){var a=this.$parent;this.$broadcast("$destroy");this.$$destroyed=!0;this===q&&l.$$applicationDestroyed();r(this,-this.$$watchersCount);for(var b in this.$$listenerCount)t(this,this.$$listenerCount[b],b);a&&a.$$childHead==this&&(a.$$childHead=this.$$nextSibling);a&&a.$$childTail==this&&(a.$$childTail=this.$$prevSibling);
this.$$prevSibling&&(this.$$prevSibling.$$nextSibling=this.$$nextSibling);this.$$nextSibling&&(this.$$nextSibling.$$prevSibling=this.$$prevSibling);this.$destroy=this.$digest=this.$apply=this.$evalAsync=this.$applyAsync=y;this.$on=this.$watch=this.$watchGroup=function(){return y};this.$$listeners={};this.$parent=this.$$nextSibling=this.$$prevSibling=this.$$childHead=this.$$childTail=this.$root=this.$$watchers=null}},$eval:function(a,b){return h(a)(this,b)},$evalAsync:function(a,b){q.$$phase||z.length||
l.defer(function(){z.length&&q.$digest()});z.push({scope:this,expression:a,locals:b})},$$postDigest:function(a){N.push(a)},$apply:function(a){try{p("$apply");try{return this.$eval(a)}finally{q.$$phase=null}}catch(b){g(b)}finally{try{q.$digest()}catch(c){throw g(c),c;}}},$applyAsync:function(a){function b(){c.$eval(a)}var c=this;a&&w.push(b);u()},$on:function(a,b){var c=this.$$listeners[a];c||(this.$$listeners[a]=c=[]);c.push(b);var d=this;do d.$$listenerCount[a]||(d.$$listenerCount[a]=0),d.$$listenerCount[a]++;
while(d=d.$parent);var e=this;return function(){var d=c.indexOf(b);-1!==d&&(c[d]=null,t(e,1,a))}},$emit:function(a,b){var c=[],d,e=this,f=!1,h={name:a,targetScope:e,stopPropagation:function(){f=!0},preventDefault:function(){h.defaultPrevented=!0},defaultPrevented:!1},k=db([h],arguments,1),l,n;do{d=e.$$listeners[a]||c;h.currentScope=e;l=0;for(n=d.length;l<n;l++)if(d[l])try{d[l].apply(null,k)}catch(p){g(p)}else d.splice(l,1),l--,n--;if(f)return h.currentScope=null,h;e=e.$parent}while(e);h.currentScope=
null;return h},$broadcast:function(a,b){var c=this,d=this,e={name:a,targetScope:this,preventDefault:function(){e.defaultPrevented=!0},defaultPrevented:!1};if(!this.$$listenerCount[a])return e;for(var f=db([e],arguments,1),h,k;c=d;){e.currentScope=c;d=c.$$listeners[a]||[];h=0;for(k=d.length;h<k;h++)if(d[h])try{d[h].apply(null,f)}catch(l){g(l)}else d.splice(h,1),h--,k--;if(!(d=c.$$listenerCount[a]&&c.$$childHead||c!==this&&c.$$nextSibling))for(;c!==this&&!(d=c.$$nextSibling);)c=c.$parent}e.currentScope=
null;return e}};var q=new n,z=q.$$asyncQueue=[],N=q.$$postDigestQueue=[],w=q.$$applyAsyncQueue=[];return q}]}function ee(){var b=/^\s*(https?|ftp|mailto|tel|file):/,a=/^\s*((https?|ftp|file|blob):|data:image\/)/;this.aHrefSanitizationWhitelist=function(a){return A(a)?(b=a,this):b};this.imgSrcSanitizationWhitelist=function(b){return A(b)?(a=b,this):a};this.$get=function(){return function(c,d){var e=d?a:b,f;f=Aa(c).href;return""===f||f.match(e)?c:"unsafe:"+f}}}function Xf(b){if("self"===b)return b;
if(G(b)){if(-1<b.indexOf("***"))throw Ca("iwcard",b);b=sd(b).replace("\\*\\*",".*").replace("\\*","[^:/.?&;]*");return new RegExp("^"+b+"$")}if(Oa(b))return new RegExp("^"+b.source+"$");throw Ca("imatcher");}function td(b){var a=[];A(b)&&m(b,function(b){a.push(Xf(b))});return a}function mf(){this.SCE_CONTEXTS=oa;var b=["self"],a=[];this.resourceUrlWhitelist=function(a){arguments.length&&(b=td(a));return b};this.resourceUrlBlacklist=function(b){arguments.length&&(a=td(b));return a};this.$get=["$injector",
function(c){function d(a,b){return"self"===a?ed(b):!!a.exec(b.href)}function e(a){var b=function(a){this.$$unwrapTrustedValue=function(){return a}};a&&(b.prototype=new a);b.prototype.valueOf=function(){return this.$$unwrapTrustedValue()};b.prototype.toString=function(){return this.$$unwrapTrustedValue().toString()};return b}var f=function(a){throw Ca("unsafe");};c.has("$sanitize")&&(f=c.get("$sanitize"));var g=e(),h={};h[oa.HTML]=e(g);h[oa.CSS]=e(g);h[oa.URL]=e(g);h[oa.JS]=e(g);h[oa.RESOURCE_URL]=
e(h[oa.URL]);return{trustAs:function(a,b){var c=h.hasOwnProperty(a)?h[a]:null;if(!c)throw Ca("icontext",a,b);if(null===b||v(b)||""===b)return b;if("string"!==typeof b)throw Ca("itype",a);return new c(b)},getTrusted:function(c,e){if(null===e||v(e)||""===e)return e;var g=h.hasOwnProperty(c)?h[c]:null;if(g&&e instanceof g)return e.$$unwrapTrustedValue();if(c===oa.RESOURCE_URL){var g=Aa(e.toString()),p,r,t=!1;p=0;for(r=b.length;p<r;p++)if(d(b[p],g)){t=!0;break}if(t)for(p=0,r=a.length;p<r;p++)if(d(a[p],
g)){t=!1;break}if(t)return e;throw Ca("insecurl",e.toString());}if(c===oa.HTML)return f(e);throw Ca("unsafe");},valueOf:function(a){return a instanceof g?a.$$unwrapTrustedValue():a}}}]}function lf(){var b=!0;this.enabled=function(a){arguments.length&&(b=!!a);return b};this.$get=["$parse","$sceDelegate",function(a,c){if(b&&8>Wa)throw Ca("iequirks");var d=ja(oa);d.isEnabled=function(){return b};d.trustAs=c.trustAs;d.getTrusted=c.getTrusted;d.valueOf=c.valueOf;b||(d.trustAs=d.getTrusted=function(a,b){return b},
d.valueOf=$a);d.parseAs=function(b,c){var e=a(c);return e.literal&&e.constant?e:a(c,function(a){return d.getTrusted(b,a)})};var e=d.parseAs,f=d.getTrusted,g=d.trustAs;m(oa,function(a,b){var c=F(b);d[gb("parse_as_"+c)]=function(b){return e(a,b)};d[gb("get_trusted_"+c)]=function(b){return f(a,b)};d[gb("trust_as_"+c)]=function(b){return g(a,b)}});return d}]}function nf(){this.$get=["$window","$document",function(b,a){var c={},d=Y((/android (\d+)/.exec(F((b.navigator||{}).userAgent))||[])[1]),e=/Boxee/i.test((b.navigator||
{}).userAgent),f=a[0]||{},g,h=/^(Moz|webkit|ms)(?=[A-Z])/,l=f.body&&f.body.style,k=!1,n=!1;if(l){for(var p in l)if(k=h.exec(p)){g=k[0];g=g.substr(0,1).toUpperCase()+g.substr(1);break}g||(g="WebkitOpacity"in l&&"webkit");k=!!("transition"in l||g+"Transition"in l);n=!!("animation"in l||g+"Animation"in l);!d||k&&n||(k=G(l.webkitTransition),n=G(l.webkitAnimation))}return{history:!(!b.history||!b.history.pushState||4>d||e),hasEvent:function(a){if("input"===a&&11>=Wa)return!1;if(v(c[a])){var b=f.createElement("div");
c[a]="on"+a in b}return c[a]},csp:Fa(),vendorPrefix:g,transitions:k,animations:n,android:d}}]}function pf(){this.$get=["$templateCache","$http","$q","$sce",function(b,a,c,d){function e(f,g){e.totalPendingRequests++;G(f)&&b.get(f)||(f=d.getTrustedResourceUrl(f));var h=a.defaults&&a.defaults.transformResponse;J(h)?h=h.filter(function(a){return a!==Zb}):h===Zb&&(h=null);return a.get(f,{cache:b,transformResponse:h})["finally"](function(){e.totalPendingRequests--}).then(function(a){b.put(f,a.data);return a.data},
function(a){if(!g)throw fa("tpload",f,a.status,a.statusText);return c.reject(a)})}e.totalPendingRequests=0;return e}]}function qf(){this.$get=["$rootScope","$browser","$location",function(b,a,c){return{findBindings:function(a,b,c){a=a.getElementsByClassName("ng-binding");var g=[];m(a,function(a){var d=aa.element(a).data("$binding");d&&m(d,function(d){c?(new RegExp("(^|\\s)"+sd(b)+"(\\s|\\||$)")).test(d)&&g.push(a):-1!=d.indexOf(b)&&g.push(a)})});return g},findModels:function(a,b,c){for(var g=["ng-",
"data-ng-","ng\\:"],h=0;h<g.length;++h){var l=a.querySelectorAll("["+g[h]+"model"+(c?"=":"*=")+'"'+b+'"]');if(l.length)return l}},getLocation:function(){return c.url()},setLocation:function(a){a!==c.url()&&(c.url(a),b.$digest())},whenStable:function(b){a.notifyWhenNoOutstandingRequests(b)}}}]}function rf(){this.$get=["$rootScope","$browser","$q","$$q","$exceptionHandler",function(b,a,c,d,e){function f(f,l,k){x(f)||(k=l,l=f,f=y);var n=ua.call(arguments,3),p=A(k)&&!k,r=(p?d:c).defer(),t=r.promise,m;
m=a.defer(function(){try{r.resolve(f.apply(null,n))}catch(a){r.reject(a),e(a)}finally{delete g[t.$$timeoutId]}p||b.$apply()},l);t.$$timeoutId=m;g[m]=r;return t}var g={};f.cancel=function(b){return b&&b.$$timeoutId in g?(g[b.$$timeoutId].reject("canceled"),delete g[b.$$timeoutId],a.defer.cancel(b.$$timeoutId)):!1};return f}]}function Aa(b){Wa&&(Z.setAttribute("href",b),b=Z.href);Z.setAttribute("href",b);return{href:Z.href,protocol:Z.protocol?Z.protocol.replace(/:$/,""):"",host:Z.host,search:Z.search?
Z.search.replace(/^\?/,""):"",hash:Z.hash?Z.hash.replace(/^#/,""):"",hostname:Z.hostname,port:Z.port,pathname:"/"===Z.pathname.charAt(0)?Z.pathname:"/"+Z.pathname}}function ed(b){b=G(b)?Aa(b):b;return b.protocol===ud.protocol&&b.host===ud.host}function sf(){this.$get=qa(Q)}function vd(b){function a(a){try{return decodeURIComponent(a)}catch(b){return a}}var c=b[0]||{},d={},e="";return function(){var b,g,h,l,k;b=c.cookie||"";if(b!==e)for(e=b,b=e.split("; "),d={},h=0;h<b.length;h++)g=b[h],l=g.indexOf("="),
0<l&&(k=a(g.substring(0,l)),v(d[k])&&(d[k]=a(g.substring(l+1))));return d}}function wf(){this.$get=vd}function Jc(b){function a(c,d){if(B(c)){var e={};m(c,function(b,c){e[c]=a(c,b)});return e}return b.factory(c+"Filter",d)}this.register=a;this.$get=["$injector",function(a){return function(b){return a.get(b+"Filter")}}];a("currency",wd);a("date",xd);a("filter",Yf);a("json",Zf);a("limitTo",$f);a("lowercase",ag);a("number",yd);a("orderBy",zd);a("uppercase",bg)}function Yf(){return function(b,a,c){if(!Da(b)){if(null==
b)return b;throw I("filter")("notarray",b);}var d;switch(gc(a)){case "function":break;case "boolean":case "null":case "number":case "string":d=!0;case "object":a=cg(a,c,d);break;default:return b}return Array.prototype.filter.call(b,a)}}function cg(b,a,c){var d=B(b)&&"$"in b;!0===a?a=ka:x(a)||(a=function(a,b){if(v(a))return!1;if(null===a||null===b)return a===b;if(B(b)||B(a)&&!pc(a))return!1;a=F(""+a);b=F(""+b);return-1!==a.indexOf(b)});return function(e){return d&&!B(e)?Ma(e,b.$,a,!1):Ma(e,b,a,c)}}
function Ma(b,a,c,d,e){var f=gc(b),g=gc(a);if("string"===g&&"!"===a.charAt(0))return!Ma(b,a.substring(1),c,d);if(J(b))return b.some(function(b){return Ma(b,a,c,d)});switch(f){case "object":var h;if(d){for(h in b)if("$"!==h.charAt(0)&&Ma(b[h],a,c,!0))return!0;return e?!1:Ma(b,a,c,!1)}if("object"===g){for(h in a)if(e=a[h],!x(e)&&!v(e)&&(f="$"===h,!Ma(f?b:b[h],e,c,f,f)))return!1;return!0}return c(b,a);case "function":return!1;default:return c(b,a)}}function gc(b){return null===b?"null":typeof b}function wd(b){var a=
b.NUMBER_FORMATS;return function(b,d,e){v(d)&&(d=a.CURRENCY_SYM);v(e)&&(e=a.PATTERNS[1].maxFrac);return null==b?b:Ad(b,a.PATTERNS[1],a.GROUP_SEP,a.DECIMAL_SEP,e).replace(/\u00A4/g,d)}}function yd(b){var a=b.NUMBER_FORMATS;return function(b,d){return null==b?b:Ad(b,a.PATTERNS[0],a.GROUP_SEP,a.DECIMAL_SEP,d)}}function Ad(b,a,c,d,e){if(B(b))return"";var f=0>b;b=Math.abs(b);var g=Infinity===b;if(!g&&!isFinite(b))return"";var h=b+"",l="",k=!1,n=[];g&&(l="\u221e");if(!g&&-1!==h.indexOf("e")){var p=h.match(/([\d\.]+)e(-?)(\d+)/);
p&&"-"==p[2]&&p[3]>e+1?b=0:(l=h,k=!0)}if(g||k)0<e&&1>b&&(l=b.toFixed(e),b=parseFloat(l));else{g=(h.split(Bd)[1]||"").length;v(e)&&(e=Math.min(Math.max(a.minFrac,g),a.maxFrac));b=+(Math.round(+(b.toString()+"e"+e)).toString()+"e"+-e);var g=(""+b).split(Bd),h=g[0],g=g[1]||"",p=0,r=a.lgSize,t=a.gSize;if(h.length>=r+t)for(p=h.length-r,k=0;k<p;k++)0===(p-k)%t&&0!==k&&(l+=c),l+=h.charAt(k);for(k=p;k<h.length;k++)0===(h.length-k)%r&&0!==k&&(l+=c),l+=h.charAt(k);for(;g.length<e;)g+="0";e&&"0"!==e&&(l+=d+
g.substr(0,e))}0===b&&(f=!1);n.push(f?a.negPre:a.posPre,l,f?a.negSuf:a.posSuf);return n.join("")}function Gb(b,a,c){var d="";0>b&&(d="-",b=-b);for(b=""+b;b.length<a;)b="0"+b;c&&(b=b.substr(b.length-a));return d+b}function $(b,a,c,d){c=c||0;return function(e){e=e["get"+b]();if(0<c||e>-c)e+=c;0===e&&-12==c&&(e=12);return Gb(e,a,d)}}function Hb(b,a){return function(c,d){var e=c["get"+b](),f=sb(a?"SHORT"+b:b);return d[f][e]}}function Cd(b){var a=(new Date(b,0,1)).getDay();return new Date(b,0,(4>=a?5:
12)-a)}function Dd(b){return function(a){var c=Cd(a.getFullYear());a=+new Date(a.getFullYear(),a.getMonth(),a.getDate()+(4-a.getDay()))-+c;a=1+Math.round(a/6048E5);return Gb(a,b)}}function hc(b,a){return 0>=b.getFullYear()?a.ERAS[0]:a.ERAS[1]}function xd(b){function a(a){var b;if(b=a.match(c)){a=new Date(0);var f=0,g=0,h=b[8]?a.setUTCFullYear:a.setFullYear,l=b[8]?a.setUTCHours:a.setHours;b[9]&&(f=Y(b[9]+b[10]),g=Y(b[9]+b[11]));h.call(a,Y(b[1]),Y(b[2])-1,Y(b[3]));f=Y(b[4]||0)-f;g=Y(b[5]||0)-g;h=Y(b[6]||
0);b=Math.round(1E3*parseFloat("0."+(b[7]||0)));l.call(a,f,g,h,b)}return a}var c=/^(\d{4})-?(\d\d)-?(\d\d)(?:T(\d\d)(?::?(\d\d)(?::?(\d\d)(?:\.(\d+))?)?)?(Z|([+-])(\d\d):?(\d\d))?)?$/;return function(c,e,f){var g="",h=[],l,k;e=e||"mediumDate";e=b.DATETIME_FORMATS[e]||e;G(c)&&(c=dg.test(c)?Y(c):a(c));V(c)&&(c=new Date(c));if(!da(c)||!isFinite(c.getTime()))return c;for(;e;)(k=eg.exec(e))?(h=db(h,k,1),e=h.pop()):(h.push(e),e=null);var n=c.getTimezoneOffset();f&&(n=vc(f,c.getTimezoneOffset()),c=Ob(c,
f,!0));m(h,function(a){l=fg[a];g+=l?l(c,b.DATETIME_FORMATS,n):a.replace(/(^'|'$)/g,"").replace(/''/g,"'")});return g}}function Zf(){return function(b,a){v(a)&&(a=2);return eb(b,a)}}function $f(){return function(b,a,c){a=Infinity===Math.abs(Number(a))?Number(a):Y(a);if(isNaN(a))return b;V(b)&&(b=b.toString());if(!J(b)&&!G(b))return b;c=!c||isNaN(c)?0:Y(c);c=0>c&&c>=-b.length?b.length+c:c;return 0<=a?b.slice(c,c+a):0===c?b.slice(a,b.length):b.slice(Math.max(0,c+a),c)}}function zd(b){function a(a,c){c=
c?-1:1;return a.map(function(a){var d=1,h=$a;if(x(a))h=a;else if(G(a)){if("+"==a.charAt(0)||"-"==a.charAt(0))d="-"==a.charAt(0)?-1:1,a=a.substring(1);if(""!==a&&(h=b(a),h.constant))var l=h(),h=function(a){return a[l]}}return{get:h,descending:d*c}})}function c(a){switch(typeof a){case "number":case "boolean":case "string":return!0;default:return!1}}return function(b,e,f){if(!Da(b))return b;J(e)||(e=[e]);0===e.length&&(e=["+"]);var g=a(e,f);g.push({get:function(){return{}},descending:f?-1:1});b=Array.prototype.map.call(b,
function(a,b){return{value:a,predicateValues:g.map(function(d){var e=d.get(a);d=typeof e;if(null===e)d="string",e="null";else if("string"===d)e=e.toLowerCase();else if("object"===d)a:{if("function"===typeof e.valueOf&&(e=e.valueOf(),c(e)))break a;if(pc(e)&&(e=e.toString(),c(e)))break a;e=b}return{value:e,type:d}})}});b.sort(function(a,b){for(var c=0,d=0,e=g.length;d<e;++d){var c=a.predicateValues[d],f=b.predicateValues[d],t=0;c.type===f.type?c.value!==f.value&&(t=c.value<f.value?-1:1):t=c.type<f.type?
-1:1;if(c=t*g[d].descending)break}return c});return b=b.map(function(a){return a.value})}}function Na(b){x(b)&&(b={link:b});b.restrict=b.restrict||"AC";return qa(b)}function Ed(b,a,c,d,e){var f=this,g=[];f.$error={};f.$$success={};f.$pending=w;f.$name=e(a.name||a.ngForm||"")(c);f.$dirty=!1;f.$pristine=!0;f.$valid=!0;f.$invalid=!1;f.$submitted=!1;f.$$parentForm=Ib;f.$rollbackViewValue=function(){m(g,function(a){a.$rollbackViewValue()})};f.$commitViewValue=function(){m(g,function(a){a.$commitViewValue()})};
f.$addControl=function(a){Ta(a.$name,"input");g.push(a);a.$name&&(f[a.$name]=a);a.$$parentForm=f};f.$$renameControl=function(a,b){var c=a.$name;f[c]===a&&delete f[c];f[b]=a;a.$name=b};f.$removeControl=function(a){a.$name&&f[a.$name]===a&&delete f[a.$name];m(f.$pending,function(b,c){f.$setValidity(c,null,a)});m(f.$error,function(b,c){f.$setValidity(c,null,a)});m(f.$$success,function(b,c){f.$setValidity(c,null,a)});cb(g,a);a.$$parentForm=Ib};Fd({ctrl:this,$element:b,set:function(a,b,c){var d=a[b];d?
-1===d.indexOf(c)&&d.push(c):a[b]=[c]},unset:function(a,b,c){var d=a[b];d&&(cb(d,c),0===d.length&&delete a[b])},$animate:d});f.$setDirty=function(){d.removeClass(b,Ya);d.addClass(b,Jb);f.$dirty=!0;f.$pristine=!1;f.$$parentForm.$setDirty()};f.$setPristine=function(){d.setClass(b,Ya,Jb+" ng-submitted");f.$dirty=!1;f.$pristine=!0;f.$submitted=!1;m(g,function(a){a.$setPristine()})};f.$setUntouched=function(){m(g,function(a){a.$setUntouched()})};f.$setSubmitted=function(){d.addClass(b,"ng-submitted");
f.$submitted=!0;f.$$parentForm.$setSubmitted()}}function ic(b){b.$formatters.push(function(a){return b.$isEmpty(a)?a:a.toString()})}function jb(b,a,c,d,e,f){var g=F(a[0].type);if(!e.android){var h=!1;a.on("compositionstart",function(a){h=!0});a.on("compositionend",function(){h=!1;l()})}var l=function(b){k&&(f.defer.cancel(k),k=null);if(!h){var e=a.val();b=b&&b.type;"password"===g||c.ngTrim&&"false"===c.ngTrim||(e=T(e));(d.$viewValue!==e||""===e&&d.$$hasNativeValidators)&&d.$setViewValue(e,b)}};if(e.hasEvent("input"))a.on("input",
l);else{var k,n=function(a,b,c){k||(k=f.defer(function(){k=null;b&&b.value===c||l(a)}))};a.on("keydown",function(a){var b=a.keyCode;91===b||15<b&&19>b||37<=b&&40>=b||n(a,this,this.value)});if(e.hasEvent("paste"))a.on("paste cut",n)}a.on("change",l);d.$render=function(){var b=d.$isEmpty(d.$viewValue)?"":d.$viewValue;a.val()!==b&&a.val(b)}}function Kb(b,a){return function(c,d){var e,f;if(da(c))return c;if(G(c)){'"'==c.charAt(0)&&'"'==c.charAt(c.length-1)&&(c=c.substring(1,c.length-1));if(gg.test(c))return new Date(c);
b.lastIndex=0;if(e=b.exec(c))return e.shift(),f=d?{yyyy:d.getFullYear(),MM:d.getMonth()+1,dd:d.getDate(),HH:d.getHours(),mm:d.getMinutes(),ss:d.getSeconds(),sss:d.getMilliseconds()/1E3}:{yyyy:1970,MM:1,dd:1,HH:0,mm:0,ss:0,sss:0},m(e,function(b,c){c<a.length&&(f[a[c]]=+b)}),new Date(f.yyyy,f.MM-1,f.dd,f.HH,f.mm,f.ss||0,1E3*f.sss||0)}return NaN}}function kb(b,a,c,d){return function(e,f,g,h,l,k,n){function p(a){return a&&!(a.getTime&&a.getTime()!==a.getTime())}function r(a){return A(a)&&!da(a)?c(a)||
w:a}Gd(e,f,g,h);jb(e,f,g,h,l,k);var t=h&&h.$options&&h.$options.timezone,m;h.$$parserName=b;h.$parsers.push(function(b){return h.$isEmpty(b)?null:a.test(b)?(b=c(b,m),t&&(b=Ob(b,t)),b):w});h.$formatters.push(function(a){if(a&&!da(a))throw lb("datefmt",a);if(p(a))return(m=a)&&t&&(m=Ob(m,t,!0)),n("date")(a,d,t);m=null;return""});if(A(g.min)||g.ngMin){var s;h.$validators.min=function(a){return!p(a)||v(s)||c(a)>=s};g.$observe("min",function(a){s=r(a);h.$validate()})}if(A(g.max)||g.ngMax){var u;h.$validators.max=
function(a){return!p(a)||v(u)||c(a)<=u};g.$observe("max",function(a){u=r(a);h.$validate()})}}}function Gd(b,a,c,d){(d.$$hasNativeValidators=B(a[0].validity))&&d.$parsers.push(function(b){var c=a.prop("validity")||{};return c.badInput&&!c.typeMismatch?w:b})}function Hd(b,a,c,d,e){if(A(d)){b=b(d);if(!b.constant)throw lb("constexpr",c,d);return b(a)}return e}function jc(b,a){b="ngClass"+b;return["$animate",function(c){function d(a,b){var c=[],d=0;a:for(;d<a.length;d++){for(var e=a[d],n=0;n<b.length;n++)if(e==
b[n])continue a;c.push(e)}return c}function e(a){var b=[];return J(a)?(m(a,function(a){b=b.concat(e(a))}),b):G(a)?a.split(" "):B(a)?(m(a,function(a,c){a&&(b=b.concat(c.split(" ")))}),b):a}return{restrict:"AC",link:function(f,g,h){function l(a,b){var c=g.data("$classCounts")||ha(),d=[];m(a,function(a){if(0<b||c[a])c[a]=(c[a]||0)+b,c[a]===+(0<b)&&d.push(a)});g.data("$classCounts",c);return d.join(" ")}function k(b){if(!0===a||f.$index%2===a){var k=e(b||[]);if(!n){var m=l(k,1);h.$addClass(m)}else if(!ka(b,
n)){var s=e(n),m=d(k,s),k=d(s,k),m=l(m,1),k=l(k,-1);m&&m.length&&c.addClass(g,m);k&&k.length&&c.removeClass(g,k)}}n=ja(b)}var n;f.$watch(h[b],k,!0);h.$observe("class",function(a){k(f.$eval(h[b]))});"ngClass"!==b&&f.$watch("$index",function(c,d){var g=c&1;if(g!==(d&1)){var k=e(f.$eval(h[b]));g===a?(g=l(k,1),h.$addClass(g)):(g=l(k,-1),h.$removeClass(g))}})}}}]}function Fd(b){function a(a,b){b&&!f[a]?(l.addClass(e,a),f[a]=!0):!b&&f[a]&&(l.removeClass(e,a),f[a]=!1)}function c(b,c){b=b?"-"+zc(b,"-"):"";
a(mb+b,!0===c);a(Id+b,!1===c)}var d=b.ctrl,e=b.$element,f={},g=b.set,h=b.unset,l=b.$animate;f[Id]=!(f[mb]=e.hasClass(mb));d.$setValidity=function(b,e,f){v(e)?(d.$pending||(d.$pending={}),g(d.$pending,b,f)):(d.$pending&&h(d.$pending,b,f),Jd(d.$pending)&&(d.$pending=w));bb(e)?e?(h(d.$error,b,f),g(d.$$success,b,f)):(g(d.$error,b,f),h(d.$$success,b,f)):(h(d.$error,b,f),h(d.$$success,b,f));d.$pending?(a(Kd,!0),d.$valid=d.$invalid=w,c("",null)):(a(Kd,!1),d.$valid=Jd(d.$error),d.$invalid=!d.$valid,c("",
d.$valid));e=d.$pending&&d.$pending[b]?w:d.$error[b]?!1:d.$$success[b]?!0:null;c(b,e);d.$$parentForm.$setValidity(b,e,d)}}function Jd(b){if(b)for(var a in b)if(b.hasOwnProperty(a))return!1;return!0}var hg=/^\/(.+)\/([a-z]*)$/,F=function(b){return G(b)?b.toLowerCase():b},ta=Object.prototype.hasOwnProperty,sb=function(b){return G(b)?b.toUpperCase():b},Wa,C,ra,ua=[].slice,Kf=[].splice,ig=[].push,va=Object.prototype.toString,qc=Object.getPrototypeOf,Ea=I("ng"),aa=Q.angular||(Q.angular={}),Rb,nb=0;Wa=
X.documentMode;y.$inject=[];$a.$inject=[];var J=Array.isArray,sc=/^\[object (Uint8(Clamped)?)|(Uint16)|(Uint32)|(Int8)|(Int16)|(Int32)|(Float(32)|(64))Array\]$/,T=function(b){return G(b)?b.trim():b},sd=function(b){return b.replace(/([-()\[\]{}+?*.$\^|,:#<!\\])/g,"\\$1").replace(/\x08/g,"\\x08")},Fa=function(){if(!A(Fa.rules)){var b=X.querySelector("[ng-csp]")||X.querySelector("[data-ng-csp]");if(b){var a=b.getAttribute("ng-csp")||b.getAttribute("data-ng-csp");Fa.rules={noUnsafeEval:!a||-1!==a.indexOf("no-unsafe-eval"),
noInlineStyle:!a||-1!==a.indexOf("no-inline-style")}}else{b=Fa;try{new Function(""),a=!1}catch(c){a=!0}b.rules={noUnsafeEval:a,noInlineStyle:!1}}}return Fa.rules},pb=function(){if(A(pb.name_))return pb.name_;var b,a,c=Qa.length,d,e;for(a=0;a<c;++a)if(d=Qa[a],b=X.querySelector("["+d.replace(":","\\:")+"jq]")){e=b.getAttribute(d+"jq");break}return pb.name_=e},Qa=["ng-","data-ng-","ng:","x-ng-"],$d=/[A-Z]/g,Ac=!1,Qb,pa=1,Pa=3,de={full:"1.4.6",major:1,minor:4,dot:6,codeName:"multiplicative-elevation"};
R.expando="ng339";var hb=R.cache={},Cf=1;R._data=function(b){return this.cache[b[this.expando]]||{}};var xf=/([\:\-\_]+(.))/g,yf=/^moz([A-Z])/,jg={mouseleave:"mouseout",mouseenter:"mouseover"},Tb=I("jqLite"),Bf=/^<(\w+)\s*\/?>(?:<\/\1>|)$/,Sb=/<|&#?\w+;/,zf=/<([\w:]+)/,Af=/<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,ma={option:[1,'<select multiple="multiple">',"</select>"],thead:[1,"<table>","</table>"],col:[2,"<table><colgroup>","</colgroup></table>"],tr:[2,"<table><tbody>",
"</tbody></table>"],td:[3,"<table><tbody><tr>","</tr></tbody></table>"],_default:[0,"",""]};ma.optgroup=ma.option;ma.tbody=ma.tfoot=ma.colgroup=ma.caption=ma.thead;ma.th=ma.td;var Ra=R.prototype={ready:function(b){function a(){c||(c=!0,b())}var c=!1;"complete"===X.readyState?setTimeout(a):(this.on("DOMContentLoaded",a),R(Q).on("load",a))},toString:function(){var b=[];m(this,function(a){b.push(""+a)});return"["+b.join(", ")+"]"},eq:function(b){return 0<=b?C(this[b]):C(this[this.length+b])},length:0,
push:ig,sort:[].sort,splice:[].splice},Bb={};m("multiple selected checked disabled readOnly required open".split(" "),function(b){Bb[F(b)]=b});var Rc={};m("input select option textarea button form details".split(" "),function(b){Rc[b]=!0});var Zc={ngMinlength:"minlength",ngMaxlength:"maxlength",ngMin:"min",ngMax:"max",ngPattern:"pattern"};m({data:Vb,removeData:vb,hasData:function(b){for(var a in hb[b.ng339])return!0;return!1}},function(b,a){R[a]=b});m({data:Vb,inheritedData:Ab,scope:function(b){return C.data(b,
"$scope")||Ab(b.parentNode||b,["$isolateScope","$scope"])},isolateScope:function(b){return C.data(b,"$isolateScope")||C.data(b,"$isolateScopeNoTemplate")},controller:Oc,injector:function(b){return Ab(b,"$injector")},removeAttr:function(b,a){b.removeAttribute(a)},hasClass:xb,css:function(b,a,c){a=gb(a);if(A(c))b.style[a]=c;else return b.style[a]},attr:function(b,a,c){var d=b.nodeType;if(d!==Pa&&2!==d&&8!==d)if(d=F(a),Bb[d])if(A(c))c?(b[a]=!0,b.setAttribute(a,d)):(b[a]=!1,b.removeAttribute(d));else return b[a]||
(b.attributes.getNamedItem(a)||y).specified?d:w;else if(A(c))b.setAttribute(a,c);else if(b.getAttribute)return b=b.getAttribute(a,2),null===b?w:b},prop:function(b,a,c){if(A(c))b[a]=c;else return b[a]},text:function(){function b(a,b){if(v(b)){var d=a.nodeType;return d===pa||d===Pa?a.textContent:""}a.textContent=b}b.$dv="";return b}(),val:function(b,a){if(v(a)){if(b.multiple&&"select"===wa(b)){var c=[];m(b.options,function(a){a.selected&&c.push(a.value||a.text)});return 0===c.length?null:c}return b.value}b.value=
a},html:function(b,a){if(v(a))return b.innerHTML;ub(b,!0);b.innerHTML=a},empty:Pc},function(b,a){R.prototype[a]=function(a,d){var e,f,g=this.length;if(b!==Pc&&v(2==b.length&&b!==xb&&b!==Oc?a:d)){if(B(a)){for(e=0;e<g;e++)if(b===Vb)b(this[e],a);else for(f in a)b(this[e],f,a[f]);return this}e=b.$dv;g=v(e)?Math.min(g,1):g;for(f=0;f<g;f++){var h=b(this[f],a,d);e=e?e+h:h}return e}for(e=0;e<g;e++)b(this[e],a,d);return this}});m({removeData:vb,on:function a(c,d,e,f){if(A(f))throw Tb("onargs");if(Kc(c)){var g=
wb(c,!0);f=g.events;var h=g.handle;h||(h=g.handle=Ef(c,f));for(var g=0<=d.indexOf(" ")?d.split(" "):[d],l=g.length;l--;){d=g[l];var k=f[d];k||(f[d]=[],"mouseenter"===d||"mouseleave"===d?a(c,jg[d],function(a){var c=a.relatedTarget;c&&(c===this||this.contains(c))||h(a,d)}):"$destroy"!==d&&c.addEventListener(d,h,!1),k=f[d]);k.push(e)}}},off:Nc,one:function(a,c,d){a=C(a);a.on(c,function f(){a.off(c,d);a.off(c,f)});a.on(c,d)},replaceWith:function(a,c){var d,e=a.parentNode;ub(a);m(new R(c),function(c){d?
e.insertBefore(c,d.nextSibling):e.replaceChild(c,a);d=c})},children:function(a){var c=[];m(a.childNodes,function(a){a.nodeType===pa&&c.push(a)});return c},contents:function(a){return a.contentDocument||a.childNodes||[]},append:function(a,c){var d=a.nodeType;if(d===pa||11===d){c=new R(c);for(var d=0,e=c.length;d<e;d++)a.appendChild(c[d])}},prepend:function(a,c){if(a.nodeType===pa){var d=a.firstChild;m(new R(c),function(c){a.insertBefore(c,d)})}},wrap:function(a,c){c=C(c).eq(0).clone()[0];var d=a.parentNode;
d&&d.replaceChild(c,a);c.appendChild(a)},remove:Wb,detach:function(a){Wb(a,!0)},after:function(a,c){var d=a,e=a.parentNode;c=new R(c);for(var f=0,g=c.length;f<g;f++){var h=c[f];e.insertBefore(h,d.nextSibling);d=h}},addClass:zb,removeClass:yb,toggleClass:function(a,c,d){c&&m(c.split(" "),function(c){var f=d;v(f)&&(f=!xb(a,c));(f?zb:yb)(a,c)})},parent:function(a){return(a=a.parentNode)&&11!==a.nodeType?a:null},next:function(a){return a.nextElementSibling},find:function(a,c){return a.getElementsByTagName?
a.getElementsByTagName(c):[]},clone:Ub,triggerHandler:function(a,c,d){var e,f,g=c.type||c,h=wb(a);if(h=(h=h&&h.events)&&h[g])e={preventDefault:function(){this.defaultPrevented=!0},isDefaultPrevented:function(){return!0===this.defaultPrevented},stopImmediatePropagation:function(){this.immediatePropagationStopped=!0},isImmediatePropagationStopped:function(){return!0===this.immediatePropagationStopped},stopPropagation:y,type:g,target:a},c.type&&(e=P(e,c)),c=ja(h),f=d?[e].concat(d):[e],m(c,function(c){e.isImmediatePropagationStopped()||
c.apply(a,f)})}},function(a,c){R.prototype[c]=function(c,e,f){for(var g,h=0,l=this.length;h<l;h++)v(g)?(g=a(this[h],c,e,f),A(g)&&(g=C(g))):Mc(g,a(this[h],c,e,f));return A(g)?g:this};R.prototype.bind=R.prototype.on;R.prototype.unbind=R.prototype.off});Ua.prototype={put:function(a,c){this[Ga(a,this.nextUid)]=c},get:function(a){return this[Ga(a,this.nextUid)]},remove:function(a){var c=this[a=Ga(a,this.nextUid)];delete this[a];return c}};var vf=[function(){this.$get=[function(){return Ua}]}],Tc=/^[^\(]*\(\s*([^\)]*)\)/m,
kg=/,/,lg=/^\s*(_?)(\S+?)\1\s*$/,Sc=/((\/\/.*$)|(\/\*[\s\S]*?\*\/))/mg,Ha=I("$injector");fb.$$annotate=function(a,c,d){var e;if("function"===typeof a){if(!(e=a.$inject)){e=[];if(a.length){if(c)throw G(d)&&d||(d=a.name||Ff(a)),Ha("strictdi",d);c=a.toString().replace(Sc,"");c=c.match(Tc);m(c[1].split(kg),function(a){a.replace(lg,function(a,c,d){e.push(d)})})}a.$inject=e}}else J(a)?(c=a.length-1,Sa(a[c],"fn"),e=a.slice(0,c)):Sa(a,"fn",!0);return e};var Ld=I("$animate"),Se=function(){this.$get=["$q",
"$$rAF",function(a,c){function d(){}d.all=y;d.chain=y;d.prototype={end:y,cancel:y,resume:y,pause:y,complete:y,then:function(d,f){return a(function(a){c(function(){a()})}).then(d,f)}};return d}]},Re=function(){var a=new Ua,c=[];this.$get=["$$AnimateRunner","$rootScope",function(d,e){function f(a,c,d){var e=!1;c&&(c=G(c)?c.split(" "):J(c)?c:[],m(c,function(c){c&&(e=!0,a[c]=d)}));return e}function g(){m(c,function(c){var d=a.get(c);if(d){var e=Gf(c.attr("class")),f="",g="";m(d,function(a,c){a!==!!e[c]&&
(a?f+=(f.length?" ":"")+c:g+=(g.length?" ":"")+c)});m(c,function(a){f&&zb(a,f);g&&yb(a,g)});a.remove(c)}});c.length=0}return{enabled:y,on:y,off:y,pin:y,push:function(h,l,k,n){n&&n();k=k||{};k.from&&h.css(k.from);k.to&&h.css(k.to);if(k.addClass||k.removeClass)if(l=k.addClass,n=k.removeClass,k=a.get(h)||{},l=f(k,l,!0),n=f(k,n,!1),l||n)a.put(h,k),c.push(h),1===c.length&&e.$$postDigest(g);return new d}}}]},Pe=["$provide",function(a){var c=this;this.$$registeredAnimations=Object.create(null);this.register=
function(d,e){if(d&&"."!==d.charAt(0))throw Ld("notcsel",d);var f=d+"-animation";c.$$registeredAnimations[d.substr(1)]=f;a.factory(f,e)};this.classNameFilter=function(a){if(1===arguments.length&&(this.$$classNameFilter=a instanceof RegExp?a:null)&&/(\s+|\/)ng-animate(\s+|\/)/.test(this.$$classNameFilter.toString()))throw Ld("nongcls","ng-animate");return this.$$classNameFilter};this.$get=["$$animateQueue",function(a){function c(a,d,e){if(e){var l;a:{for(l=0;l<e.length;l++){var k=e[l];if(1===k.nodeType){l=
k;break a}}l=void 0}!l||l.parentNode||l.previousElementSibling||(e=null)}e?e.after(a):d.prepend(a)}return{on:a.on,off:a.off,pin:a.pin,enabled:a.enabled,cancel:function(a){a.end&&a.end()},enter:function(f,g,h,l){g=g&&C(g);h=h&&C(h);g=g||h.parent();c(f,g,h);return a.push(f,"enter",Ia(l))},move:function(f,g,h,l){g=g&&C(g);h=h&&C(h);g=g||h.parent();c(f,g,h);return a.push(f,"move",Ia(l))},leave:function(c,e){return a.push(c,"leave",Ia(e),function(){c.remove()})},addClass:function(c,e,h){h=Ia(h);h.addClass=
ib(h.addclass,e);return a.push(c,"addClass",h)},removeClass:function(c,e,h){h=Ia(h);h.removeClass=ib(h.removeClass,e);return a.push(c,"removeClass",h)},setClass:function(c,e,h,l){l=Ia(l);l.addClass=ib(l.addClass,e);l.removeClass=ib(l.removeClass,h);return a.push(c,"setClass",l)},animate:function(c,e,h,l,k){k=Ia(k);k.from=k.from?P(k.from,e):e;k.to=k.to?P(k.to,h):h;k.tempClasses=ib(k.tempClasses,l||"ng-inline-animate");return a.push(c,"animate",k)}}}]}],Qe=function(){this.$get=["$$rAF","$q",function(a,
c){var d=function(){};d.prototype={done:function(a){this.defer&&this.defer[!0===a?"reject":"resolve"]()},end:function(){this.done()},cancel:function(){this.done(!0)},getPromise:function(){this.defer||(this.defer=c.defer());return this.defer.promise},then:function(a,c){return this.getPromise().then(a,c)},"catch":function(a){return this.getPromise()["catch"](a)},"finally":function(a){return this.getPromise()["finally"](a)}};return function(c,f){function g(){a(function(){f.addClass&&(c.addClass(f.addClass),
f.addClass=null);f.removeClass&&(c.removeClass(f.removeClass),f.removeClass=null);f.to&&(c.css(f.to),f.to=null);h||l.done();h=!0});return l}f.from&&(c.css(f.from),f.from=null);var h,l=new d;return{start:g,end:g}}}]},fa=I("$compile");Cc.$inject=["$provide","$$sanitizeUriProvider"];var Vc=/^((?:x|data)[\:\-_])/i,Lf=I("$controller"),Uc=/^(\S+)(\s+as\s+(\w+))?$/,Ye=function(){this.$get=["$document",function(a){return function(c){c?!c.nodeType&&c instanceof C&&(c=c[0]):c=a[0].body;return c.offsetWidth+
1}}]},$c="application/json",$b={"Content-Type":$c+";charset=utf-8"},Nf=/^\[|^\{(?!\{)/,Of={"[":/]$/,"{":/}$/},Mf=/^\)\]\}',?\n/,mg=I("$http"),dd=function(a){return function(){throw mg("legacy",a);}},La=aa.$interpolateMinErr=I("$interpolate");La.throwNoconcat=function(a){throw La("noconcat",a);};La.interr=function(a,c){return La("interr",a,c.toString())};var ng=/^([^\?#]*)(\?([^#]*))?(#(.*))?$/,Rf={http:80,https:443,ftp:21},Db=I("$location"),og={$$html5:!1,$$replace:!1,absUrl:Eb("$$absUrl"),url:function(a){if(v(a))return this.$$url;
var c=ng.exec(a);(c[1]||""===a)&&this.path(decodeURIComponent(c[1]));(c[2]||c[1]||""===a)&&this.search(c[3]||"");this.hash(c[5]||"");return this},protocol:Eb("$$protocol"),host:Eb("$$host"),port:Eb("$$port"),path:id("$$path",function(a){a=null!==a?a.toString():"";return"/"==a.charAt(0)?a:"/"+a}),search:function(a,c){switch(arguments.length){case 0:return this.$$search;case 1:if(G(a)||V(a))a=a.toString(),this.$$search=xc(a);else if(B(a))a=ga(a,{}),m(a,function(c,e){null==c&&delete a[e]}),this.$$search=
a;else throw Db("isrcharg");break;default:v(c)||null===c?delete this.$$search[a]:this.$$search[a]=c}this.$$compose();return this},hash:id("$$hash",function(a){return null!==a?a.toString():""}),replace:function(){this.$$replace=!0;return this}};m([hd,cc,bc],function(a){a.prototype=Object.create(og);a.prototype.state=function(c){if(!arguments.length)return this.$$state;if(a!==bc||!this.$$html5)throw Db("nostate");this.$$state=v(c)?null:c;return this}});var ea=I("$parse"),Sf=Function.prototype.call,
Tf=Function.prototype.apply,Uf=Function.prototype.bind,Lb=ha();m("+ - * / % === !== == != < > <= >= && || ! = |".split(" "),function(a){Lb[a]=!0});var pg={n:"\n",f:"\f",r:"\r",t:"\t",v:"\v","'":"'",'"':'"'},ec=function(a){this.options=a};ec.prototype={constructor:ec,lex:function(a){this.text=a;this.index=0;for(this.tokens=[];this.index<this.text.length;)if(a=this.text.charAt(this.index),'"'===a||"'"===a)this.readString(a);else if(this.isNumber(a)||"."===a&&this.isNumber(this.peek()))this.readNumber();
else if(this.isIdent(a))this.readIdent();else if(this.is(a,"(){}[].,;:?"))this.tokens.push({index:this.index,text:a}),this.index++;else if(this.isWhitespace(a))this.index++;else{var c=a+this.peek(),d=c+this.peek(2),e=Lb[c],f=Lb[d];Lb[a]||e||f?(a=f?d:e?c:a,this.tokens.push({index:this.index,text:a,operator:!0}),this.index+=a.length):this.throwError("Unexpected next character ",this.index,this.index+1)}return this.tokens},is:function(a,c){return-1!==c.indexOf(a)},peek:function(a){a=a||1;return this.index+
a<this.text.length?this.text.charAt(this.index+a):!1},isNumber:function(a){return"0"<=a&&"9">=a&&"string"===typeof a},isWhitespace:function(a){return" "===a||"\r"===a||"\t"===a||"\n"===a||"\v"===a||"\u00a0"===a},isIdent:function(a){return"a"<=a&&"z">=a||"A"<=a&&"Z">=a||"_"===a||"$"===a},isExpOperator:function(a){return"-"===a||"+"===a||this.isNumber(a)},throwError:function(a,c,d){d=d||this.index;c=A(c)?"s "+c+"-"+this.index+" ["+this.text.substring(c,d)+"]":" "+d;throw ea("lexerr",a,c,this.text);
},readNumber:function(){for(var a="",c=this.index;this.index<this.text.length;){var d=F(this.text.charAt(this.index));if("."==d||this.isNumber(d))a+=d;else{var e=this.peek();if("e"==d&&this.isExpOperator(e))a+=d;else if(this.isExpOperator(d)&&e&&this.isNumber(e)&&"e"==a.charAt(a.length-1))a+=d;else if(!this.isExpOperator(d)||e&&this.isNumber(e)||"e"!=a.charAt(a.length-1))break;else this.throwError("Invalid exponent")}this.index++}this.tokens.push({index:c,text:a,constant:!0,value:Number(a)})},readIdent:function(){for(var a=
this.index;this.index<this.text.length;){var c=this.text.charAt(this.index);if(!this.isIdent(c)&&!this.isNumber(c))break;this.index++}this.tokens.push({index:a,text:this.text.slice(a,this.index),identifier:!0})},readString:function(a){var c=this.index;this.index++;for(var d="",e=a,f=!1;this.index<this.text.length;){var g=this.text.charAt(this.index),e=e+g;if(f)"u"===g?(f=this.text.substring(this.index+1,this.index+5),f.match(/[\da-f]{4}/i)||this.throwError("Invalid unicode escape [\\u"+f+"]"),this.index+=
4,d+=String.fromCharCode(parseInt(f,16))):d+=pg[g]||g,f=!1;else if("\\"===g)f=!0;else{if(g===a){this.index++;this.tokens.push({index:c,text:e,constant:!0,value:d});return}d+=g}this.index++}this.throwError("Unterminated quote",c)}};var s=function(a,c){this.lexer=a;this.options=c};s.Program="Program";s.ExpressionStatement="ExpressionStatement";s.AssignmentExpression="AssignmentExpression";s.ConditionalExpression="ConditionalExpression";s.LogicalExpression="LogicalExpression";s.BinaryExpression="BinaryExpression";
s.UnaryExpression="UnaryExpression";s.CallExpression="CallExpression";s.MemberExpression="MemberExpression";s.Identifier="Identifier";s.Literal="Literal";s.ArrayExpression="ArrayExpression";s.Property="Property";s.ObjectExpression="ObjectExpression";s.ThisExpression="ThisExpression";s.NGValueParameter="NGValueParameter";s.prototype={ast:function(a){this.text=a;this.tokens=this.lexer.lex(a);a=this.program();0!==this.tokens.length&&this.throwError("is an unexpected token",this.tokens[0]);return a},
program:function(){for(var a=[];;)if(0<this.tokens.length&&!this.peek("}",")",";","]")&&a.push(this.expressionStatement()),!this.expect(";"))return{type:s.Program,body:a}},expressionStatement:function(){return{type:s.ExpressionStatement,expression:this.filterChain()}},filterChain:function(){for(var a=this.expression();this.expect("|");)a=this.filter(a);return a},expression:function(){return this.assignment()},assignment:function(){var a=this.ternary();this.expect("=")&&(a={type:s.AssignmentExpression,
left:a,right:this.assignment(),operator:"="});return a},ternary:function(){var a=this.logicalOR(),c,d;return this.expect("?")&&(c=this.expression(),this.consume(":"))?(d=this.expression(),{type:s.ConditionalExpression,test:a,alternate:c,consequent:d}):a},logicalOR:function(){for(var a=this.logicalAND();this.expect("||");)a={type:s.LogicalExpression,operator:"||",left:a,right:this.logicalAND()};return a},logicalAND:function(){for(var a=this.equality();this.expect("&&");)a={type:s.LogicalExpression,
operator:"&&",left:a,right:this.equality()};return a},equality:function(){for(var a=this.relational(),c;c=this.expect("==","!=","===","!==");)a={type:s.BinaryExpression,operator:c.text,left:a,right:this.relational()};return a},relational:function(){for(var a=this.additive(),c;c=this.expect("<",">","<=",">=");)a={type:s.BinaryExpression,operator:c.text,left:a,right:this.additive()};return a},additive:function(){for(var a=this.multiplicative(),c;c=this.expect("+","-");)a={type:s.BinaryExpression,operator:c.text,
left:a,right:this.multiplicative()};return a},multiplicative:function(){for(var a=this.unary(),c;c=this.expect("*","/","%");)a={type:s.BinaryExpression,operator:c.text,left:a,right:this.unary()};return a},unary:function(){var a;return(a=this.expect("+","-","!"))?{type:s.UnaryExpression,operator:a.text,prefix:!0,argument:this.unary()}:this.primary()},primary:function(){var a;this.expect("(")?(a=this.filterChain(),this.consume(")")):this.expect("[")?a=this.arrayDeclaration():this.expect("{")?a=this.object():
this.constants.hasOwnProperty(this.peek().text)?a=ga(this.constants[this.consume().text]):this.peek().identifier?a=this.identifier():this.peek().constant?a=this.constant():this.throwError("not a primary expression",this.peek());for(var c;c=this.expect("(","[",".");)"("===c.text?(a={type:s.CallExpression,callee:a,arguments:this.parseArguments()},this.consume(")")):"["===c.text?(a={type:s.MemberExpression,object:a,property:this.expression(),computed:!0},this.consume("]")):"."===c.text?a={type:s.MemberExpression,
object:a,property:this.identifier(),computed:!1}:this.throwError("IMPOSSIBLE");return a},filter:function(a){a=[a];for(var c={type:s.CallExpression,callee:this.identifier(),arguments:a,filter:!0};this.expect(":");)a.push(this.expression());return c},parseArguments:function(){var a=[];if(")"!==this.peekToken().text){do a.push(this.expression());while(this.expect(","))}return a},identifier:function(){var a=this.consume();a.identifier||this.throwError("is not a valid identifier",a);return{type:s.Identifier,
name:a.text}},constant:function(){return{type:s.Literal,value:this.consume().value}},arrayDeclaration:function(){var a=[];if("]"!==this.peekToken().text){do{if(this.peek("]"))break;a.push(this.expression())}while(this.expect(","))}this.consume("]");return{type:s.ArrayExpression,elements:a}},object:function(){var a=[],c;if("}"!==this.peekToken().text){do{if(this.peek("}"))break;c={type:s.Property,kind:"init"};this.peek().constant?c.key=this.constant():this.peek().identifier?c.key=this.identifier():
this.throwError("invalid key",this.peek());this.consume(":");c.value=this.expression();a.push(c)}while(this.expect(","))}this.consume("}");return{type:s.ObjectExpression,properties:a}},throwError:function(a,c){throw ea("syntax",c.text,a,c.index+1,this.text,this.text.substring(c.index));},consume:function(a){if(0===this.tokens.length)throw ea("ueoe",this.text);var c=this.expect(a);c||this.throwError("is unexpected, expecting ["+a+"]",this.peek());return c},peekToken:function(){if(0===this.tokens.length)throw ea("ueoe",
this.text);return this.tokens[0]},peek:function(a,c,d,e){return this.peekAhead(0,a,c,d,e)},peekAhead:function(a,c,d,e,f){if(this.tokens.length>a){a=this.tokens[a];var g=a.text;if(g===c||g===d||g===e||g===f||!(c||d||e||f))return a}return!1},expect:function(a,c,d,e){return(a=this.peek(a,c,d,e))?(this.tokens.shift(),a):!1},constants:{"true":{type:s.Literal,value:!0},"false":{type:s.Literal,value:!1},"null":{type:s.Literal,value:null},undefined:{type:s.Literal,value:w},"this":{type:s.ThisExpression}}};
pd.prototype={compile:function(a,c){var d=this,e=this.astBuilder.ast(a);this.state={nextId:0,filters:{},expensiveChecks:c,fn:{vars:[],body:[],own:{}},assign:{vars:[],body:[],own:{}},inputs:[]};U(e,d.$filter);var f="",g;this.stage="assign";if(g=nd(e))this.state.computing="assign",f=this.nextId(),this.recurse(g,f),this.return_(f),f="fn.assign="+this.generateFunction("assign","s,v,l");g=ld(e.body);d.stage="inputs";m(g,function(a,c){var e="fn"+c;d.state[e]={vars:[],body:[],own:{}};d.state.computing=e;
var f=d.nextId();d.recurse(a,f);d.return_(f);d.state.inputs.push(e);a.watchId=c});this.state.computing="fn";this.stage="main";this.recurse(e);f='"'+this.USE+" "+this.STRICT+'";\n'+this.filterPrefix()+"var fn="+this.generateFunction("fn","s,l,a,i")+f+this.watchFns()+"return fn;";f=(new Function("$filter","ensureSafeMemberName","ensureSafeObject","ensureSafeFunction","ifDefined","plus","text",f))(this.$filter,Xa,Ba,jd,Vf,kd,a);this.state=this.stage=w;f.literal=od(e);f.constant=e.constant;return f},
USE:"use",STRICT:"strict",watchFns:function(){var a=[],c=this.state.inputs,d=this;m(c,function(c){a.push("var "+c+"="+d.generateFunction(c,"s"))});c.length&&a.push("fn.inputs=["+c.join(",")+"];");return a.join("")},generateFunction:function(a,c){return"function("+c+"){"+this.varsPrefix(a)+this.body(a)+"};"},filterPrefix:function(){var a=[],c=this;m(this.state.filters,function(d,e){a.push(d+"=$filter("+c.escape(e)+")")});return a.length?"var "+a.join(",")+";":""},varsPrefix:function(a){return this.state[a].vars.length?
"var "+this.state[a].vars.join(",")+";":""},body:function(a){return this.state[a].body.join("")},recurse:function(a,c,d,e,f,g){var h,l,k=this,n,p;e=e||y;if(!g&&A(a.watchId))c=c||this.nextId(),this.if_("i",this.lazyAssign(c,this.computedMember("i",a.watchId)),this.lazyRecurse(a,c,d,e,f,!0));else switch(a.type){case s.Program:m(a.body,function(c,d){k.recurse(c.expression,w,w,function(a){l=a});d!==a.body.length-1?k.current().body.push(l,";"):k.return_(l)});break;case s.Literal:p=this.escape(a.value);
this.assign(c,p);e(p);break;case s.UnaryExpression:this.recurse(a.argument,w,w,function(a){l=a});p=a.operator+"("+this.ifDefined(l,0)+")";this.assign(c,p);e(p);break;case s.BinaryExpression:this.recurse(a.left,w,w,function(a){h=a});this.recurse(a.right,w,w,function(a){l=a});p="+"===a.operator?this.plus(h,l):"-"===a.operator?this.ifDefined(h,0)+a.operator+this.ifDefined(l,0):"("+h+")"+a.operator+"("+l+")";this.assign(c,p);e(p);break;case s.LogicalExpression:c=c||this.nextId();k.recurse(a.left,c);k.if_("&&"===
a.operator?c:k.not(c),k.lazyRecurse(a.right,c));e(c);break;case s.ConditionalExpression:c=c||this.nextId();k.recurse(a.test,c);k.if_(c,k.lazyRecurse(a.alternate,c),k.lazyRecurse(a.consequent,c));e(c);break;case s.Identifier:c=c||this.nextId();d&&(d.context="inputs"===k.stage?"s":this.assign(this.nextId(),this.getHasOwnProperty("l",a.name)+"?l:s"),d.computed=!1,d.name=a.name);Xa(a.name);k.if_("inputs"===k.stage||k.not(k.getHasOwnProperty("l",a.name)),function(){k.if_("inputs"===k.stage||"s",function(){f&&
1!==f&&k.if_(k.not(k.nonComputedMember("s",a.name)),k.lazyAssign(k.nonComputedMember("s",a.name),"{}"));k.assign(c,k.nonComputedMember("s",a.name))})},c&&k.lazyAssign(c,k.nonComputedMember("l",a.name)));(k.state.expensiveChecks||Fb(a.name))&&k.addEnsureSafeObject(c);e(c);break;case s.MemberExpression:h=d&&(d.context=this.nextId())||this.nextId();c=c||this.nextId();k.recurse(a.object,h,w,function(){k.if_(k.notNull(h),function(){if(a.computed)l=k.nextId(),k.recurse(a.property,l),k.addEnsureSafeMemberName(l),
f&&1!==f&&k.if_(k.not(k.computedMember(h,l)),k.lazyAssign(k.computedMember(h,l),"{}")),p=k.ensureSafeObject(k.computedMember(h,l)),k.assign(c,p),d&&(d.computed=!0,d.name=l);else{Xa(a.property.name);f&&1!==f&&k.if_(k.not(k.nonComputedMember(h,a.property.name)),k.lazyAssign(k.nonComputedMember(h,a.property.name),"{}"));p=k.nonComputedMember(h,a.property.name);if(k.state.expensiveChecks||Fb(a.property.name))p=k.ensureSafeObject(p);k.assign(c,p);d&&(d.computed=!1,d.name=a.property.name)}},function(){k.assign(c,
"undefined")});e(c)},!!f);break;case s.CallExpression:c=c||this.nextId();a.filter?(l=k.filter(a.callee.name),n=[],m(a.arguments,function(a){var c=k.nextId();k.recurse(a,c);n.push(c)}),p=l+"("+n.join(",")+")",k.assign(c,p),e(c)):(l=k.nextId(),h={},n=[],k.recurse(a.callee,l,h,function(){k.if_(k.notNull(l),function(){k.addEnsureSafeFunction(l);m(a.arguments,function(a){k.recurse(a,k.nextId(),w,function(a){n.push(k.ensureSafeObject(a))})});h.name?(k.state.expensiveChecks||k.addEnsureSafeObject(h.context),
p=k.member(h.context,h.name,h.computed)+"("+n.join(",")+")"):p=l+"("+n.join(",")+")";p=k.ensureSafeObject(p);k.assign(c,p)},function(){k.assign(c,"undefined")});e(c)}));break;case s.AssignmentExpression:l=this.nextId();h={};if(!md(a.left))throw ea("lval");this.recurse(a.left,w,h,function(){k.if_(k.notNull(h.context),function(){k.recurse(a.right,l);k.addEnsureSafeObject(k.member(h.context,h.name,h.computed));p=k.member(h.context,h.name,h.computed)+a.operator+l;k.assign(c,p);e(c||p)})},1);break;case s.ArrayExpression:n=
[];m(a.elements,function(a){k.recurse(a,k.nextId(),w,function(a){n.push(a)})});p="["+n.join(",")+"]";this.assign(c,p);e(p);break;case s.ObjectExpression:n=[];m(a.properties,function(a){k.recurse(a.value,k.nextId(),w,function(c){n.push(k.escape(a.key.type===s.Identifier?a.key.name:""+a.key.value)+":"+c)})});p="{"+n.join(",")+"}";this.assign(c,p);e(p);break;case s.ThisExpression:this.assign(c,"s");e("s");break;case s.NGValueParameter:this.assign(c,"v"),e("v")}},getHasOwnProperty:function(a,c){var d=
a+"."+c,e=this.current().own;e.hasOwnProperty(d)||(e[d]=this.nextId(!1,a+"&&("+this.escape(c)+" in "+a+")"));return e[d]},assign:function(a,c){if(a)return this.current().body.push(a,"=",c,";"),a},filter:function(a){this.state.filters.hasOwnProperty(a)||(this.state.filters[a]=this.nextId(!0));return this.state.filters[a]},ifDefined:function(a,c){return"ifDefined("+a+","+this.escape(c)+")"},plus:function(a,c){return"plus("+a+","+c+")"},return_:function(a){this.current().body.push("return ",a,";")},
if_:function(a,c,d){if(!0===a)c();else{var e=this.current().body;e.push("if(",a,"){");c();e.push("}");d&&(e.push("else{"),d(),e.push("}"))}},not:function(a){return"!("+a+")"},notNull:function(a){return a+"!=null"},nonComputedMember:function(a,c){return a+"."+c},computedMember:function(a,c){return a+"["+c+"]"},member:function(a,c,d){return d?this.computedMember(a,c):this.nonComputedMember(a,c)},addEnsureSafeObject:function(a){this.current().body.push(this.ensureSafeObject(a),";")},addEnsureSafeMemberName:function(a){this.current().body.push(this.ensureSafeMemberName(a),
";")},addEnsureSafeFunction:function(a){this.current().body.push(this.ensureSafeFunction(a),";")},ensureSafeObject:function(a){return"ensureSafeObject("+a+",text)"},ensureSafeMemberName:function(a){return"ensureSafeMemberName("+a+",text)"},ensureSafeFunction:function(a){return"ensureSafeFunction("+a+",text)"},lazyRecurse:function(a,c,d,e,f,g){var h=this;return function(){h.recurse(a,c,d,e,f,g)}},lazyAssign:function(a,c){var d=this;return function(){d.assign(a,c)}},stringEscapeRegex:/[^ a-zA-Z0-9]/g,
stringEscapeFn:function(a){return"\\u"+("0000"+a.charCodeAt(0).toString(16)).slice(-4)},escape:function(a){if(G(a))return"'"+a.replace(this.stringEscapeRegex,this.stringEscapeFn)+"'";if(V(a))return a.toString();if(!0===a)return"true";if(!1===a)return"false";if(null===a)return"null";if("undefined"===typeof a)return"undefined";throw ea("esc");},nextId:function(a,c){var d="v"+this.state.nextId++;a||this.current().vars.push(d+(c?"="+c:""));return d},current:function(){return this.state[this.state.computing]}};
qd.prototype={compile:function(a,c){var d=this,e=this.astBuilder.ast(a);this.expression=a;this.expensiveChecks=c;U(e,d.$filter);var f,g;if(f=nd(e))g=this.recurse(f);f=ld(e.body);var h;f&&(h=[],m(f,function(a,c){var e=d.recurse(a);a.input=e;h.push(e);a.watchId=c}));var l=[];m(e.body,function(a){l.push(d.recurse(a.expression))});f=0===e.body.length?function(){}:1===e.body.length?l[0]:function(a,c){var d;m(l,function(e){d=e(a,c)});return d};g&&(f.assign=function(a,c,d){return g(a,d,c)});h&&(f.inputs=
h);f.literal=od(e);f.constant=e.constant;return f},recurse:function(a,c,d){var e,f,g=this,h;if(a.input)return this.inputs(a.input,a.watchId);switch(a.type){case s.Literal:return this.value(a.value,c);case s.UnaryExpression:return f=this.recurse(a.argument),this["unary"+a.operator](f,c);case s.BinaryExpression:return e=this.recurse(a.left),f=this.recurse(a.right),this["binary"+a.operator](e,f,c);case s.LogicalExpression:return e=this.recurse(a.left),f=this.recurse(a.right),this["binary"+a.operator](e,
f,c);case s.ConditionalExpression:return this["ternary?:"](this.recurse(a.test),this.recurse(a.alternate),this.recurse(a.consequent),c);case s.Identifier:return Xa(a.name,g.expression),g.identifier(a.name,g.expensiveChecks||Fb(a.name),c,d,g.expression);case s.MemberExpression:return e=this.recurse(a.object,!1,!!d),a.computed||(Xa(a.property.name,g.expression),f=a.property.name),a.computed&&(f=this.recurse(a.property)),a.computed?this.computedMember(e,f,c,d,g.expression):this.nonComputedMember(e,f,
g.expensiveChecks,c,d,g.expression);case s.CallExpression:return h=[],m(a.arguments,function(a){h.push(g.recurse(a))}),a.filter&&(f=this.$filter(a.callee.name)),a.filter||(f=this.recurse(a.callee,!0)),a.filter?function(a,d,e,g){for(var r=[],m=0;m<h.length;++m)r.push(h[m](a,d,e,g));a=f.apply(w,r,g);return c?{context:w,name:w,value:a}:a}:function(a,d,e,p){var r=f(a,d,e,p),m;if(null!=r.value){Ba(r.context,g.expression);jd(r.value,g.expression);m=[];for(var s=0;s<h.length;++s)m.push(Ba(h[s](a,d,e,p),
g.expression));m=Ba(r.value.apply(r.context,m),g.expression)}return c?{value:m}:m};case s.AssignmentExpression:return e=this.recurse(a.left,!0,1),f=this.recurse(a.right),function(a,d,h,p){var r=e(a,d,h,p);a=f(a,d,h,p);Ba(r.value,g.expression);r.context[r.name]=a;return c?{value:a}:a};case s.ArrayExpression:return h=[],m(a.elements,function(a){h.push(g.recurse(a))}),function(a,d,e,f){for(var g=[],m=0;m<h.length;++m)g.push(h[m](a,d,e,f));return c?{value:g}:g};case s.ObjectExpression:return h=[],m(a.properties,
function(a){h.push({key:a.key.type===s.Identifier?a.key.name:""+a.key.value,value:g.recurse(a.value)})}),function(a,d,e,f){for(var g={},m=0;m<h.length;++m)g[h[m].key]=h[m].value(a,d,e,f);return c?{value:g}:g};case s.ThisExpression:return function(a){return c?{value:a}:a};case s.NGValueParameter:return function(a,d,e,f){return c?{value:e}:e}}},"unary+":function(a,c){return function(d,e,f,g){d=a(d,e,f,g);d=A(d)?+d:0;return c?{value:d}:d}},"unary-":function(a,c){return function(d,e,f,g){d=a(d,e,f,g);
d=A(d)?-d:0;return c?{value:d}:d}},"unary!":function(a,c){return function(d,e,f,g){d=!a(d,e,f,g);return c?{value:d}:d}},"binary+":function(a,c,d){return function(e,f,g,h){var l=a(e,f,g,h);e=c(e,f,g,h);l=kd(l,e);return d?{value:l}:l}},"binary-":function(a,c,d){return function(e,f,g,h){var l=a(e,f,g,h);e=c(e,f,g,h);l=(A(l)?l:0)-(A(e)?e:0);return d?{value:l}:l}},"binary*":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)*c(e,f,g,h);return d?{value:e}:e}},"binary/":function(a,c,d){return function(e,
f,g,h){e=a(e,f,g,h)/c(e,f,g,h);return d?{value:e}:e}},"binary%":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)%c(e,f,g,h);return d?{value:e}:e}},"binary===":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)===c(e,f,g,h);return d?{value:e}:e}},"binary!==":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)!==c(e,f,g,h);return d?{value:e}:e}},"binary==":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)==c(e,f,g,h);return d?{value:e}:e}},"binary!=":function(a,c,d){return function(e,
f,g,h){e=a(e,f,g,h)!=c(e,f,g,h);return d?{value:e}:e}},"binary<":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)<c(e,f,g,h);return d?{value:e}:e}},"binary>":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)>c(e,f,g,h);return d?{value:e}:e}},"binary<=":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)<=c(e,f,g,h);return d?{value:e}:e}},"binary>=":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)>=c(e,f,g,h);return d?{value:e}:e}},"binary&&":function(a,c,d){return function(e,f,g,h){e=
a(e,f,g,h)&&c(e,f,g,h);return d?{value:e}:e}},"binary||":function(a,c,d){return function(e,f,g,h){e=a(e,f,g,h)||c(e,f,g,h);return d?{value:e}:e}},"ternary?:":function(a,c,d,e){return function(f,g,h,l){f=a(f,g,h,l)?c(f,g,h,l):d(f,g,h,l);return e?{value:f}:f}},value:function(a,c){return function(){return c?{context:w,name:w,value:a}:a}},identifier:function(a,c,d,e,f){return function(g,h,l,k){g=h&&a in h?h:g;e&&1!==e&&g&&!g[a]&&(g[a]={});h=g?g[a]:w;c&&Ba(h,f);return d?{context:g,name:a,value:h}:h}},
computedMember:function(a,c,d,e,f){return function(g,h,l,k){var n=a(g,h,l,k),p,m;null!=n&&(p=c(g,h,l,k),Xa(p,f),e&&1!==e&&n&&!n[p]&&(n[p]={}),m=n[p],Ba(m,f));return d?{context:n,name:p,value:m}:m}},nonComputedMember:function(a,c,d,e,f,g){return function(h,l,k,n){h=a(h,l,k,n);f&&1!==f&&h&&!h[c]&&(h[c]={});l=null!=h?h[c]:w;(d||Fb(c))&&Ba(l,g);return e?{context:h,name:c,value:l}:l}},inputs:function(a,c){return function(d,e,f,g){return g?g[c]:a(d,e,f)}}};var fc=function(a,c,d){this.lexer=a;this.$filter=
c;this.options=d;this.ast=new s(this.lexer);this.astCompiler=d.csp?new qd(this.ast,c):new pd(this.ast,c)};fc.prototype={constructor:fc,parse:function(a){return this.astCompiler.compile(a,this.options.expensiveChecks)}};ha();ha();var Wf=Object.prototype.valueOf,Ca=I("$sce"),oa={HTML:"html",CSS:"css",URL:"url",RESOURCE_URL:"resourceUrl",JS:"js"},fa=I("$compile"),Z=X.createElement("a"),ud=Aa(Q.location.href);vd.$inject=["$document"];Jc.$inject=["$provide"];wd.$inject=["$locale"];yd.$inject=["$locale"];
var Bd=".",fg={yyyy:$("FullYear",4),yy:$("FullYear",2,0,!0),y:$("FullYear",1),MMMM:Hb("Month"),MMM:Hb("Month",!0),MM:$("Month",2,1),M:$("Month",1,1),dd:$("Date",2),d:$("Date",1),HH:$("Hours",2),H:$("Hours",1),hh:$("Hours",2,-12),h:$("Hours",1,-12),mm:$("Minutes",2),m:$("Minutes",1),ss:$("Seconds",2),s:$("Seconds",1),sss:$("Milliseconds",3),EEEE:Hb("Day"),EEE:Hb("Day",!0),a:function(a,c){return 12>a.getHours()?c.AMPMS[0]:c.AMPMS[1]},Z:function(a,c,d){a=-1*d;return a=(0<=a?"+":"")+(Gb(Math[0<a?"floor":
"ceil"](a/60),2)+Gb(Math.abs(a%60),2))},ww:Dd(2),w:Dd(1),G:hc,GG:hc,GGG:hc,GGGG:function(a,c){return 0>=a.getFullYear()?c.ERANAMES[0]:c.ERANAMES[1]}},eg=/((?:[^yMdHhmsaZEwG']+)|(?:'(?:[^']|'')*')|(?:E+|y+|M+|d+|H+|h+|m+|s+|a|Z|G+|w+))(.*)/,dg=/^\-?\d+$/;xd.$inject=["$locale"];var ag=qa(F),bg=qa(sb);zd.$inject=["$parse"];var fe=qa({restrict:"E",compile:function(a,c){if(!c.href&&!c.xlinkHref)return function(a,c){if("a"===c[0].nodeName.toLowerCase()){var f="[object SVGAnimatedString]"===va.call(c.prop("href"))?
"xlink:href":"href";c.on("click",function(a){c.attr(f)||a.preventDefault()})}}}}),tb={};m(Bb,function(a,c){function d(a,d,f){a.$watch(f[e],function(a){f.$set(c,!!a)})}if("multiple"!=a){var e=ya("ng-"+c),f=d;"checked"===a&&(f=function(a,c,f){f.ngModel!==f[e]&&d(a,c,f)});tb[e]=function(){return{restrict:"A",priority:100,link:f}}}});m(Zc,function(a,c){tb[c]=function(){return{priority:100,link:function(a,e,f){if("ngPattern"===c&&"/"==f.ngPattern.charAt(0)&&(e=f.ngPattern.match(hg))){f.$set("ngPattern",
new RegExp(e[1],e[2]));return}a.$watch(f[c],function(a){f.$set(c,a)})}}}});m(["src","srcset","href"],function(a){var c=ya("ng-"+a);tb[c]=function(){return{priority:99,link:function(d,e,f){var g=a,h=a;"href"===a&&"[object SVGAnimatedString]"===va.call(e.prop("href"))&&(h="xlinkHref",f.$attr[h]="xlink:href",g=null);f.$observe(c,function(c){c?(f.$set(h,c),Wa&&g&&e.prop(g,f[h])):"href"===a&&f.$set(h,null)})}}}});var Ib={$addControl:y,$$renameControl:function(a,c){a.$name=c},$removeControl:y,$setValidity:y,
$setDirty:y,$setPristine:y,$setSubmitted:y};Ed.$inject=["$element","$attrs","$scope","$animate","$interpolate"];var Md=function(a){return["$timeout","$parse",function(c,d){function e(a){return""===a?d('this[""]').assign:d(a).assign||y}return{name:"form",restrict:a?"EAC":"E",require:["form","^^?form"],controller:Ed,compile:function(d,g){d.addClass(Ya).addClass(mb);var h=g.name?"name":a&&g.ngForm?"ngForm":!1;return{pre:function(a,d,f,g){var m=g[0];if(!("action"in f)){var t=function(c){a.$apply(function(){m.$commitViewValue();
m.$setSubmitted()});c.preventDefault()};d[0].addEventListener("submit",t,!1);d.on("$destroy",function(){c(function(){d[0].removeEventListener("submit",t,!1)},0,!1)})}(g[1]||m.$$parentForm).$addControl(m);var s=h?e(m.$name):y;h&&(s(a,m),f.$observe(h,function(c){m.$name!==c&&(s(a,w),m.$$parentForm.$$renameControl(m,c),s=e(m.$name),s(a,m))}));d.on("$destroy",function(){m.$$parentForm.$removeControl(m);s(a,w);P(m,Ib)})}}}}}]},ge=Md(),te=Md(!0),gg=/\d{4}-[01]\d-[0-3]\dT[0-2]\d:[0-5]\d:[0-5]\d\.\d+([+-][0-2]\d:[0-5]\d|Z)/,
qg=/^(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/,rg=/^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i,sg=/^\s*(\-|\+)?(\d+|(\d*(\.\d*)))([eE][+-]?\d+)?\s*$/,Nd=/^(\d{4})-(\d{2})-(\d{2})$/,Od=/^(\d{4})-(\d\d)-(\d\d)T(\d\d):(\d\d)(?::(\d\d)(\.\d{1,3})?)?$/,kc=/^(\d{4})-W(\d\d)$/,Pd=/^(\d{4})-(\d\d)$/,Qd=/^(\d\d):(\d\d)(?::(\d\d)(\.\d{1,3})?)?$/,Rd={text:function(a,c,d,e,f,g){jb(a,c,d,e,f,g);ic(e)},date:kb("date",
Nd,Kb(Nd,["yyyy","MM","dd"]),"yyyy-MM-dd"),"datetime-local":kb("datetimelocal",Od,Kb(Od,"yyyy MM dd HH mm ss sss".split(" ")),"yyyy-MM-ddTHH:mm:ss.sss"),time:kb("time",Qd,Kb(Qd,["HH","mm","ss","sss"]),"HH:mm:ss.sss"),week:kb("week",kc,function(a,c){if(da(a))return a;if(G(a)){kc.lastIndex=0;var d=kc.exec(a);if(d){var e=+d[1],f=+d[2],g=d=0,h=0,l=0,k=Cd(e),f=7*(f-1);c&&(d=c.getHours(),g=c.getMinutes(),h=c.getSeconds(),l=c.getMilliseconds());return new Date(e,0,k.getDate()+f,d,g,h,l)}}return NaN},"yyyy-Www"),
month:kb("month",Pd,Kb(Pd,["yyyy","MM"]),"yyyy-MM"),number:function(a,c,d,e,f,g){Gd(a,c,d,e);jb(a,c,d,e,f,g);e.$$parserName="number";e.$parsers.push(function(a){return e.$isEmpty(a)?null:sg.test(a)?parseFloat(a):w});e.$formatters.push(function(a){if(!e.$isEmpty(a)){if(!V(a))throw lb("numfmt",a);a=a.toString()}return a});if(A(d.min)||d.ngMin){var h;e.$validators.min=function(a){return e.$isEmpty(a)||v(h)||a>=h};d.$observe("min",function(a){A(a)&&!V(a)&&(a=parseFloat(a,10));h=V(a)&&!isNaN(a)?a:w;e.$validate()})}if(A(d.max)||
d.ngMax){var l;e.$validators.max=function(a){return e.$isEmpty(a)||v(l)||a<=l};d.$observe("max",function(a){A(a)&&!V(a)&&(a=parseFloat(a,10));l=V(a)&&!isNaN(a)?a:w;e.$validate()})}},url:function(a,c,d,e,f,g){jb(a,c,d,e,f,g);ic(e);e.$$parserName="url";e.$validators.url=function(a,c){var d=a||c;return e.$isEmpty(d)||qg.test(d)}},email:function(a,c,d,e,f,g){jb(a,c,d,e,f,g);ic(e);e.$$parserName="email";e.$validators.email=function(a,c){var d=a||c;return e.$isEmpty(d)||rg.test(d)}},radio:function(a,c,
d,e){v(d.name)&&c.attr("name",++nb);c.on("click",function(a){c[0].checked&&e.$setViewValue(d.value,a&&a.type)});e.$render=function(){c[0].checked=d.value==e.$viewValue};d.$observe("value",e.$render)},checkbox:function(a,c,d,e,f,g,h,l){var k=Hd(l,a,"ngTrueValue",d.ngTrueValue,!0),n=Hd(l,a,"ngFalseValue",d.ngFalseValue,!1);c.on("click",function(a){e.$setViewValue(c[0].checked,a&&a.type)});e.$render=function(){c[0].checked=e.$viewValue};e.$isEmpty=function(a){return!1===a};e.$formatters.push(function(a){return ka(a,
k)});e.$parsers.push(function(a){return a?k:n})},hidden:y,button:y,submit:y,reset:y,file:y},Dc=["$browser","$sniffer","$filter","$parse",function(a,c,d,e){return{restrict:"E",require:["?ngModel"],link:{pre:function(f,g,h,l){l[0]&&(Rd[F(h.type)]||Rd.text)(f,g,h,l[0],c,a,d,e)}}}}],tg=/^(true|false|\d+)$/,Le=function(){return{restrict:"A",priority:100,compile:function(a,c){return tg.test(c.ngValue)?function(a,c,f){f.$set("value",a.$eval(f.ngValue))}:function(a,c,f){a.$watch(f.ngValue,function(a){f.$set("value",
a)})}}}},le=["$compile",function(a){return{restrict:"AC",compile:function(c){a.$$addBindingClass(c);return function(c,e,f){a.$$addBindingInfo(e,f.ngBind);e=e[0];c.$watch(f.ngBind,function(a){e.textContent=v(a)?"":a})}}}}],ne=["$interpolate","$compile",function(a,c){return{compile:function(d){c.$$addBindingClass(d);return function(d,f,g){d=a(f.attr(g.$attr.ngBindTemplate));c.$$addBindingInfo(f,d.expressions);f=f[0];g.$observe("ngBindTemplate",function(a){f.textContent=v(a)?"":a})}}}}],me=["$sce","$parse",
"$compile",function(a,c,d){return{restrict:"A",compile:function(e,f){var g=c(f.ngBindHtml),h=c(f.ngBindHtml,function(a){return(a||"").toString()});d.$$addBindingClass(e);return function(c,e,f){d.$$addBindingInfo(e,f.ngBindHtml);c.$watch(h,function(){e.html(a.getTrustedHtml(g(c))||"")})}}}}],Ke=qa({restrict:"A",require:"ngModel",link:function(a,c,d,e){e.$viewChangeListeners.push(function(){a.$eval(d.ngChange)})}}),oe=jc("",!0),qe=jc("Odd",0),pe=jc("Even",1),re=Na({compile:function(a,c){c.$set("ngCloak",
w);a.removeClass("ng-cloak")}}),se=[function(){return{restrict:"A",scope:!0,controller:"@",priority:500}}],Ic={},ug={blur:!0,focus:!0};m("click dblclick mousedown mouseup mouseover mouseout mousemove mouseenter mouseleave keydown keyup keypress submit focus blur copy cut paste".split(" "),function(a){var c=ya("ng-"+a);Ic[c]=["$parse","$rootScope",function(d,e){return{restrict:"A",compile:function(f,g){var h=d(g[c],null,!0);return function(c,d){d.on(a,function(d){var f=function(){h(c,{$event:d})};
ug[a]&&e.$$phase?c.$evalAsync(f):c.$apply(f)})}}}}]});var ve=["$animate",function(a){return{multiElement:!0,transclude:"element",priority:600,terminal:!0,restrict:"A",$$tlb:!0,link:function(c,d,e,f,g){var h,l,k;c.$watch(e.ngIf,function(c){c?l||g(function(c,f){l=f;c[c.length++]=X.createComment(" end ngIf: "+e.ngIf+" ");h={clone:c};a.enter(c,d.parent(),d)}):(k&&(k.remove(),k=null),l&&(l.$destroy(),l=null),h&&(k=rb(h.clone),a.leave(k).then(function(){k=null}),h=null))})}}}],we=["$templateRequest","$anchorScroll",
"$animate",function(a,c,d){return{restrict:"ECA",priority:400,terminal:!0,transclude:"element",controller:aa.noop,compile:function(e,f){var g=f.ngInclude||f.src,h=f.onload||"",l=f.autoscroll;return function(e,f,m,r,t){var s=0,v,u,q,z=function(){u&&(u.remove(),u=null);v&&(v.$destroy(),v=null);q&&(d.leave(q).then(function(){u=null}),u=q,q=null)};e.$watch(g,function(g){var m=function(){!A(l)||l&&!e.$eval(l)||c()},p=++s;g?(a(g,!0).then(function(a){if(p===s){var c=e.$new();r.template=a;a=t(c,function(a){z();
d.enter(a,null,f).then(m)});v=c;q=a;v.$emit("$includeContentLoaded",g);e.$eval(h)}},function(){p===s&&(z(),e.$emit("$includeContentError",g))}),e.$emit("$includeContentRequested",g)):(z(),r.template=null)})}}}}],Ne=["$compile",function(a){return{restrict:"ECA",priority:-400,require:"ngInclude",link:function(c,d,e,f){/SVG/.test(d[0].toString())?(d.empty(),a(Lc(f.template,X).childNodes)(c,function(a){d.append(a)},{futureParentElement:d})):(d.html(f.template),a(d.contents())(c))}}}],xe=Na({priority:450,
compile:function(){return{pre:function(a,c,d){a.$eval(d.ngInit)}}}}),Je=function(){return{restrict:"A",priority:100,require:"ngModel",link:function(a,c,d,e){var f=c.attr(d.$attr.ngList)||", ",g="false"!==d.ngTrim,h=g?T(f):f;e.$parsers.push(function(a){if(!v(a)){var c=[];a&&m(a.split(h),function(a){a&&c.push(g?T(a):a)});return c}});e.$formatters.push(function(a){return J(a)?a.join(f):w});e.$isEmpty=function(a){return!a||!a.length}}}},mb="ng-valid",Id="ng-invalid",Ya="ng-pristine",Jb="ng-dirty",Kd=
"ng-pending",lb=I("ngModel"),vg=["$scope","$exceptionHandler","$attrs","$element","$parse","$animate","$timeout","$rootScope","$q","$interpolate",function(a,c,d,e,f,g,h,l,k,n){this.$modelValue=this.$viewValue=Number.NaN;this.$$rawModelValue=w;this.$validators={};this.$asyncValidators={};this.$parsers=[];this.$formatters=[];this.$viewChangeListeners=[];this.$untouched=!0;this.$touched=!1;this.$pristine=!0;this.$dirty=!1;this.$valid=!0;this.$invalid=!1;this.$error={};this.$$success={};this.$pending=
w;this.$name=n(d.name||"",!1)(a);this.$$parentForm=Ib;var p=f(d.ngModel),r=p.assign,t=p,s=r,K=null,u,q=this;this.$$setOptions=function(a){if((q.$options=a)&&a.getterSetter){var c=f(d.ngModel+"()"),h=f(d.ngModel+"($$$p)");t=function(a){var d=p(a);x(d)&&(d=c(a));return d};s=function(a,c){x(p(a))?h(a,{$$$p:q.$modelValue}):r(a,q.$modelValue)}}else if(!p.assign)throw lb("nonassign",d.ngModel,xa(e));};this.$render=y;this.$isEmpty=function(a){return v(a)||""===a||null===a||a!==a};var z=0;Fd({ctrl:this,$element:e,
set:function(a,c){a[c]=!0},unset:function(a,c){delete a[c]},$animate:g});this.$setPristine=function(){q.$dirty=!1;q.$pristine=!0;g.removeClass(e,Jb);g.addClass(e,Ya)};this.$setDirty=function(){q.$dirty=!0;q.$pristine=!1;g.removeClass(e,Ya);g.addClass(e,Jb);q.$$parentForm.$setDirty()};this.$setUntouched=function(){q.$touched=!1;q.$untouched=!0;g.setClass(e,"ng-untouched","ng-touched")};this.$setTouched=function(){q.$touched=!0;q.$untouched=!1;g.setClass(e,"ng-touched","ng-untouched")};this.$rollbackViewValue=
function(){h.cancel(K);q.$viewValue=q.$$lastCommittedViewValue;q.$render()};this.$validate=function(){if(!V(q.$modelValue)||!isNaN(q.$modelValue)){var a=q.$$rawModelValue,c=q.$valid,d=q.$modelValue,e=q.$options&&q.$options.allowInvalid;q.$$runValidators(a,q.$$lastCommittedViewValue,function(f){e||c===f||(q.$modelValue=f?a:w,q.$modelValue!==d&&q.$$writeModelToScope())})}};this.$$runValidators=function(a,c,d){function e(){var d=!0;m(q.$validators,function(e,f){var g=e(a,c);d=d&&g;h(f,g)});return d?
!0:(m(q.$asyncValidators,function(a,c){h(c,null)}),!1)}function f(){var d=[],e=!0;m(q.$asyncValidators,function(f,g){var k=f(a,c);if(!k||!x(k.then))throw lb("$asyncValidators",k);h(g,w);d.push(k.then(function(){h(g,!0)},function(a){e=!1;h(g,!1)}))});d.length?k.all(d).then(function(){g(e)},y):g(!0)}function h(a,c){l===z&&q.$setValidity(a,c)}function g(a){l===z&&d(a)}z++;var l=z;(function(){var a=q.$$parserName||"parse";if(v(u))h(a,null);else return u||(m(q.$validators,function(a,c){h(c,null)}),m(q.$asyncValidators,
function(a,c){h(c,null)})),h(a,u),u;return!0})()?e()?f():g(!1):g(!1)};this.$commitViewValue=function(){var a=q.$viewValue;h.cancel(K);if(q.$$lastCommittedViewValue!==a||""===a&&q.$$hasNativeValidators)q.$$lastCommittedViewValue=a,q.$pristine&&this.$setDirty(),this.$$parseAndValidate()};this.$$parseAndValidate=function(){var c=q.$$lastCommittedViewValue;if(u=v(c)?w:!0)for(var d=0;d<q.$parsers.length;d++)if(c=q.$parsers[d](c),v(c)){u=!1;break}V(q.$modelValue)&&isNaN(q.$modelValue)&&(q.$modelValue=t(a));
var e=q.$modelValue,f=q.$options&&q.$options.allowInvalid;q.$$rawModelValue=c;f&&(q.$modelValue=c,q.$modelValue!==e&&q.$$writeModelToScope());q.$$runValidators(c,q.$$lastCommittedViewValue,function(a){f||(q.$modelValue=a?c:w,q.$modelValue!==e&&q.$$writeModelToScope())})};this.$$writeModelToScope=function(){s(a,q.$modelValue);m(q.$viewChangeListeners,function(a){try{a()}catch(d){c(d)}})};this.$setViewValue=function(a,c){q.$viewValue=a;q.$options&&!q.$options.updateOnDefault||q.$$debounceViewValueCommit(c)};
this.$$debounceViewValueCommit=function(c){var d=0,e=q.$options;e&&A(e.debounce)&&(e=e.debounce,V(e)?d=e:V(e[c])?d=e[c]:V(e["default"])&&(d=e["default"]));h.cancel(K);d?K=h(function(){q.$commitViewValue()},d):l.$$phase?q.$commitViewValue():a.$apply(function(){q.$commitViewValue()})};a.$watch(function(){var c=t(a);if(c!==q.$modelValue&&(q.$modelValue===q.$modelValue||c===c)){q.$modelValue=q.$$rawModelValue=c;u=w;for(var d=q.$formatters,e=d.length,f=c;e--;)f=d[e](f);q.$viewValue!==f&&(q.$viewValue=
q.$$lastCommittedViewValue=f,q.$render(),q.$$runValidators(c,f,y))}return c})}],Ie=["$rootScope",function(a){return{restrict:"A",require:["ngModel","^?form","^?ngModelOptions"],controller:vg,priority:1,compile:function(c){c.addClass(Ya).addClass("ng-untouched").addClass(mb);return{pre:function(a,c,f,g){var h=g[0];c=g[1]||h.$$parentForm;h.$$setOptions(g[2]&&g[2].$options);c.$addControl(h);f.$observe("name",function(a){h.$name!==a&&h.$$parentForm.$$renameControl(h,a)});a.$on("$destroy",function(){h.$$parentForm.$removeControl(h)})},
post:function(c,e,f,g){var h=g[0];if(h.$options&&h.$options.updateOn)e.on(h.$options.updateOn,function(a){h.$$debounceViewValueCommit(a&&a.type)});e.on("blur",function(e){h.$touched||(a.$$phase?c.$evalAsync(h.$setTouched):c.$apply(h.$setTouched))})}}}}}],wg=/(\s+|^)default(\s+|$)/,Me=function(){return{restrict:"A",controller:["$scope","$attrs",function(a,c){var d=this;this.$options=ga(a.$eval(c.ngModelOptions));A(this.$options.updateOn)?(this.$options.updateOnDefault=!1,this.$options.updateOn=T(this.$options.updateOn.replace(wg,
function(){d.$options.updateOnDefault=!0;return" "}))):this.$options.updateOnDefault=!0}]}},ye=Na({terminal:!0,priority:1E3}),xg=I("ngOptions"),yg=/^\s*([\s\S]+?)(?:\s+as\s+([\s\S]+?))?(?:\s+group\s+by\s+([\s\S]+?))?(?:\s+disable\s+when\s+([\s\S]+?))?\s+for\s+(?:([\$\w][\$\w]*)|(?:\(\s*([\$\w][\$\w]*)\s*,\s*([\$\w][\$\w]*)\s*\)))\s+in\s+([\s\S]+?)(?:\s+track\s+by\s+([\s\S]+?))?$/,Ge=["$compile","$parse",function(a,c){function d(a,d,e){function f(a,c,d,e,h){this.selectValue=a;this.viewValue=c;this.label=
d;this.group=e;this.disabled=h}function n(a){var c;if(!s&&Da(a))c=a;else{c=[];for(var d in a)a.hasOwnProperty(d)&&"$"!==d.charAt(0)&&c.push(d)}return c}var m=a.match(yg);if(!m)throw xg("iexp",a,xa(d));var r=m[5]||m[7],s=m[6];a=/ as /.test(m[0])&&m[1];var v=m[9];d=c(m[2]?m[1]:r);var w=a&&c(a)||d,u=v&&c(v),q=v?function(a,c){return u(e,c)}:function(a){return Ga(a)},z=function(a,c){return q(a,x(a,c))},y=c(m[2]||m[1]),A=c(m[3]||""),O=c(m[4]||""),H=c(m[8]),C={},x=s?function(a,c){C[s]=c;C[r]=a;return C}:
function(a){C[r]=a;return C};return{trackBy:v,getTrackByValue:z,getWatchables:c(H,function(a){var c=[];a=a||[];for(var d=n(a),f=d.length,h=0;h<f;h++){var g=a===d?h:d[h],k=x(a[g],g),g=q(a[g],k);c.push(g);if(m[2]||m[1])g=y(e,k),c.push(g);m[4]&&(k=O(e,k),c.push(k))}return c}),getOptions:function(){for(var a=[],c={},d=H(e)||[],h=n(d),g=h.length,m=0;m<g;m++){var p=d===h?m:h[m],r=x(d[p],p),s=w(e,r),p=q(s,r),t=y(e,r),u=A(e,r),r=O(e,r),s=new f(p,s,t,u,r);a.push(s);c[p]=s}return{items:a,selectValueMap:c,getOptionFromViewValue:function(a){return c[z(a)]},
getViewValueFromOption:function(a){return v?aa.copy(a.viewValue):a.viewValue}}}}}var e=X.createElement("option"),f=X.createElement("optgroup");return{restrict:"A",terminal:!0,require:["select","?ngModel"],link:function(c,h,l,k){function n(a,c){a.element=c;c.disabled=a.disabled;a.value!==c.value&&(c.value=a.selectValue);a.label!==c.label&&(c.label=a.label,c.textContent=a.label)}function p(a,c,d,e){c&&F(c.nodeName)===d?d=c:(d=e.cloneNode(!1),c?a.insertBefore(d,c):a.appendChild(d));return d}function r(a){for(var c;a;)c=
a.nextSibling,Wb(a),a=c}function s(a){var c=q&&q[0],d=H&&H[0];if(c||d)for(;a&&(a===c||a===d);)a=a.nextSibling;return a}function v(){var a=x&&u.readValue();x=B.getOptions();var c={},d=h[0].firstChild;O&&h.prepend(q);d=s(d);x.items.forEach(function(a){var g,k;a.group?(g=c[a.group],g||(g=p(h[0],d,"optgroup",f),d=g.nextSibling,g.label=a.group,g=c[a.group]={groupElement:g,currentOptionElement:g.firstChild}),k=p(g.groupElement,g.currentOptionElement,"option",e),n(a,k),g.currentOptionElement=k.nextSibling):
(k=p(h[0],d,"option",e),n(a,k),d=k.nextSibling)});Object.keys(c).forEach(function(a){r(c[a].currentOptionElement)});r(d);w.$render();if(!w.$isEmpty(a)){var g=u.readValue();(B.trackBy?ka(a,g):a===g)||(w.$setViewValue(g),w.$render())}}var w=k[1];if(w){var u=k[0];k=l.multiple;for(var q,z=0,y=h.children(),A=y.length;z<A;z++)if(""===y[z].value){q=y.eq(z);break}var O=!!q,H=C(e.cloneNode(!1));H.val("?");var x,B=d(l.ngOptions,h,c);k?(w.$isEmpty=function(a){return!a||0===a.length},u.writeValue=function(a){x.items.forEach(function(a){a.element.selected=
!1});a&&a.forEach(function(a){(a=x.getOptionFromViewValue(a))&&!a.disabled&&(a.element.selected=!0)})},u.readValue=function(){var a=h.val()||[],c=[];m(a,function(a){(a=x.selectValueMap[a])&&!a.disabled&&c.push(x.getViewValueFromOption(a))});return c},B.trackBy&&c.$watchCollection(function(){if(J(w.$viewValue))return w.$viewValue.map(function(a){return B.getTrackByValue(a)})},function(){w.$render()})):(u.writeValue=function(a){var c=x.getOptionFromViewValue(a);c&&!c.disabled?h[0].value!==c.selectValue&&
(H.remove(),O||q.remove(),h[0].value=c.selectValue,c.element.selected=!0,c.element.setAttribute("selected","selected")):null===a||O?(H.remove(),O||h.prepend(q),h.val(""),q.prop("selected",!0),q.attr("selected",!0)):(O||q.remove(),h.prepend(H),h.val("?"),H.prop("selected",!0),H.attr("selected",!0))},u.readValue=function(){var a=x.selectValueMap[h.val()];return a&&!a.disabled?(O||q.remove(),H.remove(),x.getViewValueFromOption(a)):null},B.trackBy&&c.$watch(function(){return B.getTrackByValue(w.$viewValue)},
function(){w.$render()}));O?(q.remove(),a(q)(c),q.removeClass("ng-scope")):q=C(e.cloneNode(!1));v();c.$watchCollection(B.getWatchables,v)}}}}],ze=["$locale","$interpolate","$log",function(a,c,d){var e=/{}/g,f=/^when(Minus)?(.+)$/;return{link:function(g,h,l){function k(a){h.text(a||"")}var n=l.count,p=l.$attr.when&&h.attr(l.$attr.when),r=l.offset||0,s=g.$eval(p)||{},w={},A=c.startSymbol(),u=c.endSymbol(),q=A+n+"-"+r+u,z=aa.noop,x;m(l,function(a,c){var d=f.exec(c);d&&(d=(d[1]?"-":"")+F(d[2]),s[d]=h.attr(l.$attr[c]))});
m(s,function(a,d){w[d]=c(a.replace(e,q))});g.$watch(n,function(c){var e=parseFloat(c),f=isNaN(e);f||e in s||(e=a.pluralCat(e-r));e===x||f&&V(x)&&isNaN(x)||(z(),f=w[e],v(f)?(null!=c&&d.debug("ngPluralize: no rule defined for '"+e+"' in "+p),z=y,k()):z=g.$watch(f,k),x=e)})}}}],Ae=["$parse","$animate",function(a,c){var d=I("ngRepeat"),e=function(a,c,d,e,k,m,p){a[d]=e;k&&(a[k]=m);a.$index=c;a.$first=0===c;a.$last=c===p-1;a.$middle=!(a.$first||a.$last);a.$odd=!(a.$even=0===(c&1))};return{restrict:"A",
multiElement:!0,transclude:"element",priority:1E3,terminal:!0,$$tlb:!0,compile:function(f,g){var h=g.ngRepeat,l=X.createComment(" end ngRepeat: "+h+" "),k=h.match(/^\s*([\s\S]+?)\s+in\s+([\s\S]+?)(?:\s+as\s+([\s\S]+?))?(?:\s+track\s+by\s+([\s\S]+?))?\s*$/);if(!k)throw d("iexp",h);var n=k[1],p=k[2],r=k[3],s=k[4],k=n.match(/^(?:(\s*[\$\w]+)|\(\s*([\$\w]+)\s*,\s*([\$\w]+)\s*\))$/);if(!k)throw d("iidexp",n);var v=k[3]||k[1],y=k[2];if(r&&(!/^[$a-zA-Z_][$a-zA-Z0-9_]*$/.test(r)||/^(null|undefined|this|\$index|\$first|\$middle|\$last|\$even|\$odd|\$parent|\$root|\$id)$/.test(r)))throw d("badident",
r);var u,q,z,A,x={$id:Ga};s?u=a(s):(z=function(a,c){return Ga(c)},A=function(a){return a});return function(a,f,g,k,n){u&&(q=function(c,d,e){y&&(x[y]=c);x[v]=d;x.$index=e;return u(a,x)});var s=ha();a.$watchCollection(p,function(g){var k,p,t=f[0],u,x=ha(),B,G,J,M,I,F,L;r&&(a[r]=g);if(Da(g))I=g,p=q||z;else for(L in p=q||A,I=[],g)ta.call(g,L)&&"$"!==L.charAt(0)&&I.push(L);B=I.length;L=Array(B);for(k=0;k<B;k++)if(G=g===I?k:I[k],J=g[G],M=p(G,J,k),s[M])F=s[M],delete s[M],x[M]=F,L[k]=F;else{if(x[M])throw m(L,
function(a){a&&a.scope&&(s[a.id]=a)}),d("dupes",h,M,J);L[k]={id:M,scope:w,clone:w};x[M]=!0}for(u in s){F=s[u];M=rb(F.clone);c.leave(M);if(M[0].parentNode)for(k=0,p=M.length;k<p;k++)M[k].$$NG_REMOVED=!0;F.scope.$destroy()}for(k=0;k<B;k++)if(G=g===I?k:I[k],J=g[G],F=L[k],F.scope){u=t;do u=u.nextSibling;while(u&&u.$$NG_REMOVED);F.clone[0]!=u&&c.move(rb(F.clone),null,C(t));t=F.clone[F.clone.length-1];e(F.scope,k,v,J,y,G,B)}else n(function(a,d){F.scope=d;var f=l.cloneNode(!1);a[a.length++]=f;c.enter(a,
null,C(t));t=f;F.clone=a;x[F.id]=F;e(F.scope,k,v,J,y,G,B)});s=x})}}}}],Be=["$animate",function(a){return{restrict:"A",multiElement:!0,link:function(c,d,e){c.$watch(e.ngShow,function(c){a[c?"removeClass":"addClass"](d,"ng-hide",{tempClasses:"ng-hide-animate"})})}}}],ue=["$animate",function(a){return{restrict:"A",multiElement:!0,link:function(c,d,e){c.$watch(e.ngHide,function(c){a[c?"addClass":"removeClass"](d,"ng-hide",{tempClasses:"ng-hide-animate"})})}}}],Ce=Na(function(a,c,d){a.$watch(d.ngStyle,
function(a,d){d&&a!==d&&m(d,function(a,d){c.css(d,"")});a&&c.css(a)},!0)}),De=["$animate",function(a){return{require:"ngSwitch",controller:["$scope",function(){this.cases={}}],link:function(c,d,e,f){var g=[],h=[],l=[],k=[],n=function(a,c){return function(){a.splice(c,1)}};c.$watch(e.ngSwitch||e.on,function(c){var d,e;d=0;for(e=l.length;d<e;++d)a.cancel(l[d]);d=l.length=0;for(e=k.length;d<e;++d){var s=rb(h[d].clone);k[d].$destroy();(l[d]=a.leave(s)).then(n(l,d))}h.length=0;k.length=0;(g=f.cases["!"+
c]||f.cases["?"])&&m(g,function(c){c.transclude(function(d,e){k.push(e);var f=c.element;d[d.length++]=X.createComment(" end ngSwitchWhen: ");h.push({clone:d});a.enter(d,f.parent(),f)})})})}}}],Ee=Na({transclude:"element",priority:1200,require:"^ngSwitch",multiElement:!0,link:function(a,c,d,e,f){e.cases["!"+d.ngSwitchWhen]=e.cases["!"+d.ngSwitchWhen]||[];e.cases["!"+d.ngSwitchWhen].push({transclude:f,element:c})}}),Fe=Na({transclude:"element",priority:1200,require:"^ngSwitch",multiElement:!0,link:function(a,
c,d,e,f){e.cases["?"]=e.cases["?"]||[];e.cases["?"].push({transclude:f,element:c})}}),He=Na({restrict:"EAC",link:function(a,c,d,e,f){if(!f)throw I("ngTransclude")("orphan",xa(c));f(function(a){c.empty();c.append(a)})}}),he=["$templateCache",function(a){return{restrict:"E",terminal:!0,compile:function(c,d){"text/ng-template"==d.type&&a.put(d.id,c[0].text)}}}],zg={$setViewValue:y,$render:y},Ag=["$element","$scope","$attrs",function(a,c,d){var e=this,f=new Ua;e.ngModelCtrl=zg;e.unknownOption=C(X.createElement("option"));
e.renderUnknownOption=function(c){c="? "+Ga(c)+" ?";e.unknownOption.val(c);a.prepend(e.unknownOption);a.val(c)};c.$on("$destroy",function(){e.renderUnknownOption=y});e.removeUnknownOption=function(){e.unknownOption.parent()&&e.unknownOption.remove()};e.readValue=function(){e.removeUnknownOption();return a.val()};e.writeValue=function(c){e.hasOption(c)?(e.removeUnknownOption(),a.val(c),""===c&&e.emptyOption.prop("selected",!0)):null==c&&e.emptyOption?(e.removeUnknownOption(),a.val("")):e.renderUnknownOption(c)};
e.addOption=function(a,c){Ta(a,'"option value"');""===a&&(e.emptyOption=c);var d=f.get(a)||0;f.put(a,d+1)};e.removeOption=function(a){var c=f.get(a);c&&(1===c?(f.remove(a),""===a&&(e.emptyOption=w)):f.put(a,c-1))};e.hasOption=function(a){return!!f.get(a)}}],ie=function(){return{restrict:"E",require:["select","?ngModel"],controller:Ag,link:function(a,c,d,e){var f=e[1];if(f){var g=e[0];g.ngModelCtrl=f;f.$render=function(){g.writeValue(f.$viewValue)};c.on("change",function(){a.$apply(function(){f.$setViewValue(g.readValue())})});
if(d.multiple){g.readValue=function(){var a=[];m(c.find("option"),function(c){c.selected&&a.push(c.value)});return a};g.writeValue=function(a){var d=new Ua(a);m(c.find("option"),function(a){a.selected=A(d.get(a.value))})};var h,l=NaN;a.$watch(function(){l!==f.$viewValue||ka(h,f.$viewValue)||(h=ja(f.$viewValue),f.$render());l=f.$viewValue});f.$isEmpty=function(a){return!a||0===a.length}}}}}},ke=["$interpolate",function(a){return{restrict:"E",priority:100,compile:function(c,d){if(A(d.value))var e=a(d.value,
!0);else{var f=a(c.text(),!0);f||d.$set("value",c.text())}return function(a,c,d){function k(a){p.addOption(a,c);p.ngModelCtrl.$render();c[0].hasAttribute("selected")&&(c[0].selected=!0)}var m=c.parent(),p=m.data("$selectController")||m.parent().data("$selectController");if(p&&p.ngModelCtrl){if(e){var r;d.$observe("value",function(a){A(r)&&p.removeOption(r);r=a;k(a)})}else f?a.$watch(f,function(a,c){d.$set("value",a);c!==a&&p.removeOption(c);k(a)}):k(d.value);c.on("$destroy",function(){p.removeOption(d.value);
p.ngModelCtrl.$render()})}}}}}],je=qa({restrict:"E",terminal:!1}),Fc=function(){return{restrict:"A",require:"?ngModel",link:function(a,c,d,e){e&&(d.required=!0,e.$validators.required=function(a,c){return!d.required||!e.$isEmpty(c)},d.$observe("required",function(){e.$validate()}))}}},Ec=function(){return{restrict:"A",require:"?ngModel",link:function(a,c,d,e){if(e){var f,g=d.ngPattern||d.pattern;d.$observe("pattern",function(a){G(a)&&0<a.length&&(a=new RegExp("^"+a+"$"));if(a&&!a.test)throw I("ngPattern")("noregexp",
g,a,xa(c));f=a||w;e.$validate()});e.$validators.pattern=function(a,c){return e.$isEmpty(c)||v(f)||f.test(c)}}}}},Hc=function(){return{restrict:"A",require:"?ngModel",link:function(a,c,d,e){if(e){var f=-1;d.$observe("maxlength",function(a){a=Y(a);f=isNaN(a)?-1:a;e.$validate()});e.$validators.maxlength=function(a,c){return 0>f||e.$isEmpty(c)||c.length<=f}}}}},Gc=function(){return{restrict:"A",require:"?ngModel",link:function(a,c,d,e){if(e){var f=0;d.$observe("minlength",function(a){f=Y(a)||0;e.$validate()});
e.$validators.minlength=function(a,c){return e.$isEmpty(c)||c.length>=f}}}}};Q.angular.bootstrap?console.log("WARNING: Tried to load angular more than once."):(ae(),ce(aa),aa.module("ngLocale",[],["$provide",function(a){function c(a){a+="";var c=a.indexOf(".");return-1==c?0:a.length-c-1}a.value("$locale",{DATETIME_FORMATS:{AMPMS:["AM","PM"],DAY:"Sunday Monday Tuesday Wednesday Thursday Friday Saturday".split(" "),ERANAMES:["Before Christ","Anno Domini"],ERAS:["BC","AD"],FIRSTDAYOFWEEK:6,MONTH:"January February March April May June July August September October November December".split(" "),
SHORTDAY:"Sun Mon Tue Wed Thu Fri Sat".split(" "),SHORTMONTH:"Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec".split(" "),WEEKENDRANGE:[5,6],fullDate:"EEEE, MMMM d, y",longDate:"MMMM d, y",medium:"MMM d, y h:mm:ss a",mediumDate:"MMM d, y",mediumTime:"h:mm:ss a","short":"M/d/yy h:mm a",shortDate:"M/d/yy",shortTime:"h:mm a"},NUMBER_FORMATS:{CURRENCY_SYM:"$",DECIMAL_SEP:".",GROUP_SEP:",",PATTERNS:[{gSize:3,lgSize:3,maxFrac:3,minFrac:0,minInt:1,negPre:"-",negSuf:"",posPre:"",posSuf:""},{gSize:3,lgSize:3,
maxFrac:2,minFrac:2,minInt:1,negPre:"-\u00a4",negSuf:"",posPre:"\u00a4",posSuf:""}]},id:"en-us",pluralCat:function(a,e){var f=a|0,g=e;w===g&&(g=Math.min(c(a),3));Math.pow(10,g);return 1==f&&0==g?"one":"other"}})}]),C(X).ready(function(){Xd(X,yc)}));if(typeof define === "function"){define("YS-frame/angular/1.4.6/angular.min",[],function(){return angular;})};})(window,document);!window.angular.$$csp().noInlineStyle&&window.angular.element(document.head).prepend('<style type="text/css">@charset "UTF-8";[ng\\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\\:form{display:block;}.ng-animate-shim{visibility:hidden;}.ng-anchor{position:absolute;}</style>');
//# sourceMappingURL=angular.min.js.map

/** layui-v2.2.6 MIT License By https://www.layui.com */
 ;!function(e){"use strict";var t=document,n={modules:{},status:{},timeout:10,event:{}},o=function(){this.v="2.2.6"},r=function(){var e=t.currentScript?t.currentScript.src:function(){for(var e,n=t.scripts,o=n.length-1,r=o;r>0;r--)if("interactive"===n[r].readyState){e=n[r].src;break}return e||n[o].src}();return e.substring(0,e.lastIndexOf("/")+1)}(),a=function(t){e.console&&console.error&&console.error("Layui hint: "+t)},i="undefined"!=typeof opera&&"[object Opera]"===opera.toString(),u={layer:"modules/layer",laydate:"modules/laydate",laypage:"modules/laypage",laytpl:"modules/laytpl",layim:"modules/layim",layedit:"modules/layedit",form:"modules/form",upload:"modules/upload",tree:"modules/tree",table:"modules/table",element:"modules/element",util:"modules/util",flow:"modules/flow",carousel:"modules/carousel",code:"modules/code",jquery:"modules/jquery",mobile:"modules/mobile","layui.all":"../layui.all"};o.prototype.cache=n,o.prototype.define=function(e,t){var o=this,r="function"==typeof e,a=function(){var e=function(e,t){layui[e]=t,n.status[e]=!0};return"function"==typeof t&&t(function(o,r){e(o,r),n.callback[o]=function(){t(e)}}),this};return r&&(t=e,e=[]),layui["layui.all"]||!layui["layui.all"]&&layui["layui.mobile"]?a.call(o):(o.use(e,a),o)},o.prototype.use=function(e,o,l){function s(e,t){var o="PLaySTATION 3"===navigator.platform?/^complete$/:/^(complete|loaded)$/;("load"===e.type||o.test((e.currentTarget||e.srcElement).readyState))&&(n.modules[f]=t,d.removeChild(v),function r(){return++m>1e3*n.timeout/4?a(f+" is not a valid module"):void(n.status[f]?c():setTimeout(r,4))}())}function c(){l.push(layui[f]),e.length>1?y.use(e.slice(1),o,l):"function"==typeof o&&o.apply(layui,l)}var y=this,p=n.dir=n.dir?n.dir:r,d=t.getElementsByTagName("head")[0];e="string"==typeof e?[e]:e,window.jQuery&&jQuery.fn.on&&(y.each(e,function(t,n){"jquery"===n&&e.splice(t,1)}),layui.jquery=layui.$=jQuery);var f=e[0],m=0;if(l=l||[],n.host=n.host||(p.match(/\/\/([\s\S]+?)\//)||["//"+location.host+"/"])[0],0===e.length||layui["layui.all"]&&u[f]||!layui["layui.all"]&&layui["layui.mobile"]&&u[f])return c(),y;if(n.modules[f])!function g(){return++m>1e3*n.timeout/4?a(f+" is not a valid module"):void("string"==typeof n.modules[f]&&n.status[f]?c():setTimeout(g,4))}();else{var v=t.createElement("script"),h=(u[f]?p+"lay/":/^\{\/\}/.test(y.modules[f])?"":n.base||"")+(y.modules[f]||f)+".js";h=h.replace(/^\{\/\}/,""),v.async=!0,v.charset="utf-8",v.src=h+function(){var e=n.version===!0?n.v||(new Date).getTime():n.version||"";return e?"?v="+e:""}(),d.appendChild(v),!v.attachEvent||v.attachEvent.toString&&v.attachEvent.toString().indexOf("[native code")<0||i?v.addEventListener("load",function(e){s(e,h)},!1):v.attachEvent("onreadystatechange",function(e){s(e,h)}),n.modules[f]=h}return y},o.prototype.getStyle=function(t,n){var o=t.currentStyle?t.currentStyle:e.getComputedStyle(t,null);return o[o.getPropertyValue?"getPropertyValue":"getAttribute"](n)},o.prototype.link=function(e,o,r){var i=this,u=t.createElement("link"),l=t.getElementsByTagName("head")[0];"string"==typeof o&&(r=o);var s=(r||e).replace(/\.|\//g,""),c=u.id="layuicss-"+s,y=0;return u.rel="stylesheet",u.href=e+(n.debug?"?v="+(new Date).getTime():""),u.media="all",t.getElementById(c)||l.appendChild(u),"function"!=typeof o?i:(function p(){return++y>1e3*n.timeout/100?a(e+" timeout"):void(1989===parseInt(i.getStyle(t.getElementById(c),"width"))?function(){o()}():setTimeout(p,100))}(),i)},n.callback={},o.prototype.factory=function(e){if(layui[e])return"function"==typeof n.callback[e]?n.callback[e]:null},o.prototype.addcss=function(e,t,o){return layui.link(n.dir+"css/"+e,t,o)},o.prototype.img=function(e,t,n){var o=new Image;return o.src=e,o.complete?t(o):(o.onload=function(){o.onload=null,t(o)},void(o.onerror=function(e){o.onerror=null,n(e)}))},o.prototype.config=function(e){e=e||{};for(var t in e)n[t]=e[t];return this},o.prototype.modules=function(){var e={};for(var t in u)e[t]=u[t];return e}(),o.prototype.extend=function(e){var t=this;e=e||{};for(var n in e)t[n]||t.modules[n]?a("模块名 "+n+" 已被占用"):t.modules[n]=e[n];return t},o.prototype.router=function(e){var t=this,e=e||location.hash,n={path:[],search:{},hash:(e.match(/[^#](#.*$)/)||[])[1]||""};return/^#\//.test(e)?(e=e.replace(/^#\//,""),n.href="/"+e,e=e.replace(/([^#])(#.*$)/,"$1").split("/")||[],t.each(e,function(e,t){/^\w+=/.test(t)?function(){t=t.split("="),n.search[t[0]]=t[1]}():n.path.push(t)}),n):n},o.prototype.data=function(t,n,o){if(t=t||"layui",o=o||localStorage,e.JSON&&e.JSON.parse){if(null===n)return delete o[t];n="object"==typeof n?n:{key:n};try{var r=JSON.parse(o[t])}catch(a){var r={}}return"value"in n&&(r[n.key]=n.value),n.remove&&delete r[n.key],o[t]=JSON.stringify(r),n.key?r[n.key]:r}},o.prototype.sessionData=function(e,t){return this.data(e,t,sessionStorage)},o.prototype.device=function(t){var n=navigator.userAgent.toLowerCase(),o=function(e){var t=new RegExp(e+"/([^\\s\\_\\-]+)");return e=(n.match(t)||[])[1],e||!1},r={os:function(){return/windows/.test(n)?"windows":/linux/.test(n)?"linux":/iphone|ipod|ipad|ios/.test(n)?"ios":/mac/.test(n)?"mac":void 0}(),ie:function(){return!!(e.ActiveXObject||"ActiveXObject"in e)&&((n.match(/msie\s(\d+)/)||[])[1]||"11")}(),weixin:o("micromessenger")};return t&&!r[t]&&(r[t]=o(t)),r.android=/android/.test(n),r.ios="ios"===r.os,r},o.prototype.hint=function(){return{error:a}},o.prototype.each=function(e,t){var n,o=this;if("function"!=typeof t)return o;if(e=e||[],e.constructor===Object){for(n in e)if(t.call(e[n],n,e[n]))break}else for(n=0;n<e.length&&!t.call(e[n],n,e[n]);n++);return o},o.prototype.sort=function(e,t,n){var o=JSON.parse(JSON.stringify(e||[]));return t?(o.sort(function(e,n){var o=/^-?\d+$/,r=e[t],a=n[t];return o.test(r)&&(r=parseFloat(r)),o.test(a)&&(a=parseFloat(a)),r&&!a?1:!r&&a?-1:r>a?1:r<a?-1:0}),n&&o.reverse(),o):o},o.prototype.stope=function(t){t=t||e.event;try{t.stopPropagation()}catch(n){t.cancelBubble=!0}},o.prototype.onevent=function(e,t,n){return"string"!=typeof e||"function"!=typeof n?this:o.event(e,t,null,n)},o.prototype.event=o.event=function(e,t,o,r){var a=this,i=null,u=t.match(/\((.*)\)$/)||[],l=(e+"."+t).replace(u[0],""),s=u[1]||"",c=function(e,t){var n=t&&t.call(a,o);n===!1&&null===i&&(i=!1)};return r?(n.event[l]=n.event[l]||{},n.event[l][s]=[r],this):(layui.each(n.event[l],function(e,t){return"{*}"===s?void layui.each(t,c):(""===e&&layui.each(t,c),void(e===s&&layui.each(t,c)))}),i)},e.layui=new o}(window);layui.define(function(a){var i=layui.cache;layui.config({dir:i.dir.replace(/lay\/dest\/$/,"")}),a("layui.all",layui.v)});layui.define(function(e){"use strict";var r={open:"{{",close:"}}"},c={exp:function(e){return new RegExp(e,"g")},query:function(e,c,t){var o=["#([\\s\\S])+?","([^{#}])*?"][e||0];return n((c||"")+r.open+o+r.close+(t||""))},escape:function(e){return String(e||"").replace(/&(?!#?[a-zA-Z0-9]+;)/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/'/g,"&#39;").replace(/"/g,"&quot;")},error:function(e,r){var c="Laytpl Error：";return"object"==typeof console&&console.error(c+e+"\n"+(r||"")),c+e}},n=c.exp,t=function(e){this.tpl=e};t.pt=t.prototype,window.errors=0,t.pt.parse=function(e,t){var o=this,p=e,a=n("^"+r.open+"#",""),l=n(r.close+"$","");e=e.replace(/\s+|\r|\t|\n/g," ").replace(n(r.open+"#"),r.open+"# ").replace(n(r.close+"}"),"} "+r.close).replace(/\\/g,"\\\\").replace(n(r.open+"!(.+?)!"+r.close),function(e){return e=e.replace(n("^"+r.open+"!"),"").replace(n("!"+r.close),"").replace(n(r.open+"|"+r.close),function(e){return e.replace(/(.)/g,"\\$1")})}).replace(/(?="|')/g,"\\").replace(c.query(),function(e){return e=e.replace(a,"").replace(l,""),'";'+e.replace(/\\/g,"")+';view+="'}).replace(c.query(1),function(e){var c='"+(';return e.replace(/\s/g,"")===r.open+r.close?"":(e=e.replace(n(r.open+"|"+r.close),""),/^=/.test(e)&&(e=e.replace(/^=/,""),c='"+_escape_('),c+e.replace(/\\/g,"")+')+"')}),e='"use strict";var view = "'+e+'";return view;';try{return o.cache=e=new Function("d, _escape_",e),e(t,c.escape)}catch(u){return delete o.cache,c.error(u,p)}},t.pt.render=function(e,r){var n,t=this;return e?(n=t.cache?t.cache(e,c.escape):t.parse(t.tpl,e),r?void r(n):n):c.error("no data")};var o=function(e){return"string"!=typeof e?c.error("Template not found"):new t(e)};o.config=function(e){e=e||{};for(var c in e)r[c]=e[c]},o.v="1.2.0",e("laytpl",o)});layui.define(function(e){"use strict";var a=document,t="getElementById",n="getElementsByTagName",i="laypage",r="layui-disabled",u=function(e){var a=this;a.config=e||{},a.config.index=++s.index,a.render(!0)};u.prototype.type=function(){var e=this.config;if("object"==typeof e.elem)return void 0===e.elem.length?2:3},u.prototype.view=function(){var e=this,a=e.config,t=a.groups="groups"in a?0|a.groups:5;a.layout="object"==typeof a.layout?a.layout:["prev","page","next"],a.count=0|a.count,a.curr=0|a.curr||1,a.limits="object"==typeof a.limits?a.limits:[10,20,30,40,50],a.limit=0|a.limit||10,a.pages=Math.ceil(a.count/a.limit)||1,a.curr>a.pages&&(a.curr=a.pages),t<0?t=1:t>a.pages&&(t=a.pages),a.prev="prev"in a?a.prev:"&#x4E0A;&#x4E00;&#x9875;",a.next="next"in a?a.next:"&#x4E0B;&#x4E00;&#x9875;";var n=a.pages>t?Math.ceil((a.curr+(t>1?1:0))/(t>0?t:1)):1,i={prev:function(){return a.prev?'<a href="javascript:;" class="layui-laypage-prev'+(1==a.curr?" "+r:"")+'" data-page="'+(a.curr-1)+'">'+a.prev+"</a>":""}(),page:function(){var e=[];if(a.count<1)return"";n>1&&a.first!==!1&&0!==t&&e.push('<a href="javascript:;" class="layui-laypage-first" data-page="1"  title="&#x9996;&#x9875;">'+(a.first||1)+"</a>");var i=Math.floor((t-1)/2),r=n>1?a.curr-i:1,u=n>1?function(){var e=a.curr+(t-i-1);return e>a.pages?a.pages:e}():t;for(u-r<t-1&&(r=u-t+1),a.first!==!1&&r>2&&e.push('<span class="layui-laypage-spr">&#x2026;</span>');r<=u;r++)r===a.curr?e.push('<span class="layui-laypage-curr"><em class="layui-laypage-em" '+(/^#/.test(a.theme)?'style="background-color:'+a.theme+';"':"")+"></em><em>"+r+"</em></span>"):e.push('<a href="javascript:;" data-page="'+r+'">'+r+"</a>");return a.pages>t&&a.pages>u&&a.last!==!1&&(u+1<a.pages&&e.push('<span class="layui-laypage-spr">&#x2026;</span>'),0!==t&&e.push('<a href="javascript:;" class="layui-laypage-last" title="&#x5C3E;&#x9875;"  data-page="'+a.pages+'">'+(a.last||a.pages)+"</a>")),e.join("")}(),next:function(){return a.next?'<a href="javascript:;" class="layui-laypage-next'+(a.curr==a.pages?" "+r:"")+'" data-page="'+(a.curr+1)+'">'+a.next+"</a>":""}(),count:'<span class="layui-laypage-count">共 '+a.count+" 条</span>",limit:function(){var e=['<span class="layui-laypage-limits"><select lay-ignore>'];return layui.each(a.limits,function(t,n){e.push('<option value="'+n+'"'+(n===a.limit?"selected":"")+">"+n+" 条/页</option>")}),e.join("")+"</select></span>"}(),skip:function(){return['<span class="layui-laypage-skip">&#x5230;&#x7B2C;','<input type="text" min="1" value="'+a.curr+'" class="layui-input">','&#x9875;<button type="button" class="layui-laypage-btn">&#x786e;&#x5b9a;</button>',"</span>"].join("")}()};return['<div class="layui-box layui-laypage layui-laypage-'+(a.theme?/^#/.test(a.theme)?"molv":a.theme:"default")+'" id="layui-laypage-'+a.index+'">',function(){var e=[];return layui.each(a.layout,function(a,t){i[t]&&e.push(i[t])}),e.join("")}(),"</div>"].join("")},u.prototype.jump=function(e,a){if(e){var t=this,i=t.config,r=e.children,u=e[n]("button")[0],l=e[n]("input")[0],p=e[n]("select")[0],c=function(){var e=0|l.value.replace(/\s|\D/g,"");e&&(i.curr=e,t.render())};if(a)return c();for(var o=0,y=r.length;o<y;o++)"a"===r[o].nodeName.toLowerCase()&&s.on(r[o],"click",function(){var e=0|this.getAttribute("data-page");e<1||e>i.pages||(i.curr=e,t.render())});p&&s.on(p,"change",function(){var e=this.value;i.curr*e>i.count&&(i.curr=Math.ceil(i.count/e)),i.limit=e,t.render()}),u&&s.on(u,"click",function(){c()})}},u.prototype.skip=function(e){if(e){var a=this,t=e[n]("input")[0];t&&s.on(t,"keyup",function(t){var n=this.value,i=t.keyCode;/^(37|38|39|40)$/.test(i)||(/\D/.test(n)&&(this.value=n.replace(/\D/,"")),13===i&&a.jump(e,!0))})}},u.prototype.render=function(e){var n=this,i=n.config,r=n.type(),u=n.view();2===r?i.elem&&(i.elem.innerHTML=u):3===r?i.elem.html(u):a[t](i.elem)&&(a[t](i.elem).innerHTML=u),i.jump&&i.jump(i,e);var s=a[t]("layui-laypage-"+i.index);n.jump(s),i.hash&&!e&&(location.hash="!"+i.hash+"="+i.curr),n.skip(s)};var s={render:function(e){var a=new u(e);return a.index},index:layui.laypage?layui.laypage.index+1e4:0,on:function(e,a,t){return e.attachEvent?e.attachEvent("on"+a,function(a){a.target=a.srcElement,t.call(e,a)}):e.addEventListener(a,t,!1),this}};e(i,s)});!function(){"use strict";var e=window.layui&&layui.define,t={getPath:function(){var e=document.currentScript?document.currentScript.src:function(){for(var e,t=document.scripts,n=t.length-1,a=n;a>0;a--)if("interactive"===t[a].readyState){e=t[a].src;break}return e||t[n].src}();return e.substring(0,e.lastIndexOf("/")+1)}(),getStyle:function(e,t){var n=e.currentStyle?e.currentStyle:window.getComputedStyle(e,null);return n[n.getPropertyValue?"getPropertyValue":"getAttribute"](t)},link:function(e,a,i){if(n.path){var r=document.getElementsByTagName("head")[0],o=document.createElement("link");"string"==typeof a&&(i=a);var s=(i||e).replace(/\.|\//g,""),l="layuicss-"+s,d=0;o.rel="stylesheet",o.href=n.path+e,o.id=l,document.getElementById(l)||r.appendChild(o),"function"==typeof a&&!function c(){return++d>80?window.console&&console.error("laydate.css: Invalid"):void(1989===parseInt(t.getStyle(document.getElementById(l),"width"))?a():setTimeout(c,100))}()}}},n={v:"5.0.9",config:{},index:window.laydate&&window.laydate.v?1e5:0,path:t.getPath,set:function(e){var t=this;return t.config=w.extend({},t.config,e),t},ready:function(a){var i="laydate",r="",o=(e?"modules/laydate/":"theme/")+"default/laydate.css?v="+n.v+r;return e?layui.addcss(o,a,i):t.link(o,a,i),this}},a=function(){var e=this;return{hint:function(t){e.hint.call(e,t)},config:e.config}},i="laydate",r=".layui-laydate",o="layui-this",s="laydate-disabled",l="开始日期超出了结束日期<br>建议重新选择",d=[100,2e5],c="layui-laydate-static",m="layui-laydate-list",u="laydate-selected",h="layui-laydate-hint",y="laydate-day-prev",f="laydate-day-next",p="layui-laydate-footer",g=".laydate-btns-confirm",v="laydate-time-text",D=".laydate-btns-time",T=function(e){var t=this;t.index=++n.index,t.config=w.extend({},t.config,n.config,e),n.ready(function(){t.init()})},w=function(e){return new C(e)},C=function(e){for(var t=0,n="object"==typeof e?[e]:(this.selector=e,document.querySelectorAll(e||null));t<n.length;t++)this.push(n[t])};C.prototype=[],C.prototype.constructor=C,w.extend=function(){var e=1,t=arguments,n=function(e,t){e=e||(t.constructor===Array?[]:{});for(var a in t)e[a]=t[a]&&t[a].constructor===Object?n(e[a],t[a]):t[a];return e};for(t[0]="object"==typeof t[0]?t[0]:{};e<t.length;e++)"object"==typeof t[e]&&n(t[0],t[e]);return t[0]},w.ie=function(){var e=navigator.userAgent.toLowerCase();return!!(window.ActiveXObject||"ActiveXObject"in window)&&((e.match(/msie\s(\d+)/)||[])[1]||"11")}(),w.stope=function(e){e=e||window.event,e.stopPropagation?e.stopPropagation():e.cancelBubble=!0},w.each=function(e,t){var n,a=this;if("function"!=typeof t)return a;if(e=e||[],e.constructor===Object){for(n in e)if(t.call(e[n],n,e[n]))break}else for(n=0;n<e.length&&!t.call(e[n],n,e[n]);n++);return a},w.digit=function(e,t,n){var a="";e=String(e),t=t||2;for(var i=e.length;i<t;i++)a+="0";return e<Math.pow(10,t)?a+(0|e):e},w.elem=function(e,t){var n=document.createElement(e);return w.each(t||{},function(e,t){n.setAttribute(e,t)}),n},C.addStr=function(e,t){return e=e.replace(/\s+/," "),t=t.replace(/\s+/," ").split(" "),w.each(t,function(t,n){new RegExp("\\b"+n+"\\b").test(e)||(e=e+" "+n)}),e.replace(/^\s|\s$/,"")},C.removeStr=function(e,t){return e=e.replace(/\s+/," "),t=t.replace(/\s+/," ").split(" "),w.each(t,function(t,n){var a=new RegExp("\\b"+n+"\\b");a.test(e)&&(e=e.replace(a,""))}),e.replace(/\s+/," ").replace(/^\s|\s$/,"")},C.prototype.find=function(e){var t=this,n=0,a=[],i="object"==typeof e;return this.each(function(r,o){for(var s=i?[e]:o.querySelectorAll(e||null);n<s.length;n++)a.push(s[n]);t.shift()}),i||(t.selector=(t.selector?t.selector+" ":"")+e),w.each(a,function(e,n){t.push(n)}),t},C.prototype.each=function(e){return w.each.call(this,this,e)},C.prototype.addClass=function(e,t){return this.each(function(n,a){a.className=C[t?"removeStr":"addStr"](a.className,e)})},C.prototype.removeClass=function(e){return this.addClass(e,!0)},C.prototype.hasClass=function(e){var t=!1;return this.each(function(n,a){new RegExp("\\b"+e+"\\b").test(a.className)&&(t=!0)}),t},C.prototype.attr=function(e,t){var n=this;return void 0===t?function(){if(n.length>0)return n[0].getAttribute(e)}():n.each(function(n,a){a.setAttribute(e,t)})},C.prototype.removeAttr=function(e){return this.each(function(t,n){n.removeAttribute(e)})},C.prototype.html=function(e){return this.each(function(t,n){n.innerHTML=e})},C.prototype.val=function(e){return this.each(function(t,n){n.value=e})},C.prototype.append=function(e){return this.each(function(t,n){"object"==typeof e?n.appendChild(e):n.innerHTML=n.innerHTML+e})},C.prototype.remove=function(e){return this.each(function(t,n){e?n.removeChild(e):n.parentNode.removeChild(n)})},C.prototype.on=function(e,t){return this.each(function(n,a){a.attachEvent?a.attachEvent("on"+e,function(e){e.target=e.srcElement,t.call(a,e)}):a.addEventListener(e,t,!1)})},C.prototype.off=function(e,t){return this.each(function(n,a){a.detachEvent?a.detachEvent("on"+e,t):a.removeEventListener(e,t,!1)})},T.isLeapYear=function(e){return e%4===0&&e%100!==0||e%400===0},T.prototype.config={type:"date",range:!1,format:"yyyy-MM-dd",value:null,min:"1900-1-1",max:"2099-12-31",trigger:"focus",show:!1,showBottom:!0,btns:["clear","now","confirm"],lang:"cn",theme:"default",position:null,calendar:!1,mark:{},zIndex:null,done:null,change:null},T.prototype.lang=function(){var e=this,t=e.config,n={cn:{weeks:["SUN","MON","TUE","WED","THU","FRI","SAT"],time:["时","分","秒"],timeTips:"选择时间",startTime:"开始时间",endTime:"结束时间",dateTips:"返回日期",month:["一","二","三","四","五","六","七","八","九","十","十一","十二"],tools:{confirm:"确定",clear:"清空",now:"现在"}},en:{weeks:["Su","Mo","Tu","We","Th","Fr","Sa"],time:["Hours","Minutes","Seconds"],timeTips:"Select Time",startTime:"Start Time",endTime:"End Time",dateTips:"Select Date",month:["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],tools:{confirm:"Confirm",clear:"Clear",now:"Now"}}};return n[t.lang]||n.cn},T.prototype.init=function(){var e=this,t=e.config,n="yyyy|y|MM|M|dd|d|HH|H|mm|m|ss|s",a="static"===t.position,i={year:"yyyy",month:"yyyy-MM",date:"yyyy-MM-dd",time:"HH:mm:ss",datetime:"yyyy-MM-dd HH:mm:ss"};t.elem=w(t.elem),t.eventElem=w(t.eventElem),t.elem[0]&&(t.range===!0&&(t.range="-"),t.format===i.date&&(t.format=i[t.type]),e.format=t.format.match(new RegExp(n+"|.","g"))||[],e.EXP_IF="",e.EXP_SPLIT="",w.each(e.format,function(t,a){var i=new RegExp(n).test(a)?"\\d{"+function(){return new RegExp(n).test(e.format[0===t?t+1:t-1]||"")?/^yyyy|y$/.test(a)?4:a.length:/^yyyy$/.test(a)?"1,4":/^y$/.test(a)?"1,308":"1,2"}()+"}":"\\"+a;e.EXP_IF=e.EXP_IF+i,e.EXP_SPLIT=e.EXP_SPLIT+"("+i+")"}),e.EXP_IF=new RegExp("^"+(t.range?e.EXP_IF+"\\s\\"+t.range+"\\s"+e.EXP_IF:e.EXP_IF)+"$"),e.EXP_SPLIT=new RegExp("^"+e.EXP_SPLIT+"$",""),e.isInput(t.elem[0])||"focus"===t.trigger&&(t.trigger="click"),t.elem.attr("lay-key")||(t.elem.attr("lay-key",e.index),t.eventElem.attr("lay-key",e.index)),t.mark=w.extend({},t.calendar&&"cn"===t.lang?{"0-1-1":"元旦","0-2-14":"情人","0-3-8":"妇女","0-3-12":"植树","0-4-1":"愚人","0-5-1":"劳动","0-5-4":"青年","0-6-1":"儿童","0-9-10":"教师","0-9-18":"国耻","0-10-1":"国庆","0-12-25":"圣诞"}:{},t.mark),w.each(["min","max"],function(e,n){var a=[],i=[];if("number"==typeof t[n]){var r=t[n],o=(new Date).getTime(),s=864e5,l=new Date(r?r<s?o+r*s:r:o);a=[l.getFullYear(),l.getMonth()+1,l.getDate()],r<s||(i=[l.getHours(),l.getMinutes(),l.getSeconds()])}else a=(t[n].match(/\d+-\d+-\d+/)||[""])[0].split("-"),i=(t[n].match(/\d+:\d+:\d+/)||[""])[0].split(":");t[n]={year:0|a[0]||(new Date).getFullYear(),month:a[1]?(0|a[1])-1:(new Date).getMonth(),date:0|a[2]||(new Date).getDate(),hours:0|i[0],minutes:0|i[1],seconds:0|i[2]}}),e.elemID="layui-laydate"+t.elem.attr("lay-key"),(t.show||a)&&e.render(),a||e.events(),t.value&&(t.value.constructor===Date?e.setValue(e.parse(0,e.systemDate(t.value))):e.setValue(t.value)))},T.prototype.render=function(){var e=this,t=e.config,n=e.lang(),a="static"===t.position,i=e.elem=w.elem("div",{id:e.elemID,"class":["layui-laydate",t.range?" layui-laydate-range":"",a?" "+c:"",t.theme&&"default"!==t.theme&&!/^#/.test(t.theme)?" laydate-theme-"+t.theme:""].join("")}),r=e.elemMain=[],o=e.elemHeader=[],s=e.elemCont=[],l=e.table=[],d=e.footer=w.elem("div",{"class":p});if(t.zIndex&&(i.style.zIndex=t.zIndex),w.each(new Array(2),function(e){if(!t.range&&e>0)return!0;var a=w.elem("div",{"class":"layui-laydate-header"}),i=[function(){var e=w.elem("i",{"class":"layui-icon laydate-icon laydate-prev-y"});return e.innerHTML="&#xe65a;",e}(),function(){var e=w.elem("i",{"class":"layui-icon laydate-icon laydate-prev-m"});return e.innerHTML="&#xe603;",e}(),function(){var e=w.elem("div",{"class":"laydate-set-ym"}),t=w.elem("span"),n=w.elem("span");return e.appendChild(t),e.appendChild(n),e}(),function(){var e=w.elem("i",{"class":"layui-icon laydate-icon laydate-next-m"});return e.innerHTML="&#xe602;",e}(),function(){var e=w.elem("i",{"class":"layui-icon laydate-icon laydate-next-y"});return e.innerHTML="&#xe65b;",e}()],d=w.elem("div",{"class":"layui-laydate-content"}),c=w.elem("table"),m=w.elem("thead"),u=w.elem("tr");w.each(i,function(e,t){a.appendChild(t)}),m.appendChild(u),w.each(new Array(6),function(e){var t=c.insertRow(0);w.each(new Array(7),function(a){if(0===e){var i=w.elem("th");i.innerHTML=n.weeks[a],u.appendChild(i)}t.insertCell(a)})}),c.insertBefore(m,c.children[0]),d.appendChild(c),r[e]=w.elem("div",{"class":"layui-laydate-main laydate-main-list-"+e}),r[e].appendChild(a),r[e].appendChild(d),o.push(i),s.push(d),l.push(c)}),w(d).html(function(){var e=[],i=[];return"datetime"===t.type&&e.push('<span lay-type="datetime" class="laydate-btns-time">'+n.timeTips+"</span>"),w.each(t.btns,function(e,r){var o=n.tools[r]||"btn";t.range&&"now"===r||(a&&"clear"===r&&(o="cn"===t.lang?"重置":"Reset"),i.push('<span lay-type="'+r+'" class="laydate-btns-'+r+'">'+o+"</span>"))}),e.push('<div class="laydate-footer-btns">'+i.join("")+"</div>"),e.join("")}()),w.each(r,function(e,t){i.appendChild(t)}),t.showBottom&&i.appendChild(d),/^#/.test(t.theme)){var m=w.elem("style"),u=["#{{id}} .layui-laydate-header{background-color:{{theme}};}","#{{id}} .layui-this{background-color:{{theme}} !important;}"].join("").replace(/{{id}}/g,e.elemID).replace(/{{theme}}/g,t.theme);"styleSheet"in m?(m.setAttribute("type","text/css"),m.styleSheet.cssText=u):m.innerHTML=u,w(i).addClass("laydate-theme-molv"),i.appendChild(m)}e.remove(T.thisElemDate),a?t.elem.append(i):(document.body.appendChild(i),e.position()),e.checkDate().calendar(),e.changeEvent(),T.thisElemDate=e.elemID,"function"==typeof t.ready&&t.ready(w.extend({},t.dateTime,{month:t.dateTime.month+1}))},T.prototype.remove=function(e){var t=this,n=(t.config,w("#"+(e||t.elemID)));return n.hasClass(c)||t.checkDate(function(){n.remove()}),t},T.prototype.position=function(){var e=this,t=e.config,n=e.bindElem||t.elem[0],a=n.getBoundingClientRect(),i=e.elem.offsetWidth,r=e.elem.offsetHeight,o=function(e){return e=e?"scrollLeft":"scrollTop",document.body[e]|document.documentElement[e]},s=function(e){return document.documentElement[e?"clientWidth":"clientHeight"]},l=5,d=a.left,c=a.bottom;d+i+l>s("width")&&(d=s("width")-i-l),c+r+l>s()&&(c=a.top>r?a.top-r:s()-r,c-=2*l),t.position&&(e.elem.style.position=t.position),e.elem.style.left=d+("fixed"===t.position?0:o(1))+"px",e.elem.style.top=c+("fixed"===t.position?0:o())+"px"},T.prototype.hint=function(e){var t=this,n=(t.config,w.elem("div",{"class":h}));n.innerHTML=e||"",w(t.elem).find("."+h).remove(),t.elem.appendChild(n),clearTimeout(t.hinTimer),t.hinTimer=setTimeout(function(){w(t.elem).find("."+h).remove()},3e3)},T.prototype.getAsYM=function(e,t,n){return n?t--:t++,t<0&&(t=11,e--),t>11&&(t=0,e++),[e,t]},T.prototype.systemDate=function(e){var t=e||new Date;return{year:t.getFullYear(),month:t.getMonth(),date:t.getDate(),hours:e?e.getHours():0,minutes:e?e.getMinutes():0,seconds:e?e.getSeconds():0}},T.prototype.checkDate=function(e){var t,a,i=this,r=(new Date,i.config),o=r.dateTime=r.dateTime||i.systemDate(),s=i.bindElem||r.elem[0],l=(i.isInput(s)?"val":"html",i.isInput(s)?s.value:"static"===r.position?"":s.innerHTML),c=function(e){e.year>d[1]&&(e.year=d[1],a=!0),e.month>11&&(e.month=11,a=!0),e.hours>23&&(e.hours=0,a=!0),e.minutes>59&&(e.minutes=0,e.hours++,a=!0),e.seconds>59&&(e.seconds=0,e.minutes++,a=!0),t=n.getEndDate(e.month+1,e.year),e.date>t&&(e.date=t,a=!0)},m=function(e,t,n){var o=["startTime","endTime"];t=(t.match(i.EXP_SPLIT)||[]).slice(1),n=n||0,r.range&&(i[o[n]]=i[o[n]]||{}),w.each(i.format,function(s,l){var c=parseFloat(t[s]);t[s].length<l.length&&(a=!0),/yyyy|y/.test(l)?(c<d[0]&&(c=d[0],a=!0),e.year=c):/MM|M/.test(l)?(c<1&&(c=1,a=!0),e.month=c-1):/dd|d/.test(l)?(c<1&&(c=1,a=!0),e.date=c):/HH|H/.test(l)?(c<1&&(c=0,a=!0),e.hours=c,r.range&&(i[o[n]].hours=c)):/mm|m/.test(l)?(c<1&&(c=0,a=!0),e.minutes=c,r.range&&(i[o[n]].minutes=c)):/ss|s/.test(l)&&(c<1&&(c=0,a=!0),e.seconds=c,r.range&&(i[o[n]].seconds=c))}),c(e)};return"limit"===e?(c(o),i):(l=l||r.value,"string"==typeof l&&(l=l.replace(/\s+/g," ").replace(/^\s|\s$/g,"")),i.startState&&!i.endState&&(delete i.startState,i.endState=!0),"string"==typeof l&&l?i.EXP_IF.test(l)?r.range?(l=l.split(" "+r.range+" "),i.startDate=i.startDate||i.systemDate(),i.endDate=i.endDate||i.systemDate(),r.dateTime=w.extend({},i.startDate),w.each([i.startDate,i.endDate],function(e,t){m(t,l[e],e)})):m(o,l):(i.hint("日期格式不合法<br>必须遵循下述格式：<br>"+(r.range?r.format+" "+r.range+" "+r.format:r.format)+"<br>已为你重置"),a=!0):l&&l.constructor===Date?r.dateTime=i.systemDate(l):(r.dateTime=i.systemDate(),delete i.startState,delete i.endState,delete i.startDate,delete i.endDate,delete i.startTime,delete i.endTime),c(o),a&&l&&i.setValue(r.range?i.endDate?i.parse():"":i.parse()),e&&e(),i)},T.prototype.mark=function(e,t){var n,a=this,i=a.config;return w.each(i.mark,function(e,a){var i=e.split("-");i[0]!=t[0]&&0!=i[0]||i[1]!=t[1]&&0!=i[1]||i[2]!=t[2]||(n=a||t[2])}),n&&e.html('<span class="laydate-day-mark">'+n+"</span>"),a},T.prototype.limit=function(e,t,n,a){var i,r=this,o=r.config,l={},d=o[n>41?"endDate":"dateTime"],c=w.extend({},d,t||{});return w.each({now:c,min:o.min,max:o.max},function(e,t){l[e]=r.newDate(w.extend({year:t.year,month:t.month,date:t.date},function(){var e={};return w.each(a,function(n,a){e[a]=t[a]}),e}())).getTime()}),i=l.now<l.min||l.now>l.max,e&&e[i?"addClass":"removeClass"](s),i},T.prototype.calendar=function(e){var t,a,i,r=this,s=r.config,l=e||s.dateTime,c=new Date,m=r.lang(),u="date"!==s.type&&"datetime"!==s.type,h=e?1:0,y=w(r.table[h]).find("td"),f=w(r.elemHeader[h][2]).find("span");if(l.year<d[0]&&(l.year=d[0],r.hint("最低只能支持到公元"+d[0]+"年")),l.year>d[1]&&(l.year=d[1],r.hint("最高只能支持到公元"+d[1]+"年")),r.firstDate||(r.firstDate=w.extend({},l)),c.setFullYear(l.year,l.month,1),t=c.getDay(),a=n.getEndDate(l.month||12,l.year),i=n.getEndDate(l.month+1,l.year),w.each(y,function(e,n){var d=[l.year,l.month],c=0;n=w(n),n.removeAttr("class"),e<t?(c=a-t+e,n.addClass("laydate-day-prev"),d=r.getAsYM(l.year,l.month,"sub")):e>=t&&e<i+t?(c=e-t,s.range||c+1===l.date&&n.addClass(o)):(c=e-i-t,n.addClass("laydate-day-next"),d=r.getAsYM(l.year,l.month)),d[1]++,d[2]=c+1,n.attr("lay-ymd",d.join("-")).html(d[2]),r.mark(n,d).limit(n,{year:d[0],month:d[1]-1,date:d[2]},e)}),w(f[0]).attr("lay-ym",l.year+"-"+(l.month+1)),w(f[1]).attr("lay-ym",l.year+"-"+(l.month+1)),"cn"===s.lang?(w(f[0]).attr("lay-type","year").html(l.year+"年"),w(f[1]).attr("lay-type","month").html(l.month+1+"月")):(w(f[0]).attr("lay-type","month").html(m.month[l.month]),w(f[1]).attr("lay-type","year").html(l.year)),u&&(s.range&&(e?r.endDate=r.endDate||{year:l.year+("year"===s.type?1:0),month:l.month+("month"===s.type?0:-1)}:r.startDate=r.startDate||{year:l.year,month:l.month},e&&(r.listYM=[[r.startDate.year,r.startDate.month+1],[r.endDate.year,r.endDate.month+1]],r.list(s.type,0).list(s.type,1),"time"===s.type?r.setBtnStatus("时间",w.extend({},r.systemDate(),r.startTime),w.extend({},r.systemDate(),r.endTime)):r.setBtnStatus(!0))),s.range||(r.listYM=[[l.year,l.month+1]],r.list(s.type,0))),s.range&&!e){var p=r.getAsYM(l.year,l.month);r.calendar(w.extend({},l,{year:p[0],month:p[1]}))}return s.range||r.limit(w(r.footer).find(g),null,0,["hours","minutes","seconds"]),s.range&&e&&!u&&r.stampRange(),r},T.prototype.list=function(e,t){var n=this,a=n.config,i=a.dateTime,r=n.lang(),l=a.range&&"date"!==a.type&&"datetime"!==a.type,d=w.elem("ul",{"class":m+" "+{year:"laydate-year-list",month:"laydate-month-list",time:"laydate-time-list"}[e]}),c=n.elemHeader[t],u=w(c[2]).find("span"),h=n.elemCont[t||0],y=w(h).find("."+m)[0],f="cn"===a.lang,p=f?"年":"",T=n.listYM[t]||{},C=["hours","minutes","seconds"],x=["startTime","endTime"][t];if(T[0]<1&&(T[0]=1),"year"===e){var M,b=M=T[0]-7;b<1&&(b=M=1),w.each(new Array(15),function(e){var i=w.elem("li",{"lay-ym":M}),r={year:M};M==T[0]&&w(i).addClass(o),i.innerHTML=M+p,d.appendChild(i),M<n.firstDate.year?(r.month=a.min.month,r.date=a.min.date):M>=n.firstDate.year&&(r.month=a.max.month,r.date=a.max.date),n.limit(w(i),r,t),M++}),w(u[f?0:1]).attr("lay-ym",M-8+"-"+T[1]).html(b+p+" - "+(M-1+p))}else if("month"===e)w.each(new Array(12),function(e){var i=w.elem("li",{"lay-ym":e}),s={year:T[0],month:e};e+1==T[1]&&w(i).addClass(o),i.innerHTML=r.month[e]+(f?"月":""),d.appendChild(i),T[0]<n.firstDate.year?s.date=a.min.date:T[0]>=n.firstDate.year&&(s.date=a.max.date),n.limit(w(i),s,t)}),w(u[f?0:1]).attr("lay-ym",T[0]+"-"+T[1]).html(T[0]+p);else if("time"===e){var E=function(){w(d).find("ol").each(function(e,a){w(a).find("li").each(function(a,i){n.limit(w(i),[{hours:a},{hours:n[x].hours,minutes:a},{hours:n[x].hours,minutes:n[x].minutes,seconds:a}][e],t,[["hours"],["hours","minutes"],["hours","minutes","seconds"]][e])})}),a.range||n.limit(w(n.footer).find(g),n[x],0,["hours","minutes","seconds"])};a.range?n[x]||(n[x]={hours:0,minutes:0,seconds:0}):n[x]=i,w.each([24,60,60],function(e,t){var a=w.elem("li"),i=["<p>"+r.time[e]+"</p><ol>"];w.each(new Array(t),function(t){i.push("<li"+(n[x][C[e]]===t?' class="'+o+'"':"")+">"+w.digit(t,2)+"</li>")}),a.innerHTML=i.join("")+"</ol>",d.appendChild(a)}),E()}if(y&&h.removeChild(y),h.appendChild(d),"year"===e||"month"===e)w(n.elemMain[t]).addClass("laydate-ym-show"),w(d).find("li").on("click",function(){var r=0|w(this).attr("lay-ym");if(!w(this).hasClass(s)){if(0===t)i[e]=r,l&&(n.startDate[e]=r),n.limit(w(n.footer).find(g),null,0);else if(l)n.endDate[e]=r;else{var c="year"===e?n.getAsYM(r,T[1]-1,"sub"):n.getAsYM(T[0],r,"sub");w.extend(i,{year:c[0],month:c[1]})}"year"===a.type||"month"===a.type?(w(d).find("."+o).removeClass(o),w(this).addClass(o),"month"===a.type&&"year"===e&&(n.listYM[t][0]=r,l&&(n[["startDate","endDate"][t]].year=r),n.list("month",t))):(n.checkDate("limit").calendar(),n.closeList()),n.setBtnStatus(),a.range||n.done(null,"change"),w(n.footer).find(D).removeClass(s)}});else{var S=w.elem("span",{"class":v}),k=function(){w(d).find("ol").each(function(e){var t=this,a=w(t).find("li");t.scrollTop=30*(n[x][C[e]]-2),t.scrollTop<=0&&a.each(function(e,n){if(!w(this).hasClass(s))return t.scrollTop=30*(e-2),!0})})},H=w(c[2]).find("."+v);k(),S.innerHTML=a.range?[r.startTime,r.endTime][t]:r.timeTips,w(n.elemMain[t]).addClass("laydate-time-show"),H[0]&&H.remove(),c[2].appendChild(S),w(d).find("ol").each(function(e){var t=this;w(t).find("li").on("click",function(){var r=0|this.innerHTML;w(this).hasClass(s)||(a.range?n[x][C[e]]=r:i[C[e]]=r,w(t).find("."+o).removeClass(o),w(this).addClass(o),E(),k(),(n.endDate||"time"===a.type)&&n.done(null,"change"),n.setBtnStatus())})})}return n},T.prototype.listYM=[],T.prototype.closeList=function(){var e=this;e.config;w.each(e.elemCont,function(t,n){w(this).find("."+m).remove(),w(e.elemMain[t]).removeClass("laydate-ym-show laydate-time-show")}),w(e.elem).find("."+v).remove()},T.prototype.setBtnStatus=function(e,t,n){var a,i=this,r=i.config,o=w(i.footer).find(g),d=r.range&&"date"!==r.type&&"time"!==r.type;d&&(t=t||i.startDate,n=n||i.endDate,a=i.newDate(t).getTime()>i.newDate(n).getTime(),i.limit(null,t)||i.limit(null,n)?o.addClass(s):o[a?"addClass":"removeClass"](s),e&&a&&i.hint("string"==typeof e?l.replace(/日期/g,e):l))},T.prototype.parse=function(e,t){var n=this,a=n.config,i=t||(e?w.extend({},n.endDate,n.endTime):a.range?w.extend({},n.startDate,n.startTime):a.dateTime),r=n.format.concat();return w.each(r,function(e,t){/yyyy|y/.test(t)?r[e]=w.digit(i.year,t.length):/MM|M/.test(t)?r[e]=w.digit(i.month+1,t.length):/dd|d/.test(t)?r[e]=w.digit(i.date,t.length):/HH|H/.test(t)?r[e]=w.digit(i.hours,t.length):/mm|m/.test(t)?r[e]=w.digit(i.minutes,t.length):/ss|s/.test(t)&&(r[e]=w.digit(i.seconds,t.length))}),a.range&&!e?r.join("")+" "+a.range+" "+n.parse(1):r.join("")},T.prototype.newDate=function(e){return e=e||{},new Date(e.year||1,e.month||0,e.date||1,e.hours||0,e.minutes||0,e.seconds||0)},T.prototype.setValue=function(e){var t=this,n=t.config,a=t.bindElem||n.elem[0],i=t.isInput(a)?"val":"html";return"static"===n.position||w(a)[i](e||""),this},T.prototype.stampRange=function(){var e,t,n=this,a=n.config,i=w(n.elem).find("td");if(a.range&&!n.endDate&&w(n.footer).find(g).addClass(s),n.endDate)return e=n.newDate({year:n.startDate.year,month:n.startDate.month,date:n.startDate.date}).getTime(),t=n.newDate({year:n.endDate.year,month:n.endDate.month,date:n.endDate.date}).getTime(),e>t?n.hint(l):void w.each(i,function(a,i){var r=w(i).attr("lay-ymd").split("-"),s=n.newDate({year:r[0],month:r[1]-1,date:r[2]}).getTime();w(i).removeClass(u+" "+o),s!==e&&s!==t||w(i).addClass(w(i).hasClass(y)||w(i).hasClass(f)?u:o),s>e&&s<t&&w(i).addClass(u)})},T.prototype.done=function(e,t){var n=this,a=n.config,i=w.extend({},n.startDate?w.extend(n.startDate,n.startTime):a.dateTime),r=w.extend({},w.extend(n.endDate,n.endTime));return w.each([i,r],function(e,t){"month"in t&&w.extend(t,{month:t.month+1})}),e=e||[n.parse(),i,r],"function"==typeof a[t||"done"]&&a[t||"done"].apply(a,e),n},T.prototype.choose=function(e){var t=this,n=t.config,a=n.dateTime,i=w(t.elem).find("td"),r=e.attr("lay-ymd").split("-"),l=function(e){new Date;e&&w.extend(a,r),n.range&&(t.startDate?w.extend(t.startDate,r):t.startDate=w.extend({},r,t.startTime),t.startYMD=r)};if(r={year:0|r[0],month:(0|r[1])-1,date:0|r[2]},!e.hasClass(s))if(n.range){if(w.each(["startTime","endTime"],function(e,n){t[n]=t[n]||{hours:0,minutes:0,seconds:0}}),t.endState)l(),delete t.endState,delete t.endDate,t.startState=!0,i.removeClass(o+" "+u),e.addClass(o);else if(t.startState){if(e.addClass(o),t.endDate?w.extend(t.endDate,r):t.endDate=w.extend({},r,t.endTime),t.newDate(r).getTime()<t.newDate(t.startYMD).getTime()){var d=w.extend({},t.endDate,{hours:t.startDate.hours,minutes:t.startDate.minutes,seconds:t.startDate.seconds});w.extend(t.endDate,t.startDate,{hours:t.endDate.hours,minutes:t.endDate.minutes,seconds:t.endDate.seconds}),t.startDate=d}n.showBottom||t.done(),t.stampRange(),t.endState=!0,t.done(null,"change")}else e.addClass(o),l(),t.startState=!0;w(t.footer).find(g)[t.endDate?"removeClass":"addClass"](s)}else"static"===n.position?(l(!0),t.calendar().done().done(null,"change")):"date"===n.type?(l(!0),t.setValue(t.parse()).remove().done()):"datetime"===n.type&&(l(!0),t.calendar().done(null,"change"))},T.prototype.tool=function(e,t){var n=this,a=n.config,i=a.dateTime,r="static"===a.position,o={datetime:function(){w(e).hasClass(s)||(n.list("time",0),a.range&&n.list("time",1),w(e).attr("lay-type","date").html(n.lang().dateTips))},date:function(){n.closeList(),w(e).attr("lay-type","datetime").html(n.lang().timeTips)},clear:function(){n.setValue("").remove(),r&&(w.extend(i,n.firstDate),n.calendar()),a.range&&(delete n.startState,delete n.endState,delete n.endDate,delete n.startTime,delete n.endTime),n.done(["",{},{}])},now:function(){var e=new Date;w.extend(i,n.systemDate(),{hours:e.getHours(),minutes:e.getMinutes(),seconds:e.getSeconds()}),n.setValue(n.parse()).remove(),r&&n.calendar(),n.done()},confirm:function(){if(a.range){if(!n.endDate)return n.hint("请先选择日期范围");if(w(e).hasClass(s))return n.hint("time"===a.type?l.replace(/日期/g,"时间"):l)}else if(w(e).hasClass(s))return n.hint("不在有效日期或时间范围内");n.done(),n.setValue(n.parse()).remove()}};o[t]&&o[t]()},T.prototype.change=function(e){var t=this,n=t.config,a=n.dateTime,i=n.range&&("year"===n.type||"month"===n.type),r=t.elemCont[e||0],o=t.listYM[e],s=function(s){var l=["startDate","endDate"][e],d=w(r).find(".laydate-year-list")[0],c=w(r).find(".laydate-month-list")[0];return d&&(o[0]=s?o[0]-15:o[0]+15,t.list("year",e)),c&&(s?o[0]--:o[0]++,t.list("month",e)),(d||c)&&(w.extend(a,{year:o[0]}),i&&(t[l].year=o[0]),n.range||t.done(null,"change"),t.setBtnStatus(),n.range||t.limit(w(t.footer).find(g),{year:o[0]})),d||c};return{prevYear:function(){s("sub")||(a.year--,t.checkDate("limit").calendar(),n.range||t.done(null,"change"))},prevMonth:function(){var e=t.getAsYM(a.year,a.month,"sub");w.extend(a,{year:e[0],month:e[1]}),t.checkDate("limit").calendar(),n.range||t.done(null,"change")},nextMonth:function(){var e=t.getAsYM(a.year,a.month);w.extend(a,{year:e[0],month:e[1]}),t.checkDate("limit").calendar(),n.range||t.done(null,"change")},nextYear:function(){s()||(a.year++,t.checkDate("limit").calendar(),n.range||t.done(null,"change"))}}},T.prototype.changeEvent=function(){var e=this;e.config;w(e.elem).on("click",function(e){w.stope(e)}),w.each(e.elemHeader,function(t,n){w(n[0]).on("click",function(n){e.change(t).prevYear()}),w(n[1]).on("click",function(n){e.change(t).prevMonth()}),w(n[2]).find("span").on("click",function(n){var a=w(this),i=a.attr("lay-ym"),r=a.attr("lay-type");i&&(i=i.split("-"),e.listYM[t]=[0|i[0],0|i[1]],e.list(r,t),w(e.footer).find(D).addClass(s))}),w(n[3]).on("click",function(n){e.change(t).nextMonth()}),w(n[4]).on("click",function(n){e.change(t).nextYear()})}),w.each(e.table,function(t,n){var a=w(n).find("td");a.on("click",function(){e.choose(w(this))})}),w(e.footer).find("span").on("click",function(){var t=w(this).attr("lay-type");e.tool(this,t)})},T.prototype.isInput=function(e){return/input|textarea/.test(e.tagName.toLocaleLowerCase())},T.prototype.events=function(){var e=this,t=e.config,n=function(n,a){n.on(t.trigger,function(){a&&(e.bindElem=this),e.render()})};t.elem[0]&&!t.elem[0].eventHandler&&(n(t.elem,"bind"),n(t.eventElem),w(document).on("click",function(n){n.target!==t.elem[0]&&n.target!==t.eventElem[0]&&n.target!==w(t.closeStop)[0]&&e.remove()}).on("keydown",function(t){13===t.keyCode&&w("#"+e.elemID)[0]&&e.elemID===T.thisElem&&(t.preventDefault(),w(e.footer).find(g)[0].click())}),w(window).on("resize",function(){return!(!e.elem||!w(r)[0])&&void e.position()}),t.elem[0].eventHandler=!0)},n.render=function(e){var t=new T(e);return a.call(t)},n.getEndDate=function(e,t){var n=new Date;return n.setFullYear(t||n.getFullYear(),e||n.getMonth()+1,1),new Date(n.getTime()-864e5).getDate()},window.lay=window.lay||w,e?(n.ready(),layui.define(function(e){n.path=layui.cache.dir,e(i,n)})):"function"==typeof define&&define.amd?define(function(){return n}):function(){n.ready(),window.laydate=n}()}();!function(e,t){"object"==typeof module&&"object"==typeof module.exports?module.exports=e.document?t(e,!0):function(e){if(!e.document)throw new Error("jQuery requires a window with a document");return t(e)}:t(e)}("undefined"!=typeof window?window:this,function(e,t){function n(e){var t=!!e&&"length"in e&&e.length,n=pe.type(e);return"function"!==n&&!pe.isWindow(e)&&("array"===n||0===t||"number"==typeof t&&t>0&&t-1 in e)}function r(e,t,n){if(pe.isFunction(t))return pe.grep(e,function(e,r){return!!t.call(e,r,e)!==n});if(t.nodeType)return pe.grep(e,function(e){return e===t!==n});if("string"==typeof t){if(Ce.test(t))return pe.filter(t,e,n);t=pe.filter(t,e)}return pe.grep(e,function(e){return pe.inArray(e,t)>-1!==n})}function i(e,t){do e=e[t];while(e&&1!==e.nodeType);return e}function o(e){var t={};return pe.each(e.match(De)||[],function(e,n){t[n]=!0}),t}function a(){re.addEventListener?(re.removeEventListener("DOMContentLoaded",s),e.removeEventListener("load",s)):(re.detachEvent("onreadystatechange",s),e.detachEvent("onload",s))}function s(){(re.addEventListener||"load"===e.event.type||"complete"===re.readyState)&&(a(),pe.ready())}function u(e,t,n){if(void 0===n&&1===e.nodeType){var r="data-"+t.replace(_e,"-$1").toLowerCase();if(n=e.getAttribute(r),"string"==typeof n){try{n="true"===n||"false"!==n&&("null"===n?null:+n+""===n?+n:qe.test(n)?pe.parseJSON(n):n)}catch(i){}pe.data(e,t,n)}else n=void 0}return n}function l(e){var t;for(t in e)if(("data"!==t||!pe.isEmptyObject(e[t]))&&"toJSON"!==t)return!1;return!0}function c(e,t,n,r){if(He(e)){var i,o,a=pe.expando,s=e.nodeType,u=s?pe.cache:e,l=s?e[a]:e[a]&&a;if(l&&u[l]&&(r||u[l].data)||void 0!==n||"string"!=typeof t)return l||(l=s?e[a]=ne.pop()||pe.guid++:a),u[l]||(u[l]=s?{}:{toJSON:pe.noop}),"object"!=typeof t&&"function"!=typeof t||(r?u[l]=pe.extend(u[l],t):u[l].data=pe.extend(u[l].data,t)),o=u[l],r||(o.data||(o.data={}),o=o.data),void 0!==n&&(o[pe.camelCase(t)]=n),"string"==typeof t?(i=o[t],null==i&&(i=o[pe.camelCase(t)])):i=o,i}}function f(e,t,n){if(He(e)){var r,i,o=e.nodeType,a=o?pe.cache:e,s=o?e[pe.expando]:pe.expando;if(a[s]){if(t&&(r=n?a[s]:a[s].data)){pe.isArray(t)?t=t.concat(pe.map(t,pe.camelCase)):t in r?t=[t]:(t=pe.camelCase(t),t=t in r?[t]:t.split(" ")),i=t.length;for(;i--;)delete r[t[i]];if(n?!l(r):!pe.isEmptyObject(r))return}(n||(delete a[s].data,l(a[s])))&&(o?pe.cleanData([e],!0):fe.deleteExpando||a!=a.window?delete a[s]:a[s]=void 0)}}}function d(e,t,n,r){var i,o=1,a=20,s=r?function(){return r.cur()}:function(){return pe.css(e,t,"")},u=s(),l=n&&n[3]||(pe.cssNumber[t]?"":"px"),c=(pe.cssNumber[t]||"px"!==l&&+u)&&Me.exec(pe.css(e,t));if(c&&c[3]!==l){l=l||c[3],n=n||[],c=+u||1;do o=o||".5",c/=o,pe.style(e,t,c+l);while(o!==(o=s()/u)&&1!==o&&--a)}return n&&(c=+c||+u||0,i=n[1]?c+(n[1]+1)*n[2]:+n[2],r&&(r.unit=l,r.start=c,r.end=i)),i}function p(e){var t=ze.split("|"),n=e.createDocumentFragment();if(n.createElement)for(;t.length;)n.createElement(t.pop());return n}function h(e,t){var n,r,i=0,o="undefined"!=typeof e.getElementsByTagName?e.getElementsByTagName(t||"*"):"undefined"!=typeof e.querySelectorAll?e.querySelectorAll(t||"*"):void 0;if(!o)for(o=[],n=e.childNodes||e;null!=(r=n[i]);i++)!t||pe.nodeName(r,t)?o.push(r):pe.merge(o,h(r,t));return void 0===t||t&&pe.nodeName(e,t)?pe.merge([e],o):o}function g(e,t){for(var n,r=0;null!=(n=e[r]);r++)pe._data(n,"globalEval",!t||pe._data(t[r],"globalEval"))}function m(e){Be.test(e.type)&&(e.defaultChecked=e.checked)}function y(e,t,n,r,i){for(var o,a,s,u,l,c,f,d=e.length,y=p(t),v=[],x=0;x<d;x++)if(a=e[x],a||0===a)if("object"===pe.type(a))pe.merge(v,a.nodeType?[a]:a);else if(Ue.test(a)){for(u=u||y.appendChild(t.createElement("div")),l=(We.exec(a)||["",""])[1].toLowerCase(),f=Xe[l]||Xe._default,u.innerHTML=f[1]+pe.htmlPrefilter(a)+f[2],o=f[0];o--;)u=u.lastChild;if(!fe.leadingWhitespace&&$e.test(a)&&v.push(t.createTextNode($e.exec(a)[0])),!fe.tbody)for(a="table"!==l||Ve.test(a)?"<table>"!==f[1]||Ve.test(a)?0:u:u.firstChild,o=a&&a.childNodes.length;o--;)pe.nodeName(c=a.childNodes[o],"tbody")&&!c.childNodes.length&&a.removeChild(c);for(pe.merge(v,u.childNodes),u.textContent="";u.firstChild;)u.removeChild(u.firstChild);u=y.lastChild}else v.push(t.createTextNode(a));for(u&&y.removeChild(u),fe.appendChecked||pe.grep(h(v,"input"),m),x=0;a=v[x++];)if(r&&pe.inArray(a,r)>-1)i&&i.push(a);else if(s=pe.contains(a.ownerDocument,a),u=h(y.appendChild(a),"script"),s&&g(u),n)for(o=0;a=u[o++];)Ie.test(a.type||"")&&n.push(a);return u=null,y}function v(){return!0}function x(){return!1}function b(){try{return re.activeElement}catch(e){}}function w(e,t,n,r,i,o){var a,s;if("object"==typeof t){"string"!=typeof n&&(r=r||n,n=void 0);for(s in t)w(e,s,n,r,t[s],o);return e}if(null==r&&null==i?(i=n,r=n=void 0):null==i&&("string"==typeof n?(i=r,r=void 0):(i=r,r=n,n=void 0)),i===!1)i=x;else if(!i)return e;return 1===o&&(a=i,i=function(e){return pe().off(e),a.apply(this,arguments)},i.guid=a.guid||(a.guid=pe.guid++)),e.each(function(){pe.event.add(this,t,i,r,n)})}function T(e,t){return pe.nodeName(e,"table")&&pe.nodeName(11!==t.nodeType?t:t.firstChild,"tr")?e.getElementsByTagName("tbody")[0]||e.appendChild(e.ownerDocument.createElement("tbody")):e}function C(e){return e.type=(null!==pe.find.attr(e,"type"))+"/"+e.type,e}function E(e){var t=it.exec(e.type);return t?e.type=t[1]:e.removeAttribute("type"),e}function N(e,t){if(1===t.nodeType&&pe.hasData(e)){var n,r,i,o=pe._data(e),a=pe._data(t,o),s=o.events;if(s){delete a.handle,a.events={};for(n in s)for(r=0,i=s[n].length;r<i;r++)pe.event.add(t,n,s[n][r])}a.data&&(a.data=pe.extend({},a.data))}}function k(e,t){var n,r,i;if(1===t.nodeType){if(n=t.nodeName.toLowerCase(),!fe.noCloneEvent&&t[pe.expando]){i=pe._data(t);for(r in i.events)pe.removeEvent(t,r,i.handle);t.removeAttribute(pe.expando)}"script"===n&&t.text!==e.text?(C(t).text=e.text,E(t)):"object"===n?(t.parentNode&&(t.outerHTML=e.outerHTML),fe.html5Clone&&e.innerHTML&&!pe.trim(t.innerHTML)&&(t.innerHTML=e.innerHTML)):"input"===n&&Be.test(e.type)?(t.defaultChecked=t.checked=e.checked,t.value!==e.value&&(t.value=e.value)):"option"===n?t.defaultSelected=t.selected=e.defaultSelected:"input"!==n&&"textarea"!==n||(t.defaultValue=e.defaultValue)}}function S(e,t,n,r){t=oe.apply([],t);var i,o,a,s,u,l,c=0,f=e.length,d=f-1,p=t[0],g=pe.isFunction(p);if(g||f>1&&"string"==typeof p&&!fe.checkClone&&rt.test(p))return e.each(function(i){var o=e.eq(i);g&&(t[0]=p.call(this,i,o.html())),S(o,t,n,r)});if(f&&(l=y(t,e[0].ownerDocument,!1,e,r),i=l.firstChild,1===l.childNodes.length&&(l=i),i||r)){for(s=pe.map(h(l,"script"),C),a=s.length;c<f;c++)o=l,c!==d&&(o=pe.clone(o,!0,!0),a&&pe.merge(s,h(o,"script"))),n.call(e[c],o,c);if(a)for(u=s[s.length-1].ownerDocument,pe.map(s,E),c=0;c<a;c++)o=s[c],Ie.test(o.type||"")&&!pe._data(o,"globalEval")&&pe.contains(u,o)&&(o.src?pe._evalUrl&&pe._evalUrl(o.src):pe.globalEval((o.text||o.textContent||o.innerHTML||"").replace(ot,"")));l=i=null}return e}function A(e,t,n){for(var r,i=t?pe.filter(t,e):e,o=0;null!=(r=i[o]);o++)n||1!==r.nodeType||pe.cleanData(h(r)),r.parentNode&&(n&&pe.contains(r.ownerDocument,r)&&g(h(r,"script")),r.parentNode.removeChild(r));return e}function D(e,t){var n=pe(t.createElement(e)).appendTo(t.body),r=pe.css(n[0],"display");return n.detach(),r}function j(e){var t=re,n=lt[e];return n||(n=D(e,t),"none"!==n&&n||(ut=(ut||pe("<iframe frameborder='0' width='0' height='0'/>")).appendTo(t.documentElement),t=(ut[0].contentWindow||ut[0].contentDocument).document,t.write(),t.close(),n=D(e,t),ut.detach()),lt[e]=n),n}function L(e,t){return{get:function(){return e()?void delete this.get:(this.get=t).apply(this,arguments)}}}function H(e){if(e in Et)return e;for(var t=e.charAt(0).toUpperCase()+e.slice(1),n=Ct.length;n--;)if(e=Ct[n]+t,e in Et)return e}function q(e,t){for(var n,r,i,o=[],a=0,s=e.length;a<s;a++)r=e[a],r.style&&(o[a]=pe._data(r,"olddisplay"),n=r.style.display,t?(o[a]||"none"!==n||(r.style.display=""),""===r.style.display&&Re(r)&&(o[a]=pe._data(r,"olddisplay",j(r.nodeName)))):(i=Re(r),(n&&"none"!==n||!i)&&pe._data(r,"olddisplay",i?n:pe.css(r,"display"))));for(a=0;a<s;a++)r=e[a],r.style&&(t&&"none"!==r.style.display&&""!==r.style.display||(r.style.display=t?o[a]||"":"none"));return e}function _(e,t,n){var r=bt.exec(t);return r?Math.max(0,r[1]-(n||0))+(r[2]||"px"):t}function F(e,t,n,r,i){for(var o=n===(r?"border":"content")?4:"width"===t?1:0,a=0;o<4;o+=2)"margin"===n&&(a+=pe.css(e,n+Oe[o],!0,i)),r?("content"===n&&(a-=pe.css(e,"padding"+Oe[o],!0,i)),"margin"!==n&&(a-=pe.css(e,"border"+Oe[o]+"Width",!0,i))):(a+=pe.css(e,"padding"+Oe[o],!0,i),"padding"!==n&&(a+=pe.css(e,"border"+Oe[o]+"Width",!0,i)));return a}function M(t,n,r){var i=!0,o="width"===n?t.offsetWidth:t.offsetHeight,a=ht(t),s=fe.boxSizing&&"border-box"===pe.css(t,"boxSizing",!1,a);if(re.msFullscreenElement&&e.top!==e&&t.getClientRects().length&&(o=Math.round(100*t.getBoundingClientRect()[n])),o<=0||null==o){if(o=gt(t,n,a),(o<0||null==o)&&(o=t.style[n]),ft.test(o))return o;i=s&&(fe.boxSizingReliable()||o===t.style[n]),o=parseFloat(o)||0}return o+F(t,n,r||(s?"border":"content"),i,a)+"px"}function O(e,t,n,r,i){return new O.prototype.init(e,t,n,r,i)}function R(){return e.setTimeout(function(){Nt=void 0}),Nt=pe.now()}function P(e,t){var n,r={height:e},i=0;for(t=t?1:0;i<4;i+=2-t)n=Oe[i],r["margin"+n]=r["padding"+n]=e;return t&&(r.opacity=r.width=e),r}function B(e,t,n){for(var r,i=($.tweeners[t]||[]).concat($.tweeners["*"]),o=0,a=i.length;o<a;o++)if(r=i[o].call(n,t,e))return r}function W(e,t,n){var r,i,o,a,s,u,l,c,f=this,d={},p=e.style,h=e.nodeType&&Re(e),g=pe._data(e,"fxshow");n.queue||(s=pe._queueHooks(e,"fx"),null==s.unqueued&&(s.unqueued=0,u=s.empty.fire,s.empty.fire=function(){s.unqueued||u()}),s.unqueued++,f.always(function(){f.always(function(){s.unqueued--,pe.queue(e,"fx").length||s.empty.fire()})})),1===e.nodeType&&("height"in t||"width"in t)&&(n.overflow=[p.overflow,p.overflowX,p.overflowY],l=pe.css(e,"display"),c="none"===l?pe._data(e,"olddisplay")||j(e.nodeName):l,"inline"===c&&"none"===pe.css(e,"float")&&(fe.inlineBlockNeedsLayout&&"inline"!==j(e.nodeName)?p.zoom=1:p.display="inline-block")),n.overflow&&(p.overflow="hidden",fe.shrinkWrapBlocks()||f.always(function(){p.overflow=n.overflow[0],p.overflowX=n.overflow[1],p.overflowY=n.overflow[2]}));for(r in t)if(i=t[r],St.exec(i)){if(delete t[r],o=o||"toggle"===i,i===(h?"hide":"show")){if("show"!==i||!g||void 0===g[r])continue;h=!0}d[r]=g&&g[r]||pe.style(e,r)}else l=void 0;if(pe.isEmptyObject(d))"inline"===("none"===l?j(e.nodeName):l)&&(p.display=l);else{g?"hidden"in g&&(h=g.hidden):g=pe._data(e,"fxshow",{}),o&&(g.hidden=!h),h?pe(e).show():f.done(function(){pe(e).hide()}),f.done(function(){var t;pe._removeData(e,"fxshow");for(t in d)pe.style(e,t,d[t])});for(r in d)a=B(h?g[r]:0,r,f),r in g||(g[r]=a.start,h&&(a.end=a.start,a.start="width"===r||"height"===r?1:0))}}function I(e,t){var n,r,i,o,a;for(n in e)if(r=pe.camelCase(n),i=t[r],o=e[n],pe.isArray(o)&&(i=o[1],o=e[n]=o[0]),n!==r&&(e[r]=o,delete e[n]),a=pe.cssHooks[r],a&&"expand"in a){o=a.expand(o),delete e[r];for(n in o)n in e||(e[n]=o[n],t[n]=i)}else t[r]=i}function $(e,t,n){var r,i,o=0,a=$.prefilters.length,s=pe.Deferred().always(function(){delete u.elem}),u=function(){if(i)return!1;for(var t=Nt||R(),n=Math.max(0,l.startTime+l.duration-t),r=n/l.duration||0,o=1-r,a=0,u=l.tweens.length;a<u;a++)l.tweens[a].run(o);return s.notifyWith(e,[l,o,n]),o<1&&u?n:(s.resolveWith(e,[l]),!1)},l=s.promise({elem:e,props:pe.extend({},t),opts:pe.extend(!0,{specialEasing:{},easing:pe.easing._default},n),originalProperties:t,originalOptions:n,startTime:Nt||R(),duration:n.duration,tweens:[],createTween:function(t,n){var r=pe.Tween(e,l.opts,t,n,l.opts.specialEasing[t]||l.opts.easing);return l.tweens.push(r),r},stop:function(t){var n=0,r=t?l.tweens.length:0;if(i)return this;for(i=!0;n<r;n++)l.tweens[n].run(1);return t?(s.notifyWith(e,[l,1,0]),s.resolveWith(e,[l,t])):s.rejectWith(e,[l,t]),this}}),c=l.props;for(I(c,l.opts.specialEasing);o<a;o++)if(r=$.prefilters[o].call(l,e,c,l.opts))return pe.isFunction(r.stop)&&(pe._queueHooks(l.elem,l.opts.queue).stop=pe.proxy(r.stop,r)),r;return pe.map(c,B,l),pe.isFunction(l.opts.start)&&l.opts.start.call(e,l),pe.fx.timer(pe.extend(u,{elem:e,anim:l,queue:l.opts.queue})),l.progress(l.opts.progress).done(l.opts.done,l.opts.complete).fail(l.opts.fail).always(l.opts.always)}function z(e){return pe.attr(e,"class")||""}function X(e){return function(t,n){"string"!=typeof t&&(n=t,t="*");var r,i=0,o=t.toLowerCase().match(De)||[];if(pe.isFunction(n))for(;r=o[i++];)"+"===r.charAt(0)?(r=r.slice(1)||"*",(e[r]=e[r]||[]).unshift(n)):(e[r]=e[r]||[]).push(n)}}function U(e,t,n,r){function i(s){var u;return o[s]=!0,pe.each(e[s]||[],function(e,s){var l=s(t,n,r);return"string"!=typeof l||a||o[l]?a?!(u=l):void 0:(t.dataTypes.unshift(l),i(l),!1)}),u}var o={},a=e===Qt;return i(t.dataTypes[0])||!o["*"]&&i("*")}function V(e,t){var n,r,i=pe.ajaxSettings.flatOptions||{};for(r in t)void 0!==t[r]&&((i[r]?e:n||(n={}))[r]=t[r]);return n&&pe.extend(!0,e,n),e}function Y(e,t,n){for(var r,i,o,a,s=e.contents,u=e.dataTypes;"*"===u[0];)u.shift(),void 0===i&&(i=e.mimeType||t.getResponseHeader("Content-Type"));if(i)for(a in s)if(s[a]&&s[a].test(i)){u.unshift(a);break}if(u[0]in n)o=u[0];else{for(a in n){if(!u[0]||e.converters[a+" "+u[0]]){o=a;break}r||(r=a)}o=o||r}if(o)return o!==u[0]&&u.unshift(o),n[o]}function J(e,t,n,r){var i,o,a,s,u,l={},c=e.dataTypes.slice();if(c[1])for(a in e.converters)l[a.toLowerCase()]=e.converters[a];for(o=c.shift();o;)if(e.responseFields[o]&&(n[e.responseFields[o]]=t),!u&&r&&e.dataFilter&&(t=e.dataFilter(t,e.dataType)),u=o,o=c.shift())if("*"===o)o=u;else if("*"!==u&&u!==o){if(a=l[u+" "+o]||l["* "+o],!a)for(i in l)if(s=i.split(" "),s[1]===o&&(a=l[u+" "+s[0]]||l["* "+s[0]])){a===!0?a=l[i]:l[i]!==!0&&(o=s[0],c.unshift(s[1]));break}if(a!==!0)if(a&&e["throws"])t=a(t);else try{t=a(t)}catch(f){return{state:"parsererror",error:a?f:"No conversion from "+u+" to "+o}}}return{state:"success",data:t}}function G(e){return e.style&&e.style.display||pe.css(e,"display")}function K(e){for(;e&&1===e.nodeType;){if("none"===G(e)||"hidden"===e.type)return!0;e=e.parentNode}return!1}function Q(e,t,n,r){var i;if(pe.isArray(t))pe.each(t,function(t,i){n||rn.test(e)?r(e,i):Q(e+"["+("object"==typeof i&&null!=i?t:"")+"]",i,n,r)});else if(n||"object"!==pe.type(t))r(e,t);else for(i in t)Q(e+"["+i+"]",t[i],n,r)}function Z(){try{return new e.XMLHttpRequest}catch(t){}}function ee(){try{return new e.ActiveXObject("Microsoft.XMLHTTP")}catch(t){}}function te(e){return pe.isWindow(e)?e:9===e.nodeType&&(e.defaultView||e.parentWindow)}var ne=[],re=e.document,ie=ne.slice,oe=ne.concat,ae=ne.push,se=ne.indexOf,ue={},le=ue.toString,ce=ue.hasOwnProperty,fe={},de="1.12.3",pe=function(e,t){return new pe.fn.init(e,t)},he=/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,ge=/^-ms-/,me=/-([\da-z])/gi,ye=function(e,t){return t.toUpperCase()};pe.fn=pe.prototype={jquery:de,constructor:pe,selector:"",length:0,toArray:function(){return ie.call(this)},get:function(e){return null!=e?e<0?this[e+this.length]:this[e]:ie.call(this)},pushStack:function(e){var t=pe.merge(this.constructor(),e);return t.prevObject=this,t.context=this.context,t},each:function(e){return pe.each(this,e)},map:function(e){return this.pushStack(pe.map(this,function(t,n){return e.call(t,n,t)}))},slice:function(){return this.pushStack(ie.apply(this,arguments))},first:function(){return this.eq(0)},last:function(){return this.eq(-1)},eq:function(e){var t=this.length,n=+e+(e<0?t:0);return this.pushStack(n>=0&&n<t?[this[n]]:[])},end:function(){return this.prevObject||this.constructor()},push:ae,sort:ne.sort,splice:ne.splice},pe.extend=pe.fn.extend=function(){var e,t,n,r,i,o,a=arguments[0]||{},s=1,u=arguments.length,l=!1;for("boolean"==typeof a&&(l=a,a=arguments[s]||{},s++),"object"==typeof a||pe.isFunction(a)||(a={}),s===u&&(a=this,s--);s<u;s++)if(null!=(i=arguments[s]))for(r in i)e=a[r],n=i[r],a!==n&&(l&&n&&(pe.isPlainObject(n)||(t=pe.isArray(n)))?(t?(t=!1,o=e&&pe.isArray(e)?e:[]):o=e&&pe.isPlainObject(e)?e:{},a[r]=pe.extend(l,o,n)):void 0!==n&&(a[r]=n));return a},pe.extend({expando:"jQuery"+(de+Math.random()).replace(/\D/g,""),isReady:!0,error:function(e){throw new Error(e)},noop:function(){},isFunction:function(e){return"function"===pe.type(e)},isArray:Array.isArray||function(e){return"array"===pe.type(e)},isWindow:function(e){return null!=e&&e==e.window},isNumeric:function(e){var t=e&&e.toString();return!pe.isArray(e)&&t-parseFloat(t)+1>=0},isEmptyObject:function(e){var t;for(t in e)return!1;return!0},isPlainObject:function(e){var t;if(!e||"object"!==pe.type(e)||e.nodeType||pe.isWindow(e))return!1;try{if(e.constructor&&!ce.call(e,"constructor")&&!ce.call(e.constructor.prototype,"isPrototypeOf"))return!1}catch(n){return!1}if(!fe.ownFirst)for(t in e)return ce.call(e,t);for(t in e);return void 0===t||ce.call(e,t)},type:function(e){return null==e?e+"":"object"==typeof e||"function"==typeof e?ue[le.call(e)]||"object":typeof e},globalEval:function(t){t&&pe.trim(t)&&(e.execScript||function(t){e.eval.call(e,t)})(t)},camelCase:function(e){return e.replace(ge,"ms-").replace(me,ye)},nodeName:function(e,t){return e.nodeName&&e.nodeName.toLowerCase()===t.toLowerCase()},each:function(e,t){var r,i=0;if(n(e))for(r=e.length;i<r&&t.call(e[i],i,e[i])!==!1;i++);else for(i in e)if(t.call(e[i],i,e[i])===!1)break;return e},trim:function(e){return null==e?"":(e+"").replace(he,"")},makeArray:function(e,t){var r=t||[];return null!=e&&(n(Object(e))?pe.merge(r,"string"==typeof e?[e]:e):ae.call(r,e)),r},inArray:function(e,t,n){var r;if(t){if(se)return se.call(t,e,n);for(r=t.length,n=n?n<0?Math.max(0,r+n):n:0;n<r;n++)if(n in t&&t[n]===e)return n}return-1},merge:function(e,t){for(var n=+t.length,r=0,i=e.length;r<n;)e[i++]=t[r++];if(n!==n)for(;void 0!==t[r];)e[i++]=t[r++];return e.length=i,e},grep:function(e,t,n){for(var r,i=[],o=0,a=e.length,s=!n;o<a;o++)r=!t(e[o],o),r!==s&&i.push(e[o]);return i},map:function(e,t,r){var i,o,a=0,s=[];if(n(e))for(i=e.length;a<i;a++)o=t(e[a],a,r),null!=o&&s.push(o);else for(a in e)o=t(e[a],a,r),null!=o&&s.push(o);return oe.apply([],s)},guid:1,proxy:function(e,t){var n,r,i;if("string"==typeof t&&(i=e[t],t=e,e=i),pe.isFunction(e))return n=ie.call(arguments,2),r=function(){return e.apply(t||this,n.concat(ie.call(arguments)))},r.guid=e.guid=e.guid||pe.guid++,r},now:function(){return+new Date},support:fe}),"function"==typeof Symbol&&(pe.fn[Symbol.iterator]=ne[Symbol.iterator]),pe.each("Boolean Number String Function Array Date RegExp Object Error Symbol".split(" "),function(e,t){ue["[object "+t+"]"]=t.toLowerCase()});var ve=function(e){function t(e,t,n,r){var i,o,a,s,u,l,f,p,h=t&&t.ownerDocument,g=t?t.nodeType:9;if(n=n||[],"string"!=typeof e||!e||1!==g&&9!==g&&11!==g)return n;if(!r&&((t?t.ownerDocument||t:B)!==H&&L(t),t=t||H,_)){if(11!==g&&(l=ye.exec(e)))if(i=l[1]){if(9===g){if(!(a=t.getElementById(i)))return n;if(a.id===i)return n.push(a),n}else if(h&&(a=h.getElementById(i))&&R(t,a)&&a.id===i)return n.push(a),n}else{if(l[2])return Q.apply(n,t.getElementsByTagName(e)),n;if((i=l[3])&&w.getElementsByClassName&&t.getElementsByClassName)return Q.apply(n,t.getElementsByClassName(i)),n}if(w.qsa&&!X[e+" "]&&(!F||!F.test(e))){if(1!==g)h=t,p=e;else if("object"!==t.nodeName.toLowerCase()){for((s=t.getAttribute("id"))?s=s.replace(xe,"\\$&"):t.setAttribute("id",s=P),f=N(e),o=f.length,u=de.test(s)?"#"+s:"[id='"+s+"']";o--;)f[o]=u+" "+d(f[o]);p=f.join(","),h=ve.test(e)&&c(t.parentNode)||t}if(p)try{return Q.apply(n,h.querySelectorAll(p)),n}catch(m){}finally{s===P&&t.removeAttribute("id")}}}return S(e.replace(se,"$1"),t,n,r)}function n(){function e(n,r){return t.push(n+" ")>T.cacheLength&&delete e[t.shift()],e[n+" "]=r}var t=[];return e}function r(e){return e[P]=!0,e}function i(e){var t=H.createElement("div");try{return!!e(t)}catch(n){return!1}finally{t.parentNode&&t.parentNode.removeChild(t),t=null}}function o(e,t){for(var n=e.split("|"),r=n.length;r--;)T.attrHandle[n[r]]=t}function a(e,t){var n=t&&e,r=n&&1===e.nodeType&&1===t.nodeType&&(~t.sourceIndex||V)-(~e.sourceIndex||V);if(r)return r;if(n)for(;n=n.nextSibling;)if(n===t)return-1;return e?1:-1}function s(e){return function(t){var n=t.nodeName.toLowerCase();return"input"===n&&t.type===e}}function u(e){return function(t){var n=t.nodeName.toLowerCase();return("input"===n||"button"===n)&&t.type===e}}function l(e){return r(function(t){return t=+t,r(function(n,r){for(var i,o=e([],n.length,t),a=o.length;a--;)n[i=o[a]]&&(n[i]=!(r[i]=n[i]))})})}function c(e){return e&&"undefined"!=typeof e.getElementsByTagName&&e}function f(){}function d(e){for(var t=0,n=e.length,r="";t<n;t++)r+=e[t].value;return r}function p(e,t,n){var r=t.dir,i=n&&"parentNode"===r,o=I++;return t.first?function(t,n,o){for(;t=t[r];)if(1===t.nodeType||i)return e(t,n,o)}:function(t,n,a){var s,u,l,c=[W,o];if(a){for(;t=t[r];)if((1===t.nodeType||i)&&e(t,n,a))return!0}else for(;t=t[r];)if(1===t.nodeType||i){if(l=t[P]||(t[P]={}),u=l[t.uniqueID]||(l[t.uniqueID]={}),(s=u[r])&&s[0]===W&&s[1]===o)return c[2]=s[2];if(u[r]=c,c[2]=e(t,n,a))return!0}}}function h(e){return e.length>1?function(t,n,r){for(var i=e.length;i--;)if(!e[i](t,n,r))return!1;return!0}:e[0]}function g(e,n,r){for(var i=0,o=n.length;i<o;i++)t(e,n[i],r);return r}function m(e,t,n,r,i){for(var o,a=[],s=0,u=e.length,l=null!=t;s<u;s++)(o=e[s])&&(n&&!n(o,r,i)||(a.push(o),l&&t.push(s)));return a}function y(e,t,n,i,o,a){return i&&!i[P]&&(i=y(i)),o&&!o[P]&&(o=y(o,a)),r(function(r,a,s,u){var l,c,f,d=[],p=[],h=a.length,y=r||g(t||"*",s.nodeType?[s]:s,[]),v=!e||!r&&t?y:m(y,d,e,s,u),x=n?o||(r?e:h||i)?[]:a:v;if(n&&n(v,x,s,u),i)for(l=m(x,p),i(l,[],s,u),c=l.length;c--;)(f=l[c])&&(x[p[c]]=!(v[p[c]]=f));if(r){if(o||e){if(o){for(l=[],c=x.length;c--;)(f=x[c])&&l.push(v[c]=f);o(null,x=[],l,u)}for(c=x.length;c--;)(f=x[c])&&(l=o?ee(r,f):d[c])>-1&&(r[l]=!(a[l]=f))}}else x=m(x===a?x.splice(h,x.length):x),o?o(null,a,x,u):Q.apply(a,x)})}function v(e){for(var t,n,r,i=e.length,o=T.relative[e[0].type],a=o||T.relative[" "],s=o?1:0,u=p(function(e){return e===t},a,!0),l=p(function(e){return ee(t,e)>-1},a,!0),c=[function(e,n,r){var i=!o&&(r||n!==A)||((t=n).nodeType?u(e,n,r):l(e,n,r));return t=null,i}];s<i;s++)if(n=T.relative[e[s].type])c=[p(h(c),n)];else{if(n=T.filter[e[s].type].apply(null,e[s].matches),n[P]){for(r=++s;r<i&&!T.relative[e[r].type];r++);return y(s>1&&h(c),s>1&&d(e.slice(0,s-1).concat({value:" "===e[s-2].type?"*":""})).replace(se,"$1"),n,s<r&&v(e.slice(s,r)),r<i&&v(e=e.slice(r)),r<i&&d(e))}c.push(n)}return h(c)}function x(e,n){var i=n.length>0,o=e.length>0,a=function(r,a,s,u,l){var c,f,d,p=0,h="0",g=r&&[],y=[],v=A,x=r||o&&T.find.TAG("*",l),b=W+=null==v?1:Math.random()||.1,w=x.length;for(l&&(A=a===H||a||l);h!==w&&null!=(c=x[h]);h++){if(o&&c){for(f=0,a||c.ownerDocument===H||(L(c),s=!_);d=e[f++];)if(d(c,a||H,s)){u.push(c);break}l&&(W=b)}i&&((c=!d&&c)&&p--,r&&g.push(c))}if(p+=h,i&&h!==p){for(f=0;d=n[f++];)d(g,y,a,s);if(r){if(p>0)for(;h--;)g[h]||y[h]||(y[h]=G.call(u));y=m(y)}Q.apply(u,y),l&&!r&&y.length>0&&p+n.length>1&&t.uniqueSort(u)}return l&&(W=b,A=v),g};return i?r(a):a}var b,w,T,C,E,N,k,S,A,D,j,L,H,q,_,F,M,O,R,P="sizzle"+1*new Date,B=e.document,W=0,I=0,$=n(),z=n(),X=n(),U=function(e,t){return e===t&&(j=!0),0},V=1<<31,Y={}.hasOwnProperty,J=[],G=J.pop,K=J.push,Q=J.push,Z=J.slice,ee=function(e,t){for(var n=0,r=e.length;n<r;n++)if(e[n]===t)return n;return-1},te="checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped",ne="[\\x20\\t\\r\\n\\f]",re="(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+",ie="\\["+ne+"*("+re+")(?:"+ne+"*([*^$|!~]?=)"+ne+"*(?:'((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\"|("+re+"))|)"+ne+"*\\]",oe=":("+re+")(?:\\((('((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\")|((?:\\\\.|[^\\\\()[\\]]|"+ie+")*)|.*)\\)|)",ae=new RegExp(ne+"+","g"),se=new RegExp("^"+ne+"+|((?:^|[^\\\\])(?:\\\\.)*)"+ne+"+$","g"),ue=new RegExp("^"+ne+"*,"+ne+"*"),le=new RegExp("^"+ne+"*([>+~]|"+ne+")"+ne+"*"),ce=new RegExp("="+ne+"*([^\\]'\"]*?)"+ne+"*\\]","g"),fe=new RegExp(oe),de=new RegExp("^"+re+"$"),pe={ID:new RegExp("^#("+re+")"),CLASS:new RegExp("^\\.("+re+")"),TAG:new RegExp("^("+re+"|[*])"),ATTR:new RegExp("^"+ie),PSEUDO:new RegExp("^"+oe),CHILD:new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\("+ne+"*(even|odd|(([+-]|)(\\d*)n|)"+ne+"*(?:([+-]|)"+ne+"*(\\d+)|))"+ne+"*\\)|)","i"),bool:new RegExp("^(?:"+te+")$","i"),needsContext:new RegExp("^"+ne+"*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\("+ne+"*((?:-\\d)?\\d*)"+ne+"*\\)|)(?=[^-]|$)","i")},he=/^(?:input|select|textarea|button)$/i,ge=/^h\d$/i,me=/^[^{]+\{\s*\[native \w/,ye=/^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/,ve=/[+~]/,xe=/'|\\/g,be=new RegExp("\\\\([\\da-f]{1,6}"+ne+"?|("+ne+")|.)","ig"),we=function(e,t,n){var r="0x"+t-65536;return r!==r||n?t:r<0?String.fromCharCode(r+65536):String.fromCharCode(r>>10|55296,1023&r|56320)},Te=function(){L()};try{Q.apply(J=Z.call(B.childNodes),B.childNodes),J[B.childNodes.length].nodeType}catch(Ce){Q={apply:J.length?function(e,t){K.apply(e,Z.call(t))}:function(e,t){for(var n=e.length,r=0;e[n++]=t[r++];);e.length=n-1}}}w=t.support={},E=t.isXML=function(e){var t=e&&(e.ownerDocument||e).documentElement;return!!t&&"HTML"!==t.nodeName},L=t.setDocument=function(e){var t,n,r=e?e.ownerDocument||e:B;return r!==H&&9===r.nodeType&&r.documentElement?(H=r,q=H.documentElement,_=!E(H),(n=H.defaultView)&&n.top!==n&&(n.addEventListener?n.addEventListener("unload",Te,!1):n.attachEvent&&n.attachEvent("onunload",Te)),w.attributes=i(function(e){return e.className="i",!e.getAttribute("className")}),w.getElementsByTagName=i(function(e){return e.appendChild(H.createComment("")),!e.getElementsByTagName("*").length}),w.getElementsByClassName=me.test(H.getElementsByClassName),w.getById=i(function(e){return q.appendChild(e).id=P,!H.getElementsByName||!H.getElementsByName(P).length}),w.getById?(T.find.ID=function(e,t){if("undefined"!=typeof t.getElementById&&_){var n=t.getElementById(e);return n?[n]:[]}},T.filter.ID=function(e){var t=e.replace(be,we);return function(e){return e.getAttribute("id")===t}}):(delete T.find.ID,T.filter.ID=function(e){var t=e.replace(be,we);return function(e){var n="undefined"!=typeof e.getAttributeNode&&e.getAttributeNode("id");return n&&n.value===t}}),T.find.TAG=w.getElementsByTagName?function(e,t){return"undefined"!=typeof t.getElementsByTagName?t.getElementsByTagName(e):w.qsa?t.querySelectorAll(e):void 0}:function(e,t){var n,r=[],i=0,o=t.getElementsByTagName(e);if("*"===e){for(;n=o[i++];)1===n.nodeType&&r.push(n);return r}return o},T.find.CLASS=w.getElementsByClassName&&function(e,t){if("undefined"!=typeof t.getElementsByClassName&&_)return t.getElementsByClassName(e)},M=[],F=[],(w.qsa=me.test(H.querySelectorAll))&&(i(function(e){q.appendChild(e).innerHTML="<a id='"+P+"'></a><select id='"+P+"-\r\\' msallowcapture=''><option selected=''></option></select>",e.querySelectorAll("[msallowcapture^='']").length&&F.push("[*^$]="+ne+"*(?:''|\"\")"),e.querySelectorAll("[selected]").length||F.push("\\["+ne+"*(?:value|"+te+")"),e.querySelectorAll("[id~="+P+"-]").length||F.push("~="),e.querySelectorAll(":checked").length||F.push(":checked"),e.querySelectorAll("a#"+P+"+*").length||F.push(".#.+[+~]")}),i(function(e){var t=H.createElement("input");t.setAttribute("type","hidden"),e.appendChild(t).setAttribute("name","D"),e.querySelectorAll("[name=d]").length&&F.push("name"+ne+"*[*^$|!~]?="),e.querySelectorAll(":enabled").length||F.push(":enabled",":disabled"),e.querySelectorAll("*,:x"),F.push(",.*:")})),(w.matchesSelector=me.test(O=q.matches||q.webkitMatchesSelector||q.mozMatchesSelector||q.oMatchesSelector||q.msMatchesSelector))&&i(function(e){w.disconnectedMatch=O.call(e,"div"),O.call(e,"[s!='']:x"),M.push("!=",oe)}),F=F.length&&new RegExp(F.join("|")),M=M.length&&new RegExp(M.join("|")),t=me.test(q.compareDocumentPosition),R=t||me.test(q.contains)?function(e,t){var n=9===e.nodeType?e.documentElement:e,r=t&&t.parentNode;return e===r||!(!r||1!==r.nodeType||!(n.contains?n.contains(r):e.compareDocumentPosition&&16&e.compareDocumentPosition(r)))}:function(e,t){if(t)for(;t=t.parentNode;)if(t===e)return!0;return!1},U=t?function(e,t){if(e===t)return j=!0,0;var n=!e.compareDocumentPosition-!t.compareDocumentPosition;return n?n:(n=(e.ownerDocument||e)===(t.ownerDocument||t)?e.compareDocumentPosition(t):1,1&n||!w.sortDetached&&t.compareDocumentPosition(e)===n?e===H||e.ownerDocument===B&&R(B,e)?-1:t===H||t.ownerDocument===B&&R(B,t)?1:D?ee(D,e)-ee(D,t):0:4&n?-1:1)}:function(e,t){if(e===t)return j=!0,0;var n,r=0,i=e.parentNode,o=t.parentNode,s=[e],u=[t];if(!i||!o)return e===H?-1:t===H?1:i?-1:o?1:D?ee(D,e)-ee(D,t):0;if(i===o)return a(e,t);for(n=e;n=n.parentNode;)s.unshift(n);for(n=t;n=n.parentNode;)u.unshift(n);for(;s[r]===u[r];)r++;return r?a(s[r],u[r]):s[r]===B?-1:u[r]===B?1:0},H):H},t.matches=function(e,n){return t(e,null,null,n)},t.matchesSelector=function(e,n){if((e.ownerDocument||e)!==H&&L(e),n=n.replace(ce,"='$1']"),w.matchesSelector&&_&&!X[n+" "]&&(!M||!M.test(n))&&(!F||!F.test(n)))try{var r=O.call(e,n);if(r||w.disconnectedMatch||e.document&&11!==e.document.nodeType)return r}catch(i){}return t(n,H,null,[e]).length>0},t.contains=function(e,t){return(e.ownerDocument||e)!==H&&L(e),R(e,t)},t.attr=function(e,t){(e.ownerDocument||e)!==H&&L(e);var n=T.attrHandle[t.toLowerCase()],r=n&&Y.call(T.attrHandle,t.toLowerCase())?n(e,t,!_):void 0;return void 0!==r?r:w.attributes||!_?e.getAttribute(t):(r=e.getAttributeNode(t))&&r.specified?r.value:null},t.error=function(e){throw new Error("Syntax error, unrecognized expression: "+e)},t.uniqueSort=function(e){var t,n=[],r=0,i=0;if(j=!w.detectDuplicates,D=!w.sortStable&&e.slice(0),e.sort(U),j){for(;t=e[i++];)t===e[i]&&(r=n.push(i));for(;r--;)e.splice(n[r],1)}return D=null,e},C=t.getText=function(e){var t,n="",r=0,i=e.nodeType;if(i){if(1===i||9===i||11===i){if("string"==typeof e.textContent)return e.textContent;for(e=e.firstChild;e;e=e.nextSibling)n+=C(e)}else if(3===i||4===i)return e.nodeValue}else for(;t=e[r++];)n+=C(t);return n},T=t.selectors={cacheLength:50,createPseudo:r,match:pe,attrHandle:{},find:{},relative:{">":{dir:"parentNode",first:!0}," ":{dir:"parentNode"},"+":{dir:"previousSibling",first:!0},"~":{dir:"previousSibling"}},preFilter:{ATTR:function(e){return e[1]=e[1].replace(be,we),e[3]=(e[3]||e[4]||e[5]||"").replace(be,we),"~="===e[2]&&(e[3]=" "+e[3]+" "),e.slice(0,4)},CHILD:function(e){return e[1]=e[1].toLowerCase(),"nth"===e[1].slice(0,3)?(e[3]||t.error(e[0]),e[4]=+(e[4]?e[5]+(e[6]||1):2*("even"===e[3]||"odd"===e[3])),e[5]=+(e[7]+e[8]||"odd"===e[3])):e[3]&&t.error(e[0]),e},PSEUDO:function(e){var t,n=!e[6]&&e[2];return pe.CHILD.test(e[0])?null:(e[3]?e[2]=e[4]||e[5]||"":n&&fe.test(n)&&(t=N(n,!0))&&(t=n.indexOf(")",n.length-t)-n.length)&&(e[0]=e[0].slice(0,t),e[2]=n.slice(0,t)),e.slice(0,3))}},filter:{TAG:function(e){var t=e.replace(be,we).toLowerCase();return"*"===e?function(){return!0}:function(e){return e.nodeName&&e.nodeName.toLowerCase()===t}},CLASS:function(e){var t=$[e+" "];return t||(t=new RegExp("(^|"+ne+")"+e+"("+ne+"|$)"))&&$(e,function(e){return t.test("string"==typeof e.className&&e.className||"undefined"!=typeof e.getAttribute&&e.getAttribute("class")||"")})},ATTR:function(e,n,r){return function(i){var o=t.attr(i,e);return null==o?"!="===n:!n||(o+="","="===n?o===r:"!="===n?o!==r:"^="===n?r&&0===o.indexOf(r):"*="===n?r&&o.indexOf(r)>-1:"$="===n?r&&o.slice(-r.length)===r:"~="===n?(" "+o.replace(ae," ")+" ").indexOf(r)>-1:"|="===n&&(o===r||o.slice(0,r.length+1)===r+"-"))}},CHILD:function(e,t,n,r,i){var o="nth"!==e.slice(0,3),a="last"!==e.slice(-4),s="of-type"===t;return 1===r&&0===i?function(e){return!!e.parentNode}:function(t,n,u){var l,c,f,d,p,h,g=o!==a?"nextSibling":"previousSibling",m=t.parentNode,y=s&&t.nodeName.toLowerCase(),v=!u&&!s,x=!1;if(m){if(o){for(;g;){for(d=t;d=d[g];)if(s?d.nodeName.toLowerCase()===y:1===d.nodeType)return!1;h=g="only"===e&&!h&&"nextSibling"}return!0}if(h=[a?m.firstChild:m.lastChild],a&&v){for(d=m,f=d[P]||(d[P]={}),c=f[d.uniqueID]||(f[d.uniqueID]={}),
l=c[e]||[],p=l[0]===W&&l[1],x=p&&l[2],d=p&&m.childNodes[p];d=++p&&d&&d[g]||(x=p=0)||h.pop();)if(1===d.nodeType&&++x&&d===t){c[e]=[W,p,x];break}}else if(v&&(d=t,f=d[P]||(d[P]={}),c=f[d.uniqueID]||(f[d.uniqueID]={}),l=c[e]||[],p=l[0]===W&&l[1],x=p),x===!1)for(;(d=++p&&d&&d[g]||(x=p=0)||h.pop())&&((s?d.nodeName.toLowerCase()!==y:1!==d.nodeType)||!++x||(v&&(f=d[P]||(d[P]={}),c=f[d.uniqueID]||(f[d.uniqueID]={}),c[e]=[W,x]),d!==t)););return x-=i,x===r||x%r===0&&x/r>=0}}},PSEUDO:function(e,n){var i,o=T.pseudos[e]||T.setFilters[e.toLowerCase()]||t.error("unsupported pseudo: "+e);return o[P]?o(n):o.length>1?(i=[e,e,"",n],T.setFilters.hasOwnProperty(e.toLowerCase())?r(function(e,t){for(var r,i=o(e,n),a=i.length;a--;)r=ee(e,i[a]),e[r]=!(t[r]=i[a])}):function(e){return o(e,0,i)}):o}},pseudos:{not:r(function(e){var t=[],n=[],i=k(e.replace(se,"$1"));return i[P]?r(function(e,t,n,r){for(var o,a=i(e,null,r,[]),s=e.length;s--;)(o=a[s])&&(e[s]=!(t[s]=o))}):function(e,r,o){return t[0]=e,i(t,null,o,n),t[0]=null,!n.pop()}}),has:r(function(e){return function(n){return t(e,n).length>0}}),contains:r(function(e){return e=e.replace(be,we),function(t){return(t.textContent||t.innerText||C(t)).indexOf(e)>-1}}),lang:r(function(e){return de.test(e||"")||t.error("unsupported lang: "+e),e=e.replace(be,we).toLowerCase(),function(t){var n;do if(n=_?t.lang:t.getAttribute("xml:lang")||t.getAttribute("lang"))return n=n.toLowerCase(),n===e||0===n.indexOf(e+"-");while((t=t.parentNode)&&1===t.nodeType);return!1}}),target:function(t){var n=e.location&&e.location.hash;return n&&n.slice(1)===t.id},root:function(e){return e===q},focus:function(e){return e===H.activeElement&&(!H.hasFocus||H.hasFocus())&&!!(e.type||e.href||~e.tabIndex)},enabled:function(e){return e.disabled===!1},disabled:function(e){return e.disabled===!0},checked:function(e){var t=e.nodeName.toLowerCase();return"input"===t&&!!e.checked||"option"===t&&!!e.selected},selected:function(e){return e.parentNode&&e.parentNode.selectedIndex,e.selected===!0},empty:function(e){for(e=e.firstChild;e;e=e.nextSibling)if(e.nodeType<6)return!1;return!0},parent:function(e){return!T.pseudos.empty(e)},header:function(e){return ge.test(e.nodeName)},input:function(e){return he.test(e.nodeName)},button:function(e){var t=e.nodeName.toLowerCase();return"input"===t&&"button"===e.type||"button"===t},text:function(e){var t;return"input"===e.nodeName.toLowerCase()&&"text"===e.type&&(null==(t=e.getAttribute("type"))||"text"===t.toLowerCase())},first:l(function(){return[0]}),last:l(function(e,t){return[t-1]}),eq:l(function(e,t,n){return[n<0?n+t:n]}),even:l(function(e,t){for(var n=0;n<t;n+=2)e.push(n);return e}),odd:l(function(e,t){for(var n=1;n<t;n+=2)e.push(n);return e}),lt:l(function(e,t,n){for(var r=n<0?n+t:n;--r>=0;)e.push(r);return e}),gt:l(function(e,t,n){for(var r=n<0?n+t:n;++r<t;)e.push(r);return e})}},T.pseudos.nth=T.pseudos.eq;for(b in{radio:!0,checkbox:!0,file:!0,password:!0,image:!0})T.pseudos[b]=s(b);for(b in{submit:!0,reset:!0})T.pseudos[b]=u(b);return f.prototype=T.filters=T.pseudos,T.setFilters=new f,N=t.tokenize=function(e,n){var r,i,o,a,s,u,l,c=z[e+" "];if(c)return n?0:c.slice(0);for(s=e,u=[],l=T.preFilter;s;){r&&!(i=ue.exec(s))||(i&&(s=s.slice(i[0].length)||s),u.push(o=[])),r=!1,(i=le.exec(s))&&(r=i.shift(),o.push({value:r,type:i[0].replace(se," ")}),s=s.slice(r.length));for(a in T.filter)!(i=pe[a].exec(s))||l[a]&&!(i=l[a](i))||(r=i.shift(),o.push({value:r,type:a,matches:i}),s=s.slice(r.length));if(!r)break}return n?s.length:s?t.error(e):z(e,u).slice(0)},k=t.compile=function(e,t){var n,r=[],i=[],o=X[e+" "];if(!o){for(t||(t=N(e)),n=t.length;n--;)o=v(t[n]),o[P]?r.push(o):i.push(o);o=X(e,x(i,r)),o.selector=e}return o},S=t.select=function(e,t,n,r){var i,o,a,s,u,l="function"==typeof e&&e,f=!r&&N(e=l.selector||e);if(n=n||[],1===f.length){if(o=f[0]=f[0].slice(0),o.length>2&&"ID"===(a=o[0]).type&&w.getById&&9===t.nodeType&&_&&T.relative[o[1].type]){if(t=(T.find.ID(a.matches[0].replace(be,we),t)||[])[0],!t)return n;l&&(t=t.parentNode),e=e.slice(o.shift().value.length)}for(i=pe.needsContext.test(e)?0:o.length;i--&&(a=o[i],!T.relative[s=a.type]);)if((u=T.find[s])&&(r=u(a.matches[0].replace(be,we),ve.test(o[0].type)&&c(t.parentNode)||t))){if(o.splice(i,1),e=r.length&&d(o),!e)return Q.apply(n,r),n;break}}return(l||k(e,f))(r,t,!_,n,!t||ve.test(e)&&c(t.parentNode)||t),n},w.sortStable=P.split("").sort(U).join("")===P,w.detectDuplicates=!!j,L(),w.sortDetached=i(function(e){return 1&e.compareDocumentPosition(H.createElement("div"))}),i(function(e){return e.innerHTML="<a href='#'></a>","#"===e.firstChild.getAttribute("href")})||o("type|href|height|width",function(e,t,n){if(!n)return e.getAttribute(t,"type"===t.toLowerCase()?1:2)}),w.attributes&&i(function(e){return e.innerHTML="<input/>",e.firstChild.setAttribute("value",""),""===e.firstChild.getAttribute("value")})||o("value",function(e,t,n){if(!n&&"input"===e.nodeName.toLowerCase())return e.defaultValue}),i(function(e){return null==e.getAttribute("disabled")})||o(te,function(e,t,n){var r;if(!n)return e[t]===!0?t.toLowerCase():(r=e.getAttributeNode(t))&&r.specified?r.value:null}),t}(e);pe.find=ve,pe.expr=ve.selectors,pe.expr[":"]=pe.expr.pseudos,pe.uniqueSort=pe.unique=ve.uniqueSort,pe.text=ve.getText,pe.isXMLDoc=ve.isXML,pe.contains=ve.contains;var xe=function(e,t,n){for(var r=[],i=void 0!==n;(e=e[t])&&9!==e.nodeType;)if(1===e.nodeType){if(i&&pe(e).is(n))break;r.push(e)}return r},be=function(e,t){for(var n=[];e;e=e.nextSibling)1===e.nodeType&&e!==t&&n.push(e);return n},we=pe.expr.match.needsContext,Te=/^<([\w-]+)\s*\/?>(?:<\/\1>|)$/,Ce=/^.[^:#\[\.,]*$/;pe.filter=function(e,t,n){var r=t[0];return n&&(e=":not("+e+")"),1===t.length&&1===r.nodeType?pe.find.matchesSelector(r,e)?[r]:[]:pe.find.matches(e,pe.grep(t,function(e){return 1===e.nodeType}))},pe.fn.extend({find:function(e){var t,n=[],r=this,i=r.length;if("string"!=typeof e)return this.pushStack(pe(e).filter(function(){for(t=0;t<i;t++)if(pe.contains(r[t],this))return!0}));for(t=0;t<i;t++)pe.find(e,r[t],n);return n=this.pushStack(i>1?pe.unique(n):n),n.selector=this.selector?this.selector+" "+e:e,n},filter:function(e){return this.pushStack(r(this,e||[],!1))},not:function(e){return this.pushStack(r(this,e||[],!0))},is:function(e){return!!r(this,"string"==typeof e&&we.test(e)?pe(e):e||[],!1).length}});var Ee,Ne=/^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/,ke=pe.fn.init=function(e,t,n){var r,i;if(!e)return this;if(n=n||Ee,"string"==typeof e){if(r="<"===e.charAt(0)&&">"===e.charAt(e.length-1)&&e.length>=3?[null,e,null]:Ne.exec(e),!r||!r[1]&&t)return!t||t.jquery?(t||n).find(e):this.constructor(t).find(e);if(r[1]){if(t=t instanceof pe?t[0]:t,pe.merge(this,pe.parseHTML(r[1],t&&t.nodeType?t.ownerDocument||t:re,!0)),Te.test(r[1])&&pe.isPlainObject(t))for(r in t)pe.isFunction(this[r])?this[r](t[r]):this.attr(r,t[r]);return this}if(i=re.getElementById(r[2]),i&&i.parentNode){if(i.id!==r[2])return Ee.find(e);this.length=1,this[0]=i}return this.context=re,this.selector=e,this}return e.nodeType?(this.context=this[0]=e,this.length=1,this):pe.isFunction(e)?"undefined"!=typeof n.ready?n.ready(e):e(pe):(void 0!==e.selector&&(this.selector=e.selector,this.context=e.context),pe.makeArray(e,this))};ke.prototype=pe.fn,Ee=pe(re);var Se=/^(?:parents|prev(?:Until|All))/,Ae={children:!0,contents:!0,next:!0,prev:!0};pe.fn.extend({has:function(e){var t,n=pe(e,this),r=n.length;return this.filter(function(){for(t=0;t<r;t++)if(pe.contains(this,n[t]))return!0})},closest:function(e,t){for(var n,r=0,i=this.length,o=[],a=we.test(e)||"string"!=typeof e?pe(e,t||this.context):0;r<i;r++)for(n=this[r];n&&n!==t;n=n.parentNode)if(n.nodeType<11&&(a?a.index(n)>-1:1===n.nodeType&&pe.find.matchesSelector(n,e))){o.push(n);break}return this.pushStack(o.length>1?pe.uniqueSort(o):o)},index:function(e){return e?"string"==typeof e?pe.inArray(this[0],pe(e)):pe.inArray(e.jquery?e[0]:e,this):this[0]&&this[0].parentNode?this.first().prevAll().length:-1},add:function(e,t){return this.pushStack(pe.uniqueSort(pe.merge(this.get(),pe(e,t))))},addBack:function(e){return this.add(null==e?this.prevObject:this.prevObject.filter(e))}}),pe.each({parent:function(e){var t=e.parentNode;return t&&11!==t.nodeType?t:null},parents:function(e){return xe(e,"parentNode")},parentsUntil:function(e,t,n){return xe(e,"parentNode",n)},next:function(e){return i(e,"nextSibling")},prev:function(e){return i(e,"previousSibling")},nextAll:function(e){return xe(e,"nextSibling")},prevAll:function(e){return xe(e,"previousSibling")},nextUntil:function(e,t,n){return xe(e,"nextSibling",n)},prevUntil:function(e,t,n){return xe(e,"previousSibling",n)},siblings:function(e){return be((e.parentNode||{}).firstChild,e)},children:function(e){return be(e.firstChild)},contents:function(e){return pe.nodeName(e,"iframe")?e.contentDocument||e.contentWindow.document:pe.merge([],e.childNodes)}},function(e,t){pe.fn[e]=function(n,r){var i=pe.map(this,t,n);return"Until"!==e.slice(-5)&&(r=n),r&&"string"==typeof r&&(i=pe.filter(r,i)),this.length>1&&(Ae[e]||(i=pe.uniqueSort(i)),Se.test(e)&&(i=i.reverse())),this.pushStack(i)}});var De=/\S+/g;pe.Callbacks=function(e){e="string"==typeof e?o(e):pe.extend({},e);var t,n,r,i,a=[],s=[],u=-1,l=function(){for(i=e.once,r=t=!0;s.length;u=-1)for(n=s.shift();++u<a.length;)a[u].apply(n[0],n[1])===!1&&e.stopOnFalse&&(u=a.length,n=!1);e.memory||(n=!1),t=!1,i&&(a=n?[]:"")},c={add:function(){return a&&(n&&!t&&(u=a.length-1,s.push(n)),function r(t){pe.each(t,function(t,n){pe.isFunction(n)?e.unique&&c.has(n)||a.push(n):n&&n.length&&"string"!==pe.type(n)&&r(n)})}(arguments),n&&!t&&l()),this},remove:function(){return pe.each(arguments,function(e,t){for(var n;(n=pe.inArray(t,a,n))>-1;)a.splice(n,1),n<=u&&u--}),this},has:function(e){return e?pe.inArray(e,a)>-1:a.length>0},empty:function(){return a&&(a=[]),this},disable:function(){return i=s=[],a=n="",this},disabled:function(){return!a},lock:function(){return i=!0,n||c.disable(),this},locked:function(){return!!i},fireWith:function(e,n){return i||(n=n||[],n=[e,n.slice?n.slice():n],s.push(n),t||l()),this},fire:function(){return c.fireWith(this,arguments),this},fired:function(){return!!r}};return c},pe.extend({Deferred:function(e){var t=[["resolve","done",pe.Callbacks("once memory"),"resolved"],["reject","fail",pe.Callbacks("once memory"),"rejected"],["notify","progress",pe.Callbacks("memory")]],n="pending",r={state:function(){return n},always:function(){return i.done(arguments).fail(arguments),this},then:function(){var e=arguments;return pe.Deferred(function(n){pe.each(t,function(t,o){var a=pe.isFunction(e[t])&&e[t];i[o[1]](function(){var e=a&&a.apply(this,arguments);e&&pe.isFunction(e.promise)?e.promise().progress(n.notify).done(n.resolve).fail(n.reject):n[o[0]+"With"](this===r?n.promise():this,a?[e]:arguments)})}),e=null}).promise()},promise:function(e){return null!=e?pe.extend(e,r):r}},i={};return r.pipe=r.then,pe.each(t,function(e,o){var a=o[2],s=o[3];r[o[1]]=a.add,s&&a.add(function(){n=s},t[1^e][2].disable,t[2][2].lock),i[o[0]]=function(){return i[o[0]+"With"](this===i?r:this,arguments),this},i[o[0]+"With"]=a.fireWith}),r.promise(i),e&&e.call(i,i),i},when:function(e){var t,n,r,i=0,o=ie.call(arguments),a=o.length,s=1!==a||e&&pe.isFunction(e.promise)?a:0,u=1===s?e:pe.Deferred(),l=function(e,n,r){return function(i){n[e]=this,r[e]=arguments.length>1?ie.call(arguments):i,r===t?u.notifyWith(n,r):--s||u.resolveWith(n,r)}};if(a>1)for(t=new Array(a),n=new Array(a),r=new Array(a);i<a;i++)o[i]&&pe.isFunction(o[i].promise)?o[i].promise().progress(l(i,n,t)).done(l(i,r,o)).fail(u.reject):--s;return s||u.resolveWith(r,o),u.promise()}});var je;pe.fn.ready=function(e){return pe.ready.promise().done(e),this},pe.extend({isReady:!1,readyWait:1,holdReady:function(e){e?pe.readyWait++:pe.ready(!0)},ready:function(e){(e===!0?--pe.readyWait:pe.isReady)||(pe.isReady=!0,e!==!0&&--pe.readyWait>0||(je.resolveWith(re,[pe]),pe.fn.triggerHandler&&(pe(re).triggerHandler("ready"),pe(re).off("ready"))))}}),pe.ready.promise=function(t){if(!je)if(je=pe.Deferred(),"complete"===re.readyState||"loading"!==re.readyState&&!re.documentElement.doScroll)e.setTimeout(pe.ready);else if(re.addEventListener)re.addEventListener("DOMContentLoaded",s),e.addEventListener("load",s);else{re.attachEvent("onreadystatechange",s),e.attachEvent("onload",s);var n=!1;try{n=null==e.frameElement&&re.documentElement}catch(r){}n&&n.doScroll&&!function i(){if(!pe.isReady){try{n.doScroll("left")}catch(t){return e.setTimeout(i,50)}a(),pe.ready()}}()}return je.promise(t)},pe.ready.promise();var Le;for(Le in pe(fe))break;fe.ownFirst="0"===Le,fe.inlineBlockNeedsLayout=!1,pe(function(){var e,t,n,r;n=re.getElementsByTagName("body")[0],n&&n.style&&(t=re.createElement("div"),r=re.createElement("div"),r.style.cssText="position:absolute;border:0;width:0;height:0;top:0;left:-9999px",n.appendChild(r).appendChild(t),"undefined"!=typeof t.style.zoom&&(t.style.cssText="display:inline;margin:0;border:0;padding:1px;width:1px;zoom:1",fe.inlineBlockNeedsLayout=e=3===t.offsetWidth,e&&(n.style.zoom=1)),n.removeChild(r))}),function(){var e=re.createElement("div");fe.deleteExpando=!0;try{delete e.test}catch(t){fe.deleteExpando=!1}e=null}();var He=function(e){var t=pe.noData[(e.nodeName+" ").toLowerCase()],n=+e.nodeType||1;return(1===n||9===n)&&(!t||t!==!0&&e.getAttribute("classid")===t)},qe=/^(?:\{[\w\W]*\}|\[[\w\W]*\])$/,_e=/([A-Z])/g;pe.extend({cache:{},noData:{"applet ":!0,"embed ":!0,"object ":"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"},hasData:function(e){return e=e.nodeType?pe.cache[e[pe.expando]]:e[pe.expando],!!e&&!l(e)},data:function(e,t,n){return c(e,t,n)},removeData:function(e,t){return f(e,t)},_data:function(e,t,n){return c(e,t,n,!0)},_removeData:function(e,t){return f(e,t,!0)}}),pe.fn.extend({data:function(e,t){var n,r,i,o=this[0],a=o&&o.attributes;if(void 0===e){if(this.length&&(i=pe.data(o),1===o.nodeType&&!pe._data(o,"parsedAttrs"))){for(n=a.length;n--;)a[n]&&(r=a[n].name,0===r.indexOf("data-")&&(r=pe.camelCase(r.slice(5)),u(o,r,i[r])));pe._data(o,"parsedAttrs",!0)}return i}return"object"==typeof e?this.each(function(){pe.data(this,e)}):arguments.length>1?this.each(function(){pe.data(this,e,t)}):o?u(o,e,pe.data(o,e)):void 0},removeData:function(e){return this.each(function(){pe.removeData(this,e)})}}),pe.extend({queue:function(e,t,n){var r;if(e)return t=(t||"fx")+"queue",r=pe._data(e,t),n&&(!r||pe.isArray(n)?r=pe._data(e,t,pe.makeArray(n)):r.push(n)),r||[]},dequeue:function(e,t){t=t||"fx";var n=pe.queue(e,t),r=n.length,i=n.shift(),o=pe._queueHooks(e,t),a=function(){pe.dequeue(e,t)};"inprogress"===i&&(i=n.shift(),r--),i&&("fx"===t&&n.unshift("inprogress"),delete o.stop,i.call(e,a,o)),!r&&o&&o.empty.fire()},_queueHooks:function(e,t){var n=t+"queueHooks";return pe._data(e,n)||pe._data(e,n,{empty:pe.Callbacks("once memory").add(function(){pe._removeData(e,t+"queue"),pe._removeData(e,n)})})}}),pe.fn.extend({queue:function(e,t){var n=2;return"string"!=typeof e&&(t=e,e="fx",n--),arguments.length<n?pe.queue(this[0],e):void 0===t?this:this.each(function(){var n=pe.queue(this,e,t);pe._queueHooks(this,e),"fx"===e&&"inprogress"!==n[0]&&pe.dequeue(this,e)})},dequeue:function(e){return this.each(function(){pe.dequeue(this,e)})},clearQueue:function(e){return this.queue(e||"fx",[])},promise:function(e,t){var n,r=1,i=pe.Deferred(),o=this,a=this.length,s=function(){--r||i.resolveWith(o,[o])};for("string"!=typeof e&&(t=e,e=void 0),e=e||"fx";a--;)n=pe._data(o[a],e+"queueHooks"),n&&n.empty&&(r++,n.empty.add(s));return s(),i.promise(t)}}),function(){var e;fe.shrinkWrapBlocks=function(){if(null!=e)return e;e=!1;var t,n,r;return n=re.getElementsByTagName("body")[0],n&&n.style?(t=re.createElement("div"),r=re.createElement("div"),r.style.cssText="position:absolute;border:0;width:0;height:0;top:0;left:-9999px",n.appendChild(r).appendChild(t),"undefined"!=typeof t.style.zoom&&(t.style.cssText="-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;margin:0;border:0;padding:1px;width:1px;zoom:1",t.appendChild(re.createElement("div")).style.width="5px",e=3!==t.offsetWidth),n.removeChild(r),e):void 0}}();var Fe=/[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source,Me=new RegExp("^(?:([+-])=|)("+Fe+")([a-z%]*)$","i"),Oe=["Top","Right","Bottom","Left"],Re=function(e,t){return e=t||e,"none"===pe.css(e,"display")||!pe.contains(e.ownerDocument,e)},Pe=function(e,t,n,r,i,o,a){var s=0,u=e.length,l=null==n;if("object"===pe.type(n)){i=!0;for(s in n)Pe(e,t,s,n[s],!0,o,a)}else if(void 0!==r&&(i=!0,pe.isFunction(r)||(a=!0),l&&(a?(t.call(e,r),t=null):(l=t,t=function(e,t,n){return l.call(pe(e),n)})),t))for(;s<u;s++)t(e[s],n,a?r:r.call(e[s],s,t(e[s],n)));return i?e:l?t.call(e):u?t(e[0],n):o},Be=/^(?:checkbox|radio)$/i,We=/<([\w:-]+)/,Ie=/^$|\/(?:java|ecma)script/i,$e=/^\s+/,ze="abbr|article|aside|audio|bdi|canvas|data|datalist|details|dialog|figcaption|figure|footer|header|hgroup|main|mark|meter|nav|output|picture|progress|section|summary|template|time|video";!function(){var e=re.createElement("div"),t=re.createDocumentFragment(),n=re.createElement("input");e.innerHTML="  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>",fe.leadingWhitespace=3===e.firstChild.nodeType,fe.tbody=!e.getElementsByTagName("tbody").length,fe.htmlSerialize=!!e.getElementsByTagName("link").length,fe.html5Clone="<:nav></:nav>"!==re.createElement("nav").cloneNode(!0).outerHTML,n.type="checkbox",n.checked=!0,t.appendChild(n),fe.appendChecked=n.checked,e.innerHTML="<textarea>x</textarea>",fe.noCloneChecked=!!e.cloneNode(!0).lastChild.defaultValue,t.appendChild(e),n=re.createElement("input"),n.setAttribute("type","radio"),n.setAttribute("checked","checked"),n.setAttribute("name","t"),e.appendChild(n),fe.checkClone=e.cloneNode(!0).cloneNode(!0).lastChild.checked,fe.noCloneEvent=!!e.addEventListener,e[pe.expando]=1,fe.attributes=!e.getAttribute(pe.expando)}();var Xe={option:[1,"<select multiple='multiple'>","</select>"],legend:[1,"<fieldset>","</fieldset>"],area:[1,"<map>","</map>"],param:[1,"<object>","</object>"],thead:[1,"<table>","</table>"],tr:[2,"<table><tbody>","</tbody></table>"],col:[2,"<table><tbody></tbody><colgroup>","</colgroup></table>"],td:[3,"<table><tbody><tr>","</tr></tbody></table>"],_default:fe.htmlSerialize?[0,"",""]:[1,"X<div>","</div>"]};Xe.optgroup=Xe.option,Xe.tbody=Xe.tfoot=Xe.colgroup=Xe.caption=Xe.thead,Xe.th=Xe.td;var Ue=/<|&#?\w+;/,Ve=/<tbody/i;!function(){var t,n,r=re.createElement("div");for(t in{submit:!0,change:!0,focusin:!0})n="on"+t,(fe[t]=n in e)||(r.setAttribute(n,"t"),fe[t]=r.attributes[n].expando===!1);r=null}();var Ye=/^(?:input|select|textarea)$/i,Je=/^key/,Ge=/^(?:mouse|pointer|contextmenu|drag|drop)|click/,Ke=/^(?:focusinfocus|focusoutblur)$/,Qe=/^([^.]*)(?:\.(.+)|)/;pe.event={global:{},add:function(e,t,n,r,i){var o,a,s,u,l,c,f,d,p,h,g,m=pe._data(e);if(m){for(n.handler&&(u=n,n=u.handler,i=u.selector),n.guid||(n.guid=pe.guid++),(a=m.events)||(a=m.events={}),(c=m.handle)||(c=m.handle=function(e){return"undefined"==typeof pe||e&&pe.event.triggered===e.type?void 0:pe.event.dispatch.apply(c.elem,arguments)},c.elem=e),t=(t||"").match(De)||[""],s=t.length;s--;)o=Qe.exec(t[s])||[],p=g=o[1],h=(o[2]||"").split(".").sort(),p&&(l=pe.event.special[p]||{},p=(i?l.delegateType:l.bindType)||p,l=pe.event.special[p]||{},f=pe.extend({type:p,origType:g,data:r,handler:n,guid:n.guid,selector:i,needsContext:i&&pe.expr.match.needsContext.test(i),namespace:h.join(".")},u),(d=a[p])||(d=a[p]=[],d.delegateCount=0,l.setup&&l.setup.call(e,r,h,c)!==!1||(e.addEventListener?e.addEventListener(p,c,!1):e.attachEvent&&e.attachEvent("on"+p,c))),l.add&&(l.add.call(e,f),f.handler.guid||(f.handler.guid=n.guid)),i?d.splice(d.delegateCount++,0,f):d.push(f),pe.event.global[p]=!0);e=null}},remove:function(e,t,n,r,i){var o,a,s,u,l,c,f,d,p,h,g,m=pe.hasData(e)&&pe._data(e);if(m&&(c=m.events)){for(t=(t||"").match(De)||[""],l=t.length;l--;)if(s=Qe.exec(t[l])||[],p=g=s[1],h=(s[2]||"").split(".").sort(),p){for(f=pe.event.special[p]||{},p=(r?f.delegateType:f.bindType)||p,d=c[p]||[],s=s[2]&&new RegExp("(^|\\.)"+h.join("\\.(?:.*\\.|)")+"(\\.|$)"),u=o=d.length;o--;)a=d[o],!i&&g!==a.origType||n&&n.guid!==a.guid||s&&!s.test(a.namespace)||r&&r!==a.selector&&("**"!==r||!a.selector)||(d.splice(o,1),a.selector&&d.delegateCount--,f.remove&&f.remove.call(e,a));u&&!d.length&&(f.teardown&&f.teardown.call(e,h,m.handle)!==!1||pe.removeEvent(e,p,m.handle),delete c[p])}else for(p in c)pe.event.remove(e,p+t[l],n,r,!0);pe.isEmptyObject(c)&&(delete m.handle,pe._removeData(e,"events"))}},trigger:function(t,n,r,i){var o,a,s,u,l,c,f,d=[r||re],p=ce.call(t,"type")?t.type:t,h=ce.call(t,"namespace")?t.namespace.split("."):[];if(s=c=r=r||re,3!==r.nodeType&&8!==r.nodeType&&!Ke.test(p+pe.event.triggered)&&(p.indexOf(".")>-1&&(h=p.split("."),p=h.shift(),h.sort()),a=p.indexOf(":")<0&&"on"+p,t=t[pe.expando]?t:new pe.Event(p,"object"==typeof t&&t),t.isTrigger=i?2:3,t.namespace=h.join("."),t.rnamespace=t.namespace?new RegExp("(^|\\.)"+h.join("\\.(?:.*\\.|)")+"(\\.|$)"):null,t.result=void 0,t.target||(t.target=r),n=null==n?[t]:pe.makeArray(n,[t]),l=pe.event.special[p]||{},i||!l.trigger||l.trigger.apply(r,n)!==!1)){if(!i&&!l.noBubble&&!pe.isWindow(r)){for(u=l.delegateType||p,Ke.test(u+p)||(s=s.parentNode);s;s=s.parentNode)d.push(s),c=s;c===(r.ownerDocument||re)&&d.push(c.defaultView||c.parentWindow||e)}for(f=0;(s=d[f++])&&!t.isPropagationStopped();)t.type=f>1?u:l.bindType||p,o=(pe._data(s,"events")||{})[t.type]&&pe._data(s,"handle"),o&&o.apply(s,n),o=a&&s[a],o&&o.apply&&He(s)&&(t.result=o.apply(s,n),t.result===!1&&t.preventDefault());if(t.type=p,!i&&!t.isDefaultPrevented()&&(!l._default||l._default.apply(d.pop(),n)===!1)&&He(r)&&a&&r[p]&&!pe.isWindow(r)){c=r[a],c&&(r[a]=null),pe.event.triggered=p;try{r[p]()}catch(g){}pe.event.triggered=void 0,c&&(r[a]=c)}return t.result}},dispatch:function(e){e=pe.event.fix(e);var t,n,r,i,o,a=[],s=ie.call(arguments),u=(pe._data(this,"events")||{})[e.type]||[],l=pe.event.special[e.type]||{};if(s[0]=e,e.delegateTarget=this,!l.preDispatch||l.preDispatch.call(this,e)!==!1){for(a=pe.event.handlers.call(this,e,u),t=0;(i=a[t++])&&!e.isPropagationStopped();)for(e.currentTarget=i.elem,n=0;(o=i.handlers[n++])&&!e.isImmediatePropagationStopped();)e.rnamespace&&!e.rnamespace.test(o.namespace)||(e.handleObj=o,e.data=o.data,r=((pe.event.special[o.origType]||{}).handle||o.handler).apply(i.elem,s),void 0!==r&&(e.result=r)===!1&&(e.preventDefault(),e.stopPropagation()));return l.postDispatch&&l.postDispatch.call(this,e),e.result}},handlers:function(e,t){var n,r,i,o,a=[],s=t.delegateCount,u=e.target;if(s&&u.nodeType&&("click"!==e.type||isNaN(e.button)||e.button<1))for(;u!=this;u=u.parentNode||this)if(1===u.nodeType&&(u.disabled!==!0||"click"!==e.type)){for(r=[],n=0;n<s;n++)o=t[n],i=o.selector+" ",void 0===r[i]&&(r[i]=o.needsContext?pe(i,this).index(u)>-1:pe.find(i,this,null,[u]).length),r[i]&&r.push(o);r.length&&a.push({elem:u,handlers:r})}return s<t.length&&a.push({elem:this,handlers:t.slice(s)}),a},fix:function(e){if(e[pe.expando])return e;var t,n,r,i=e.type,o=e,a=this.fixHooks[i];for(a||(this.fixHooks[i]=a=Ge.test(i)?this.mouseHooks:Je.test(i)?this.keyHooks:{}),r=a.props?this.props.concat(a.props):this.props,e=new pe.Event(o),t=r.length;t--;)n=r[t],e[n]=o[n];return e.target||(e.target=o.srcElement||re),3===e.target.nodeType&&(e.target=e.target.parentNode),e.metaKey=!!e.metaKey,a.filter?a.filter(e,o):e},props:"altKey bubbles cancelable ctrlKey currentTarget detail eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),fixHooks:{},keyHooks:{props:"char charCode key keyCode".split(" "),filter:function(e,t){return null==e.which&&(e.which=null!=t.charCode?t.charCode:t.keyCode),e}},mouseHooks:{props:"button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),filter:function(e,t){var n,r,i,o=t.button,a=t.fromElement;return null==e.pageX&&null!=t.clientX&&(r=e.target.ownerDocument||re,i=r.documentElement,n=r.body,e.pageX=t.clientX+(i&&i.scrollLeft||n&&n.scrollLeft||0)-(i&&i.clientLeft||n&&n.clientLeft||0),e.pageY=t.clientY+(i&&i.scrollTop||n&&n.scrollTop||0)-(i&&i.clientTop||n&&n.clientTop||0)),!e.relatedTarget&&a&&(e.relatedTarget=a===e.target?t.toElement:a),e.which||void 0===o||(e.which=1&o?1:2&o?3:4&o?2:0),e}},special:{load:{noBubble:!0},focus:{trigger:function(){if(this!==b()&&this.focus)try{return this.focus(),!1}catch(e){}},delegateType:"focusin"},blur:{trigger:function(){if(this===b()&&this.blur)return this.blur(),!1},delegateType:"focusout"},click:{trigger:function(){if(pe.nodeName(this,"input")&&"checkbox"===this.type&&this.click)return this.click(),!1},_default:function(e){return pe.nodeName(e.target,"a")}},beforeunload:{postDispatch:function(e){void 0!==e.result&&e.originalEvent&&(e.originalEvent.returnValue=e.result)}}},simulate:function(e,t,n){var r=pe.extend(new pe.Event,n,{type:e,isSimulated:!0});pe.event.trigger(r,null,t),r.isDefaultPrevented()&&n.preventDefault()}},pe.removeEvent=re.removeEventListener?function(e,t,n){e.removeEventListener&&e.removeEventListener(t,n)}:function(e,t,n){var r="on"+t;e.detachEvent&&("undefined"==typeof e[r]&&(e[r]=null),e.detachEvent(r,n))},pe.Event=function(e,t){return this instanceof pe.Event?(e&&e.type?(this.originalEvent=e,this.type=e.type,this.isDefaultPrevented=e.defaultPrevented||void 0===e.defaultPrevented&&e.returnValue===!1?v:x):this.type=e,t&&pe.extend(this,t),this.timeStamp=e&&e.timeStamp||pe.now(),void(this[pe.expando]=!0)):new pe.Event(e,t)},pe.Event.prototype={constructor:pe.Event,isDefaultPrevented:x,isPropagationStopped:x,isImmediatePropagationStopped:x,preventDefault:function(){var e=this.originalEvent;this.isDefaultPrevented=v,e&&(e.preventDefault?e.preventDefault():e.returnValue=!1)},stopPropagation:function(){var e=this.originalEvent;this.isPropagationStopped=v,e&&!this.isSimulated&&(e.stopPropagation&&e.stopPropagation(),e.cancelBubble=!0)},stopImmediatePropagation:function(){var e=this.originalEvent;this.isImmediatePropagationStopped=v,e&&e.stopImmediatePropagation&&e.stopImmediatePropagation(),this.stopPropagation()}},pe.each({mouseenter:"mouseover",mouseleave:"mouseout",pointerenter:"pointerover",pointerleave:"pointerout"},function(e,t){pe.event.special[e]={delegateType:t,bindType:t,handle:function(e){var n,r=this,i=e.relatedTarget,o=e.handleObj;return i&&(i===r||pe.contains(r,i))||(e.type=o.origType,n=o.handler.apply(this,arguments),e.type=t),n}}}),fe.submit||(pe.event.special.submit={setup:function(){return!pe.nodeName(this,"form")&&void pe.event.add(this,"click._submit keypress._submit",function(e){var t=e.target,n=pe.nodeName(t,"input")||pe.nodeName(t,"button")?pe.prop(t,"form"):void 0;n&&!pe._data(n,"submit")&&(pe.event.add(n,"submit._submit",function(e){e._submitBubble=!0}),pe._data(n,"submit",!0))})},postDispatch:function(e){e._submitBubble&&(delete e._submitBubble,this.parentNode&&!e.isTrigger&&pe.event.simulate("submit",this.parentNode,e))},teardown:function(){return!pe.nodeName(this,"form")&&void pe.event.remove(this,"._submit")}}),fe.change||(pe.event.special.change={setup:function(){return Ye.test(this.nodeName)?("checkbox"!==this.type&&"radio"!==this.type||(pe.event.add(this,"propertychange._change",function(e){"checked"===e.originalEvent.propertyName&&(this._justChanged=!0)}),pe.event.add(this,"click._change",function(e){this._justChanged&&!e.isTrigger&&(this._justChanged=!1),pe.event.simulate("change",this,e)})),!1):void pe.event.add(this,"beforeactivate._change",function(e){var t=e.target;Ye.test(t.nodeName)&&!pe._data(t,"change")&&(pe.event.add(t,"change._change",function(e){!this.parentNode||e.isSimulated||e.isTrigger||pe.event.simulate("change",this.parentNode,e)}),pe._data(t,"change",!0))})},handle:function(e){var t=e.target;if(this!==t||e.isSimulated||e.isTrigger||"radio"!==t.type&&"checkbox"!==t.type)return e.handleObj.handler.apply(this,arguments)},teardown:function(){return pe.event.remove(this,"._change"),!Ye.test(this.nodeName)}}),fe.focusin||pe.each({focus:"focusin",blur:"focusout"},function(e,t){var n=function(e){pe.event.simulate(t,e.target,pe.event.fix(e))};pe.event.special[t]={setup:function(){var r=this.ownerDocument||this,i=pe._data(r,t);i||r.addEventListener(e,n,!0),pe._data(r,t,(i||0)+1)},teardown:function(){var r=this.ownerDocument||this,i=pe._data(r,t)-1;i?pe._data(r,t,i):(r.removeEventListener(e,n,!0),pe._removeData(r,t))}}}),pe.fn.extend({on:function(e,t,n,r){return w(this,e,t,n,r)},one:function(e,t,n,r){return w(this,e,t,n,r,1)},off:function(e,t,n){var r,i;if(e&&e.preventDefault&&e.handleObj)return r=e.handleObj,pe(e.delegateTarget).off(r.namespace?r.origType+"."+r.namespace:r.origType,r.selector,r.handler),this;if("object"==typeof e){for(i in e)this.off(i,t,e[i]);return this}return t!==!1&&"function"!=typeof t||(n=t,t=void 0),n===!1&&(n=x),this.each(function(){pe.event.remove(this,e,n,t)})},trigger:function(e,t){return this.each(function(){pe.event.trigger(e,t,this)})},triggerHandler:function(e,t){var n=this[0];if(n)return pe.event.trigger(e,t,n,!0)}});var Ze=/ jQuery\d+="(?:null|\d+)"/g,et=new RegExp("<(?:"+ze+")[\\s/>]","i"),tt=/<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:-]+)[^>]*)\/>/gi,nt=/<script|<style|<link/i,rt=/checked\s*(?:[^=]|=\s*.checked.)/i,it=/^true\/(.*)/,ot=/^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g,at=p(re),st=at.appendChild(re.createElement("div"));pe.extend({htmlPrefilter:function(e){return e.replace(tt,"<$1></$2>")},clone:function(e,t,n){var r,i,o,a,s,u=pe.contains(e.ownerDocument,e);if(fe.html5Clone||pe.isXMLDoc(e)||!et.test("<"+e.nodeName+">")?o=e.cloneNode(!0):(st.innerHTML=e.outerHTML,st.removeChild(o=st.firstChild)),!(fe.noCloneEvent&&fe.noCloneChecked||1!==e.nodeType&&11!==e.nodeType||pe.isXMLDoc(e)))for(r=h(o),s=h(e),a=0;null!=(i=s[a]);++a)r[a]&&k(i,r[a]);if(t)if(n)for(s=s||h(e),r=r||h(o),a=0;null!=(i=s[a]);a++)N(i,r[a]);else N(e,o);return r=h(o,"script"),r.length>0&&g(r,!u&&h(e,"script")),r=s=i=null,o},cleanData:function(e,t){for(var n,r,i,o,a=0,s=pe.expando,u=pe.cache,l=fe.attributes,c=pe.event.special;null!=(n=e[a]);a++)if((t||He(n))&&(i=n[s],o=i&&u[i])){if(o.events)for(r in o.events)c[r]?pe.event.remove(n,r):pe.removeEvent(n,r,o.handle);u[i]&&(delete u[i],l||"undefined"==typeof n.removeAttribute?n[s]=void 0:n.removeAttribute(s),ne.push(i))}}}),pe.fn.extend({domManip:S,detach:function(e){return A(this,e,!0)},remove:function(e){return A(this,e)},text:function(e){return Pe(this,function(e){return void 0===e?pe.text(this):this.empty().append((this[0]&&this[0].ownerDocument||re).createTextNode(e))},null,e,arguments.length)},append:function(){return S(this,arguments,function(e){if(1===this.nodeType||11===this.nodeType||9===this.nodeType){var t=T(this,e);t.appendChild(e)}})},prepend:function(){return S(this,arguments,function(e){if(1===this.nodeType||11===this.nodeType||9===this.nodeType){var t=T(this,e);t.insertBefore(e,t.firstChild)}})},before:function(){return S(this,arguments,function(e){this.parentNode&&this.parentNode.insertBefore(e,this)})},after:function(){return S(this,arguments,function(e){this.parentNode&&this.parentNode.insertBefore(e,this.nextSibling)})},empty:function(){for(var e,t=0;null!=(e=this[t]);t++){for(1===e.nodeType&&pe.cleanData(h(e,!1));e.firstChild;)e.removeChild(e.firstChild);e.options&&pe.nodeName(e,"select")&&(e.options.length=0)}return this},clone:function(e,t){return e=null!=e&&e,t=null==t?e:t,this.map(function(){return pe.clone(this,e,t)})},html:function(e){return Pe(this,function(e){var t=this[0]||{},n=0,r=this.length;if(void 0===e)return 1===t.nodeType?t.innerHTML.replace(Ze,""):void 0;if("string"==typeof e&&!nt.test(e)&&(fe.htmlSerialize||!et.test(e))&&(fe.leadingWhitespace||!$e.test(e))&&!Xe[(We.exec(e)||["",""])[1].toLowerCase()]){e=pe.htmlPrefilter(e);try{for(;n<r;n++)t=this[n]||{},1===t.nodeType&&(pe.cleanData(h(t,!1)),t.innerHTML=e);t=0}catch(i){}}t&&this.empty().append(e)},null,e,arguments.length)},replaceWith:function(){var e=[];return S(this,arguments,function(t){var n=this.parentNode;pe.inArray(this,e)<0&&(pe.cleanData(h(this)),
n&&n.replaceChild(t,this))},e)}}),pe.each({appendTo:"append",prependTo:"prepend",insertBefore:"before",insertAfter:"after",replaceAll:"replaceWith"},function(e,t){pe.fn[e]=function(e){for(var n,r=0,i=[],o=pe(e),a=o.length-1;r<=a;r++)n=r===a?this:this.clone(!0),pe(o[r])[t](n),ae.apply(i,n.get());return this.pushStack(i)}});var ut,lt={HTML:"block",BODY:"block"},ct=/^margin/,ft=new RegExp("^("+Fe+")(?!px)[a-z%]+$","i"),dt=function(e,t,n,r){var i,o,a={};for(o in t)a[o]=e.style[o],e.style[o]=t[o];i=n.apply(e,r||[]);for(o in t)e.style[o]=a[o];return i},pt=re.documentElement;!function(){function t(){var t,c,f=re.documentElement;f.appendChild(u),l.style.cssText="-webkit-box-sizing:border-box;box-sizing:border-box;position:relative;display:block;margin:auto;border:1px;padding:1px;top:1%;width:50%",n=i=s=!1,r=a=!0,e.getComputedStyle&&(c=e.getComputedStyle(l),n="1%"!==(c||{}).top,s="2px"===(c||{}).marginLeft,i="4px"===(c||{width:"4px"}).width,l.style.marginRight="50%",r="4px"===(c||{marginRight:"4px"}).marginRight,t=l.appendChild(re.createElement("div")),t.style.cssText=l.style.cssText="-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;margin:0;border:0;padding:0",t.style.marginRight=t.style.width="0",l.style.width="1px",a=!parseFloat((e.getComputedStyle(t)||{}).marginRight),l.removeChild(t)),l.style.display="none",o=0===l.getClientRects().length,o&&(l.style.display="",l.innerHTML="<table><tr><td></td><td>t</td></tr></table>",t=l.getElementsByTagName("td"),t[0].style.cssText="margin:0;border:0;padding:0;display:none",o=0===t[0].offsetHeight,o&&(t[0].style.display="",t[1].style.display="none",o=0===t[0].offsetHeight)),f.removeChild(u)}var n,r,i,o,a,s,u=re.createElement("div"),l=re.createElement("div");l.style&&(l.style.cssText="float:left;opacity:.5",fe.opacity="0.5"===l.style.opacity,fe.cssFloat=!!l.style.cssFloat,l.style.backgroundClip="content-box",l.cloneNode(!0).style.backgroundClip="",fe.clearCloneStyle="content-box"===l.style.backgroundClip,u=re.createElement("div"),u.style.cssText="border:0;width:8px;height:0;top:0;left:-9999px;padding:0;margin-top:1px;position:absolute",l.innerHTML="",u.appendChild(l),fe.boxSizing=""===l.style.boxSizing||""===l.style.MozBoxSizing||""===l.style.WebkitBoxSizing,pe.extend(fe,{reliableHiddenOffsets:function(){return null==n&&t(),o},boxSizingReliable:function(){return null==n&&t(),i},pixelMarginRight:function(){return null==n&&t(),r},pixelPosition:function(){return null==n&&t(),n},reliableMarginRight:function(){return null==n&&t(),a},reliableMarginLeft:function(){return null==n&&t(),s}}))}();var ht,gt,mt=/^(top|right|bottom|left)$/;e.getComputedStyle?(ht=function(t){var n=t.ownerDocument.defaultView;return n&&n.opener||(n=e),n.getComputedStyle(t)},gt=function(e,t,n){var r,i,o,a,s=e.style;return n=n||ht(e),a=n?n.getPropertyValue(t)||n[t]:void 0,""!==a&&void 0!==a||pe.contains(e.ownerDocument,e)||(a=pe.style(e,t)),n&&!fe.pixelMarginRight()&&ft.test(a)&&ct.test(t)&&(r=s.width,i=s.minWidth,o=s.maxWidth,s.minWidth=s.maxWidth=s.width=a,a=n.width,s.width=r,s.minWidth=i,s.maxWidth=o),void 0===a?a:a+""}):pt.currentStyle&&(ht=function(e){return e.currentStyle},gt=function(e,t,n){var r,i,o,a,s=e.style;return n=n||ht(e),a=n?n[t]:void 0,null==a&&s&&s[t]&&(a=s[t]),ft.test(a)&&!mt.test(t)&&(r=s.left,i=e.runtimeStyle,o=i&&i.left,o&&(i.left=e.currentStyle.left),s.left="fontSize"===t?"1em":a,a=s.pixelLeft+"px",s.left=r,o&&(i.left=o)),void 0===a?a:a+""||"auto"});var yt=/alpha\([^)]*\)/i,vt=/opacity\s*=\s*([^)]*)/i,xt=/^(none|table(?!-c[ea]).+)/,bt=new RegExp("^("+Fe+")(.*)$","i"),wt={position:"absolute",visibility:"hidden",display:"block"},Tt={letterSpacing:"0",fontWeight:"400"},Ct=["Webkit","O","Moz","ms"],Et=re.createElement("div").style;pe.extend({cssHooks:{opacity:{get:function(e,t){if(t){var n=gt(e,"opacity");return""===n?"1":n}}}},cssNumber:{animationIterationCount:!0,columnCount:!0,fillOpacity:!0,flexGrow:!0,flexShrink:!0,fontWeight:!0,lineHeight:!0,opacity:!0,order:!0,orphans:!0,widows:!0,zIndex:!0,zoom:!0},cssProps:{"float":fe.cssFloat?"cssFloat":"styleFloat"},style:function(e,t,n,r){if(e&&3!==e.nodeType&&8!==e.nodeType&&e.style){var i,o,a,s=pe.camelCase(t),u=e.style;if(t=pe.cssProps[s]||(pe.cssProps[s]=H(s)||s),a=pe.cssHooks[t]||pe.cssHooks[s],void 0===n)return a&&"get"in a&&void 0!==(i=a.get(e,!1,r))?i:u[t];if(o=typeof n,"string"===o&&(i=Me.exec(n))&&i[1]&&(n=d(e,t,i),o="number"),null!=n&&n===n&&("number"===o&&(n+=i&&i[3]||(pe.cssNumber[s]?"":"px")),fe.clearCloneStyle||""!==n||0!==t.indexOf("background")||(u[t]="inherit"),!(a&&"set"in a&&void 0===(n=a.set(e,n,r)))))try{u[t]=n}catch(l){}}},css:function(e,t,n,r){var i,o,a,s=pe.camelCase(t);return t=pe.cssProps[s]||(pe.cssProps[s]=H(s)||s),a=pe.cssHooks[t]||pe.cssHooks[s],a&&"get"in a&&(o=a.get(e,!0,n)),void 0===o&&(o=gt(e,t,r)),"normal"===o&&t in Tt&&(o=Tt[t]),""===n||n?(i=parseFloat(o),n===!0||isFinite(i)?i||0:o):o}}),pe.each(["height","width"],function(e,t){pe.cssHooks[t]={get:function(e,n,r){if(n)return xt.test(pe.css(e,"display"))&&0===e.offsetWidth?dt(e,wt,function(){return M(e,t,r)}):M(e,t,r)},set:function(e,n,r){var i=r&&ht(e);return _(e,n,r?F(e,t,r,fe.boxSizing&&"border-box"===pe.css(e,"boxSizing",!1,i),i):0)}}}),fe.opacity||(pe.cssHooks.opacity={get:function(e,t){return vt.test((t&&e.currentStyle?e.currentStyle.filter:e.style.filter)||"")?.01*parseFloat(RegExp.$1)+"":t?"1":""},set:function(e,t){var n=e.style,r=e.currentStyle,i=pe.isNumeric(t)?"alpha(opacity="+100*t+")":"",o=r&&r.filter||n.filter||"";n.zoom=1,(t>=1||""===t)&&""===pe.trim(o.replace(yt,""))&&n.removeAttribute&&(n.removeAttribute("filter"),""===t||r&&!r.filter)||(n.filter=yt.test(o)?o.replace(yt,i):o+" "+i)}}),pe.cssHooks.marginRight=L(fe.reliableMarginRight,function(e,t){if(t)return dt(e,{display:"inline-block"},gt,[e,"marginRight"])}),pe.cssHooks.marginLeft=L(fe.reliableMarginLeft,function(e,t){if(t)return(parseFloat(gt(e,"marginLeft"))||(pe.contains(e.ownerDocument,e)?e.getBoundingClientRect().left-dt(e,{marginLeft:0},function(){return e.getBoundingClientRect().left}):0))+"px"}),pe.each({margin:"",padding:"",border:"Width"},function(e,t){pe.cssHooks[e+t]={expand:function(n){for(var r=0,i={},o="string"==typeof n?n.split(" "):[n];r<4;r++)i[e+Oe[r]+t]=o[r]||o[r-2]||o[0];return i}},ct.test(e)||(pe.cssHooks[e+t].set=_)}),pe.fn.extend({css:function(e,t){return Pe(this,function(e,t,n){var r,i,o={},a=0;if(pe.isArray(t)){for(r=ht(e),i=t.length;a<i;a++)o[t[a]]=pe.css(e,t[a],!1,r);return o}return void 0!==n?pe.style(e,t,n):pe.css(e,t)},e,t,arguments.length>1)},show:function(){return q(this,!0)},hide:function(){return q(this)},toggle:function(e){return"boolean"==typeof e?e?this.show():this.hide():this.each(function(){Re(this)?pe(this).show():pe(this).hide()})}}),pe.Tween=O,O.prototype={constructor:O,init:function(e,t,n,r,i,o){this.elem=e,this.prop=n,this.easing=i||pe.easing._default,this.options=t,this.start=this.now=this.cur(),this.end=r,this.unit=o||(pe.cssNumber[n]?"":"px")},cur:function(){var e=O.propHooks[this.prop];return e&&e.get?e.get(this):O.propHooks._default.get(this)},run:function(e){var t,n=O.propHooks[this.prop];return this.options.duration?this.pos=t=pe.easing[this.easing](e,this.options.duration*e,0,1,this.options.duration):this.pos=t=e,this.now=(this.end-this.start)*t+this.start,this.options.step&&this.options.step.call(this.elem,this.now,this),n&&n.set?n.set(this):O.propHooks._default.set(this),this}},O.prototype.init.prototype=O.prototype,O.propHooks={_default:{get:function(e){var t;return 1!==e.elem.nodeType||null!=e.elem[e.prop]&&null==e.elem.style[e.prop]?e.elem[e.prop]:(t=pe.css(e.elem,e.prop,""),t&&"auto"!==t?t:0)},set:function(e){pe.fx.step[e.prop]?pe.fx.step[e.prop](e):1!==e.elem.nodeType||null==e.elem.style[pe.cssProps[e.prop]]&&!pe.cssHooks[e.prop]?e.elem[e.prop]=e.now:pe.style(e.elem,e.prop,e.now+e.unit)}}},O.propHooks.scrollTop=O.propHooks.scrollLeft={set:function(e){e.elem.nodeType&&e.elem.parentNode&&(e.elem[e.prop]=e.now)}},pe.easing={linear:function(e){return e},swing:function(e){return.5-Math.cos(e*Math.PI)/2},_default:"swing"},pe.fx=O.prototype.init,pe.fx.step={};var Nt,kt,St=/^(?:toggle|show|hide)$/,At=/queueHooks$/;pe.Animation=pe.extend($,{tweeners:{"*":[function(e,t){var n=this.createTween(e,t);return d(n.elem,e,Me.exec(t),n),n}]},tweener:function(e,t){pe.isFunction(e)?(t=e,e=["*"]):e=e.match(De);for(var n,r=0,i=e.length;r<i;r++)n=e[r],$.tweeners[n]=$.tweeners[n]||[],$.tweeners[n].unshift(t)},prefilters:[W],prefilter:function(e,t){t?$.prefilters.unshift(e):$.prefilters.push(e)}}),pe.speed=function(e,t,n){var r=e&&"object"==typeof e?pe.extend({},e):{complete:n||!n&&t||pe.isFunction(e)&&e,duration:e,easing:n&&t||t&&!pe.isFunction(t)&&t};return r.duration=pe.fx.off?0:"number"==typeof r.duration?r.duration:r.duration in pe.fx.speeds?pe.fx.speeds[r.duration]:pe.fx.speeds._default,null!=r.queue&&r.queue!==!0||(r.queue="fx"),r.old=r.complete,r.complete=function(){pe.isFunction(r.old)&&r.old.call(this),r.queue&&pe.dequeue(this,r.queue)},r},pe.fn.extend({fadeTo:function(e,t,n,r){return this.filter(Re).css("opacity",0).show().end().animate({opacity:t},e,n,r)},animate:function(e,t,n,r){var i=pe.isEmptyObject(e),o=pe.speed(t,n,r),a=function(){var t=$(this,pe.extend({},e),o);(i||pe._data(this,"finish"))&&t.stop(!0)};return a.finish=a,i||o.queue===!1?this.each(a):this.queue(o.queue,a)},stop:function(e,t,n){var r=function(e){var t=e.stop;delete e.stop,t(n)};return"string"!=typeof e&&(n=t,t=e,e=void 0),t&&e!==!1&&this.queue(e||"fx",[]),this.each(function(){var t=!0,i=null!=e&&e+"queueHooks",o=pe.timers,a=pe._data(this);if(i)a[i]&&a[i].stop&&r(a[i]);else for(i in a)a[i]&&a[i].stop&&At.test(i)&&r(a[i]);for(i=o.length;i--;)o[i].elem!==this||null!=e&&o[i].queue!==e||(o[i].anim.stop(n),t=!1,o.splice(i,1));!t&&n||pe.dequeue(this,e)})},finish:function(e){return e!==!1&&(e=e||"fx"),this.each(function(){var t,n=pe._data(this),r=n[e+"queue"],i=n[e+"queueHooks"],o=pe.timers,a=r?r.length:0;for(n.finish=!0,pe.queue(this,e,[]),i&&i.stop&&i.stop.call(this,!0),t=o.length;t--;)o[t].elem===this&&o[t].queue===e&&(o[t].anim.stop(!0),o.splice(t,1));for(t=0;t<a;t++)r[t]&&r[t].finish&&r[t].finish.call(this);delete n.finish})}}),pe.each(["toggle","show","hide"],function(e,t){var n=pe.fn[t];pe.fn[t]=function(e,r,i){return null==e||"boolean"==typeof e?n.apply(this,arguments):this.animate(P(t,!0),e,r,i)}}),pe.each({slideDown:P("show"),slideUp:P("hide"),slideToggle:P("toggle"),fadeIn:{opacity:"show"},fadeOut:{opacity:"hide"},fadeToggle:{opacity:"toggle"}},function(e,t){pe.fn[e]=function(e,n,r){return this.animate(t,e,n,r)}}),pe.timers=[],pe.fx.tick=function(){var e,t=pe.timers,n=0;for(Nt=pe.now();n<t.length;n++)e=t[n],e()||t[n]!==e||t.splice(n--,1);t.length||pe.fx.stop(),Nt=void 0},pe.fx.timer=function(e){pe.timers.push(e),e()?pe.fx.start():pe.timers.pop()},pe.fx.interval=13,pe.fx.start=function(){kt||(kt=e.setInterval(pe.fx.tick,pe.fx.interval))},pe.fx.stop=function(){e.clearInterval(kt),kt=null},pe.fx.speeds={slow:600,fast:200,_default:400},pe.fn.delay=function(t,n){return t=pe.fx?pe.fx.speeds[t]||t:t,n=n||"fx",this.queue(n,function(n,r){var i=e.setTimeout(n,t);r.stop=function(){e.clearTimeout(i)}})},function(){var e,t=re.createElement("input"),n=re.createElement("div"),r=re.createElement("select"),i=r.appendChild(re.createElement("option"));n=re.createElement("div"),n.setAttribute("className","t"),n.innerHTML="  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>",e=n.getElementsByTagName("a")[0],t.setAttribute("type","checkbox"),n.appendChild(t),e=n.getElementsByTagName("a")[0],e.style.cssText="top:1px",fe.getSetAttribute="t"!==n.className,fe.style=/top/.test(e.getAttribute("style")),fe.hrefNormalized="/a"===e.getAttribute("href"),fe.checkOn=!!t.value,fe.optSelected=i.selected,fe.enctype=!!re.createElement("form").enctype,r.disabled=!0,fe.optDisabled=!i.disabled,t=re.createElement("input"),t.setAttribute("value",""),fe.input=""===t.getAttribute("value"),t.value="t",t.setAttribute("type","radio"),fe.radioValue="t"===t.value}();var Dt=/\r/g,jt=/[\x20\t\r\n\f]+/g;pe.fn.extend({val:function(e){var t,n,r,i=this[0];{if(arguments.length)return r=pe.isFunction(e),this.each(function(n){var i;1===this.nodeType&&(i=r?e.call(this,n,pe(this).val()):e,null==i?i="":"number"==typeof i?i+="":pe.isArray(i)&&(i=pe.map(i,function(e){return null==e?"":e+""})),t=pe.valHooks[this.type]||pe.valHooks[this.nodeName.toLowerCase()],t&&"set"in t&&void 0!==t.set(this,i,"value")||(this.value=i))});if(i)return t=pe.valHooks[i.type]||pe.valHooks[i.nodeName.toLowerCase()],t&&"get"in t&&void 0!==(n=t.get(i,"value"))?n:(n=i.value,"string"==typeof n?n.replace(Dt,""):null==n?"":n)}}}),pe.extend({valHooks:{option:{get:function(e){var t=pe.find.attr(e,"value");return null!=t?t:pe.trim(pe.text(e)).replace(jt," ")}},select:{get:function(e){for(var t,n,r=e.options,i=e.selectedIndex,o="select-one"===e.type||i<0,a=o?null:[],s=o?i+1:r.length,u=i<0?s:o?i:0;u<s;u++)if(n=r[u],(n.selected||u===i)&&(fe.optDisabled?!n.disabled:null===n.getAttribute("disabled"))&&(!n.parentNode.disabled||!pe.nodeName(n.parentNode,"optgroup"))){if(t=pe(n).val(),o)return t;a.push(t)}return a},set:function(e,t){for(var n,r,i=e.options,o=pe.makeArray(t),a=i.length;a--;)if(r=i[a],pe.inArray(pe.valHooks.option.get(r),o)>-1)try{r.selected=n=!0}catch(s){r.scrollHeight}else r.selected=!1;return n||(e.selectedIndex=-1),i}}}}),pe.each(["radio","checkbox"],function(){pe.valHooks[this]={set:function(e,t){if(pe.isArray(t))return e.checked=pe.inArray(pe(e).val(),t)>-1}},fe.checkOn||(pe.valHooks[this].get=function(e){return null===e.getAttribute("value")?"on":e.value})});var Lt,Ht,qt=pe.expr.attrHandle,_t=/^(?:checked|selected)$/i,Ft=fe.getSetAttribute,Mt=fe.input;pe.fn.extend({attr:function(e,t){return Pe(this,pe.attr,e,t,arguments.length>1)},removeAttr:function(e){return this.each(function(){pe.removeAttr(this,e)})}}),pe.extend({attr:function(e,t,n){var r,i,o=e.nodeType;if(3!==o&&8!==o&&2!==o)return"undefined"==typeof e.getAttribute?pe.prop(e,t,n):(1===o&&pe.isXMLDoc(e)||(t=t.toLowerCase(),i=pe.attrHooks[t]||(pe.expr.match.bool.test(t)?Ht:Lt)),void 0!==n?null===n?void pe.removeAttr(e,t):i&&"set"in i&&void 0!==(r=i.set(e,n,t))?r:(e.setAttribute(t,n+""),n):i&&"get"in i&&null!==(r=i.get(e,t))?r:(r=pe.find.attr(e,t),null==r?void 0:r))},attrHooks:{type:{set:function(e,t){if(!fe.radioValue&&"radio"===t&&pe.nodeName(e,"input")){var n=e.value;return e.setAttribute("type",t),n&&(e.value=n),t}}}},removeAttr:function(e,t){var n,r,i=0,o=t&&t.match(De);if(o&&1===e.nodeType)for(;n=o[i++];)r=pe.propFix[n]||n,pe.expr.match.bool.test(n)?Mt&&Ft||!_t.test(n)?e[r]=!1:e[pe.camelCase("default-"+n)]=e[r]=!1:pe.attr(e,n,""),e.removeAttribute(Ft?n:r)}}),Ht={set:function(e,t,n){return t===!1?pe.removeAttr(e,n):Mt&&Ft||!_t.test(n)?e.setAttribute(!Ft&&pe.propFix[n]||n,n):e[pe.camelCase("default-"+n)]=e[n]=!0,n}},pe.each(pe.expr.match.bool.source.match(/\w+/g),function(e,t){var n=qt[t]||pe.find.attr;Mt&&Ft||!_t.test(t)?qt[t]=function(e,t,r){var i,o;return r||(o=qt[t],qt[t]=i,i=null!=n(e,t,r)?t.toLowerCase():null,qt[t]=o),i}:qt[t]=function(e,t,n){if(!n)return e[pe.camelCase("default-"+t)]?t.toLowerCase():null}}),Mt&&Ft||(pe.attrHooks.value={set:function(e,t,n){return pe.nodeName(e,"input")?void(e.defaultValue=t):Lt&&Lt.set(e,t,n)}}),Ft||(Lt={set:function(e,t,n){var r=e.getAttributeNode(n);if(r||e.setAttributeNode(r=e.ownerDocument.createAttribute(n)),r.value=t+="","value"===n||t===e.getAttribute(n))return t}},qt.id=qt.name=qt.coords=function(e,t,n){var r;if(!n)return(r=e.getAttributeNode(t))&&""!==r.value?r.value:null},pe.valHooks.button={get:function(e,t){var n=e.getAttributeNode(t);if(n&&n.specified)return n.value},set:Lt.set},pe.attrHooks.contenteditable={set:function(e,t,n){Lt.set(e,""!==t&&t,n)}},pe.each(["width","height"],function(e,t){pe.attrHooks[t]={set:function(e,n){if(""===n)return e.setAttribute(t,"auto"),n}}})),fe.style||(pe.attrHooks.style={get:function(e){return e.style.cssText||void 0},set:function(e,t){return e.style.cssText=t+""}});var Ot=/^(?:input|select|textarea|button|object)$/i,Rt=/^(?:a|area)$/i;pe.fn.extend({prop:function(e,t){return Pe(this,pe.prop,e,t,arguments.length>1)},removeProp:function(e){return e=pe.propFix[e]||e,this.each(function(){try{this[e]=void 0,delete this[e]}catch(t){}})}}),pe.extend({prop:function(e,t,n){var r,i,o=e.nodeType;if(3!==o&&8!==o&&2!==o)return 1===o&&pe.isXMLDoc(e)||(t=pe.propFix[t]||t,i=pe.propHooks[t]),void 0!==n?i&&"set"in i&&void 0!==(r=i.set(e,n,t))?r:e[t]=n:i&&"get"in i&&null!==(r=i.get(e,t))?r:e[t]},propHooks:{tabIndex:{get:function(e){var t=pe.find.attr(e,"tabindex");return t?parseInt(t,10):Ot.test(e.nodeName)||Rt.test(e.nodeName)&&e.href?0:-1}}},propFix:{"for":"htmlFor","class":"className"}}),fe.hrefNormalized||pe.each(["href","src"],function(e,t){pe.propHooks[t]={get:function(e){return e.getAttribute(t,4)}}}),fe.optSelected||(pe.propHooks.selected={get:function(e){var t=e.parentNode;return t&&(t.selectedIndex,t.parentNode&&t.parentNode.selectedIndex),null},set:function(e){var t=e.parentNode;t&&(t.selectedIndex,t.parentNode&&t.parentNode.selectedIndex)}}),pe.each(["tabIndex","readOnly","maxLength","cellSpacing","cellPadding","rowSpan","colSpan","useMap","frameBorder","contentEditable"],function(){pe.propFix[this.toLowerCase()]=this}),fe.enctype||(pe.propFix.enctype="encoding");var Pt=/[\t\r\n\f]/g;pe.fn.extend({addClass:function(e){var t,n,r,i,o,a,s,u=0;if(pe.isFunction(e))return this.each(function(t){pe(this).addClass(e.call(this,t,z(this)))});if("string"==typeof e&&e)for(t=e.match(De)||[];n=this[u++];)if(i=z(n),r=1===n.nodeType&&(" "+i+" ").replace(Pt," ")){for(a=0;o=t[a++];)r.indexOf(" "+o+" ")<0&&(r+=o+" ");s=pe.trim(r),i!==s&&pe.attr(n,"class",s)}return this},removeClass:function(e){var t,n,r,i,o,a,s,u=0;if(pe.isFunction(e))return this.each(function(t){pe(this).removeClass(e.call(this,t,z(this)))});if(!arguments.length)return this.attr("class","");if("string"==typeof e&&e)for(t=e.match(De)||[];n=this[u++];)if(i=z(n),r=1===n.nodeType&&(" "+i+" ").replace(Pt," ")){for(a=0;o=t[a++];)for(;r.indexOf(" "+o+" ")>-1;)r=r.replace(" "+o+" "," ");s=pe.trim(r),i!==s&&pe.attr(n,"class",s)}return this},toggleClass:function(e,t){var n=typeof e;return"boolean"==typeof t&&"string"===n?t?this.addClass(e):this.removeClass(e):pe.isFunction(e)?this.each(function(n){pe(this).toggleClass(e.call(this,n,z(this),t),t)}):this.each(function(){var t,r,i,o;if("string"===n)for(r=0,i=pe(this),o=e.match(De)||[];t=o[r++];)i.hasClass(t)?i.removeClass(t):i.addClass(t);else void 0!==e&&"boolean"!==n||(t=z(this),t&&pe._data(this,"__className__",t),pe.attr(this,"class",t||e===!1?"":pe._data(this,"__className__")||""))})},hasClass:function(e){var t,n,r=0;for(t=" "+e+" ";n=this[r++];)if(1===n.nodeType&&(" "+z(n)+" ").replace(Pt," ").indexOf(t)>-1)return!0;return!1}}),pe.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "),function(e,t){pe.fn[t]=function(e,n){return arguments.length>0?this.on(t,null,e,n):this.trigger(t)}}),pe.fn.extend({hover:function(e,t){return this.mouseenter(e).mouseleave(t||e)}});var Bt=e.location,Wt=pe.now(),It=/\?/,$t=/(,)|(\[|{)|(}|])|"(?:[^"\\\r\n]|\\["\\\/bfnrt]|\\u[\da-fA-F]{4})*"\s*:?|true|false|null|-?(?!0\d)\d+(?:\.\d+|)(?:[eE][+-]?\d+|)/g;pe.parseJSON=function(t){if(e.JSON&&e.JSON.parse)return e.JSON.parse(t+"");var n,r=null,i=pe.trim(t+"");return i&&!pe.trim(i.replace($t,function(e,t,i,o){return n&&t&&(r=0),0===r?e:(n=i||t,r+=!o-!i,"")}))?Function("return "+i)():pe.error("Invalid JSON: "+t)},pe.parseXML=function(t){var n,r;if(!t||"string"!=typeof t)return null;try{e.DOMParser?(r=new e.DOMParser,n=r.parseFromString(t,"text/xml")):(n=new e.ActiveXObject("Microsoft.XMLDOM"),n.async="false",n.loadXML(t))}catch(i){n=void 0}return n&&n.documentElement&&!n.getElementsByTagName("parsererror").length||pe.error("Invalid XML: "+t),n};var zt=/#.*$/,Xt=/([?&])_=[^&]*/,Ut=/^(.*?):[ \t]*([^\r\n]*)\r?$/gm,Vt=/^(?:about|app|app-storage|.+-extension|file|res|widget):$/,Yt=/^(?:GET|HEAD)$/,Jt=/^\/\//,Gt=/^([\w.+-]+:)(?:\/\/(?:[^\/?#]*@|)([^\/?#:]*)(?::(\d+)|)|)/,Kt={},Qt={},Zt="*/".concat("*"),en=Bt.href,tn=Gt.exec(en.toLowerCase())||[];pe.extend({active:0,lastModified:{},etag:{},ajaxSettings:{url:en,type:"GET",isLocal:Vt.test(tn[1]),global:!0,processData:!0,async:!0,contentType:"application/x-www-form-urlencoded; charset=UTF-8",accepts:{"*":Zt,text:"text/plain",html:"text/html",xml:"application/xml, text/xml",json:"application/json, text/javascript"},contents:{xml:/\bxml\b/,html:/\bhtml/,json:/\bjson\b/},responseFields:{xml:"responseXML",text:"responseText",json:"responseJSON"},converters:{"* text":String,"text html":!0,"text json":pe.parseJSON,"text xml":pe.parseXML},flatOptions:{url:!0,context:!0}},ajaxSetup:function(e,t){return t?V(V(e,pe.ajaxSettings),t):V(pe.ajaxSettings,e)},ajaxPrefilter:X(Kt),ajaxTransport:X(Qt),ajax:function(t,n){function r(t,n,r,i){var o,f,v,x,w,C=n;2!==b&&(b=2,u&&e.clearTimeout(u),c=void 0,s=i||"",T.readyState=t>0?4:0,o=t>=200&&t<300||304===t,r&&(x=Y(d,T,r)),x=J(d,x,T,o),o?(d.ifModified&&(w=T.getResponseHeader("Last-Modified"),w&&(pe.lastModified[a]=w),w=T.getResponseHeader("etag"),w&&(pe.etag[a]=w)),204===t||"HEAD"===d.type?C="nocontent":304===t?C="notmodified":(C=x.state,f=x.data,v=x.error,o=!v)):(v=C,!t&&C||(C="error",t<0&&(t=0))),T.status=t,T.statusText=(n||C)+"",o?g.resolveWith(p,[f,C,T]):g.rejectWith(p,[T,C,v]),T.statusCode(y),y=void 0,l&&h.trigger(o?"ajaxSuccess":"ajaxError",[T,d,o?f:v]),m.fireWith(p,[T,C]),l&&(h.trigger("ajaxComplete",[T,d]),--pe.active||pe.event.trigger("ajaxStop")))}"object"==typeof t&&(n=t,t=void 0),n=n||{};var i,o,a,s,u,l,c,f,d=pe.ajaxSetup({},n),p=d.context||d,h=d.context&&(p.nodeType||p.jquery)?pe(p):pe.event,g=pe.Deferred(),m=pe.Callbacks("once memory"),y=d.statusCode||{},v={},x={},b=0,w="canceled",T={readyState:0,getResponseHeader:function(e){var t;if(2===b){if(!f)for(f={};t=Ut.exec(s);)f[t[1].toLowerCase()]=t[2];t=f[e.toLowerCase()]}return null==t?null:t},getAllResponseHeaders:function(){return 2===b?s:null},setRequestHeader:function(e,t){var n=e.toLowerCase();return b||(e=x[n]=x[n]||e,v[e]=t),this},overrideMimeType:function(e){return b||(d.mimeType=e),this},statusCode:function(e){var t;if(e)if(b<2)for(t in e)y[t]=[y[t],e[t]];else T.always(e[T.status]);return this},abort:function(e){var t=e||w;return c&&c.abort(t),r(0,t),this}};if(g.promise(T).complete=m.add,T.success=T.done,T.error=T.fail,d.url=((t||d.url||en)+"").replace(zt,"").replace(Jt,tn[1]+"//"),d.type=n.method||n.type||d.method||d.type,d.dataTypes=pe.trim(d.dataType||"*").toLowerCase().match(De)||[""],null==d.crossDomain&&(i=Gt.exec(d.url.toLowerCase()),d.crossDomain=!(!i||i[1]===tn[1]&&i[2]===tn[2]&&(i[3]||("http:"===i[1]?"80":"443"))===(tn[3]||("http:"===tn[1]?"80":"443")))),d.data&&d.processData&&"string"!=typeof d.data&&(d.data=pe.param(d.data,d.traditional)),U(Kt,d,n,T),2===b)return T;l=pe.event&&d.global,l&&0===pe.active++&&pe.event.trigger("ajaxStart"),d.type=d.type.toUpperCase(),d.hasContent=!Yt.test(d.type),a=d.url,d.hasContent||(d.data&&(a=d.url+=(It.test(a)?"&":"?")+d.data,delete d.data),d.cache===!1&&(d.url=Xt.test(a)?a.replace(Xt,"$1_="+Wt++):a+(It.test(a)?"&":"?")+"_="+Wt++)),d.ifModified&&(pe.lastModified[a]&&T.setRequestHeader("If-Modified-Since",pe.lastModified[a]),pe.etag[a]&&T.setRequestHeader("If-None-Match",pe.etag[a])),(d.data&&d.hasContent&&d.contentType!==!1||n.contentType)&&T.setRequestHeader("Content-Type",d.contentType),T.setRequestHeader("Accept",d.dataTypes[0]&&d.accepts[d.dataTypes[0]]?d.accepts[d.dataTypes[0]]+("*"!==d.dataTypes[0]?", "+Zt+"; q=0.01":""):d.accepts["*"]);for(o in d.headers)T.setRequestHeader(o,d.headers[o]);if(d.beforeSend&&(d.beforeSend.call(p,T,d)===!1||2===b))return T.abort();w="abort";for(o in{success:1,error:1,complete:1})T[o](d[o]);if(c=U(Qt,d,n,T)){if(T.readyState=1,l&&h.trigger("ajaxSend",[T,d]),2===b)return T;d.async&&d.timeout>0&&(u=e.setTimeout(function(){T.abort("timeout")},d.timeout));try{b=1,c.send(v,r)}catch(C){if(!(b<2))throw C;r(-1,C)}}else r(-1,"No Transport");return T},getJSON:function(e,t,n){return pe.get(e,t,n,"json")},getScript:function(e,t){return pe.get(e,void 0,t,"script")}}),pe.each(["get","post"],function(e,t){pe[t]=function(e,n,r,i){return pe.isFunction(n)&&(i=i||r,r=n,n=void 0),pe.ajax(pe.extend({url:e,type:t,dataType:i,data:n,success:r},pe.isPlainObject(e)&&e))}}),pe._evalUrl=function(e){return pe.ajax({url:e,type:"GET",dataType:"script",cache:!0,async:!1,global:!1,"throws":!0})},pe.fn.extend({wrapAll:function(e){if(pe.isFunction(e))return this.each(function(t){pe(this).wrapAll(e.call(this,t))});if(this[0]){var t=pe(e,this[0].ownerDocument).eq(0).clone(!0);this[0].parentNode&&t.insertBefore(this[0]),t.map(function(){for(var e=this;e.firstChild&&1===e.firstChild.nodeType;)e=e.firstChild;return e}).append(this)}return this},wrapInner:function(e){return pe.isFunction(e)?this.each(function(t){pe(this).wrapInner(e.call(this,t))}):this.each(function(){var t=pe(this),n=t.contents();n.length?n.wrapAll(e):t.append(e)})},wrap:function(e){var t=pe.isFunction(e);return this.each(function(n){pe(this).wrapAll(t?e.call(this,n):e)})},unwrap:function(){return this.parent().each(function(){pe.nodeName(this,"body")||pe(this).replaceWith(this.childNodes)}).end()}}),pe.expr.filters.hidden=function(e){return fe.reliableHiddenOffsets()?e.offsetWidth<=0&&e.offsetHeight<=0&&!e.getClientRects().length:K(e)},pe.expr.filters.visible=function(e){return!pe.expr.filters.hidden(e)};var nn=/%20/g,rn=/\[\]$/,on=/\r?\n/g,an=/^(?:submit|button|image|reset|file)$/i,sn=/^(?:input|select|textarea|keygen)/i;pe.param=function(e,t){var n,r=[],i=function(e,t){t=pe.isFunction(t)?t():null==t?"":t,r[r.length]=encodeURIComponent(e)+"="+encodeURIComponent(t)};if(void 0===t&&(t=pe.ajaxSettings&&pe.ajaxSettings.traditional),pe.isArray(e)||e.jquery&&!pe.isPlainObject(e))pe.each(e,function(){i(this.name,this.value)});else for(n in e)Q(n,e[n],t,i);return r.join("&").replace(nn,"+")},pe.fn.extend({serialize:function(){return pe.param(this.serializeArray())},serializeArray:function(){return this.map(function(){var e=pe.prop(this,"elements");return e?pe.makeArray(e):this}).filter(function(){var e=this.type;return this.name&&!pe(this).is(":disabled")&&sn.test(this.nodeName)&&!an.test(e)&&(this.checked||!Be.test(e))}).map(function(e,t){var n=pe(this).val();return null==n?null:pe.isArray(n)?pe.map(n,function(e){return{name:t.name,value:e.replace(on,"\r\n")}}):{name:t.name,value:n.replace(on,"\r\n")}}).get()}}),pe.ajaxSettings.xhr=void 0!==e.ActiveXObject?function(){return this.isLocal?ee():re.documentMode>8?Z():/^(get|post|head|put|delete|options)$/i.test(this.type)&&Z()||ee()}:Z;var un=0,ln={},cn=pe.ajaxSettings.xhr();e.attachEvent&&e.attachEvent("onunload",function(){for(var e in ln)ln[e](void 0,!0)}),fe.cors=!!cn&&"withCredentials"in cn,cn=fe.ajax=!!cn,cn&&pe.ajaxTransport(function(t){if(!t.crossDomain||fe.cors){var n;return{send:function(r,i){var o,a=t.xhr(),s=++un;if(a.open(t.type,t.url,t.async,t.username,t.password),t.xhrFields)for(o in t.xhrFields)a[o]=t.xhrFields[o];t.mimeType&&a.overrideMimeType&&a.overrideMimeType(t.mimeType),t.crossDomain||r["X-Requested-With"]||(r["X-Requested-With"]="XMLHttpRequest");for(o in r)void 0!==r[o]&&a.setRequestHeader(o,r[o]+"");a.send(t.hasContent&&t.data||null),n=function(e,r){var o,u,l;if(n&&(r||4===a.readyState))if(delete ln[s],n=void 0,a.onreadystatechange=pe.noop,r)4!==a.readyState&&a.abort();else{l={},o=a.status,"string"==typeof a.responseText&&(l.text=a.responseText);try{u=a.statusText}catch(c){u=""}o||!t.isLocal||t.crossDomain?1223===o&&(o=204):o=l.text?200:404}l&&i(o,u,l,a.getAllResponseHeaders())},t.async?4===a.readyState?e.setTimeout(n):a.onreadystatechange=ln[s]=n:n()},abort:function(){n&&n(void 0,!0)}}}}),pe.ajaxSetup({accepts:{script:"text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"},contents:{script:/\b(?:java|ecma)script\b/},converters:{"text script":function(e){return pe.globalEval(e),e}}}),pe.ajaxPrefilter("script",function(e){void 0===e.cache&&(e.cache=!1),e.crossDomain&&(e.type="GET",e.global=!1)}),pe.ajaxTransport("script",function(e){if(e.crossDomain){var t,n=re.head||pe("head")[0]||re.documentElement;return{send:function(r,i){t=re.createElement("script"),t.async=!0,e.scriptCharset&&(t.charset=e.scriptCharset),t.src=e.url,t.onload=t.onreadystatechange=function(e,n){(n||!t.readyState||/loaded|complete/.test(t.readyState))&&(t.onload=t.onreadystatechange=null,t.parentNode&&t.parentNode.removeChild(t),t=null,n||i(200,"success"))},n.insertBefore(t,n.firstChild)},abort:function(){t&&t.onload(void 0,!0)}}}});var fn=[],dn=/(=)\?(?=&|$)|\?\?/;pe.ajaxSetup({jsonp:"callback",jsonpCallback:function(){var e=fn.pop()||pe.expando+"_"+Wt++;return this[e]=!0,e}}),pe.ajaxPrefilter("json jsonp",function(t,n,r){var i,o,a,s=t.jsonp!==!1&&(dn.test(t.url)?"url":"string"==typeof t.data&&0===(t.contentType||"").indexOf("application/x-www-form-urlencoded")&&dn.test(t.data)&&"data");if(s||"jsonp"===t.dataTypes[0])return i=t.jsonpCallback=pe.isFunction(t.jsonpCallback)?t.jsonpCallback():t.jsonpCallback,s?t[s]=t[s].replace(dn,"$1"+i):t.jsonp!==!1&&(t.url+=(It.test(t.url)?"&":"?")+t.jsonp+"="+i),t.converters["script json"]=function(){return a||pe.error(i+" was not called"),a[0]},t.dataTypes[0]="json",o=e[i],e[i]=function(){a=arguments},r.always(function(){void 0===o?pe(e).removeProp(i):e[i]=o,t[i]&&(t.jsonpCallback=n.jsonpCallback,fn.push(i)),a&&pe.isFunction(o)&&o(a[0]),a=o=void 0}),"script"}),pe.parseHTML=function(e,t,n){if(!e||"string"!=typeof e)return null;"boolean"==typeof t&&(n=t,t=!1),t=t||re;var r=Te.exec(e),i=!n&&[];return r?[t.createElement(r[1])]:(r=y([e],t,i),i&&i.length&&pe(i).remove(),pe.merge([],r.childNodes))};var pn=pe.fn.load;return pe.fn.load=function(e,t,n){if("string"!=typeof e&&pn)return pn.apply(this,arguments);var r,i,o,a=this,s=e.indexOf(" ");return s>-1&&(r=pe.trim(e.slice(s,e.length)),e=e.slice(0,s)),pe.isFunction(t)?(n=t,t=void 0):t&&"object"==typeof t&&(i="POST"),a.length>0&&pe.ajax({url:e,type:i||"GET",dataType:"html",data:t}).done(function(e){o=arguments,a.html(r?pe("<div>").append(pe.parseHTML(e)).find(r):e)}).always(n&&function(e,t){a.each(function(){n.apply(this,o||[e.responseText,t,e])})}),this},pe.each(["ajaxStart","ajaxStop","ajaxComplete","ajaxError","ajaxSuccess","ajaxSend"],function(e,t){pe.fn[t]=function(e){return this.on(t,e)}}),pe.expr.filters.animated=function(e){return pe.grep(pe.timers,function(t){return e===t.elem}).length},pe.offset={setOffset:function(e,t,n){var r,i,o,a,s,u,l,c=pe.css(e,"position"),f=pe(e),d={};"static"===c&&(e.style.position="relative"),s=f.offset(),o=pe.css(e,"top"),u=pe.css(e,"left"),l=("absolute"===c||"fixed"===c)&&pe.inArray("auto",[o,u])>-1,l?(r=f.position(),a=r.top,i=r.left):(a=parseFloat(o)||0,i=parseFloat(u)||0),pe.isFunction(t)&&(t=t.call(e,n,pe.extend({},s))),null!=t.top&&(d.top=t.top-s.top+a),null!=t.left&&(d.left=t.left-s.left+i),"using"in t?t.using.call(e,d):f.css(d)}},pe.fn.extend({offset:function(e){if(arguments.length)return void 0===e?this:this.each(function(t){pe.offset.setOffset(this,e,t)});var t,n,r={top:0,left:0},i=this[0],o=i&&i.ownerDocument;if(o)return t=o.documentElement,pe.contains(t,i)?("undefined"!=typeof i.getBoundingClientRect&&(r=i.getBoundingClientRect()),n=te(o),{top:r.top+(n.pageYOffset||t.scrollTop)-(t.clientTop||0),left:r.left+(n.pageXOffset||t.scrollLeft)-(t.clientLeft||0)}):r},position:function(){if(this[0]){var e,t,n={top:0,left:0},r=this[0];return"fixed"===pe.css(r,"position")?t=r.getBoundingClientRect():(e=this.offsetParent(),t=this.offset(),pe.nodeName(e[0],"html")||(n=e.offset()),n.top+=pe.css(e[0],"borderTopWidth",!0),n.left+=pe.css(e[0],"borderLeftWidth",!0)),{top:t.top-n.top-pe.css(r,"marginTop",!0),left:t.left-n.left-pe.css(r,"marginLeft",!0)}}},offsetParent:function(){return this.map(function(){
for(var e=this.offsetParent;e&&!pe.nodeName(e,"html")&&"static"===pe.css(e,"position");)e=e.offsetParent;return e||pt})}}),pe.each({scrollLeft:"pageXOffset",scrollTop:"pageYOffset"},function(e,t){var n=/Y/.test(t);pe.fn[e]=function(r){return Pe(this,function(e,r,i){var o=te(e);return void 0===i?o?t in o?o[t]:o.document.documentElement[r]:e[r]:void(o?o.scrollTo(n?pe(o).scrollLeft():i,n?i:pe(o).scrollTop()):e[r]=i)},e,r,arguments.length,null)}}),pe.each(["top","left"],function(e,t){pe.cssHooks[t]=L(fe.pixelPosition,function(e,n){if(n)return n=gt(e,t),ft.test(n)?pe(e).position()[t]+"px":n})}),pe.each({Height:"height",Width:"width"},function(e,t){pe.each({padding:"inner"+e,content:t,"":"outer"+e},function(n,r){pe.fn[r]=function(r,i){var o=arguments.length&&(n||"boolean"!=typeof r),a=n||(r===!0||i===!0?"margin":"border");return Pe(this,function(t,n,r){var i;return pe.isWindow(t)?t.document.documentElement["client"+e]:9===t.nodeType?(i=t.documentElement,Math.max(t.body["scroll"+e],i["scroll"+e],t.body["offset"+e],i["offset"+e],i["client"+e])):void 0===r?pe.css(t,n,a):pe.style(t,n,r,a)},t,o?r:void 0,o,null)}})}),pe.fn.extend({bind:function(e,t,n){return this.on(e,null,t,n)},unbind:function(e,t){return this.off(e,null,t)},delegate:function(e,t,n,r){return this.on(t,e,n,r)},undelegate:function(e,t,n){return 1===arguments.length?this.off(e,"**"):this.off(t,e||"**",n)}}),pe.fn.size=function(){return this.length},pe.fn.andSelf=pe.fn.addBack,layui.define(function(e){layui.$=pe,e("jquery",pe)}),pe});!function(e,t){"use strict";var i,n,a=e.layui&&layui.define,o={getPath:function(){var e=document.currentScript?document.currentScript.src:function(){for(var e,t=document.scripts,i=t.length-1,n=i;n>0;n--)if("interactive"===t[n].readyState){e=t[n].src;break}return e||t[i].src}();return e.substring(0,e.lastIndexOf("/")+1)}(),config:{},end:{},minIndex:0,minLeft:[],btn:["&#x786E;&#x5B9A;","&#x53D6;&#x6D88;"],type:["dialog","page","iframe","loading","tips"],getStyle:function(t,i){var n=t.currentStyle?t.currentStyle:e.getComputedStyle(t,null);return n[n.getPropertyValue?"getPropertyValue":"getAttribute"](i)},link:function(t,i,n){if(r.path){var a=document.getElementsByTagName("head")[0],s=document.createElement("link");"string"==typeof i&&(n=i);var l=(n||t).replace(/\.|\//g,""),f="layuicss-"+l,c=0;s.rel="stylesheet",s.href=r.path+t,s.id=f,document.getElementById(f)||a.appendChild(s),"function"==typeof i&&!function u(){return++c>80?e.console&&console.error("layer.css: Invalid"):void(1989===parseInt(o.getStyle(document.getElementById(f),"width"))?i():setTimeout(u,100))}()}}},r={v:"3.1.1",ie:function(){var t=navigator.userAgent.toLowerCase();return!!(e.ActiveXObject||"ActiveXObject"in e)&&((t.match(/msie\s(\d+)/)||[])[1]||"11")}(),index:e.layer&&e.layer.v?1e5:0,path:o.getPath,config:function(e,t){return e=e||{},r.cache=o.config=i.extend({},o.config,e),r.path=o.config.path||r.path,"string"==typeof e.extend&&(e.extend=[e.extend]),o.config.path&&r.ready(),e.extend?(a?layui.addcss("modules/layer/"+e.extend):o.link("theme/"+e.extend),this):this},ready:function(e){var t="layer",i="",n=(a?"modules/layer/":"theme/")+"default/layer.css?v="+r.v+i;return a?layui.addcss(n,e,t):o.link(n,e,t),this},alert:function(e,t,n){var a="function"==typeof t;return a&&(n=t),r.open(i.extend({content:e,yes:n},a?{}:t))},confirm:function(e,t,n,a){var s="function"==typeof t;return s&&(a=n,n=t),r.open(i.extend({content:e,btn:o.btn,yes:n,btn2:a},s?{}:t))},msg:function(e,n,a){var s="function"==typeof n,f=o.config.skin,c=(f?f+" "+f+"-msg":"")||"layui-layer-msg",u=l.anim.length-1;return s&&(a=n),r.open(i.extend({content:e,time:3e3,shade:!1,skin:c,title:!1,closeBtn:!1,btn:!1,resize:!1,end:a},s&&!o.config.skin?{skin:c+" layui-layer-hui",anim:u}:function(){return n=n||{},(n.icon===-1||n.icon===t&&!o.config.skin)&&(n.skin=c+" "+(n.skin||"layui-layer-hui")),n}()))},load:function(e,t){return r.open(i.extend({type:3,icon:e||0,resize:!1,shade:.01},t))},tips:function(e,t,n){return r.open(i.extend({type:4,content:[e,t],closeBtn:!1,time:3e3,shade:!1,resize:!1,fixed:!1,maxWidth:210},n))}},s=function(e){var t=this;t.index=++r.index,t.config=i.extend({},t.config,o.config,e),document.body?t.creat():setTimeout(function(){t.creat()},30)};s.pt=s.prototype;var l=["layui-layer",".layui-layer-title",".layui-layer-main",".layui-layer-dialog","layui-layer-iframe","layui-layer-content","layui-layer-btn","layui-layer-close"];l.anim=["layer-anim-00","layer-anim-01","layer-anim-02","layer-anim-03","layer-anim-04","layer-anim-05","layer-anim-06"],s.pt.config={type:0,shade:.3,fixed:!0,move:l[1],title:"&#x4FE1;&#x606F;",offset:"auto",area:"auto",closeBtn:1,time:0,zIndex:19891014,maxWidth:360,anim:0,isOutAnim:!0,icon:-1,moveType:1,resize:!0,scrollbar:!0,tips:2},s.pt.vessel=function(e,t){var n=this,a=n.index,r=n.config,s=r.zIndex+a,f="object"==typeof r.title,c=r.maxmin&&(1===r.type||2===r.type),u=r.title?'<div class="layui-layer-title" style="'+(f?r.title[1]:"")+'">'+(f?r.title[0]:r.title)+"</div>":"";return r.zIndex=s,t([r.shade?'<div class="layui-layer-shade" id="layui-layer-shade'+a+'" times="'+a+'" style="'+("z-index:"+(s-1)+"; ")+'"></div>':"",'<div class="'+l[0]+(" layui-layer-"+o.type[r.type])+(0!=r.type&&2!=r.type||r.shade?"":" layui-layer-border")+" "+(r.skin||"")+'" id="'+l[0]+a+'" type="'+o.type[r.type]+'" times="'+a+'" showtime="'+r.time+'" conType="'+(e?"object":"string")+'" style="z-index: '+s+"; width:"+r.area[0]+";height:"+r.area[1]+(r.fixed?"":";position:absolute;")+'">'+(e&&2!=r.type?"":u)+'<div id="'+(r.id||"")+'" class="layui-layer-content'+(0==r.type&&r.icon!==-1?" layui-layer-padding":"")+(3==r.type?" layui-layer-loading"+r.icon:"")+'">'+(0==r.type&&r.icon!==-1?'<i class="layui-layer-ico layui-layer-ico'+r.icon+'"></i>':"")+(1==r.type&&e?"":r.content||"")+'</div><span class="layui-layer-setwin">'+function(){var e=c?'<a class="layui-layer-min" href="javascript:;"><cite></cite></a><a class="layui-layer-ico layui-layer-max" href="javascript:;"></a>':"";return r.closeBtn&&(e+='<a class="layui-layer-ico '+l[7]+" "+l[7]+(r.title?r.closeBtn:4==r.type?"1":"2")+'" href="javascript:;"></a>'),e}()+"</span>"+(r.btn?function(){var e="";"string"==typeof r.btn&&(r.btn=[r.btn]);for(var t=0,i=r.btn.length;t<i;t++)e+='<a class="'+l[6]+t+'">'+r.btn[t]+"</a>";return'<div class="'+l[6]+" layui-layer-btn-"+(r.btnAlign||"")+'">'+e+"</div>"}():"")+(r.resize?'<span class="layui-layer-resize"></span>':"")+"</div>"],u,i('<div class="layui-layer-move"></div>')),n},s.pt.creat=function(){var e=this,t=e.config,a=e.index,s=t.content,f="object"==typeof s,c=i("body");if(!t.id||!i("#"+t.id)[0]){switch("string"==typeof t.area&&(t.area="auto"===t.area?["",""]:[t.area,""]),t.shift&&(t.anim=t.shift),6==r.ie&&(t.fixed=!1),t.type){case 0:t.btn="btn"in t?t.btn:o.btn[0],r.closeAll("dialog");break;case 2:var s=t.content=f?t.content:[t.content||"http://layer.layui.com","auto"];t.content='<iframe scrolling="'+(t.content[1]||"auto")+'" allowtransparency="true" id="'+l[4]+a+'" name="'+l[4]+a+'" onload="this.className=\'\';" class="layui-layer-load" frameborder="0" src="'+t.content[0]+'"></iframe>';break;case 3:delete t.title,delete t.closeBtn,t.icon===-1&&0===t.icon,r.closeAll("loading");break;case 4:f||(t.content=[t.content,"body"]),t.follow=t.content[1],t.content=t.content[0]+'<i class="layui-layer-TipsG"></i>',delete t.title,t.tips="object"==typeof t.tips?t.tips:[t.tips,!0],t.tipsMore||r.closeAll("tips")}if(e.vessel(f,function(n,r,u){c.append(n[0]),f?function(){2==t.type||4==t.type?function(){i("body").append(n[1])}():function(){s.parents("."+l[0])[0]||(s.data("display",s.css("display")).show().addClass("layui-layer-wrap").wrap(n[1]),i("#"+l[0]+a).find("."+l[5]).before(r))}()}():c.append(n[1]),i(".layui-layer-move")[0]||c.append(o.moveElem=u),e.layero=i("#"+l[0]+a),t.scrollbar||l.html.css("overflow","hidden").attr("layer-full",a)}).auto(a),i("#layui-layer-shade"+e.index).css({"background-color":t.shade[1]||"#000",opacity:t.shade[0]||t.shade}),2==t.type&&6==r.ie&&e.layero.find("iframe").attr("src",s[0]),4==t.type?e.tips():e.offset(),t.fixed&&n.on("resize",function(){e.offset(),(/^\d+%$/.test(t.area[0])||/^\d+%$/.test(t.area[1]))&&e.auto(a),4==t.type&&e.tips()}),t.time<=0||setTimeout(function(){r.close(e.index)},t.time),e.move().callback(),l.anim[t.anim]){var u="layer-anim "+l.anim[t.anim];e.layero.addClass(u).one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend",function(){i(this).removeClass(u)})}t.isOutAnim&&e.layero.data("isOutAnim",!0)}},s.pt.auto=function(e){var t=this,a=t.config,o=i("#"+l[0]+e);""===a.area[0]&&a.maxWidth>0&&(r.ie&&r.ie<8&&a.btn&&o.width(o.innerWidth()),o.outerWidth()>a.maxWidth&&o.width(a.maxWidth));var s=[o.innerWidth(),o.innerHeight()],f=o.find(l[1]).outerHeight()||0,c=o.find("."+l[6]).outerHeight()||0,u=function(e){e=o.find(e),e.height(s[1]-f-c-2*(0|parseFloat(e.css("padding-top"))))};switch(a.type){case 2:u("iframe");break;default:""===a.area[1]?a.maxHeight>0&&o.outerHeight()>a.maxHeight?(s[1]=a.maxHeight,u("."+l[5])):a.fixed&&s[1]>=n.height()&&(s[1]=n.height(),u("."+l[5])):u("."+l[5])}return t},s.pt.offset=function(){var e=this,t=e.config,i=e.layero,a=[i.outerWidth(),i.outerHeight()],o="object"==typeof t.offset;e.offsetTop=(n.height()-a[1])/2,e.offsetLeft=(n.width()-a[0])/2,o?(e.offsetTop=t.offset[0],e.offsetLeft=t.offset[1]||e.offsetLeft):"auto"!==t.offset&&("t"===t.offset?e.offsetTop=0:"r"===t.offset?e.offsetLeft=n.width()-a[0]:"b"===t.offset?e.offsetTop=n.height()-a[1]:"l"===t.offset?e.offsetLeft=0:"lt"===t.offset?(e.offsetTop=0,e.offsetLeft=0):"lb"===t.offset?(e.offsetTop=n.height()-a[1],e.offsetLeft=0):"rt"===t.offset?(e.offsetTop=0,e.offsetLeft=n.width()-a[0]):"rb"===t.offset?(e.offsetTop=n.height()-a[1],e.offsetLeft=n.width()-a[0]):e.offsetTop=t.offset),t.fixed||(e.offsetTop=/%$/.test(e.offsetTop)?n.height()*parseFloat(e.offsetTop)/100:parseFloat(e.offsetTop),e.offsetLeft=/%$/.test(e.offsetLeft)?n.width()*parseFloat(e.offsetLeft)/100:parseFloat(e.offsetLeft),e.offsetTop+=n.scrollTop(),e.offsetLeft+=n.scrollLeft()),i.attr("minLeft")&&(e.offsetTop=n.height()-(i.find(l[1]).outerHeight()||0),e.offsetLeft=i.css("left")),i.css({top:e.offsetTop,left:e.offsetLeft})},s.pt.tips=function(){var e=this,t=e.config,a=e.layero,o=[a.outerWidth(),a.outerHeight()],r=i(t.follow);r[0]||(r=i("body"));var s={width:r.outerWidth(),height:r.outerHeight(),top:r.offset().top,left:r.offset().left},f=a.find(".layui-layer-TipsG"),c=t.tips[0];t.tips[1]||f.remove(),s.autoLeft=function(){s.left+o[0]-n.width()>0?(s.tipLeft=s.left+s.width-o[0],f.css({right:12,left:"auto"})):s.tipLeft=s.left},s.where=[function(){s.autoLeft(),s.tipTop=s.top-o[1]-10,f.removeClass("layui-layer-TipsB").addClass("layui-layer-TipsT").css("border-right-color",t.tips[1])},function(){s.tipLeft=s.left+s.width+10,s.tipTop=s.top,f.removeClass("layui-layer-TipsL").addClass("layui-layer-TipsR").css("border-bottom-color",t.tips[1])},function(){s.autoLeft(),s.tipTop=s.top+s.height+10,f.removeClass("layui-layer-TipsT").addClass("layui-layer-TipsB").css("border-right-color",t.tips[1])},function(){s.tipLeft=s.left-o[0]-10,s.tipTop=s.top,f.removeClass("layui-layer-TipsR").addClass("layui-layer-TipsL").css("border-bottom-color",t.tips[1])}],s.where[c-1](),1===c?s.top-(n.scrollTop()+o[1]+16)<0&&s.where[2]():2===c?n.width()-(s.left+s.width+o[0]+16)>0||s.where[3]():3===c?s.top-n.scrollTop()+s.height+o[1]+16-n.height()>0&&s.where[0]():4===c&&o[0]+16-s.left>0&&s.where[1](),a.find("."+l[5]).css({"background-color":t.tips[1],"padding-right":t.closeBtn?"30px":""}),a.css({left:s.tipLeft-(t.fixed?n.scrollLeft():0),top:s.tipTop-(t.fixed?n.scrollTop():0)})},s.pt.move=function(){var e=this,t=e.config,a=i(document),s=e.layero,l=s.find(t.move),f=s.find(".layui-layer-resize"),c={};return t.move&&l.css("cursor","move"),l.on("mousedown",function(e){e.preventDefault(),t.move&&(c.moveStart=!0,c.offset=[e.clientX-parseFloat(s.css("left")),e.clientY-parseFloat(s.css("top"))],o.moveElem.css("cursor","move").show())}),f.on("mousedown",function(e){e.preventDefault(),c.resizeStart=!0,c.offset=[e.clientX,e.clientY],c.area=[s.outerWidth(),s.outerHeight()],o.moveElem.css("cursor","se-resize").show()}),a.on("mousemove",function(i){if(c.moveStart){var a=i.clientX-c.offset[0],o=i.clientY-c.offset[1],l="fixed"===s.css("position");if(i.preventDefault(),c.stX=l?0:n.scrollLeft(),c.stY=l?0:n.scrollTop(),!t.moveOut){var f=n.width()-s.outerWidth()+c.stX,u=n.height()-s.outerHeight()+c.stY;a<c.stX&&(a=c.stX),a>f&&(a=f),o<c.stY&&(o=c.stY),o>u&&(o=u)}s.css({left:a,top:o})}if(t.resize&&c.resizeStart){var a=i.clientX-c.offset[0],o=i.clientY-c.offset[1];i.preventDefault(),r.style(e.index,{width:c.area[0]+a,height:c.area[1]+o}),c.isResize=!0,t.resizing&&t.resizing(s)}}).on("mouseup",function(e){c.moveStart&&(delete c.moveStart,o.moveElem.hide(),t.moveEnd&&t.moveEnd(s)),c.resizeStart&&(delete c.resizeStart,o.moveElem.hide())}),e},s.pt.callback=function(){function e(){var e=a.cancel&&a.cancel(t.index,n);e===!1||r.close(t.index)}var t=this,n=t.layero,a=t.config;t.openLayer(),a.success&&(2==a.type?n.find("iframe").on("load",function(){a.success(n,t.index)}):a.success(n,t.index)),6==r.ie&&t.IE6(n),n.find("."+l[6]).children("a").on("click",function(){var e=i(this).index();if(0===e)a.yes?a.yes(t.index,n):a.btn1?a.btn1(t.index,n):r.close(t.index);else{var o=a["btn"+(e+1)]&&a["btn"+(e+1)](t.index,n);o===!1||r.close(t.index)}}),n.find("."+l[7]).on("click",e),a.shadeClose&&i("#layui-layer-shade"+t.index).on("click",function(){r.close(t.index)}),n.find(".layui-layer-min").on("click",function(){var e=a.min&&a.min(n);e===!1||r.min(t.index,a)}),n.find(".layui-layer-max").on("click",function(){i(this).hasClass("layui-layer-maxmin")?(r.restore(t.index),a.restore&&a.restore(n)):(r.full(t.index,a),setTimeout(function(){a.full&&a.full(n)},100))}),a.end&&(o.end[t.index]=a.end)},o.reselect=function(){i.each(i("select"),function(e,t){var n=i(this);n.parents("."+l[0])[0]||1==n.attr("layer")&&i("."+l[0]).length<1&&n.removeAttr("layer").show(),n=null})},s.pt.IE6=function(e){i("select").each(function(e,t){var n=i(this);n.parents("."+l[0])[0]||"none"===n.css("display")||n.attr({layer:"1"}).hide(),n=null})},s.pt.openLayer=function(){var e=this;r.zIndex=e.config.zIndex,r.setTop=function(e){var t=function(){r.zIndex++,e.css("z-index",r.zIndex+1)};return r.zIndex=parseInt(e[0].style.zIndex),e.on("mousedown",t),r.zIndex}},o.record=function(e){var t=[e.width(),e.height(),e.position().top,e.position().left+parseFloat(e.css("margin-left"))];e.find(".layui-layer-max").addClass("layui-layer-maxmin"),e.attr({area:t})},o.rescollbar=function(e){l.html.attr("layer-full")==e&&(l.html[0].style.removeProperty?l.html[0].style.removeProperty("overflow"):l.html[0].style.removeAttribute("overflow"),l.html.removeAttr("layer-full"))},e.layer=r,r.getChildFrame=function(e,t){return t=t||i("."+l[4]).attr("times"),i("#"+l[0]+t).find("iframe").contents().find(e)},r.getFrameIndex=function(e){return i("#"+e).parents("."+l[4]).attr("times")},r.iframeAuto=function(e){if(e){var t=r.getChildFrame("html",e).outerHeight(),n=i("#"+l[0]+e),a=n.find(l[1]).outerHeight()||0,o=n.find("."+l[6]).outerHeight()||0;n.css({height:t+a+o}),n.find("iframe").css({height:t})}},r.iframeSrc=function(e,t){i("#"+l[0]+e).find("iframe").attr("src",t)},r.style=function(e,t,n){var a=i("#"+l[0]+e),r=a.find(".layui-layer-content"),s=a.attr("type"),f=a.find(l[1]).outerHeight()||0,c=a.find("."+l[6]).outerHeight()||0;a.attr("minLeft");s!==o.type[3]&&s!==o.type[4]&&(n||(parseFloat(t.width)<=260&&(t.width=260),parseFloat(t.height)-f-c<=64&&(t.height=64+f+c)),a.css(t),c=a.find("."+l[6]).outerHeight(),s===o.type[2]?a.find("iframe").css({height:parseFloat(t.height)-f-c}):r.css({height:parseFloat(t.height)-f-c-parseFloat(r.css("padding-top"))-parseFloat(r.css("padding-bottom"))}))},r.min=function(e,t){var a=i("#"+l[0]+e),s=a.find(l[1]).outerHeight()||0,f=a.attr("minLeft")||181*o.minIndex+"px",c=a.css("position");o.record(a),o.minLeft[0]&&(f=o.minLeft[0],o.minLeft.shift()),a.attr("position",c),r.style(e,{width:180,height:s,left:f,top:n.height()-s,position:"fixed",overflow:"hidden"},!0),a.find(".layui-layer-min").hide(),"page"===a.attr("type")&&a.find(l[4]).hide(),o.rescollbar(e),a.attr("minLeft")||o.minIndex++,a.attr("minLeft",f)},r.restore=function(e){var t=i("#"+l[0]+e),n=t.attr("area").split(",");t.attr("type");r.style(e,{width:parseFloat(n[0]),height:parseFloat(n[1]),top:parseFloat(n[2]),left:parseFloat(n[3]),position:t.attr("position"),overflow:"visible"},!0),t.find(".layui-layer-max").removeClass("layui-layer-maxmin"),t.find(".layui-layer-min").show(),"page"===t.attr("type")&&t.find(l[4]).show(),o.rescollbar(e)},r.full=function(e){var t,a=i("#"+l[0]+e);o.record(a),l.html.attr("layer-full")||l.html.css("overflow","hidden").attr("layer-full",e),clearTimeout(t),t=setTimeout(function(){var t="fixed"===a.css("position");r.style(e,{top:t?0:n.scrollTop(),left:t?0:n.scrollLeft(),width:n.width(),height:n.height()},!0),a.find(".layui-layer-min").hide()},100)},r.title=function(e,t){var n=i("#"+l[0]+(t||r.index)).find(l[1]);n.html(e)},r.close=function(e){var t=i("#"+l[0]+e),n=t.attr("type"),a="layer-anim-close";if(t[0]){var s="layui-layer-wrap",f=function(){if(n===o.type[1]&&"object"===t.attr("conType")){t.children(":not(."+l[5]+")").remove();for(var a=t.find("."+s),r=0;r<2;r++)a.unwrap();a.css("display",a.data("display")).removeClass(s)}else{if(n===o.type[2])try{var f=i("#"+l[4]+e)[0];f.contentWindow.document.write(""),f.contentWindow.close(),t.find("."+l[5])[0].removeChild(f)}catch(c){}t[0].innerHTML="",t.remove()}"function"==typeof o.end[e]&&o.end[e](),delete o.end[e]};t.data("isOutAnim")&&t.addClass("layer-anim "+a),i("#layui-layer-moves, #layui-layer-shade"+e).remove(),6==r.ie&&o.reselect(),o.rescollbar(e),t.attr("minLeft")&&(o.minIndex--,o.minLeft.push(t.attr("minLeft"))),r.ie&&r.ie<10||!t.data("isOutAnim")?f():setTimeout(function(){f()},200)}},r.closeAll=function(e){i.each(i("."+l[0]),function(){var t=i(this),n=e?t.attr("type")===e:1;n&&r.close(t.attr("times")),n=null})};var f=r.cache||{},c=function(e){return f.skin?" "+f.skin+" "+f.skin+"-"+e:""};r.prompt=function(e,t){var a="";if(e=e||{},"function"==typeof e&&(t=e),e.area){var o=e.area;a='style="width: '+o[0]+"; height: "+o[1]+';"',delete e.area}var s,l=2==e.formType?'<textarea class="layui-layer-input"'+a+"></textarea>":function(){return'<input type="'+(1==e.formType?"password":"text")+'" class="layui-layer-input">'}(),f=e.success;return delete e.success,r.open(i.extend({type:1,btn:["&#x786E;&#x5B9A;","&#x53D6;&#x6D88;"],content:l,skin:"layui-layer-prompt"+c("prompt"),maxWidth:n.width(),success:function(t){s=t.find(".layui-layer-input"),s.val(e.value||"").focus(),"function"==typeof f&&f(t)},resize:!1,yes:function(i){var n=s.val();""===n?s.focus():n.length>(e.maxlength||500)?r.tips("&#x6700;&#x591A;&#x8F93;&#x5165;"+(e.maxlength||500)+"&#x4E2A;&#x5B57;&#x6570;",s,{tips:1}):t&&t(n,i,s)}},e))},r.tab=function(e){e=e||{};var t=e.tab||{},n="layui-this",a=e.success;return delete e.success,r.open(i.extend({type:1,skin:"layui-layer-tab"+c("tab"),resize:!1,title:function(){var e=t.length,i=1,a="";if(e>0)for(a='<span class="'+n+'">'+t[0].title+"</span>";i<e;i++)a+="<span>"+t[i].title+"</span>";return a}(),content:'<ul class="layui-layer-tabmain">'+function(){var e=t.length,i=1,a="";if(e>0)for(a='<li class="layui-layer-tabli '+n+'">'+(t[0].content||"no content")+"</li>";i<e;i++)a+='<li class="layui-layer-tabli">'+(t[i].content||"no  content")+"</li>";return a}()+"</ul>",success:function(t){var o=t.find(".layui-layer-title").children(),r=t.find(".layui-layer-tabmain").children();o.on("mousedown",function(t){t.stopPropagation?t.stopPropagation():t.cancelBubble=!0;var a=i(this),o=a.index();a.addClass(n).siblings().removeClass(n),r.eq(o).show().siblings().hide(),"function"==typeof e.change&&e.change(o)}),"function"==typeof a&&a(t)}},e))},r.photos=function(t,n,a){function o(e,t,i){var n=new Image;return n.src=e,n.complete?t(n):(n.onload=function(){n.onload=null,t(n)},void(n.onerror=function(e){n.onerror=null,i(e)}))}var s={};if(t=t||{},t.photos){var l=t.photos.constructor===Object,f=l?t.photos:{},u=f.data||[],d=f.start||0;s.imgIndex=(0|d)+1,t.img=t.img||"img";var y=t.success;if(delete t.success,l){if(0===u.length)return r.msg("&#x6CA1;&#x6709;&#x56FE;&#x7247;")}else{var p=i(t.photos),h=function(){u=[],p.find(t.img).each(function(e){var t=i(this);t.attr("layer-index",e),u.push({alt:t.attr("alt"),pid:t.attr("layer-pid"),src:t.attr("layer-src")||t.attr("src"),thumb:t.attr("src")})})};if(h(),0===u.length)return;if(n||p.on("click",t.img,function(){var e=i(this),n=e.attr("layer-index");r.photos(i.extend(t,{photos:{start:n,data:u,tab:t.tab},full:t.full}),!0),h()}),!n)return}s.imgprev=function(e){s.imgIndex--,s.imgIndex<1&&(s.imgIndex=u.length),s.tabimg(e)},s.imgnext=function(e,t){s.imgIndex++,s.imgIndex>u.length&&(s.imgIndex=1,t)||s.tabimg(e)},s.keyup=function(e){if(!s.end){var t=e.keyCode;e.preventDefault(),37===t?s.imgprev(!0):39===t?s.imgnext(!0):27===t&&r.close(s.index)}},s.tabimg=function(e){if(!(u.length<=1))return f.start=s.imgIndex-1,r.close(s.index),r.photos(t,!0,e)},s.event=function(){s.bigimg.hover(function(){s.imgsee.show()},function(){s.imgsee.hide()}),s.bigimg.find(".layui-layer-imgprev").on("click",function(e){e.preventDefault(),s.imgprev()}),s.bigimg.find(".layui-layer-imgnext").on("click",function(e){e.preventDefault(),s.imgnext()}),i(document).on("keyup",s.keyup)},s.loadi=r.load(1,{shade:!("shade"in t)&&.9,scrollbar:!1}),o(u[d].src,function(n){r.close(s.loadi),s.index=r.open(i.extend({type:1,id:"layui-layer-photos",area:function(){var a=[n.width,n.height],o=[i(e).width()-100,i(e).height()-100];if(!t.full&&(a[0]>o[0]||a[1]>o[1])){var r=[a[0]/o[0],a[1]/o[1]];r[0]>r[1]?(a[0]=a[0]/r[0],a[1]=a[1]/r[0]):r[0]<r[1]&&(a[0]=a[0]/r[1],a[1]=a[1]/r[1])}return[a[0]+"px",a[1]+"px"]}(),title:!1,shade:.9,shadeClose:!0,closeBtn:!1,move:".layui-layer-phimg img",moveType:1,scrollbar:!1,moveOut:!0,isOutAnim:!1,skin:"layui-layer-photos"+c("photos"),content:'<div class="layui-layer-phimg"><img src="'+u[d].src+'" alt="'+(u[d].alt||"")+'" layer-pid="'+u[d].pid+'"><div class="layui-layer-imgsee">'+(u.length>1?'<span class="layui-layer-imguide"><a href="javascript:;" class="layui-layer-iconext layui-layer-imgprev"></a><a href="javascript:;" class="layui-layer-iconext layui-layer-imgnext"></a></span>':"")+'<div class="layui-layer-imgbar" style="display:'+(a?"block":"")+'"><span class="layui-layer-imgtit"><a href="javascript:;">'+(u[d].alt||"")+"</a><em>"+s.imgIndex+"/"+u.length+"</em></span></div></div></div>",success:function(e,i){s.bigimg=e.find(".layui-layer-phimg"),s.imgsee=e.find(".layui-layer-imguide,.layui-layer-imgbar"),s.event(e),t.tab&&t.tab(u[d],e),"function"==typeof y&&y(e)},end:function(){s.end=!0,i(document).off("keyup",s.keyup)}},t))},function(){r.close(s.loadi),r.msg("&#x5F53;&#x524D;&#x56FE;&#x7247;&#x5730;&#x5740;&#x5F02;&#x5E38;<br>&#x662F;&#x5426;&#x7EE7;&#x7EED;&#x67E5;&#x770B;&#x4E0B;&#x4E00;&#x5F20;&#xFF1F;",{time:3e4,btn:["&#x4E0B;&#x4E00;&#x5F20;","&#x4E0D;&#x770B;&#x4E86;"],yes:function(){u.length>1&&s.imgnext(!0,!0)}})})}},o.run=function(t){i=t,n=i(e),l.html=i("html"),r.open=function(e){var t=new s(e);return t.index}},e.layui&&layui.define?(r.ready(),layui.define("jquery",function(t){r.path=layui.cache.dir,o.run(layui.$),e.layer=r,t("layer",r)})):"function"==typeof define&&define.amd?define(["jquery"],function(){return o.run(e.jQuery),r}):function(){o.run(e.jQuery),r.ready()}()}(window);layui.define("jquery",function(t){"use strict";var a=layui.$,i=(layui.hint(),layui.device()),e="element",l="layui-this",n="layui-show",s=function(){this.config={}};s.prototype.set=function(t){var i=this;return a.extend(!0,i.config,t),i},s.prototype.on=function(t,a){return layui.onevent.call(this,e,t,a)},s.prototype.tabAdd=function(t,i){var e=".layui-tab-title",l=a(".layui-tab[lay-filter="+t+"]"),n=l.children(e),s=n.children(".layui-tab-bar"),o=l.children(".layui-tab-content"),r='<li lay-id="'+(i.id||"")+'"'+(i.attr?' lay-attr="'+i.attr+'"':"")+">"+(i.title||"unnaming")+"</li>";return s[0]?s.before(r):n.append(r),o.append('<div class="layui-tab-item">'+(i.content||"")+"</div>"),f.hideTabMore(!0),f.tabAuto(),this},s.prototype.tabDelete=function(t,i){var e=".layui-tab-title",l=a(".layui-tab[lay-filter="+t+"]"),n=l.children(e),s=n.find('>li[lay-id="'+i+'"]');return f.tabDelete(null,s),this},s.prototype.tabChange=function(t,i){var e=".layui-tab-title",l=a(".layui-tab[lay-filter="+t+"]"),n=l.children(e),s=n.find('>li[lay-id="'+i+'"]');return f.tabClick.call(s[0],null,null,s),this},s.prototype.tab=function(t){t=t||{},b.on("click",t.headerElem,function(i){var e=a(this).index();f.tabClick.call(this,i,e,null,t)})},s.prototype.progress=function(t,i){var e="layui-progress",l=a("."+e+"[lay-filter="+t+"]"),n=l.find("."+e+"-bar"),s=n.find("."+e+"-text");return n.css("width",i),s.text(i),this};var o=".layui-nav",r="layui-nav-item",c="layui-nav-bar",u="layui-nav-tree",d="layui-nav-child",y="layui-nav-more",h="layui-anim layui-anim-upbit",f={tabClick:function(t,i,s,o){o=o||{};var r=s||a(this),i=i||r.parent().children("li").index(r),c=o.headerElem?r.parent():r.parents(".layui-tab").eq(0),u=o.bodyElem?a(o.bodyElem):c.children(".layui-tab-content").children(".layui-tab-item"),d=r.find("a"),y=c.attr("lay-filter");"javascript:;"!==d.attr("href")&&"_blank"===d.attr("target")||(r.addClass(l).siblings().removeClass(l),u.eq(i).addClass(n).siblings().removeClass(n)),layui.event.call(this,e,"tab("+y+")",{elem:c,index:i})},tabDelete:function(t,i){var n=i||a(this).parent(),s=n.index(),o=n.parents(".layui-tab").eq(0),r=o.children(".layui-tab-content").children(".layui-tab-item"),c=o.attr("lay-filter");n.hasClass(l)&&(n.next()[0]?f.tabClick.call(n.next()[0],null,s+1):n.prev()[0]&&f.tabClick.call(n.prev()[0],null,s-1)),n.remove(),r.eq(s).remove(),setTimeout(function(){f.tabAuto()},50),layui.event.call(this,e,"tabDelete("+c+")",{elem:o,index:s})},tabAuto:function(){var t="layui-tab-more",e="layui-tab-bar",l="layui-tab-close",n=this;a(".layui-tab").each(function(){var s=a(this),o=s.children(".layui-tab-title"),r=(s.children(".layui-tab-content").children(".layui-tab-item"),'lay-stope="tabmore"'),c=a('<span class="layui-unselect layui-tab-bar" '+r+"><i "+r+' class="layui-icon">&#xe61a;</i></span>');if(n===window&&8!=i.ie&&f.hideTabMore(!0),s.attr("lay-allowClose")&&o.find("li").each(function(){var t=a(this);if(!t.find("."+l)[0]){var i=a('<i class="layui-icon layui-unselect '+l+'">&#x1006;</i>');i.on("click",f.tabDelete),t.append(i)}}),"string"!=typeof s.attr("lay-unauto"))if(o.prop("scrollWidth")>o.outerWidth()+1){if(o.find("."+e)[0])return;o.append(c),s.attr("overflow",""),c.on("click",function(a){o[this.title?"removeClass":"addClass"](t),this.title=this.title?"":"收缩"})}else o.find("."+e).remove(),s.removeAttr("overflow")})},hideTabMore:function(t){var i=a(".layui-tab-title");t!==!0&&"tabmore"===a(t.target).attr("lay-stope")||(i.removeClass("layui-tab-more"),i.find(".layui-tab-bar").attr("title",""))},clickThis:function(){var t=a(this),i=t.parents(o),n=i.attr("lay-filter"),s=t.parent(),c=t.siblings("."+d),y="string"==typeof s.attr("lay-unselect");"javascript:;"!==t.attr("href")&&"_blank"===t.attr("target")||y||c[0]||(i.find("."+l).removeClass(l),s.addClass(l)),i.hasClass(u)&&(c.removeClass(h),c[0]&&(s["none"===c.css("display")?"addClass":"removeClass"](r+"ed"),"all"===i.attr("lay-shrink")&&s.siblings().removeClass(r+"ed"))),layui.event.call(this,e,"nav("+n+")",t)},collapse:function(){var t=a(this),i=t.find(".layui-colla-icon"),l=t.siblings(".layui-colla-content"),s=t.parents(".layui-collapse").eq(0),o=s.attr("lay-filter"),r="none"===l.css("display");if("string"==typeof s.attr("lay-accordion")){var c=s.children(".layui-colla-item").children("."+n);c.siblings(".layui-colla-title").children(".layui-colla-icon").html("&#xe602;"),c.removeClass(n)}l[r?"addClass":"removeClass"](n),i.html(r?"&#xe61a;":"&#xe602;"),layui.event.call(this,e,"collapse("+o+")",{title:t,content:l,show:r})}};s.prototype.init=function(t,e){var l=function(){return e?'[lay-filter="'+e+'"]':""}(),s={tab:function(){f.tabAuto.call({})},nav:function(){var t=200,e={},s={},p={},b=function(l,o,r){var c=a(this),f=c.find("."+d);o.hasClass(u)?l.css({top:c.position().top,height:c.children("a").outerHeight(),opacity:1}):(f.addClass(h),l.css({left:c.position().left+parseFloat(c.css("marginLeft")),top:c.position().top+c.height()-l.height()}),e[r]=setTimeout(function(){l.css({width:c.width(),opacity:1})},i.ie&&i.ie<10?0:t),clearTimeout(p[r]),"block"===f.css("display")&&clearTimeout(s[r]),s[r]=setTimeout(function(){f.addClass(n),c.find("."+y).addClass(y+"d")},300))};a(o+l).each(function(i){var l=a(this),o=a('<span class="'+c+'"></span>'),h=l.find("."+r);l.find("."+c)[0]||(l.append(o),h.on("mouseenter",function(){b.call(this,o,l,i)}).on("mouseleave",function(){l.hasClass(u)||(clearTimeout(s[i]),s[i]=setTimeout(function(){l.find("."+d).removeClass(n),l.find("."+y).removeClass(y+"d")},300))}),l.on("mouseleave",function(){clearTimeout(e[i]),p[i]=setTimeout(function(){l.hasClass(u)?o.css({height:0,top:o.position().top+o.height()/2,opacity:0}):o.css({width:0,left:o.position().left+o.width()/2,opacity:0})},t)})),h.find("a").each(function(){var t=a(this),i=(t.parent(),t.siblings("."+d));i[0]&&!t.children("."+y)[0]&&t.append('<span class="'+y+'"></span>'),t.off("click",f.clickThis).on("click",f.clickThis)})})},breadcrumb:function(){var t=".layui-breadcrumb";a(t+l).each(function(){var t=a(this),i="lay-separator",e=t.attr(i)||"/",l=t.find("a");l.next("span["+i+"]")[0]||(l.each(function(t){t!==l.length-1&&a(this).after("<span "+i+">"+e+"</span>")}),t.css("visibility","visible"))})},progress:function(){var t="layui-progress";a("."+t+l).each(function(){var i=a(this),e=i.find(".layui-progress-bar"),l=e.attr("lay-percent");e.css("width",function(){return/^.+\/.+$/.test(l)?100*new Function("return "+l)()+"%":l}()),i.attr("lay-showPercent")&&setTimeout(function(){e.html('<span class="'+t+'-text">'+l+"</span>")},350)})},collapse:function(){var t="layui-collapse";a("."+t+l).each(function(){var t=a(this).find(".layui-colla-item");t.each(function(){var t=a(this),i=t.find(".layui-colla-title"),e=t.find(".layui-colla-content"),l="none"===e.css("display");i.find(".layui-colla-icon").remove(),i.append('<i class="layui-icon layui-colla-icon">'+(l?"&#xe602;":"&#xe61a;")+"</i>"),i.off("click",f.collapse).on("click",f.collapse)})})}};return s[t]?s[t]():layui.each(s,function(t,a){a()})},s.prototype.render=s.prototype.init;var p=new s,b=a(document);p.render();var v=".layui-tab-title li";b.on("click",v,f.tabClick),b.on("click",f.hideTabMore),a(window).on("resize",f.tabAuto),t(e,p)});layui.define("layer",function(e){"use strict";var t=layui.$,i=layui.layer,n=layui.hint(),a=layui.device(),o={config:{},set:function(e){var i=this;return i.config=t.extend({},i.config,e),i},on:function(e,t){return layui.onevent.call(this,r,e,t)}},l=function(){var e=this;return{upload:function(t){e.upload.call(e,t)},config:e.config}},r="upload",u="layui-upload-file",c="layui-upload-form",f="layui-upload-iframe",s="layui-upload-choose",p=function(e){var i=this;i.config=t.extend({},i.config,o.config,e),i.render()};p.prototype.config={accept:"images",exts:"",auto:!0,bindAction:"",url:"",field:"file",method:"post",data:{},drag:!0,size:0,number:0,multiple:!1},p.prototype.render=function(e){var i=this,e=i.config;e.elem=t(e.elem),e.bindAction=t(e.bindAction),i.file(),i.events()},p.prototype.file=function(){var e=this,i=e.config,n=e.elemFile=t(['<input class="'+u+'" type="file" accept="'+i.acceptMime+'" name="'+i.field+'"',i.multiple?" multiple":"",">"].join("")),o=i.elem.next();(o.hasClass(u)||o.hasClass(c))&&o.remove(),a.ie&&a.ie<10&&i.elem.wrap('<div class="layui-upload-wrap"></div>'),e.isFile()?(e.elemFile=i.elem,i.field=i.elem[0].name):i.elem.after(n),a.ie&&a.ie<10&&e.initIE()},p.prototype.initIE=function(){var e=this,i=e.config,n=t('<iframe id="'+f+'" class="'+f+'" name="'+f+'" frameborder="0"></iframe>'),a=t(['<form target="'+f+'" class="'+c+'" method="'+i.method,'" key="set-mine" enctype="multipart/form-data" action="'+i.url+'">',"</form>"].join(""));t("#"+f)[0]||t("body").append(n),i.elem.next().hasClass(f)||(e.elemFile.wrap(a),i.elem.next("."+f).append(function(){var e=[];return layui.each(i.data,function(t,i){i="function"==typeof i?i():i,e.push('<input type="hidden" name="'+t+'" value="'+i+'">')}),e.join("")}()))},p.prototype.msg=function(e){return i.msg(e,{icon:2,shift:6})},p.prototype.isFile=function(){var e=this.config.elem[0];if(e)return"input"===e.tagName.toLocaleLowerCase()&&"file"===e.type},p.prototype.preview=function(e){var t=this;window.FileReader&&layui.each(t.chooseFiles,function(t,i){var n=new FileReader;n.readAsDataURL(i),n.onload=function(){e&&e(t,i,this.result)}})},p.prototype.upload=function(e,i){var n,o=this,l=o.config,r=o.elemFile[0],u=function(){var i=0,n=0,a=e||o.files||o.chooseFiles||r.files,u=function(){l.multiple&&i+n===o.fileLength&&"function"==typeof l.allDone&&l.allDone({total:o.fileLength,successful:i,aborted:n})};layui.each(a,function(e,a){var r=new FormData;r.append(l.field,a),layui.each(l.data,function(e,t){t="function"==typeof t?t():t,r.append(e,t)}),t.ajax({url:l.url,type:l.method,data:r,contentType:!1,processData:!1,dataType:"json",headers:l.headers||{},success:function(t){i++,d(e,t),u()},error:function(){n++,o.msg("请求上传接口出现异常"),m(e),u()}})})},c=function(){var e=t("#"+f);o.elemFile.parent().submit(),clearInterval(p.timer),p.timer=setInterval(function(){var t,i=e.contents().find("body");try{t=i.text()}catch(n){o.msg("获取上传后的响应信息出现异常"),clearInterval(p.timer),m()}t&&(clearInterval(p.timer),i.html(""),d(0,t))},30)},d=function(e,t){if(o.elemFile.next("."+s).remove(),r.value="","object"!=typeof t)try{t=JSON.parse(t)}catch(i){return t={},o.msg("请对上传接口返回有效JSON")}"function"==typeof l.done&&l.done(t,e||0,function(e){o.upload(e)})},m=function(e){l.auto&&(r.value=""),"function"==typeof l.error&&l.error(e||0,function(e){o.upload(e)})},h=l.exts,v=function(){var t=[];return layui.each(e||o.chooseFiles,function(e,i){t.push(i.name)}),t}(),g={preview:function(e){o.preview(e)},upload:function(e,t){var i={};i[e]=t,o.upload(i)},pushFile:function(){return o.files=o.files||{},layui.each(o.chooseFiles,function(e,t){o.files[e]=t}),o.files}},y=function(){return"choose"===i?l.choose&&l.choose(g):(l.before&&l.before(g),a.ie?a.ie>9?u():c():void u())};if(v=0===v.length?r.value.match(/[^\/\\]+\..+/g)||[]||"":v,0!==v.length){switch(l.accept){case"file":if(h&&!RegExp("\\w\\.("+h+")$","i").test(escape(v)))return o.msg("选择的文件中包含不支持的格式"),r.value="";break;case"video":if(!RegExp("\\w\\.("+(h||"avi|mp4|wma|rmvb|rm|flash|3gp|flv")+")$","i").test(escape(v)))return o.msg("选择的视频中包含不支持的格式"),r.value="";break;case"audio":if(!RegExp("\\w\\.("+(h||"mp3|wav|mid")+")$","i").test(escape(v)))return o.msg("选择的音频中包含不支持的格式"),r.value="";break;default:if(layui.each(v,function(e,t){RegExp("\\w\\.("+(h||"jpg|png|gif|bmp|jpeg$")+")","i").test(escape(t))||(n=!0)}),n)return o.msg("选择的图片中包含不支持的格式"),r.value=""}if(o.fileLength=function(){var t=0,i=e||o.files||o.chooseFiles||r.files;return layui.each(i,function(){t++}),t}(),l.number&&o.fileLength>l.number)return o.msg("同时最多只能上传的数量为："+l.number);if(l.size>0&&!(a.ie&&a.ie<10)){var F;if(layui.each(o.chooseFiles,function(e,t){if(t.size>1024*l.size){var i=l.size/1024;i=i>=1?Math.floor(i)+(i%1>0?i.toFixed(1):0)+"MB":l.size+"KB",r.value="",F=i}}),F)return o.msg("文件不能超过"+F)}y()}},p.prototype.events=function(){var e=this,i=e.config,o=function(t){e.chooseFiles={},layui.each(t,function(t,i){var n=(new Date).getTime();e.chooseFiles[n+"-"+t]=i})},l=function(t,n){var a=e.elemFile,o=t.length>1?t.length+"个文件":(t[0]||{}).name||a[0].value.match(/[^\/\\]+\..+/g)||[]||"";a.next().hasClass(s)&&a.next().remove(),e.upload(null,"choose"),e.isFile()||i.choose||a.after('<span class="layui-inline '+s+'">'+o+"</span>")};i.elem.off("upload.start").on("upload.start",function(){var a=t(this),o=a.attr("lay-data");if(o)try{o=new Function("return "+o)(),e.config=t.extend({},i,o)}catch(l){n.error("Upload element property lay-data configuration item has a syntax error: "+o)}e.config.item=a,e.elemFile[0].click()}),a.ie&&a.ie<10||i.elem.off("upload.over").on("upload.over",function(){var e=t(this);e.attr("lay-over","")}).off("upload.leave").on("upload.leave",function(){var e=t(this);e.removeAttr("lay-over")}).off("upload.drop").on("upload.drop",function(n,a){var r=t(this),u=a.originalEvent.dataTransfer.files||[];r.removeAttr("lay-over"),o(u),i.auto?e.upload(u):l(u)}),e.elemFile.off("upload.change").on("upload.change",function(){var t=this.files||[];o(t),i.auto?e.upload():l(t)}),i.bindAction.off("upload.action").on("upload.action",function(){e.upload()}),i.elem.data("haveEvents")||(e.elemFile.on("change",function(){t(this).trigger("upload.change")}),i.elem.on("click",function(){e.isFile()||t(this).trigger("upload.start")}),i.drag&&i.elem.on("dragover",function(e){e.preventDefault(),t(this).trigger("upload.over")}).on("dragleave",function(e){t(this).trigger("upload.leave")}).on("drop",function(e){e.preventDefault(),t(this).trigger("upload.drop",e)}),i.bindAction.on("click",function(){t(this).trigger("upload.action")}),i.elem.data("haveEvents",!0))},o.render=function(e){var t=new p(e);return l.call(t)},e(r,o)});layui.define("layer",function(e){"use strict";var t=layui.$,i=layui.layer,a=layui.hint(),n=layui.device(),l="form",r=".layui-form",s="layui-this",o="layui-hide",u="layui-disabled",c=function(){this.config={verify:{required:[/[\S]+/,"必填项不能为空"],phone:[/^1\d{10}$/,"请输入正确的手机号"],email:[/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,"邮箱格式不正确"],url:[/(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/,"链接格式不正确"],number:function(e){if(!e||isNaN(e))return"只能填写数字"},date:[/^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/,"日期格式不正确"],identity:[/(^\d{15}$)|(^\d{17}(x|X|\d)$)/,"请输入正确的身份证号"]}}};c.prototype.set=function(e){var i=this;return t.extend(!0,i.config,e),i},c.prototype.verify=function(e){var i=this;return t.extend(!0,i.config.verify,e),i},c.prototype.on=function(e,t){return layui.onevent.call(this,l,e,t)},c.prototype.render=function(e,i){var n=this,c=t(r+function(){return i?'[lay-filter="'+i+'"]':""}()),d={select:function(){var e,i="请选择",a="layui-form-select",n="layui-select-title",r="layui-select-none",d="",f=c.find("select"),y=function(i,l){t(i.target).parent().hasClass(n)&&!l||(t("."+a).removeClass(a+"ed "+a+"up"),e&&d&&e.val(d)),e=null},h=function(i,c,f){var h=t(this),p=i.find("."+n),m=p.find("input"),k=i.find("dl"),g=k.children("dd");if(!c){var x=function(){var e=i.offset().top+i.outerHeight()+5-v.scrollTop(),t=k.outerHeight();i.addClass(a+"ed"),g.removeClass(o),e+t>v.height()&&e>=t&&i.addClass(a+"up")},b=function(e){i.removeClass(a+"ed "+a+"up"),m.blur(),e||C(m.val(),function(e){e&&(d=k.find("."+s).html(),m&&m.val(d))})};p.on("click",function(e){i.hasClass(a+"ed")?b():(y(e,!0),x()),k.find("."+r).remove()}),p.find(".layui-edge").on("click",function(){m.focus()}),m.on("keyup",function(e){var t=e.keyCode;9===t&&x()}).on("keydown",function(e){var t=e.keyCode;9===t?b():13===t&&e.preventDefault()});var C=function(e,i,a){var n=0;layui.each(g,function(){var i=t(this),l=i.text(),r=l.indexOf(e)===-1;(""===e||"blur"===a?e!==l:r)&&n++,"keyup"===a&&i[r?"addClass":"removeClass"](o)});var l=n===g.length;return i(l),l},w=function(e){var t=this.value,i=e.keyCode;return 9!==i&&13!==i&&37!==i&&38!==i&&39!==i&&40!==i&&(C(t,function(e){e?k.find("."+r)[0]||k.append('<p class="'+r+'">无匹配项</p>'):k.find("."+r).remove()},"keyup"),void(""===t&&k.find("."+r).remove()))};f&&m.on("keyup",w).on("blur",function(t){e=m,d=k.find("."+s).html(),setTimeout(function(){C(m.val(),function(e){d||m.val("")},"blur")},200)}),g.on("click",function(){var e=t(this),a=e.attr("lay-value"),n=h.attr("lay-filter");return!e.hasClass(u)&&(e.hasClass("layui-select-tips")?m.val(""):(m.val(e.text()),e.addClass(s)),e.siblings().removeClass(s),h.val(a).removeClass("layui-form-danger"),layui.event.call(this,l,"select("+n+")",{elem:h[0],value:a,othis:i}),b(!0),!1)}),i.find("dl>dt").on("click",function(e){return!1}),t(document).off("click",y).on("click",y)}};f.each(function(e,l){var r=t(this),o=r.next("."+a),c=this.disabled,d=l.value,f=t(l.options[l.selectedIndex]),y=l.options[0];if("string"==typeof r.attr("lay-ignore"))return r.show();var v="string"==typeof r.attr("lay-search"),p=y?y.value?i:y.innerHTML||i:i,m=t(['<div class="'+(v?"":"layui-unselect ")+a+(c?" layui-select-disabled":"")+'">','<div class="'+n+'"><input type="text" placeholder="'+p+'" value="'+(d?f.html():"")+'" '+(v?"":"readonly")+' class="layui-input'+(v?"":" layui-unselect")+(c?" "+u:"")+'">','<i class="layui-edge"></i></div>','<dl class="layui-anim layui-anim-upbit'+(r.find("optgroup")[0]?" layui-select-group":"")+'">'+function(e){var t=[];return layui.each(e,function(e,a){0!==e||a.value?"optgroup"===a.tagName.toLowerCase()?t.push("<dt>"+a.label+"</dt>"):t.push('<dd lay-value="'+a.value+'" class="'+(d===a.value?s:"")+(a.disabled?" "+u:"")+'">'+a.innerHTML+"</dd>"):t.push('<dd lay-value="" class="layui-select-tips">'+(a.innerHTML||i)+"</dd>")}),0===t.length&&t.push('<dd lay-value="" class="'+u+'">没有选项</dd>'),t.join("")}(r.find("*"))+"</dl>","</div>"].join(""));o[0]&&o.remove(),r.after(m),h.call(this,m,c,v)})},checkbox:function(){var e={checkbox:["layui-form-checkbox","layui-form-checked","checkbox"],_switch:["layui-form-switch","layui-form-onswitch","switch"]},i=c.find("input[type=checkbox]"),a=function(e,i){var a=t(this);e.on("click",function(){var t=a.attr("lay-filter"),n=(a.attr("lay-text")||"").split("|");a[0].disabled||(a[0].checked?(a[0].checked=!1,e.removeClass(i[1]).find("em").text(n[1])):(a[0].checked=!0,e.addClass(i[1]).find("em").text(n[0])),layui.event.call(a[0],l,i[2]+"("+t+")",{elem:a[0],value:a[0].value,othis:e}))})};i.each(function(i,n){var l=t(this),r=l.attr("lay-skin"),s=(l.attr("lay-text")||"").split("|"),o=this.disabled;"switch"===r&&(r="_"+r);var c=e[r]||e.checkbox;if("string"==typeof l.attr("lay-ignore"))return l.show();var d=l.next("."+c[0]),f=t(['<div class="layui-unselect '+c[0]+(n.checked?" "+c[1]:"")+(o?" layui-checkbox-disbaled "+u:"")+'" lay-skin="'+(r||"")+'">',{_switch:"<em>"+((n.checked?s[0]:s[1])||"")+"</em><i></i>"}[r]||(n.title.replace(/\s/g,"")?"<span>"+n.title+"</span>":"")+'<i class="layui-icon">'+(r?"&#xe605;":"&#xe618;")+"</i>","</div>"].join(""));d[0]&&d.remove(),l.after(f),a.call(this,f,c)})},radio:function(){var e="layui-form-radio",i=["&#xe643;","&#xe63f;"],a=c.find("input[type=radio]"),n=function(a){var n=t(this),s="layui-anim-scaleSpring";a.on("click",function(){var o=n[0].name,u=n.parents(r),c=n.attr("lay-filter"),d=u.find("input[name="+o.replace(/(\.|#|\[|\])/g,"\\$1")+"]");n[0].disabled||(layui.each(d,function(){var a=t(this).next("."+e);this.checked=!1,a.removeClass(e+"ed"),a.find(".layui-icon").removeClass(s).html(i[1])}),n[0].checked=!0,a.addClass(e+"ed"),a.find(".layui-icon").addClass(s).html(i[0]),layui.event.call(n[0],l,"radio("+c+")",{elem:n[0],value:n[0].value,othis:a}))})};a.each(function(a,l){var r=t(this),s=r.next("."+e),o=this.disabled;if("string"==typeof r.attr("lay-ignore"))return r.show();s[0]&&s.remove();var c=t(['<div class="layui-unselect '+e+(l.checked?" "+e+"ed":"")+(o?" layui-radio-disbaled "+u:"")+'">','<i class="layui-anim layui-icon">'+i[l.checked?0:1]+"</i>","<div>"+function(){var e=l.title||"";return"string"==typeof r.next().attr("lay-radio")&&(e=r.next().html(),r.next().remove()),e}()+"</div>","</div>"].join(""));r.after(c),n.call(this,c)})}};return e?d[e]?d[e]():a.error("不支持的"+e+"表单渲染"):layui.each(d,function(e,t){t()}),n};var d=function(){var e=t(this),a=f.config.verify,s=null,o="layui-form-danger",u={},c=e.parents(r),d=c.find("*[lay-verify]"),y=e.parents("form")[0],v=c.find("input,select,textarea"),h=e.attr("lay-filter");if(layui.each(d,function(e,l){var r=t(this),u=r.attr("lay-verify").split("|"),c=r.attr("lay-verType"),d=r.val();if(r.removeClass(o),layui.each(u,function(e,t){var u,f="",y="function"==typeof a[t];if(a[t]){var u=y?f=a[t](d,l):!a[t][0].test(d);if(f=f||a[t][1],u)return"tips"===c?i.tips(f,function(){return"string"==typeof r.attr("lay-ignore")||"select"!==l.tagName.toLowerCase()&&!/^checkbox|radio$/.test(l.type)?r:r.next()}(),{tips:1}):"alert"===c?i.alert(f,{title:"提示",shadeClose:!0}):i.msg(f,{icon:5,shift:6}),n.android||n.ios||l.focus(),r.addClass(o),s=!0}}),s)return s}),s)return!1;var p={};return layui.each(v,function(e,t){if(t.name=(t.name||"").replace(/^\s*|\s*&/,""),t.name){if(/^.*\[\]$/.test(t.name)){var i=t.name.match(/^(.*)\[\]$/g)[0];p[i]=0|p[i],t.name=t.name.replace(/^(.*)\[\]$/,"$1["+p[i]++ +"]")}/^checkbox|radio$/.test(t.type)&&!t.checked||(u[t.name]=t.value)}}),layui.event.call(this,l,"submit("+h+")",{elem:this,form:y,field:u})},f=new c,y=t(document),v=t(window);f.render(),y.on("reset",r,function(){var e=t(this).attr("lay-filter");setTimeout(function(){f.render(null,e)},50)}),y.on("submit",r,d).on("click","*[lay-submit]",d),e(l,f)});layui.define("jquery",function(e){"use strict";var o=layui.$,a=layui.hint(),i="layui-tree-enter",r=function(e){this.options=e},t={arrow:["&#xe623;","&#xe625;"],checkbox:["&#xe626;","&#xe627;"],radio:["&#xe62b;","&#xe62a;"],branch:["&#xe622;","&#xe624;"],leaf:"&#xe621;"};r.prototype.init=function(e){var o=this;e.addClass("layui-box layui-tree"),o.options.skin&&e.addClass("layui-tree-skin-"+o.options.skin),o.tree(e),o.on(e)},r.prototype.tree=function(e,a){var i=this,r=i.options,n=a||r.nodes;layui.each(n,function(a,n){var l=n.children&&n.children.length>0,c=o('<ul class="'+(n.spread?"layui-show":"")+'"></ul>'),s=o(["<li "+(n.spread?'data-spread="'+n.spread+'"':"")+">",function(){return l?'<i class="layui-icon layui-tree-spread">'+(n.spread?t.arrow[1]:t.arrow[0])+"</i>":""}(),function(){return r.check?'<i class="layui-icon layui-tree-check">'+("checkbox"===r.check?t.checkbox[0]:"radio"===r.check?t.radio[0]:"")+"</i>":""}(),function(){return'<a href="'+(n.href||"javascript:;")+'" '+(r.target&&n.href?'target="'+r.target+'"':"")+">"+('<i class="layui-icon layui-tree-'+(l?"branch":"leaf")+'">'+(l?n.spread?t.branch[1]:t.branch[0]:t.leaf)+"</i>")+("<cite>"+(n.name||"未命名")+"</cite></a>")}(),"</li>"].join(""));l&&(s.append(c),i.tree(c,n.children)),e.append(s),"function"==typeof r.click&&i.click(s,n),i.spread(s,n),r.drag&&i.drag(s,n)})},r.prototype.click=function(e,o){var a=this,i=a.options;e.children("a").on("click",function(e){layui.stope(e),i.click(o)})},r.prototype.spread=function(e,o){var a=this,i=(a.options,e.children(".layui-tree-spread")),r=e.children("ul"),n=e.children("a"),l=function(){e.data("spread")?(e.data("spread",null),r.removeClass("layui-show"),i.html(t.arrow[0]),n.find(".layui-icon").html(t.branch[0])):(e.data("spread",!0),r.addClass("layui-show"),i.html(t.arrow[1]),n.find(".layui-icon").html(t.branch[1]))};r[0]&&(i.on("click",l),n.on("dblclick",l))},r.prototype.on=function(e){var a=this,r=a.options,t="layui-tree-drag";e.find("i").on("selectstart",function(e){return!1}),r.drag&&o(document).on("mousemove",function(e){var i=a.move;if(i.from){var r=(i.to,o('<div class="layui-box '+t+'"></div>'));e.preventDefault(),o("."+t)[0]||o("body").append(r);var n=o("."+t)[0]?o("."+t):r;n.addClass("layui-show").html(i.from.elem.children("a").html()),n.css({left:e.pageX+10,top:e.pageY+10})}}).on("mouseup",function(){var e=a.move;e.from&&(e.from.elem.children("a").removeClass(i),e.to&&e.to.elem.children("a").removeClass(i),a.move={},o("."+t).remove())})},r.prototype.move={},r.prototype.drag=function(e,a){var r=this,t=(r.options,e.children("a")),n=function(){var t=o(this),n=r.move;n.from&&(n.to={item:a,elem:e},t.addClass(i))};t.on("mousedown",function(){var o=r.move;o.from={item:a,elem:e}}),t.on("mouseenter",n).on("mousemove",n).on("mouseleave",function(){var e=o(this),a=r.move;a.from&&(delete a.to,e.removeClass(i))})},e("tree",function(e){var i=new r(e=e||{}),t=o(e.elem);return t[0]?void i.init(t):a.error("layui.tree 没有找到"+e.elem+"元素")})});layui.define(["laytpl","laypage","layer","form"],function(e){"use strict";var t=layui.$,i=layui.laytpl,a=layui.laypage,l=layui.layer,n=layui.form,o=layui.hint(),r=layui.device(),d={config:{checkName:"LAY_CHECKED",indexName:"LAY_TABLE_INDEX"},cache:{},index:layui.table?layui.table.index+1e4:0,set:function(e){var i=this;return i.config=t.extend({},i.config,e),i},on:function(e,t){return layui.onevent.call(this,s,e,t)}},c=function(){var e=this,t=e.config,i=t.id;return i&&(c.config[i]=t),{reload:function(t){e.reload.call(e,t)},config:t}},s="table",u=".layui-table",h="layui-hide",f="layui-none",y="layui-table-view",p=".layui-table-header",m=".layui-table-body",v=".layui-table-main",g=".layui-table-fixed",x=".layui-table-fixed-l",b=".layui-table-fixed-r",k=".layui-table-tool",C=".layui-table-page",w=".layui-table-sort",N="layui-table-edit",T="layui-table-hover",F=function(e){var t='{{#if(item2.colspan){}} colspan="{{item2.colspan}}"{{#} if(item2.rowspan){}} rowspan="{{item2.rowspan}}"{{#}}}';return e=e||{},['<table cellspacing="0" cellpadding="0" border="0" class="layui-table" ','{{# if(d.data.skin){ }}lay-skin="{{d.data.skin}}"{{# } }} {{# if(d.data.size){ }}lay-size="{{d.data.size}}"{{# } }} {{# if(d.data.even){ }}lay-even{{# } }}>',"<thead>","{{# layui.each(d.data.cols, function(i1, item1){ }}","<tr>","{{# layui.each(item1, function(i2, item2){ }}",'{{# if(item2.fixed && item2.fixed !== "right"){ left = true; } }}','{{# if(item2.fixed === "right"){ right = true; } }}',function(){return e.fixed&&"right"!==e.fixed?'{{# if(item2.fixed && item2.fixed !== "right"){ }}':"right"===e.fixed?'{{# if(item2.fixed === "right"){ }}':""}(),'<th data-field="{{ item2.field||i2 }}" {{# if(item2.minWidth){ }}data-minwidth="{{item2.minWidth}}"{{# } }} '+t+' {{# if(item2.unresize){ }}data-unresize="true"{{# } }}>','<div class="layui-table-cell laytable-cell-',"{{# if(item2.colspan > 1){ }}","group","{{# } else { }}","{{d.index}}-{{item2.field || i2}}",'{{# if(item2.type !== "normal"){ }}'," laytable-cell-{{ item2.type }}","{{# } }}","{{# } }}",'" {{#if(item2.align){}}align="{{item2.align}}"{{#}}}>','{{# if(item2.type === "checkbox"){ }}','<input type="checkbox" name="layTableCheckbox" lay-skin="primary" lay-filter="layTableAllChoose" {{# if(item2[d.data.checkName]){ }}checked{{# }; }}>',"{{# } else { }}",'<span>{{item2.title||""}}</span>',"{{# if(!(item2.colspan > 1) && item2.sort){ }}",'<span class="layui-table-sort layui-inline"><i class="layui-edge layui-table-sort-asc"></i><i class="layui-edge layui-table-sort-desc"></i></span>',"{{# } }}","{{# } }}","</div>","</th>",e.fixed?"{{# }; }}":"","{{# }); }}","</tr>","{{# }); }}","</thead>","</table>"].join("")},W=['<table cellspacing="0" cellpadding="0" border="0" class="layui-table" ','{{# if(d.data.skin){ }}lay-skin="{{d.data.skin}}"{{# } }} {{# if(d.data.size){ }}lay-size="{{d.data.size}}"{{# } }} {{# if(d.data.even){ }}lay-even{{# } }}>',"<tbody></tbody>","</table>"].join(""),z=['<div class="layui-form layui-border-box {{d.VIEW_CLASS}}" lay-filter="LAY-table-{{d.index}}" style="{{# if(d.data.width){ }}width:{{d.data.width}}px;{{# } }} {{# if(d.data.height){ }}height:{{d.data.height}}px;{{# } }}">',"{{# if(d.data.toolbar){ }}",'<div class="layui-table-tool"></div>',"{{# } }}",'<div class="layui-table-box">',"{{# var left, right; }}",'<div class="layui-table-header">',F(),"</div>",'<div class="layui-table-body layui-table-main">',W,"</div>","{{# if(left){ }}",'<div class="layui-table-fixed layui-table-fixed-l">','<div class="layui-table-header">',F({fixed:!0}),"</div>",'<div class="layui-table-body">',W,"</div>","</div>","{{# }; }}","{{# if(right){ }}",'<div class="layui-table-fixed layui-table-fixed-r">','<div class="layui-table-header">',F({fixed:"right"}),'<div class="layui-table-mend"></div>',"</div>",'<div class="layui-table-body">',W,"</div>","</div>","{{# }; }}","</div>","{{# if(d.data.page){ }}",'<div class="layui-table-page">','<div id="layui-table-page{{d.index}}"></div>',"</div>","{{# } }}","<style>","{{# layui.each(d.data.cols, function(i1, item1){","layui.each(item1, function(i2, item2){ }}",".laytable-cell-{{d.index}}-{{item2.field||i2}}{ ","{{# if(item2.width){ }}","width: {{item2.width}}px;","{{# } }}"," }","{{# });","}); }}","</style>","</div>"].join(""),A=t(window),S=t(document),M=function(e){var i=this;i.index=++d.index,i.config=t.extend({},i.config,d.config,e),i.render()};M.prototype.config={limit:10,loading:!0,cellMinWidth:60,text:{none:"无数据"}},M.prototype.render=function(){var e=this,a=e.config;if(a.elem=t(a.elem),a.where=a.where||{},a.id=a.id||a.elem.attr("id"),a.request=t.extend({pageName:"page",limitName:"limit"},a.request),a.response=t.extend({statusName:"code",statusCode:0,msgName:"msg",dataName:"data",countName:"count"},a.response),"object"==typeof a.page&&(a.limit=a.page.limit||a.limit,a.limits=a.page.limits||a.limits,e.page=a.page.curr=a.page.curr||1,delete a.page.elem,delete a.page.jump),!a.elem[0])return e;e.setArea();var l=a.elem,n=l.next("."+y),o=e.elem=t(i(z).render({VIEW_CLASS:y,data:a,index:e.index}));if(a.index=e.index,n[0]&&n.remove(),l.after(o),e.layHeader=o.find(p),e.layMain=o.find(v),e.layBody=o.find(m),e.layFixed=o.find(g),e.layFixLeft=o.find(x),e.layFixRight=o.find(b),e.layTool=o.find(k),e.layPage=o.find(C),e.layTool.html(i(t(a.toolbar).html()||"").render(a)),a.height&&e.fullSize(),a.cols.length>1){var r=e.layFixed.find(p).find("th");r.height(e.layHeader.height()-1-parseFloat(r.css("padding-top"))-parseFloat(r.css("padding-bottom")))}e.pullData(e.page),e.events()},M.prototype.initOpts=function(e){var t=this,i=(t.config,{checkbox:48,space:15,numbers:40});e.checkbox&&(e.type="checkbox"),e.space&&(e.type="space"),e.type||(e.type="normal"),"normal"!==e.type&&(e.unresize=!0,e.width=e.width||i[e.type])},M.prototype.setArea=function(){var e=this,t=e.config,i=0,a=0,l=0,n=0,o=t.width||function(){var e=function(i){var a,l;i=i||t.elem.parent(),a=i.width();try{l="none"===i.css("display")}catch(n){}return!i[0]||a&&!l?a:e(i.parent())};return e()}();e.eachCols(function(){i++}),o-=function(){return"line"===t.skin||"nob"===t.skin?2:i+1}(),layui.each(t.cols,function(t,i){layui.each(i,function(t,l){var r;return l?(e.initOpts(l),r=l.width||0,void(l.colspan>1||(/\d+%$/.test(r)?l.width=r=Math.floor(parseFloat(r)/100*o):r||(l.width=r=0,a++),n+=r))):void i.splice(t,1)})}),e.autoColNums=a,o>n&&a&&(l=(o-n)/a),layui.each(t.cols,function(e,i){layui.each(i,function(e,i){var a=i.minWidth||t.cellMinWidth;i.colspan>1||0===i.width&&(i.width=Math.floor(l>=a?l:a))})}),t.height&&/^full-\d+$/.test(t.height)&&(e.fullHeightGap=t.height.split("-")[1],t.height=A.height()-e.fullHeightGap)},M.prototype.reload=function(e){var i=this;i.config.data&&i.config.data.constructor===Array&&delete i.config.data,i.config=t.extend({},i.config,e),i.render()},M.prototype.page=1,M.prototype.pullData=function(e,i){var a=this,n=a.config,o=n.request,r=n.response,d=function(){"object"==typeof n.initSort&&a.sort(n.initSort.field,n.initSort.type)};if(a.startTime=(new Date).getTime(),n.url){var c={};c[o.pageName]=e,c[o.limitName]=n.limit;var s=t.extend(c,n.where);n.contentType&&0==n.contentType.indexOf("application/json")&&(s=JSON.stringify(s)),t.ajax({type:n.method||"get",url:n.url,contentType:n.contentType,data:s,dataType:"json",headers:n.headers||{},success:function(t){t[r.statusName]!=r.statusCode?(a.renderForm(),a.layMain.html('<div class="'+f+'">'+(t[r.msgName]||"返回的数据状态异常")+"</div>")):(a.renderData(t,e,t[r.countName]),d(),n.time=(new Date).getTime()-a.startTime+" ms"),i&&l.close(i),"function"==typeof n.done&&n.done(t,e,t[r.countName])},error:function(e,t){a.layMain.html('<div class="'+f+'">数据接口请求异常</div>'),a.renderForm(),i&&l.close(i)}})}else if(n.data&&n.data.constructor===Array){var u={},h=e*n.limit-n.limit;u[r.dataName]=n.data.concat().splice(h,n.limit),u[r.countName]=n.data.length,a.renderData(u,e,n.data.length),d(),"function"==typeof n.done&&n.done(u,e,u[r.countName])}},M.prototype.eachCols=function(e){var i=t.extend(!0,[],this.config.cols),a=[],l=0;layui.each(i,function(e,t){layui.each(t,function(t,n){if(n.colspan>1){var o=0;l++,n.CHILD_COLS=[],layui.each(i[e+1],function(e,t){t.PARENT_COL||o==n.colspan||(t.PARENT_COL=l,n.CHILD_COLS.push(t),o+=t.colspan>1?t.colspan:1)})}n.PARENT_COL||a.push(n)})});var n=function(t){layui.each(t||a,function(t,i){return i.CHILD_COLS?n(i.CHILD_COLS):void e(t,i)})};n()},M.prototype.renderData=function(e,n,o,r){var c=this,s=c.config,u=e[s.response.dataName]||[],y=[],p=[],m=[],v=function(){return!r&&c.sortKey?c.sort(c.sortKey.field,c.sortKey.sort,!0):(layui.each(u,function(e,a){var l=[],o=[],u=[],h=e+s.limit*(n-1)+1;0!==a.length&&(r||(a[d.config.indexName]=e),c.eachCols(function(e,n){var r=n.field||e,f=a[r];c.getColElem(c.layHeader,r);if(void 0!==f&&null!==f||(f=""),!(n.colspan>1)){var y=['<td data-field="'+r+'" '+function(){var e=[];return n.edit&&e.push('data-edit="'+n.edit+'"'),n.align&&e.push('align="'+n.align+'"'),n.templet&&e.push('data-content="'+f+'"'),n.toolbar&&e.push('data-off="true"'),n.event&&e.push('lay-event="'+n.event+'"'),n.style&&e.push('style="'+n.style+'"'),n.minWidth&&e.push('data-minwidth="'+n.minWidth+'"'),e.join(" ")}()+">",'<div class="layui-table-cell laytable-cell-'+function(){var e=s.index+"-"+r;return"normal"===n.type?e:e+" laytable-cell-"+n.type}()+'">'+function(){var e=t.extend(!0,{LAY_INDEX:h},a);return"checkbox"===n.type?'<input type="checkbox" name="layTableCheckbox" lay-skin="primary" '+function(){var t=d.config.checkName;return n[t]?(a[t]=n[t],n[t]?"checked":""):e[t]?"checked":""}()+">":"numbers"===n.type?h:n.toolbar?i(t(n.toolbar).html()||"").render(e):n.templet?function(){return"function"==typeof n.templet?n.templet(e):i(t(n.templet).html()||String(f)).render(e)}():f}(),"</div></td>"].join("");l.push(y),n.fixed&&"right"!==n.fixed&&o.push(y),"right"===n.fixed&&u.push(y)}}),y.push('<tr data-index="'+e+'">'+l.join("")+"</tr>"),p.push('<tr data-index="'+e+'">'+o.join("")+"</tr>"),m.push('<tr data-index="'+e+'">'+u.join("")+"</tr>"))}),c.layBody.scrollTop(0),c.layMain.find("."+f).remove(),c.layMain.find("tbody").html(y.join("")),c.layFixLeft.find("tbody").html(p.join("")),c.layFixRight.find("tbody").html(m.join("")),c.renderForm(),c.syncCheckAll(),c.haveInit?c.scrollPatch():setTimeout(function(){c.scrollPatch()},50),c.haveInit=!0,void l.close(c.tipsIndex))};return c.key=s.id||s.index,d.cache[c.key]=u,c.layPage[0===u.length&&1==n?"addClass":"removeClass"](h),r?v():0===u.length?(c.renderForm(),c.layFixed.remove(),c.layMain.find("tbody").html(""),c.layMain.find("."+f).remove(),c.layMain.append('<div class="'+f+'">'+s.text.none+"</div>")):(v(),void(s.page&&(s.page=t.extend({elem:"layui-table-page"+s.index,count:o,limit:s.limit,limits:s.limits||[10,20,30,40,50,60,70,80,90],groups:3,layout:["prev","page","next","skip","count","limit"],prev:'<i class="layui-icon">&#xe603;</i>',next:'<i class="layui-icon">&#xe602;</i>',jump:function(e,t){t||(c.page=e.curr,s.limit=e.limit,c.pullData(e.curr,c.loading()))}},s.page),s.page.count=o,a.render(s.page))))},M.prototype.getColElem=function(e,t){var i=this,a=i.config;return e.eq(0).find(".laytable-cell-"+(a.index+"-"+t)+":eq(0)")},M.prototype.renderForm=function(e){n.render(e,"LAY-table-"+this.index)},M.prototype.sort=function(e,i,a,l){var n,r,c=this,u={},h=c.config,f=h.elem.attr("lay-filter"),y=d.cache[c.key];"string"==typeof e&&c.layHeader.find("th").each(function(i,a){var l=t(this),o=l.data("field");if(o===e)return e=l,n=o,!1});try{var n=n||e.data("field");if(c.sortKey&&!a&&n===c.sortKey.field&&i===c.sortKey.sort)return;var p=c.layHeader.find("th .laytable-cell-"+h.index+"-"+n).find(w);c.layHeader.find("th").find(w).removeAttr("lay-sort"),p.attr("lay-sort",i||null),c.layFixed.find("th")}catch(m){return o.error("Table modules: Did not match to field")}c.sortKey={field:n,sort:i},"asc"===i?r=layui.sort(y,n):"desc"===i?r=layui.sort(y,n,!0):(r=layui.sort(y,d.config.indexName),delete c.sortKey),u[h.response.dataName]=r,c.renderData(u,c.page,c.count,!0),l&&layui.event.call(e,s,"sort("+f+")",{field:n,type:i})},M.prototype.loading=function(){var e=this,t=e.config;if(t.loading&&t.url)return l.msg("数据请求中",{icon:16,offset:[e.elem.offset().top+e.elem.height()/2-35-A.scrollTop()+"px",e.elem.offset().left+e.elem.width()/2-90-A.scrollLeft()+"px"],time:-1,anim:-1,fixed:!1})},M.prototype.setCheckData=function(e,t){var i=this,a=i.config,l=d.cache[i.key];l[e]&&l[e].constructor!==Array&&(l[e][a.checkName]=t)},M.prototype.syncCheckAll=function(){var e=this,t=e.config,i=e.layHeader.find('input[name="layTableCheckbox"]'),a=function(i){return e.eachCols(function(e,a){"checkbox"===a.type&&(a[t.checkName]=i)}),i};i[0]&&(d.checkStatus(e.key).isAll?(i[0].checked||(i.prop("checked",!0),e.renderForm("checkbox")),a(!0)):(i[0].checked&&(i.prop("checked",!1),e.renderForm("checkbox")),a(!1)))},M.prototype.getCssRule=function(e,t){var i=this,a=i.elem.find("style")[0],l=a.sheet||a.styleSheet||{},n=l.cssRules||l.rules;layui.each(n,function(a,l){if(l.selectorText===".laytable-cell-"+i.index+"-"+e)return t(l),!0})},M.prototype.fullSize=function(){var e,t=this,i=t.config,a=i.height;t.fullHeightGap&&(a=A.height()-t.fullHeightGap,a<135&&(a=135),t.elem.css("height",a)),e=parseFloat(a)-parseFloat(t.layHeader.height())-1,i.toolbar&&(e-=t.layTool.outerHeight()),i.page&&(e=e-t.layPage.outerHeight()-1),t.layMain.css("height",e)},M.prototype.getScrollWidth=function(e){var t=0;return e?t=e.offsetWidth-e.clientWidth:(e=document.createElement("div"),e.style.width="100px",e.style.height="100px",e.style.overflowY="scroll",document.body.appendChild(e),t=e.offsetWidth-e.clientWidth,document.body.removeChild(e)),t},M.prototype.scrollPatch=function(){var e=this,i=e.layMain.children("table"),a=e.layMain.width()-e.layMain.prop("clientWidth"),l=e.layMain.height()-e.layMain.prop("clientHeight"),n=e.getScrollWidth(e.layMain[0]),o=i.outerWidth()-e.layMain.width();if(e.autoColNums&&o<5&&!e.scrollPatchWStatus){var r=e.layHeader.eq(0).find("thead th:last-child"),d=r.data("field");e.getCssRule(d,function(t){var i=t.style.width||r.outerWidth();t.style.width=parseFloat(i)-n-o+"px",e.layMain.height()-e.layMain.prop("clientHeight")>0&&(t.style.width=parseFloat(t.style.width)-1+"px"),e.scrollPatchWStatus=!0})}if(a&&l){if(!e.elem.find(".layui-table-patch")[0]){var c=t('<th class="layui-table-patch"><div class="layui-table-cell"></div></th>');c.find("div").css({width:a}),e.layHeader.eq(0).find("thead tr").append(c)}}else e.layHeader.eq(0).find(".layui-table-patch").remove();var s=e.layMain.height(),u=s-l;e.layFixed.find(m).css("height",i.height()>u?u:"auto"),e.layFixRight[o>0?"removeClass":"addClass"](h),e.layFixRight.css("right",a-1)},M.prototype.events=function(){var e,a=this,n=a.config,o=t("body"),c={},u=a.layHeader.find("th"),h=".layui-table-cell",f=n.elem.attr("lay-filter");u.on("mousemove",function(e){var i=t(this),a=i.offset().left,l=e.clientX-a;i.attr("colspan")>1||i.data("unresize")||c.resizeStart||(c.allowResize=i.width()-l<=10,o.css("cursor",c.allowResize?"col-resize":""))}).on("mouseleave",function(){t(this);c.resizeStart||o.css("cursor","")}).on("mousedown",function(e){var i=t(this);if(c.allowResize){var l=i.data("field");e.preventDefault(),c.resizeStart=!0,c.offset=[e.clientX,e.clientY],a.getCssRule(l,function(e){var t=e.style.width||i.outerWidth();c.rule=e,c.ruleWidth=parseFloat(t),c.minWidth=i.data("minwidth")||n.cellMinWidth})}}),S.on("mousemove",function(t){if(c.resizeStart){if(t.preventDefault(),c.rule){var i=c.ruleWidth+t.clientX-c.offset[0];i<c.minWidth&&(i=c.minWidth),c.rule.style.width=i+"px",l.close(a.tipsIndex)}e=1}}).on("mouseup",function(t){c.resizeStart&&(c={},o.css("cursor",""),a.scrollPatch()),2===e&&(e=null)}),u.on("click",function(){var i,l=t(this),n=l.find(w),o=n.attr("lay-sort");return n[0]&&1!==e?(i="asc"===o?"desc":"desc"===o?null:"asc",void a.sort(l,i,null,!0)):e=2}).find(w+" .layui-edge ").on("click",function(e){var i=t(this),l=i.index(),n=i.parents("th").eq(0).data("field");layui.stope(e),0===l?a.sort(n,"asc",null,!0):a.sort(n,"desc",null,!0)}),a.elem.on("click",'input[name="layTableCheckbox"]+',function(){var e=t(this).prev(),i=a.layBody.find('input[name="layTableCheckbox"]'),l=e.parents("tr").eq(0).data("index"),n=e[0].checked,o="layTableAllChoose"===e.attr("lay-filter");o?(i.each(function(e,t){t.checked=n,a.setCheckData(e,n)}),a.syncCheckAll(),a.renderForm("checkbox")):(a.setCheckData(l,n),a.syncCheckAll()),layui.event.call(this,s,"checkbox("+f+")",{checked:n,data:d.cache[a.key]?d.cache[a.key][l]||{}:{},type:o?"all":"one"})}),a.layBody.on("mouseenter","tr",function(){var e=t(this),i=e.index();a.layBody.find("tr:eq("+i+")").addClass(T)}).on("mouseleave","tr",function(){var e=t(this),i=e.index();a.layBody.find("tr:eq("+i+")").removeClass(T)}),a.layBody.on("change","."+N,function(){var e=t(this),i=this.value,l=e.parent().data("field"),n=e.parents("tr").eq(0).data("index"),o=d.cache[a.key][n];o[l]=i,layui.event.call(this,s,"edit("+f+")",{value:i,data:o,field:l})}).on("blur","."+N,function(){var e,l=t(this),n=l.parent().data("field"),o=l.parents("tr").eq(0).data("index"),r=d.cache[a.key][o];a.eachCols(function(t,i){i.field==n&&i.templet&&(e=i.templet)}),l.siblings(h).html(e?i(t(e).html()||this.value).render(r):this.value),l.parent().data("content",this.value),l.remove()}),a.layBody.on("click","td",function(){var e=t(this),i=(e.data("field"),e.data("edit")),o=e.children(h);if(l.close(a.tipsIndex),!e.data("off"))if(i)if("select"===i);else{var d=t('<input class="layui-input '+N+'">');d[0].value=e.data("content")||o.text(),e.find("."+N)[0]||e.append(d),d.focus()}else o.find(".layui-form-switch,.layui-form-checkbox")[0]||Math.round(o.prop("scrollWidth"))>Math.round(o.outerWidth())&&(a.tipsIndex=l.tips(['<div class="layui-table-tips-main" style="margin-top: -'+(o.height()+16)+"px;"+function(){return"sm"===n.size?"padding: 4px 15px; font-size: 12px;":"lg"===n.size?"padding: 14px 15px;":""}()+'">',o.html(),"</div>",'<i class="layui-icon layui-table-tips-c">&#x1006;</i>'].join(""),o[0],{tips:[3,""],time:-1,anim:-1,maxWidth:r.ios||r.android?300:600,isOutAnim:!1,skin:"layui-table-tips",success:function(e,t){e.find(".layui-table-tips-c").on("click",function(){l.close(t)})}}))}),a.layBody.on("click","*[lay-event]",function(){var e=t(this),l=e.parents("tr").eq(0).data("index"),n=a.layBody.find('tr[data-index="'+l+'"]'),o="layui-table-click",r=d.cache[a.key][l];layui.event.call(this,s,"tool("+f+")",{data:d.clearCacheKey(r),event:e.attr("lay-event"),tr:n,del:function(){d.cache[a.key][l]=[],n.remove(),a.scrollPatch()},update:function(e){e=e||{},layui.each(e,function(e,l){if(e in r){var o,d=n.children('td[data-field="'+e+'"]');r[e]=l,a.eachCols(function(t,i){i.field==e&&i.templet&&(o=i.templet)}),d.children(h).html(o?i(t(o).html()||l).render(r):l),d.data("content",l)}})}}),n.addClass(o).siblings("tr").removeClass(o)}),a.layMain.on("scroll",function(){var e=t(this),i=e.scrollLeft(),n=e.scrollTop();a.layHeader.scrollLeft(i),a.layFixed.find(m).scrollTop(n),l.close(a.tipsIndex)}),A.on("resize",function(){a.fullSize(),a.scrollPatch()})},d.init=function(e,i){i=i||{};var a=this,l=t(e?'table[lay-filter="'+e+'"]':u+"[lay-data]"),n="Table element property lay-data configuration item has a syntax error: ";return l.each(function(){var a=t(this),l=a.attr("lay-data");try{l=new Function("return "+l)()}catch(r){o.error(n+l)}var c=[],s=t.extend({elem:this,cols:[],data:[],skin:a.attr("lay-skin"),size:a.attr("lay-size"),even:"string"==typeof a.attr("lay-even")},d.config,i,l);e&&a.hide(),a.find("thead>tr").each(function(e){s.cols[e]=[],t(this).children().each(function(i){var a=t(this),l=a.attr("lay-data");try{l=new Function("return "+l)()}catch(r){return o.error(n+l)}var d=t.extend({title:a.text(),colspan:a.attr("colspan")||0,rowspan:a.attr("rowspan")||0},l);d.colspan<2&&c.push(d),s.cols[e].push(d)})}),a.find("tbody>tr").each(function(e){var i=t(this),a={};i.children("td").each(function(e,i){var l=t(this),n=l.data("field");if(n)return a[n]=l.html()}),layui.each(c,function(e,t){var l=i.children("td").eq(e);a[t.field]=l.html()}),s.data[e]=a}),d.render(s)}),a},d.checkStatus=function(e){var t=0,i=0,a=[],l=d.cache[e]||[];return layui.each(l,function(e,l){return l.constructor===Array?void i++:void(l[d.config.checkName]&&(t++,a.push(d.clearCacheKey(l))))}),{data:a,isAll:!!l.length&&t===l.length-i}},c.config={},d.reload=function(e,i){var a=c.config[e];return i=i||{},a?(i.data&&i.data.constructor===Array&&delete a.data,d.render(t.extend(!0,{},a,i))):o.error("The ID option was not found in the table instance")},d.render=function(e){var t=new M(e);return c.call(t)},d.clearCacheKey=function(e){return e=t.extend({},e),delete e[d.config.checkName],delete e[d.config.indexName],e},d.init(),e(s,d)});layui.define("jquery",function(e){"use strict";var i=layui.$,n=(layui.hint(),layui.device(),{config:{},set:function(e){var n=this;return n.config=i.extend({},n.config,e),n},on:function(e,i){return layui.onevent.call(this,t,e,i)}}),t="carousel",a="layui-this",l=">*[carousel-item]>*",o="layui-carousel-left",r="layui-carousel-right",d="layui-carousel-prev",s="layui-carousel-next",u="layui-carousel-arrow",c="layui-carousel-ind",m=function(e){var t=this;t.config=i.extend({},t.config,n.config,e),t.render()};m.prototype.config={width:"600px",height:"280px",full:!1,arrow:"hover",indicator:"inside",autoplay:!0,interval:3e3,anim:"",trigger:"click",index:0},m.prototype.render=function(){var e=this,n=e.config;n.elem=i(n.elem),n.elem[0]&&(e.elemItem=n.elem.find(l),n.index<0&&(n.index=0),n.index>=e.elemItem.length&&(n.index=e.elemItem.length-1),n.interval<800&&(n.interval=800),n.full?n.elem.css({position:"fixed",width:"100%",height:"100%",zIndex:9999}):n.elem.css({width:n.width,height:n.height}),n.elem.attr("lay-anim",n.anim),e.elemItem.eq(n.index).addClass(a),e.elemItem.length<=1||(e.indicator(),e.arrow(),e.autoplay(),e.events()))},m.prototype.reload=function(e){var n=this;clearInterval(n.timer),n.config=i.extend({},n.config,e),n.render()},m.prototype.prevIndex=function(){var e=this,i=e.config,n=i.index-1;return n<0&&(n=e.elemItem.length-1),n},m.prototype.nextIndex=function(){var e=this,i=e.config,n=i.index+1;return n>=e.elemItem.length&&(n=0),n},m.prototype.addIndex=function(e){var i=this,n=i.config;e=e||1,n.index=n.index+e,n.index>=i.elemItem.length&&(n.index=0)},m.prototype.subIndex=function(e){var i=this,n=i.config;e=e||1,n.index=n.index-e,n.index<0&&(n.index=i.elemItem.length-1)},m.prototype.autoplay=function(){var e=this,i=e.config;i.autoplay&&(e.timer=setInterval(function(){e.slide()},i.interval))},m.prototype.arrow=function(){var e=this,n=e.config,t=i(['<button class="layui-icon '+u+'" lay-type="sub">'+("updown"===n.anim?"&#xe619;":"&#xe603;")+"</button>",'<button class="layui-icon '+u+'" lay-type="add">'+("updown"===n.anim?"&#xe61a;":"&#xe602;")+"</button>"].join(""));n.elem.attr("lay-arrow",n.arrow),n.elem.find("."+u)[0]&&n.elem.find("."+u).remove(),n.elem.append(t),t.on("click",function(){var n=i(this),t=n.attr("lay-type");e.slide(t)})},m.prototype.indicator=function(){var e=this,n=e.config,t=e.elemInd=i(['<div class="'+c+'"><ul>',function(){var i=[];return layui.each(e.elemItem,function(e){i.push("<li"+(n.index===e?' class="layui-this"':"")+"></li>")}),i.join("")}(),"</ul></div>"].join(""));n.elem.attr("lay-indicator",n.indicator),n.elem.find("."+c)[0]&&n.elem.find("."+c).remove(),n.elem.append(t),"updown"===n.anim&&t.css("margin-top",-(t.height()/2)),t.find("li").on("hover"===n.trigger?"mouseover":n.trigger,function(){var t=i(this),a=t.index();a>n.index?e.slide("add",a-n.index):a<n.index&&e.slide("sub",n.index-a)})},m.prototype.slide=function(e,i){var n=this,l=n.elemItem,u=n.config,c=u.index,m=u.elem.attr("lay-filter");n.haveSlide||("sub"===e?(n.subIndex(i),l.eq(u.index).addClass(d),setTimeout(function(){l.eq(c).addClass(r),l.eq(u.index).addClass(r)},50)):(n.addIndex(i),l.eq(u.index).addClass(s),setTimeout(function(){l.eq(c).addClass(o),l.eq(u.index).addClass(o)},50)),setTimeout(function(){l.removeClass(a+" "+d+" "+s+" "+o+" "+r),l.eq(u.index).addClass(a),n.haveSlide=!1},300),n.elemInd.find("li").eq(u.index).addClass(a).siblings().removeClass(a),n.haveSlide=!0,layui.event.call(this,t,"change("+m+")",{index:u.index,prevIndex:c,item:l.eq(u.index)}))},m.prototype.events=function(){var e=this,i=e.config;i.elem.data("haveEvents")||(i.elem.on("mouseenter",function(){clearInterval(e.timer)}).on("mouseleave",function(){e.autoplay()}),i.elem.data("haveEvents",!0))},n.render=function(e){var i=new m(e);return i},e(t,n)});layui.define("jquery",function(e){"use strict";var t=layui.$,i={fixbar:function(e){var i,o,a="layui-fixbar",r="layui-fixbar-top",n=t(document),l=t("body");e=t.extend({showHeight:200},e),e.bar1=e.bar1===!0?"&#xe606;":e.bar1,e.bar2=e.bar2===!0?"&#xe607;":e.bar2,e.bgcolor=e.bgcolor?"background-color:"+e.bgcolor:"";var c=[e.bar1,e.bar2,"&#xe604;"],g=t(['<ul class="'+a+'">',e.bar1?'<li class="layui-icon" lay-type="bar1" style="'+e.bgcolor+'">'+c[0]+"</li>":"",e.bar2?'<li class="layui-icon" lay-type="bar2" style="'+e.bgcolor+'">'+c[1]+"</li>":"",'<li class="layui-icon '+r+'" lay-type="top" style="'+e.bgcolor+'">'+c[2]+"</li>","</ul>"].join("")),s=g.find("."+r),u=function(){var t=n.scrollTop();t>=e.showHeight?i||(s.show(),i=1):i&&(s.hide(),i=0)};t("."+a)[0]||("object"==typeof e.css&&g.css(e.css),l.append(g),u(),g.find("li").on("click",function(){var i=t(this),o=i.attr("lay-type");"top"===o&&t("html,body").animate({scrollTop:0},200),e.click&&e.click.call(this,o)}),n.on("scroll",function(){clearTimeout(o),o=setTimeout(function(){u()},100)}))},countdown:function(e,t,i){var o=this,a="function"==typeof t,r=new Date(e).getTime(),n=new Date(!t||a?(new Date).getTime():t).getTime(),l=r-n,c=[Math.floor(l/864e5),Math.floor(l/36e5)%24,Math.floor(l/6e4)%60,Math.floor(l/1e3)%60];a&&(i=t);var g=setTimeout(function(){o.countdown(e,n+1e3,i)},1e3);return i&&i(l>0?c:[0,0,0,0],t,g),l<=0&&clearTimeout(g),g},timeAgo:function(e,t){var i=this,o=[[],[]],a=(new Date).getTime()-new Date(e).getTime();return a>6912e5?(a=new Date(e),o[0][0]=i.digit(a.getFullYear(),4),o[0][1]=i.digit(a.getMonth()+1),o[0][2]=i.digit(a.getDate()),t||(o[1][0]=i.digit(a.getHours()),o[1][1]=i.digit(a.getMinutes()),o[1][2]=i.digit(a.getSeconds())),o[0].join("-")+" "+o[1].join(":")):a>=864e5?(a/1e3/60/60/24|0)+"天前":a>=36e5?(a/1e3/60/60|0)+"小时前":a>=12e4?(a/1e3/60|0)+"分钟前":a<0?"未来":"刚刚"},digit:function(e,t){var i="";e=String(e),t=t||2;for(var o=e.length;o<t;o++)i+="0";return e<Math.pow(10,t)?i+(0|e):e},toDateString:function(e,t){var i=this,o=new Date(e||new Date),a=[i.digit(o.getFullYear(),4),i.digit(o.getMonth()+1),i.digit(o.getDate())],r=[i.digit(o.getHours()),i.digit(o.getMinutes()),i.digit(o.getSeconds())];return t=t||"yyyy-MM-dd HH:mm:ss",t.replace(/yyyy/g,a[0]).replace(/MM/g,a[1]).replace(/dd/g,a[2]).replace(/HH/g,r[0]).replace(/mm/g,r[1]).replace(/ss/g,r[2])}};e("util",i)});layui.define("jquery",function(e){"use strict";var l=layui.$,o=function(e){},t='<i class="layui-anim layui-anim-rotate layui-anim-loop layui-icon ">&#xe63e;</i>';o.prototype.load=function(e){var o,i,n,r,a=this,c=0;e=e||{};var f=l(e.elem);if(f[0]){var m=l(e.scrollElem||document),u=e.mb||50,s=!("isAuto"in e)||e.isAuto,v=e.end||"没有更多了",y=e.scrollElem&&e.scrollElem!==document,d="<cite>加载更多</cite>",h=l('<div class="layui-flow-more"><a href="javascript:;">'+d+"</a></div>");f.find(".layui-flow-more")[0]||f.append(h);var p=function(e,t){e=l(e),h.before(e),t=0==t||null,t?h.html(v):h.find("a").html(d),i=t,o=null,n&&n()},g=function(){o=!0,h.find("a").html(t),"function"==typeof e.done&&e.done(++c,p)};if(g(),h.find("a").on("click",function(){l(this);i||o||g()}),e.isLazyimg)var n=a.lazyimg({elem:e.elem+" img",scrollElem:e.scrollElem});return s?(m.on("scroll",function(){var e=l(this),t=e.scrollTop();r&&clearTimeout(r),i||(r=setTimeout(function(){var i=y?e.height():l(window).height(),n=y?e.prop("scrollHeight"):document.documentElement.scrollHeight;n-t-i<=u&&(o||g())},100))}),a):a}},o.prototype.lazyimg=function(e){var o,t=this,i=0;e=e||{};var n=l(e.scrollElem||document),r=e.elem||"img",a=e.scrollElem&&e.scrollElem!==document,c=function(e,l){var o=n.scrollTop(),r=o+l,c=a?function(){return e.offset().top-n.offset().top+o}():e.offset().top;if(c>=o&&c<=r&&!e.attr("src")){var m=e.attr("lay-src");layui.img(m,function(){var l=t.lazyimg.elem.eq(i);e.attr("src",m).removeAttr("lay-src"),l[0]&&f(l),i++})}},f=function(e,o){var f=a?(o||n).height():l(window).height(),m=n.scrollTop(),u=m+f;if(t.lazyimg.elem=l(r),e)c(e,f);else for(var s=0;s<t.lazyimg.elem.length;s++){var v=t.lazyimg.elem.eq(s),y=a?function(){return v.offset().top-n.offset().top+m}():v.offset().top;if(c(v,f),i=s,y>u)break}};if(f(),!o){var m;n.on("scroll",function(){var e=l(this);m&&clearTimeout(m),m=setTimeout(function(){f(null,e)},50)}),o=!0}return f},e("flow",new o)});layui.define(["layer","form"],function(t){"use strict";var e=layui.$,i=layui.layer,a=layui.form,l=(layui.hint(),layui.device()),n="layedit",o="layui-show",r="layui-disabled",c=function(){var t=this;t.index=0,t.config={tool:["strong","italic","underline","del","|","left","center","right","|","link","unlink","face","image"],hideTool:[],height:280}};c.prototype.set=function(t){var i=this;return e.extend(!0,i.config,t),i},c.prototype.on=function(t,e){return layui.onevent(n,t,e)},c.prototype.build=function(t,i){i=i||{};var a=this,n=a.config,r="layui-layedit",c=e("string"==typeof t?"#"+t:t),u="LAY_layedit_"+ ++a.index,d=c.next("."+r),y=e.extend({},n,i),f=function(){var t=[],e={};return layui.each(y.hideTool,function(t,i){e[i]=!0}),layui.each(y.tool,function(i,a){C[a]&&!e[a]&&t.push(C[a])}),t.join("")}(),m=e(['<div class="'+r+'">','<div class="layui-unselect layui-layedit-tool">'+f+"</div>",'<div class="layui-layedit-iframe">','<iframe id="'+u+'" name="'+u+'" textarea="'+t+'" frameborder="0"></iframe>',"</div>","</div>"].join(""));return l.ie&&l.ie<8?c.removeClass("layui-hide").addClass(o):(d[0]&&d.remove(),s.call(a,m,c[0],y),c.addClass("layui-hide").after(m),a.index)},c.prototype.getContent=function(t){var e=u(t);if(e[0])return d(e[0].document.body.innerHTML)},c.prototype.getText=function(t){var i=u(t);if(i[0])return e(i[0].document.body).text()},c.prototype.setContent=function(t,i,a){var l=u(t);l[0]&&(a?e(l[0].document.body).append(i):e(l[0].document.body).html(i),layedit.sync(t))},c.prototype.sync=function(t){var i=u(t);if(i[0]){var a=e("#"+i[1].attr("textarea"));a.val(d(i[0].document.body.innerHTML))}},c.prototype.getSelection=function(t){var e=u(t);if(e[0]){var i=m(e[0].document);return document.selection?i.text:i.toString()}};var s=function(t,i,a){var l=this,n=t.find("iframe");n.css({height:a.height}).on("load",function(){var o=n.contents(),r=n.prop("contentWindow"),c=o.find("head"),s=e(["<style>","*{margin: 0; padding: 0;}","body{padding: 10px; line-height: 20px; overflow-x: hidden; word-wrap: break-word; font: 14px Helvetica Neue,Helvetica,PingFang SC,Microsoft YaHei,Tahoma,Arial,sans-serif; -webkit-box-sizing: border-box !important; -moz-box-sizing: border-box !important; box-sizing: border-box !important;}","a{color:#01AAED; text-decoration:none;}a:hover{color:#c00}","p{margin-bottom: 10px;}","img{display: inline-block; border: none; vertical-align: middle;}","pre{margin: 10px 0; padding: 10px; line-height: 20px; border: 1px solid #ddd; border-left-width: 6px; background-color: #F2F2F2; color: #333; font-family: Courier New; font-size: 12px;}","</style>"].join("")),u=o.find("body");c.append(s),u.attr("contenteditable","true").css({"min-height":a.height}).html(i.value||""),y.apply(l,[r,n,i,a]),g.call(l,r,t,a)})},u=function(t){var i=e("#LAY_layedit_"+t),a=i.prop("contentWindow");return[a,i]},d=function(t){return 8==l.ie&&(t=t.replace(/<.+>/g,function(t){return t.toLowerCase()})),t},y=function(t,a,n,o){var r=t.document,c=e(r.body);c.on("keydown",function(t){var e=t.keyCode;if(13===e){var a=m(r),l=p(a),n=l.parentNode;if("pre"===n.tagName.toLowerCase()){if(t.shiftKey)return;return i.msg("请暂时用shift+enter"),!1}r.execCommand("formatBlock",!1,"<p>")}}),e(n).parents("form").on("submit",function(){var t=c.html();8==l.ie&&(t=t.replace(/<.+>/g,function(t){return t.toLowerCase()})),n.value=t}),c.on("paste",function(e){r.execCommand("formatBlock",!1,"<p>"),setTimeout(function(){f.call(t,c),n.value=c.html()},100)})},f=function(t){var i=this;i.document;t.find("*[style]").each(function(){var t=this.style.textAlign;this.removeAttribute("style"),e(this).css({"text-align":t||""})}),t.find("table").addClass("layui-table"),t.find("script,link").remove()},m=function(t){return t.selection?t.selection.createRange():t.getSelection().getRangeAt(0)},p=function(t){return t.endContainer||t.parentElement().childNodes[0]},v=function(t,i,a){var l=this.document,n=document.createElement(t);for(var o in i)n.setAttribute(o,i[o]);if(n.removeAttribute("text"),l.selection){var r=a.text||i.text;if("a"===t&&!r)return;r&&(n.innerHTML=r),a.pasteHTML(e(n).prop("outerHTML")),a.select()}else{var r=a.toString()||i.text;if("a"===t&&!r)return;r&&(n.innerHTML=r),a.deleteContents(),a.insertNode(n)}},h=function(t,i){var a=this.document,l="layedit-tool-active",n=p(m(a)),o=function(e){return t.find(".layedit-tool-"+e)};i&&i[i.hasClass(l)?"removeClass":"addClass"](l),t.find(">i").removeClass(l),o("unlink").addClass(r),e(n).parents().each(function(){var t=this.tagName.toLowerCase(),e=this.style.textAlign;"b"!==t&&"strong"!==t||o("b").addClass(l),"i"!==t&&"em"!==t||o("i").addClass(l),"u"===t&&o("u").addClass(l),"strike"===t&&o("d").addClass(l),"p"===t&&("center"===e?o("center").addClass(l):"right"===e?o("right").addClass(l):o("left").addClass(l)),"a"===t&&(o("link").addClass(l),o("unlink").removeClass(r))})},g=function(t,a,l){var n=t.document,o=e(n.body),c={link:function(i){var a=p(i),l=e(a).parent();b.call(o,{href:l.attr("href"),target:l.attr("target")},function(e){var a=l[0];"A"===a.tagName?a.href=e.url:v.call(t,"a",{target:e.target,href:e.url,text:e.url},i)})},unlink:function(t){n.execCommand("unlink")},face:function(e){x.call(this,function(i){v.call(t,"img",{src:i.src,alt:i.alt},e)})},image:function(a){var n=this;layui.use("upload",function(o){var r=l.uploadImage||{};o.render({url:r.url,method:r.type,elem:e(n).find("input")[0],done:function(e){0==e.code?(e.data=e.data||{},v.call(t,"img",{src:e.data.src,alt:e.data.title},a)):i.msg(e.msg||"上传失败")}})})},code:function(e){k.call(o,function(i){v.call(t,"pre",{text:i.code,"lay-lang":i.lang},e)})},help:function(){i.open({type:2,title:"帮助",area:["600px","380px"],shadeClose:!0,shade:.1,skin:"layui-layer-msg",content:["http://www.layui.com/about/layedit/help.html","no"]})}},s=a.find(".layui-layedit-tool"),u=function(){var i=e(this),a=i.attr("layedit-event"),l=i.attr("lay-command");if(!i.hasClass(r)){o.focus();var u=m(n);u.commonAncestorContainer;l?(n.execCommand(l),/justifyLeft|justifyCenter|justifyRight/.test(l)&&n.execCommand("formatBlock",!1,"<p>"),setTimeout(function(){o.focus()},10)):c[a]&&c[a].call(this,u),h.call(t,s,i)}},d=/image/;s.find(">i").on("mousedown",function(){var t=e(this),i=t.attr("layedit-event");d.test(i)||u.call(this)}).on("click",function(){var t=e(this),i=t.attr("layedit-event");d.test(i)&&u.call(this)}),o.on("click",function(){h.call(t,s),i.close(x.index)})},b=function(t,e){var l=this,n=i.open({type:1,id:"LAY_layedit_link",area:"350px",shade:.05,shadeClose:!0,moveType:1,title:"超链接",skin:"layui-layer-msg",content:['<ul class="layui-form" style="margin: 15px;">','<li class="layui-form-item">','<label class="layui-form-label" style="width: 60px;">URL</label>','<div class="layui-input-block" style="margin-left: 90px">','<input name="url" lay-verify="url" value="'+(t.href||"")+'" autofocus="true" autocomplete="off" class="layui-input">',"</div>","</li>",'<li class="layui-form-item">','<label class="layui-form-label" style="width: 60px;">打开方式</label>','<div class="layui-input-block" style="margin-left: 90px">','<input type="radio" name="target" value="_self" class="layui-input" title="当前窗口"'+("_self"!==t.target&&t.target?"":"checked")+">",'<input type="radio" name="target" value="_blank" class="layui-input" title="新窗口" '+("_blank"===t.target?"checked":"")+">","</div>","</li>",'<li class="layui-form-item" style="text-align: center;">','<button type="button" lay-submit lay-filter="layedit-link-yes" class="layui-btn"> 确定 </button>','<button style="margin-left: 20px;" type="button" class="layui-btn layui-btn-primary"> 取消 </button>',"</li>","</ul>"].join(""),success:function(t,n){var o="submit(layedit-link-yes)";a.render("radio"),t.find(".layui-btn-primary").on("click",function(){i.close(n),l.focus()}),a.on(o,function(t){i.close(b.index),e&&e(t.field)})}});b.index=n},x=function(t){var a=function(){var t=["[微笑]","[嘻嘻]","[哈哈]","[可爱]","[可怜]","[挖鼻]","[吃惊]","[害羞]","[挤眼]","[闭嘴]","[鄙视]","[爱你]","[泪]","[偷笑]","[亲亲]","[生病]","[太开心]","[白眼]","[右哼哼]","[左哼哼]","[嘘]","[衰]","[委屈]","[吐]","[哈欠]","[抱抱]","[怒]","[疑问]","[馋嘴]","[拜拜]","[思考]","[汗]","[困]","[睡]","[钱]","[失望]","[酷]","[色]","[哼]","[鼓掌]","[晕]","[悲伤]","[抓狂]","[黑线]","[阴险]","[怒骂]","[互粉]","[心]","[伤心]","[猪头]","[熊猫]","[兔子]","[ok]","[耶]","[good]","[NO]","[赞]","[来]","[弱]","[草泥马]","[神马]","[囧]","[浮云]","[给力]","[围观]","[威武]","[奥特曼]","[礼物]","[钟]","[话筒]","[蜡烛]","[蛋糕]"],e={};return layui.each(t,function(t,i){e[i]=layui.cache.dir+"images/face/"+t+".gif"}),e}();return x.hide=x.hide||function(t){"face"!==e(t.target).attr("layedit-event")&&i.close(x.index)},x.index=i.tips(function(){var t=[];return layui.each(a,function(e,i){t.push('<li title="'+e+'"><img src="'+i+'" alt="'+e+'"></li>')}),'<ul class="layui-clear">'+t.join("")+"</ul>"}(),this,{tips:1,time:0,skin:"layui-box layui-util-face",maxWidth:500,success:function(l,n){l.css({marginTop:-4,marginLeft:-10}).find(".layui-clear>li").on("click",function(){t&&t({src:a[this.title],alt:this.title}),i.close(n)}),e(document).off("click",x.hide).on("click",x.hide)}})},k=function(t){var e=this,l=i.open({type:1,id:"LAY_layedit_code",area:"550px",shade:.05,shadeClose:!0,moveType:1,title:"插入代码",skin:"layui-layer-msg",content:['<ul class="layui-form layui-form-pane" style="margin: 15px;">','<li class="layui-form-item">','<label class="layui-form-label">请选择语言</label>','<div class="layui-input-block">','<select name="lang">','<option value="JavaScript">JavaScript</option>','<option value="HTML">HTML</option>','<option value="CSS">CSS</option>','<option value="Java">Java</option>','<option value="PHP">PHP</option>','<option value="C#">C#</option>','<option value="Python">Python</option>','<option value="Ruby">Ruby</option>','<option value="Go">Go</option>',"</select>","</div>","</li>",'<li class="layui-form-item layui-form-text">','<label class="layui-form-label">代码</label>','<div class="layui-input-block">','<textarea name="code" lay-verify="required" autofocus="true" class="layui-textarea" style="height: 200px;"></textarea>',"</div>","</li>",'<li class="layui-form-item" style="text-align: center;">','<button type="button" lay-submit lay-filter="layedit-code-yes" class="layui-btn"> 确定 </button>','<button style="margin-left: 20px;" type="button" class="layui-btn layui-btn-primary"> 取消 </button>',"</li>","</ul>"].join(""),success:function(l,n){var o="submit(layedit-code-yes)";a.render("select"),l.find(".layui-btn-primary").on("click",function(){i.close(n),e.focus()}),a.on(o,function(e){i.close(k.index),t&&t(e.field)})}});k.index=l},C={html:'<i class="layui-icon layedit-tool-html" title="HTML源代码" lay-command="html" layedit-event="html"">&#xe64b;</i><span class="layedit-tool-mid"></span>',strong:'<i class="layui-icon layedit-tool-b" title="加粗" lay-command="Bold" layedit-event="b"">&#xe62b;</i>',italic:'<i class="layui-icon layedit-tool-i" title="斜体" lay-command="italic" layedit-event="i"">&#xe644;</i>',underline:'<i class="layui-icon layedit-tool-u" title="下划线" lay-command="underline" layedit-event="u"">&#xe646;</i>',del:'<i class="layui-icon layedit-tool-d" title="删除线" lay-command="strikeThrough" layedit-event="d"">&#xe64f;</i>',"|":'<span class="layedit-tool-mid"></span>',left:'<i class="layui-icon layedit-tool-left" title="左对齐" lay-command="justifyLeft" layedit-event="left"">&#xe649;</i>',center:'<i class="layui-icon layedit-tool-center" title="居中对齐" lay-command="justifyCenter" layedit-event="center"">&#xe647;</i>',right:'<i class="layui-icon layedit-tool-right" title="右对齐" lay-command="justifyRight" layedit-event="right"">&#xe648;</i>',link:'<i class="layui-icon layedit-tool-link" title="插入链接" layedit-event="link"">&#xe64c;</i>',unlink:'<i class="layui-icon layedit-tool-unlink layui-disabled" title="清除链接" lay-command="unlink" layedit-event="unlink"">&#xe64d;</i>',face:'<i class="layui-icon layedit-tool-face" title="表情" layedit-event="face"">&#xe650;</i>',image:'<i class="layui-icon layedit-tool-image" title="图片" layedit-event="image">&#xe64a;<input type="file" name="file"></i>',code:'<i class="layui-icon layedit-tool-code" title="插入代码" layedit-event="code">&#xe64e;</i>',help:'<i class="layui-icon layedit-tool-help" title="帮助" layedit-event="help">&#xe607;</i>'},w=new c;t(n,w)});layui.define("jquery",function(e){"use strict";var a=layui.$,l="http://www.layui.com/doc/modules/code.html";e("code",function(e){var t=[];e=e||{},e.elem=a(e.elem||".layui-code"),e.about=!("about"in e)||e.about,e.elem.each(function(){t.push(this)}),layui.each(t.reverse(),function(t,i){var c=a(i),o=c.html();(c.attr("lay-encode")||e.encode)&&(o=o.replace(/&(?!#?[a-zA-Z0-9]+;)/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/'/g,"&#39;").replace(/"/g,"&quot;")),c.html('<ol class="layui-code-ol"><li>'+o.replace(/[\r\t\n]+/g,"</li><li>")+"</li></ol>"),c.find(">.layui-code-h3")[0]||c.prepend('<h3 class="layui-code-h3">'+(c.attr("lay-title")||e.title||"code")+(e.about?'<a href="'+l+'" target="_blank">layui.code</a>':"")+"</h3>");var d=c.find(">.layui-code-ol");c.addClass("layui-box layui-code-view"),(c.attr("lay-skin")||e.skin)&&c.addClass("layui-code-"+(c.attr("lay-skin")||e.skin)),(d.find("li").length/100|0)>0&&d.css("margin-left",(d.find("li").length/100|0)+"px"),(c.attr("lay-height")||e.height)&&d.css("max-height",c.attr("lay-height")||e.height)})})}).addcss("modules/code.css","skincodecss");
window.$=window.jQuery = layui.$;
/*! YS v1.0.0 | by YSTeam | Copyright (c) 2013-2017 http://www.ysframe.com All rights reserved. | Licensed under MIT | 2017-12-22T12:00:00+0800 */ 

(function(window, undefined) {
//配置项 ============================================================================ 
// 判断生产或者是测试环境
var envir = window.location.origin.indexOf('dev')==-1?'api':'devapi';
var conf = {
  rooturl: './',    //'https://'+envir+'.helii.cn/api/',网站根目录
  tplpath:'./html',  //每个模块的静态页面存放路径
  index:"./html/mainpage.html",//默认include进来的模板
  indexpath:'mainpage',//默认进入的模块
  jsonpath:'json_dev',//约定json路径
  debug:false,//开发模式，会所有的请求为get方法，并请求本地json;
  console:true,//ture开启，则打印一些YS提示错误信息，false关闭,不打印YS的提示信息; 
  opendomain:'http://p5u15xcze.bkt.clouddn.com',
  prvdomain:'http://p4y9n22tm.bkt.clouddn.com',
  subfix:['a','b','c','d','e','f','g','h','i','j','k'], //约定数据容器的后缀；
   ext:{ 
      jichu:['桩芯','基础承台垫层','基础承台、地梁','电梯基坑垫层','电梯基坑'],
      dixia:['一','二','三','四','五','六','七','八','九','十'],
      zhuti:['首','二','三','四','五','六','七','八','九','十','十一','十二','十三','十四','十五','十六','十七','十八','十九','二十','二十一','二十二','二十三','二十四','二十五','二十六','二十七','二十八','二十九','三十','三十一','三十二','三十三','三十四','三十五','三十六','三十七','三十八','三十九','四十','四十一','四十二','四十三','四十四','四十五','四十六','四十七','四十八','四十九','五十','五十一','五十二','五十三','五十四','五十五','五十六','五十七','五十八','五十九','六十','六十一','六十二','六十三','六十四','六十五','六十六','六十七','六十八','六十九','七十','七十一','七十二','七十三','七十四','七十五','七十六','七十七','七十八','七十九','八十'],
      wumian:['屋面梁板',
              '屋面柱',
              '小屋面梁板',
              
],
  },

};


var $ = layui.$;
var Jquery = layui.$;
//业务逻辑块 ============================================================================
var main = { 
  ctrl_init:function(sco) {// 主控制器初始化 
   

      





    //退出登录
    sco.com_objc.url = 'user/logout';     
     sco.com_objc.before = function(sco){
        sco.com_objc.params.uid = YSfac.getstore("uid");
     };
     sco.com_objc.done = function(re,sco){
       if (re.code==0) {
        layer.msg("退出成功，1s后将跳转");
        setTimeout(function() {
         window.location.href = "/login.html";
         YSfac.delCookie('token');
        }, 1000);
       }
     };

function newTimer(s) {


function getNow(s) {
    return s < 10 ? '0' + s: s;
}

var myDate = new Date(s);
//获取当前年
var year=myDate.getFullYear();
//获取当前月
var month=myDate.getMonth()+1;
//获取当前日
var date=myDate.getDate(); 
var h=myDate.getHours();       //获取当前小时数(0-23)
var m=myDate.getMinutes();     //获取当前分钟数(0-59)
 
var day=myDate.getDay(); 
var show_day=new Array('星期一','星期二','星期三','星期四','星期五','星期六','星期日'); 

$('#DateTime1').text(year+'年'+getNow(month)+"月"+getNow(date)+"日");
$('#DateTime2').text(getNow(h)+':'+getNow(m));
$('#DateTime3').text(show_day[day-1]);

    }




    //获取用户信息
    sco.com_objd.url = 'user/loginUserInfo';  
     sco.com_objd.before = function(sco){

     };
     sco.com_objd.done = function(re,sco){

      newTimer(re.time);
setTimeout(function(){
  sco.fetch('com_objd');
},7200000);

       if (re.code==0) {
          YSfac.setstore('uid',re.data.uid);

       }
     };

sco.fetch('com_objd');



    function stopit() {
      clearTimeout(slidint);
    }
      
    function week() {
      var d, day, x, s = " ";
      var x = new Array("星期日", "星期一", "星期二");
      var x = x.concat("星期三", "星期四", "星期五");
      var x = x.concat("星期六");
      d = new Date();
      day = d.getDay();
      return (s += x[day]);
    }
      
    window.onload = function() {
     // newTimer();
    }
//配置导航是否显示子单位下拉

//退出登录
sco.com_obja.url = 'user/logout'; 
 sco.com_obja.done = function(re,sco){
   if (re.code==0) {
    layer.msg("退出成功，1s后将跳转");
    setTimeout(function() {
     window.location.reload();
    }, 1000);
   }
 };


//修改密码11
sco.com_objb.url = 'user/updatePwd'; 

sco.com_objb.before = function(sco){

if (!sco.com_objb.params.oldPwd||sco.com_objb.params.oldPwd=='') {
  layer.msg('旧密码不能为空！');return false;
}
if (!sco.com_objb.params.reoldpaw||sco.com_objb.params.reoldpaw=='') {
  layer.msg('新密码不能为空！');return false;
}
if (!(/(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$/.test(sco.com_objb.params.reoldpaw))) {
  layer.msg('密码需要8~20位同时包含数字和大小写字母！');return false;
}
if (!sco.com_objb.params.newPwd||sco.com_objb.params.newPwd=='') {
  layer.msg('确认密码不能为空！');return false;
}
if (sco.com_objb.params.reoldpaw!=sco.com_objb.params.newPwd) {
  layer.msg('新密码不一致，请重新输入！');
  return false;
}
};
sco.com_objb.done = function(re,sco){
  if(re.code==0){
    layer.closeAll();
    layer.msg('密码修改成功！');
    sco.com_objb.params.oldPwd='';
    sco.com_objb.params.reoldpaw='';
    sco.com_objb.params.newPwd='';
  }
  else{
    layer.msg(re.msg);
  }
}


//获取用户信息
// sco.com_objc.url='user/loginUserInfo';
// sco.fetch('com_objc');






  },


// 首页模块
mainpage:function(sco) {
  $("#main_e").empty()
  sco.dtwidth=0;
  sco.link=0;
  sco.device=0;
  sco.task=0;
  sco.filelist=[];
  sco.gl='';
  sco.showf=0;
  sco.iconlist=[
   {id:1,deviceName:"GAP",status:1},{id:2,deviceName:"路由器",status:1},{id:3,deviceName:"防火墙",status:1},{id:4,deviceName:"IPS",status:1},{id:5,deviceName:"可信任安全网关",status:1},{id:6,deviceName:"交换器",status:1},{id:7,deviceName:"安管隔离网闸",status:1},{id:8,deviceName:"编码器",status:1},{id:9,deviceName:"FGAP",status:1},{id:10,deviceName:"光交换",status:2}
  ,{id:11,deviceName:"矩阵抽象",status:2},{id:12,deviceName:"摄像头",status:2},{id:13,deviceName:"3AS",status:2},{id:14,deviceName:"ADS",status:2},{id:15,deviceName:"AS",status:2},{id:16,deviceName:"ASM",status:2},{id:17,deviceName:"AWS",status:2},{id:18,deviceName:"DTI",status:2},{id:19,deviceName:"EWP",status:3},{id:20,deviceName:"IWP",status:3}
  ,{id:21,deviceName:"MOC",status:3},{id:22,deviceName:"PAS",status:3},{id:23,deviceName:"SS",status:3},{id:24,deviceName:"SSE",status:3},{id:25,deviceName:"TAS",status:3},{id:26,deviceName:"TMS",status:3},{id:27,deviceName:"UAS",status:3},{id:28,deviceName:"UMS",status:4},{id:29,deviceName:"TZ",status:4},{id:30,deviceName:"存储陈列",status:4}
  ,{id:31,deviceName:"磁盘陈列",status:4},{id:32,deviceName:"摄像头",status:4},{id:33,deviceName:"服务器",status:4},{id:34,deviceName:"矩阵",status:4},{id:35,deviceName:"内网管理员",status:4},{id:36,deviceName:"入侵ids",status:4},{id:37,deviceName:"摄像头2",status:5},{id:38,deviceName:"数据库",status:5},{id:39,deviceName:"云控制台",status:5},{id:40,deviceName:"终端",status:5}
  ,{id:41,deviceName:"主机ids",status:5},{id:42,deviceName:"政党军机构",status:5},{id:43,deviceName:"公安驻地外",status:5},{id:44,deviceName:"海关",status:5},{id:45,deviceName:"民政机构",status:5},{id:46,deviceName:"内网",status:6},{id:47,deviceName:"社保机构",status:6},{id:48,deviceName:"社会企事业单位",status:6},{id:49,deviceName:"视频内网",status:6},{id:50,deviceName:"视频终端",status:6}
  ,{id:51,deviceName:"外部接入单位",status:6},{id:52,deviceName:"外网",status:6},{id:53,deviceName:"操作台",status:6}];
  sco.navup = false;
  sco.syxmenu1 = false;
  sco.syxmenu2 = false;
// 1、获取链路监控列表 YS_lista
// 2、获取单个设备的系统占用率(cpu,内存,硬盘,网络)   YS_obja
// 3、获取链路的状态  YS_objb
// 4、获取报警总览数据 YS_objc
// 5、获取报警排行数据 YS_objd
// 6、获取报警数量统计 YS_obje
// 7、获取后台数据统计 YS_objf


//设置流动点的随机数
//随机数  
sco.suiji = ''; 
function suiji(){
function diu_Randomize(b,e){   
    if(!b && b!=0 || !e){return "?";}   
    return Math.floor( ( Math.random() * e ) + b );   
}   
setInterval(function(){
 sco.suiji = diu_Randomize(0,10);

},2000);

}
//suiji();













// 1、获取链路监控列表 YS_lista
  sco.YS_lista.url="index/topology";
  sco.YS_lista.params={};
  sco.YS_lista.load = false;
  sco.YS_lista.done = function(re, sco) {
    
      sco.fetch('YS_listb');
    if(sco.YS_lista.data&&sco.YS_lista.data.list.length>0){
      sco.YS_lista.data.list.map(function(elem,index){


        if(elem.topologyNodes.length>0){

          if(elem.topologyNodes[0].fromDeviceId==0){
            var temp = [];
            temp.push(elem.topologyNodes[0]);
            elem.topologyNodes = get_child(elem.topologyNodes,elem.topologyNodes[0].toDeviceId,temp);
          }


          elem.topologyNodes.map(function(e){


            // if(sco.YS_listb.data.hasOwnProperty(e.toDeviceId)){
            //   e.deviceStatus = 0;//正常
            //   if(sco.YS_listb.data[e.toDeviceId]>1){
            //     e.deviceStatus = 2;//报警
            //   }
            // }else{
            //   e.deviceStatus = 1;//不通
            // }



            if(!e.bottomType)e.bottomType = 0;
            if(!e.show)e.show = 0;

              //遍历获取设备名
              if(sco.YS_lista.data.devices[e.toDeviceId]){
                  e.deviceInfo = sco.YS_lista.data.devices[e.toDeviceId];
              }

                // if(sco.iconlist[e.toDeviceId]){
                //   e.deviceInfo = sco.iconlist[e.toDeviceId];
                // }else{
                //   e.deviceInfo = [];
                // }
    // elem.map(function(elem3,index3){

    // })

              // sco.iconlist.map(function(elem2){
              //   if(elem2.id==e.toDeviceId){
              //     e.deviceInfo = elem2;
              //   }
              // })




              // if(e.deviceIds&&e.deviceIds.length>0){
              //   sco.filelist=e.deviceIds
              // }

            if(e.child&&e.child.length>0){
              e.child.map(function(e2){
                // if(sco.YS_listb.data.hasOwnProperty(e2.toDeviceId)){
                //   e2.deviceStatus = 0;//正常
                //   if(sco.YS_listb.data[e2.toDeviceId]>1){
                //     e2.deviceStatus = 2;//报警
                //   }
                // }else{
                //   e2.deviceStatus = 1;//不通
                // }

                //child遍历获取设备名
                if(sco.YS_lista.data.devices[e2.toDeviceId]){
                  e2.deviceInfo = sco.YS_lista.data.devices[e2.toDeviceId];
                }
                // if(sco.iconlist[e2.toDeviceId+1]){
                //   e2.deviceInfo = sco.iconlist[e2.toDeviceId+1];
                // }else{
                //   e2.deviceInfo = [];
                // }

                // sco.iconlist.map(function(elem2){
                //   if(elem2.id==e2.toDeviceId){
                //     e2.deviceInfo = elem2;
                //   }
                // })



                if(!e2.show)e2.show = 0;
                if(!e2.bottomType)e2.bottomType = 0;
              })
            }
          })   

        }

      })   


  
    }

sco.YS_lista.data.list.map(function(elem,index){
  elem.topologyNodes.map(function(el1){
    if(el1.child){
      el1.child.map(function(el2){
        if(el2.deviceStatus==1){
         sco.gl="#666"
        }else{
          sco.gl="#046d83"
        }
      })
    }
  })
})



    YS("jsPlumb",function(){
     //   if(sco.box==true){
     //   jsPlumb.deleteEveryEndpoint();
     //   jsPlumb.detachEveryConnection();
     // sco.box=false
     // }
  function deepCopy(p, c) {  //克隆对象
          var c = c || {};  
          for (var i in p) {  
              if(! p.hasOwnProperty(i)){  
                  continue;  
              }  
              if (typeof p[i] === 'object') {  
                  c[i] = (p[i].constructor === Array) ? [] : {};  
                  deepCopy(p[i], c[i]);  
              } else {  
                  c[i] = p[i];  
              }  
          }  
          return c;  
      } 

 // function epym() {
 //       $("#leftlink").empty()
 //       jsPlumb.deleteEveryEndpoint();
 //       jsPlumb.detachEveryConnection();
 // }
 // sco.epym=epym
 jsPlumb.ready(function(){
  var gl=sco.gl
  var connectorPaintStyle = {//基本连接线样式
                  lineWidth: 0.5,
                  strokeStyle: gl,
                  joinstyle: "",
                  outlineColor: gl,
                  outlineWidth: 0
              };
     var origin = {//起点
                  endpoint: ["Dot", { radius: 2 }],
                  connectorStyle: connectorPaintStyle,
                  paintStyle: {fillStyle: "#03343d",},
                  isSource: true,
                  connector: ["Straight", { stub: [0, 0], gap: 0, cornerRadius: 0, alwaysRespectStubs: false }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
                  isTarget: false,
                  maxConnections:-1,
              };
      var destination = {//终点
                  endpoint: ["Dot", { radius: 2 }], 
                  connectorStyle: connectorPaintStyle,
                  paintStyle: {fillStyle: "#03343d",},
                  isSource: false,
                  connector: ["Straight", { stub: [0, 0], gap: 0, cornerRadius: 0, alwaysRespectStubs: false }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
                  isTarget: false,
                  maxConnections:-1,
              };
   jsPlumb.setContainer('leftlink')
   sco.box=true;
   $("#leftlink .linkpoint").each(function(){
        jsPlumb.addEndpoint(this, { anchors: "Center" }, deepCopy(origin, {uuid:$(this).attr("id")+"origin"}));//起点
        jsPlumb.addEndpoint(this, { anchors: "Center" },  deepCopy(destination, {uuid:$(this).attr("id")+"destination"}));//终点
   });

sco.YS_lista.data.list.map(function(elem,index){
  elem.topologyNodes.map(function(el1){
    if(el1.child){
      el1.child.map(function(el2){
        if(el2.returnToId){
          jsPlumb.connect({uuids:[index+"s"+el2.toDeviceId+"origin",index+"s"+el2.returnToId+"destination"]});
        }
      })
    }
  })
})
  

 })

})
console.log($("#leftlink").width())
sco.dtwidth=$("#leftlink").width()
  };


sco.YS_listb.url = "topology/queryAllLevel";
sco.YS_listb.load = false;
sco.YS_listb.done = function(re, sco) {
    

      if(sco.YS_lista.data&&sco.YS_lista.data.list.length>0){
        sco.YS_lista.data.list.map(function(elem,index){


          if(elem.topologyNodes.length>0){


            elem.topologyNodes.map(function(e){
                //判断主路文件夹
                if(e.deviceIds&&e.deviceIds.length>0){
                    e.deviceIdsElem = [];
                  e.deviceIds.map(function(el2){
                    var temp = {'toDeviceId':el2,'deviceStatus':0};
                    if(re.data.hasOwnProperty(el2)){
                      // temp.deviceStatus = 0;//正常
                      if(re.data[el2]==2){
                        temp.deviceStatus = 2;//报警
                        temp.alarm = 1;
                      }

                      if(re.data[el2]==3){
                        temp.deviceStatus = 1;//不通
                      }

                    }else{
                      temp.deviceStatus = 1;//不通
                    }     

                    e.deviceIdsElem.push(temp);             
                  })
                }


              //判断主路
              if(re.data.hasOwnProperty(e.toDeviceId)){
                e.deviceStatus = 0;//正常
                if(re.data[e.toDeviceId]==2){
                  e.deviceStatus = 2;//报警
                  e.alarm = 1;
                }

                if(re.data[e.toDeviceId]==3){
                  e.deviceStatus = 1;//不通
                }

              }else{
                e.deviceStatus = 1;//不通
              }


              //子节点
              if(e.child&&e.child.length>0){
                e.child.map(function(e2){

                  //子节点文件夹
                  if(e2.deviceIds&&e2.deviceIds.length>0){
                      e2.deviceIdsElem = [];
                    e2.deviceIds.map(function(el3){
                      var temp = {'toDeviceId':el3,'deviceStatus':0};
                      if(re.data.hasOwnProperty(el3)){
                        // temp.deviceStatus = 0;//正常
                        if(re.data[el3]==2){
                          temp.deviceStatus = 2;//报警
                          temp.alarm = 1;
                        }

                        if(re.data[el3]==3){
                          temp.deviceStatus = 1;//不通
                        }

                      }else{
                        temp.deviceStatus = 1;//不通
                      }     

                      e2.deviceIdsElem.push(temp);             
                    })
                  }


                  if(re.data.hasOwnProperty(e2.toDeviceId)){
                    e2.deviceStatus = 0;//正常
                    if(re.data[e2.toDeviceId]==2){
                      e2.deviceStatus = 2;//报警
                      e2.alarm = 1;
                    }
                    if(re.data[e2.toDeviceId]==3){
                      e2.deviceStatus = 1;//不通
                    }

                  }else{
                    e2.deviceStatus = 1;//不通
                  }

                })
              }
            })   

          }

        })   


      }

sco.isredx=0;
      sco.YS_lista.data.list.map(function(elem2,index2){

        elem2.lastErrorIndex = get_last_error(elem2.topologyNodes);
        if (get_last_errors(elem2.topologyNodes)) {
           elem2.deviceStatus =   get_last_errors(elem2.topologyNodes);
           if (elem2.deviceStatus==1&&elem2.topologyNodes) {
           //elem2.topologyNodes.child[0].deviceStatus=1;
        elem2.topologyNodes.map(function(el){
                if (el.child) {
                  el.child[0].deviceStatus=1;
                  sco.isredx=1;
                } 
                  else {
                    return;
                  }
      })
           } 
        }
       
        

      }) 

  
}


    console.log(sco.YS_lista);

    //获取每一条链路最后一次不通的设备下标
    function get_last_error(arr){
      var result = -1;
      arr.map(function(elem,index){
        if(elem.deviceStatus==1){
          result = index;
        }
      })
      return result;
    }

    function get_last_errors(arr){
      var result = -1;
      var dev = -1;
      arr.map(function(elem,index){
        if(elem.deviceStatus==1){
          result = index;
        }
      if (result!=-1) {
      arr.map(function(elem2,index2){
                 if (index2<result) {
                  elem2.deviceStatus = 1;
                    dev = 1;
                 } 
          })
      }

      
      })
    return dev;
    }



   sco.int = setInterval(function(){
      if(sco.YS_listb.url!="topology/queryAllLevel"){
        window.clearInterval(sco.int);
        return false;
      }
      sco.fetch('YS_listb');
   },10000)
 
      sco.fetch('YS_lista');


  sco.listkey = '';

// 获取设备资源消耗信息
  sco.YS_obja.url = "device/queryExplorer";
  // sco.YS_obja.params = {deviceId:3};
  sco.YS_obja.done=function(re,sco){
    var speeds = 0;
   if(re.code==0&&re.data&&re.data['deviceExplorer']){
    setTimeout(function(){
      var cpu = re.data.deviceExplorer.cpu.useRateStr;
      var disk = re.data.deviceExplorer.disk.useRateStr;
      var mem = re.data.deviceExplorer.mem.useRateStr;
      var ecpu = "ecpu"+sco.listkey  + sco.YS_obja.params.deviceId;
      var eyp = "eyp" +sco.listkey + sco.YS_obja.params.deviceId;
      var enc = "enc" +sco.listkey + sco.YS_obja.params.deviceId;
      var ewl = "ewl" +sco.listkey + sco.YS_obja.params.deviceId;
      cpu = Number(cpu.replace(/\%/g,''));

      disk = Number(disk.replace(/\%/g,''));
      mem = Number(mem.replace(/\%/g,''));
      var redcol1 = '#ffb71a';
      var redcol2 = '#ffb71a';
      var redcol3 = '#ffb71a';
      
    if (cpu>79.99) {var redcol1 = 'red';}
    if (disk>79.99) {var redcol2 = 'red';}
    if (mem>79.99) {
      var redcol3 = 'red';
      if(sco.YS_lista.data&&sco.YS_lista.data.list.length>0){
        sco.YS_lista.data.list.map(function(elem,index){


          if(elem.topologyNodes.length>0){


            elem.topologyNodes.map(function(e){
if (e.toDeviceId==sco.YS_obja.params.deviceId) {
   e.deviceStatus = 2;//报警
} 


            })   

          }

        })   


      }


    }

  
             var myCircle1 = Circles.create({
      id:                  ecpu,
      radius:              26,
      value:               cpu,
      maxValue:            100,
      width:               5,
      text:                function(value){return value + '%';},
      colors:              ['#868db6', redcol1],
      duration:            speeds,
      textClass:           'clt',
      styleText:           true
    });
      var myCircle2 = Circles.create({
      id:                  eyp,
      radius:              26,
      value:               disk,
      maxValue:            100,
      width:               5,
      text:                function(value){return value + '%';},
      colors:              ['#868db6',redcol2],
      duration:            speeds,
      textClass:           'clt',
      styleText:           true
    });
      var myCircle3 = Circles.create({
      id:                  enc,
      radius:              26,
      value:               mem,
      maxValue:            100,
      width:               5,
      text:                function(value){return value + '%';},
      colors:              ['#868db6',redcol3],
      duration:            speeds,
      textClass:           'clt',
      styleText:           true
    });
    //   var myCircle4 = Circles.create({
    //   id:                  ewl,
    //   radius:              26,
    //   value:               10,
    //   maxValue:            100,
    //   width:               5,
    //   text:                function(value){return value + '%';},
    //   colors:              ['#868db6', '#ffb71a'],
    //   duration:            400,
    //   textClass:           'clt',
    //   styleText:           true
    // });

      },100)

    }
    if(re.code==0&&!re.data){
      setTimeout(function(){
      var ecpu = "ecpu"+sco.listkey  + sco.YS_obja.params.deviceId;
      var eyp = "eyp" +sco.listkey + sco.YS_obja.params.deviceId;
      var enc = "enc" +sco.listkey + sco.YS_obja.params.deviceId;
      var myCircle1 = Circles.create({
      id:                  ecpu,
      radius:              26,
      value:               0,
      maxValue:            100,
      width:               5,
      text:                function(value){return '';},
      colors:              ['#868db6', '#ffb71a'],
      duration:            speeds,
      textClass:           'clt',
      styleText:           true
    });
      var myCircle2 = Circles.create({
      id:                  eyp,
      radius:              26,
      value:               0,
      maxValue:            100,
      width:               5,
      text:                function(value){return '';},
      colors:              ['#868db6', '#ffb71a'],
      duration:            speeds,
      textClass:           'clt',
      styleText:           true
    });
      var myCircle3 = Circles.create({
      id:                  enc,
      radius:              26,
      value:               0,
      maxValue:            100,
      width:               5,
      text:                function(value){return '';},
      colors:              ['#868db6', '#ffb71a'],
      duration:            speeds,
      textClass:           'clt',
      styleText:           true
    });
    },100)
    }
  }  





  // var temp = [];
  // var temp2 = [];
  // var result = [];
  //   // console.log(test);
  //   //先找出每条链路的头
  //   test.map(function(elem,index){
  //     if(elem.fromid==0){
  //       temp2.push(elem);
  //       temp.push(temp2);
  //     }
  //   })

  //   // console.log(temp2);

  //   //遍历得出每条链路
  //   temp2.map(function(elem,index){
  //     temp = [];
  //     temp.push(elem);

  //     temp = get_child(test,elem.id,temp);

  //     result.push(temp);

  //   })


  //   // console.log(result);
  //   //result为整理后每一条链路的设备
  //   //给每一条链路排序、分类

  //     var last = [];
  //     var lastre = [];
  //     var t = [];
  //     var savechild = [];




  //     result.map(function(elem,index){
  //       last = [];
  //       t = elem;

  //       last = check_fork(elem,last);

  //       lastre.push(last);

  //     })


  //     //格式化
  //     // function format_link(data){
  //     //   var format_link = [];
  //     //   lastre.map(function(elem){
  //     //     var format_temp = [];
  //     //     format_temp['list'] = elem;
  //     //     format_link.push(format_temp);


  //     //   })
  //     //   return format_link;
  //     // }





  //   console.log(lastre);

  //   //检测分岔
  //   function check_fork(elem,re){
  //       for (var i = 0; i < elem.length; i++) {
  //         if(i==0){
  //           re.push(elem[i]);
  //           continue;
  //         }


  //         if(elem[i].fromid==elem[i-1].id&&elem[i-1]['fork']){
  //           elem[i]['fork'] = 1;
  //         }


  //         if(elem[i].fromid==elem[i-1].id&&!elem[i-1]['fork']){
  //           //是子级
  //           re.push(elem[i]);
  //         }else{
  //           //分岔
  //           //此点不是上级的子级，遍历数组寻找父级
            
  //             elem[i]['fork'] = 1;
  //             for (var j = 0; j < re.length; j++) {
  //               if(re[j].id==elem[i].fromid){
  //                 var child = [];
  //                 savechild = [];
  //                 re[j].child? '':re[j].child = [];
  //                 child.push(elem[i]);
  //                 savechild=get_child(t,t[i].id,child);
  //                 //复原
  //                 savechild.map(function(e2){
  //                   e2.fork = 0;
  //                 })

  //                 savechild = check_fork(savechild,[]);

  //                 re[j].child.push(savechild);
  //               }
  //             }

  //         }

  //       }

  //       return re;
  //   }




    //获取子级
    function get_child(arr,id,re){
      var re1 = JSON.parse(JSON.stringify(re));


      if(re1[0].child&&re1[0].child.length>0){
        re1[0].child = check_return(arr,re1[0].child);
      }

      
      arr.map(function(e,index){
        
        if(e.child&&e.child.length>0){
          e.child = check_return(arr,e.child);
        }

        if(e.fromDeviceId==id){
          re1.push(e);
          if(has_child(arr,e.toDeviceId)){
            re1 = get_child(arr,e.toDeviceId,re1);
          }
        }



      })
        return re1;
    }


    //查看是否存在子级
    function has_child(arr,id){
      var bool = 0;

      for (var i = 0; i < arr.length; i++) {
        if(arr[i].fromDeviceId==id){
          bool = 1;break;
        }
      }

      // arr.map(function(e){
      //   if(e.fromid==id){
      //     bool = 1;
      //   }
      // })
      return bool;
    }


    //检查分支是否有回路
    function check_return(arr,child){
      var last = child[child.length-1];
      arr.map(function(e,index){
        if(e.toDeviceId==last.toDeviceId){

          if(child.length>1){
            child[child.length-2].returnToId = e.toDeviceId;
            child.splice(child.length-1,1);
          }else{
            child[child.length-1].returnToId = e.toDeviceId;
          }
        }
      })

      return child;
    }






















// 4、获取报警总览数据 YS_objc
 sco.YS_objb.url = "index/alarmOverview";

  sco.YS_objb.before=function(sco){
    var now = new Date();
    var nowdata = now.getDate() - 1
var time = now.getFullYear() + "-" +((now.getMonth()+1)<10?"0":"")+(now.getMonth()+1)+"-"+(now.getDate()<10?"0":"")+nowdata; 
if (!sco.YS_objb.params.selectDate) {
sco.YS_objb.params.selectDate = time;
} 

  }
 sco.YS_objb.done=function(re,sco){
  if(re.code==0&&re.data){
          if (!re.data.alarmLinkNum || !re.data.totalLinkNum) {
            var link = 0;
          } else {
            var link = (re.data.alarmLinkNum / re.data.totalLinkNum).toFixed(2);
          }

          if (!re.data.alarmDeviceNum || !re.data.totalDeviceNum) {
            var device = 0;
          } else {
            var device = (re.data.alarmDeviceNum / re.data.totalDeviceNum).toFixed(2);
          }

          if (!re.data.alarmTaskNum || !re.data.totalTaskNum) {
            var task = 0;
          } else {
            var task = (re.data.alarmTaskNum / re.data.totalTaskNum).toFixed(2);
          }


      
      


 $('#foo1').circleProgress({
        value: link,
        size: 90,
        thickness:20,
        startAngle:-1.57,
        // lineCap:'round',
        fill: {
          gradient: [["#22d7dd", .2], ["#22d7dd", .3], ["#fb0304",.8]],
           // gradient: ["#22d7dd","#22d7dd", "#fb0304"],
            gradientAngle:-3,
            // gradientDirection: [0.5, 0,0.5,0]
            
        },
        emptyFill:'#0c4a57',

    }).on('circle-animation-progress', function (event, progress, stepValue) {
            $(this).find('span').html(stepValue.toFixed(2) * 100 + '%');
  });


 $('#foo2').circleProgress({
        value: device,
        size: 90,
        thickness:20,
        startAngle:-1.57,
         fill: {
            gradient: [["#22d7dd", .2], ["#22d7dd", .3], ["#fb0304",.8]],
            gradientAngle:-3,
            // gradientDirection: [0.5, 0,0.5,0]
            
        },
         emptyFill:'#0c4a57',
    }).on('circle-animation-progress', function (event, progress, stepValue) {
            $(this).find('span').html(stepValue.toFixed(2) * 100 + '%');
  });
 $('#foo3').circleProgress({
        value: task,
        size: 90,
        thickness:20,
        startAngle:-1.57,
         fill: {
            gradient: [["#22d7dd", .2], ["#22d7dd", .3], ["#fb0304",.8]],
            gradientAngle:-3,
            // gradientDirection: [0.5, 0,0.5,0]
            
        },
         emptyFill:'#0c4a57',
    }).on('circle-animation-progress', function (event, progress, stepValue) {
            $(this).find('span').html(stepValue.toFixed(2) * 100 + '%');
  });
}

if(re.code==0&&!re.data){
   $('#foo1').circleProgress({
        value: 0,
        size: 90,
        thickness:20,
        startAngle:-1.57,
        // lineCap:'round',
        fill: {
          gradient: [["#22d7dd", .2], ["#22d7dd", .3], ["#fb0304",.8]],
           // gradient: ["#22d7dd","#22d7dd", "#fb0304"],
            gradientAngle:-1.15,
            // gradientDirection: [0.5, 0,0.5,0]
            
        },
        emptyFill:'#0c4a57',

    }).on('circle-animation-progress', function (event, progress, stepValue) {
            $(this).find('span').html(stepValue.toFixed(2) * 100 + '%');
  });


 $('#foo2').circleProgress({
        value: 0,
        size: 90,
        thickness:20,
        startAngle:-1.57,
         fill: {
            gradient: ["#22d7dd","#22d7dd", "#fb0304"],
            gradientAngle:-1.15,
            // gradientDirection: [0.5, 0,0.5,0]
            
        },
         emptyFill:'#0c4a57',
    }).on('circle-animation-progress', function (event, progress, stepValue) {
            $(this).find('span').html(stepValue.toFixed(2) * 100 + '%');
  });
 $('#foo3').circleProgress({
        value: 0,
        size: 90,
        thickness:20,
        startAngle:-1.57,
         fill: {
            gradient: ["#22d7dd","#5c9fa3", "#fb0304"],
            gradientAngle:-1.15,
            // gradientDirection: [0.5, 0,0.5,0]
            
        },
         emptyFill:'#0c4a57',
    }).on('circle-animation-progress', function (event, progress, stepValue) {
            $(this).find('span').html(stepValue.toFixed(2) * 100 + '%');
  });
}
 
 }
 sco.fetch("YS_objb")
// 5、获取报警排行数据 YS_objd
 sco.YS_objc.url = "index/alarmRinking";
 sco.YS_objc.params = {type:1};
 sco.nodatas =0;
 sco.YS_objc.done=function(re,sco){
  if(re.code==0){

    var b=[];

    if(re.data.rinking.length>0){
       sco.nodatas = 0;
      if (re.data.rinking[0]){re.data.rinking[0].color="#0281b6";};
       if (re.data.rinking[1]){re.data.rinking[1].color="#30c4e0";};
        if (re.data.rinking[2]){ re.data.rinking[2].color="#00b5ac";};
         if (re.data.rinking[3]){re.data.rinking[3].color="#13b697";};
      
      
     
      
      if(re.data.rinking.length>4){
          re.data.rinking[4].color="#21ce98";
      }
      
     if(JSON.stringify(re.data.nameMap)!='{}'){
      re.data.rinking.map(function(el,idx){
        // if(sco.YS_objc.params.type){}
        var sname=re.data.nameMap[el.bid].slice(0, 3)+'...'
         var name= re.data.nameMap[el.bid].length>3?sname:re.data.nameMap[el.bid]
         // if(name==undefined)
         name=name==undefined?idx:name
            b.push({
              "dataname":re.data.nameMap[el.bid],
              "country":name,
               "visits": el.alarmNum,
               "color": el.color
        })
        
         
      })
      }else{

      re.data.rinking.map(function(el,idx){
             b.push({
              
              "dataname":'数据异常',
              "country":'数据异...',
               "visits": el.alarmNum,
               "color": el.color
        })
         
      })

      }    
    }
    else{
 sco.nodatas = 1;
    }


    var chart = AmCharts.makeChart("zchart", {
    "theme": "none",
    "type": "serial",
    "startDuration": 2,
    "color":"#fff",
    "dataProvider": b,
    "startDuration":2,
    "marginTop":50,
    

    "valueAxes": [{
        "position": "left",
        "axisAlpha":0,

        "gridAlpha":0         
    }],
    // "chart":[{
    //   "color":"#fff"
    // }],
    "graphs": [{
        "balloonText": "[[dataname]]: <b>[[value]]条</b>",
        "colorField": "color",
        "fillAlphas": 0.8,
        "lineAlpha": 0,
        "type": "column",
        "columnWidth":0.5,
        "topRadius":1,
        "valueField": "visits", 
        "width":10,
       
    }],
    // "column":[{
    //     "columnwidth":0.4, 
    // }],
    "depth3D": 40,
  "angle": 20,
    "chartCursor": {
        "categoryBalloonEnabled": false,
        "cursorAlpha": 0,
        "zoomable": false
    },  
     "customBulletField":"dataname",  
    "categoryField": "country",

    "fontSize":14,    
    "categoryAxis": {
        "gridPosition": "start",
        "axisAlpha":0,
        "gridAlpha":0
        
    },

},0);

  }
 }
 sco.fetch("YS_objc")
// 6、获取报警数量统计 YS_obje
 sco.YS_objd.url = "index/alarmStatistics";
 sco.YS_objd.done = function(re,sco){
 var device = [];
 var link =[];
 var task = [];
 var data = [];
 re.data.list.map(function(el){
  device.push(el.alarmDeviceNum)
  link.push(el.alarmLinkNum)
  task.push(el.alarmTaskNum)
  data.push(YSfac.numtosecond(el.createTime).replace(/2018\-/g,'').replace(/0\:0\:0/g,''))
 })


 var myChart = echarts.init(document.getElementById('lchart'));
 var option = {

    tooltip: {
        trigger: 'axis',
        axisPointer: {
            show:false,
            lineStyle: {
                color: '#fff',
                opacity:0
            }
        }
    },
    grid: {
        left: '2%',
        right: '8%',
        bottom: '2%',
        top: '20%',
        containLabel: true
    },
    xAxis: [{
        type: 'category',
        boundaryGap: false,
        axisLine: {
            lineStyle: {
                width:3,
                color: '#019493'
            }
        },
        axisLabel: {
            margin: 10,
            textStyle: {
                fontSize: 16,
                color: '#03d6fc'
            }
        },
        axisTick: {
            show: false
        },
        data: data
    }, {
        axisPointer: {
            show: false
        },
        axisLine: {
            lineStyle: {
                color: '#0E2A43'
            }
        },
        axisTick: {
            show: false
        },
        show:false,
        position: 'bottom',
        offset: 0

    }],
    yAxis: [{
        type: 'value',
        
        axisTick: {
            show: false
        },
        axisLine: {
            lineStyle: {
                width:2,
                color: '#03d6fc'
            }
        },
        axisLabel: {
            margin: 10,
            textStyle: {
                fontSize: 16,
                color: '#03d6fc'
            }
        },
        splitLine: {
            show: false,
            lineStyle: {
                color: '#57617B'
            }
        }
    }],
    legend: {
        orient: 'vertical',
        // borderRadius:50,
        top: 20,
        right: 20,
        // data: ['任务', '设备', '线路'],
        textStyle: {
            color: '#fff ',
            fontSize: 14
        },
        // formatter(name) {
        //     return name
        // },
        itemWidth: 10,
        itemHeight: 10,
        itemGap: 25
    },



    series: [
// 任务
    {
        name: '任务',
        type: 'line',
        smooth: true,
        // stack: '总量',
        symbol: 'circle',
        symbolSize: 1,
        showSymbol: false,
        animationDelay: 2000,
        animationDuration: 1000,
        markPoint: {
            // symbol: 'image://url',
            data: [{
                type: 'max',
                name: '最大值'
            }],
            animationDelay: 3000,
            animationDuration: 1000
        },
        lineStyle: {
            normal: {
                width: 3,
                color: 'rgb(250,255,24)',
                opacity: 0.1
            }
        },
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(250,255,24, 0.2)'
                }, {
                    offset: 0.8,
                    color: 'rgba(250,255,24, 0.2)'
                }], false),
                // shadowColor: 'rgba(0, 0, 0, 1)',
                // shadowBlur: 1
            }
        },
        itemStyle: {
            normal: {
                color: 'rgb(176,171,14)',
                borderColor: 'rgba(250,255,34,.1)',
                borderWidth: 10

            }
        },
        // data: [48, 62, 72, 75, 68, 50, 28, 24, 30, 45, 58, 65]
        data:task
    }, 

// 设备
    {
        name: '设备',
        type: 'line',
        smooth: true,
        symbol: 'circle',
        // stack: '总量',
        symbolSize: 1,
        animationDelay: 1000,
        animationDuration: 1000,
        markPoint: {
            data: [{
                type: 'max',
                name: '最大值'
            }],
            animationDelay: 2000,
            animationDuration: 1000
        },
        showSymbol: false,
        lineStyle: {
            normal: {
                width: 3,
                color:'rgb(0,255,246)',
                opacity: 0.3
            }
        },
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(0, 255, 246, 0.6)'
                }, {
                    offset: 0.8,
                    color: 'rgba(0, 255, 246, 0.6)'
                }], false),
                shadowColor: 'rgba(0, 0, 0, 0.1)',
                shadowBlur: 10
            }
        },
        itemStyle: {
            normal: {
                color: 'rgb(1,204,199)',
                borderColor: 'rgba(0,255,246,0.2)',
                borderWidth: 10

            }
        },
        // data:  [11, 18, 27, 39, 55,74, 80, 78, 69,53, 39, 31]
        data:device
    }, 
// 线路
    {
        name: '线路',
        type: 'line',
        // stack: '总量',
        smooth: true,
        symbol: 'circle',
        symbolSize: 1,
        showSymbol: false,
        animationDelay: 0,
        animationDuration: 1000,
        markPoint: {
            data: [{
                type: 'max',
                name: '最大值'
            }],
            animationDelay: 1000,
            animationDuration: 1000
        },
        lineStyle: {
            normal: {
                width: 3,
                color: 'rgb(0,211,255)',
                opacity: 0.8
            }
        },
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(0,211,255, 0.7)'
                }, {
                    offset: 0.8,
                    color: 'rgba(0,211,255, 0.7)'
                }], false),
                shadowColor: 'rgba(0, 0, 0, 0.1)',
                shadowBlur: 10
            }
        },
        itemStyle: {
            normal: {

                color: 'rgb(0,211,255)',
                borderColor: 'rgba(0,211,255,0.1)',
                borderWidth: 10
            }
        },
        // data:[34, 43, 39, 25, 12, 13, 24, 40, 57, 63, 57,45]
        data:link
    }, 

 ]
};

 myChart.setOption(option);


 }
 sco.fetch("YS_objd")
// 7、获取后台数据统计 YS_objf
 sco.YS_obje.url = "index/statistics";
 sco.YS_obje.done=function(re,sco){
   if(re.code==0&&re.data){
    re.data.totalFlow=YSfac.btogb(re.data.totalFlow)
   }
 }
 sco.fetch("YS_obje")

 sco.YS_objf.url = "index/rollLog";
 sco.YS_objf.done=function(re,sco){
   if(re.code==0&&re.data){
    sco.loglength=re.data.rollLog.length>10?10:re.data.rollLog.length;

$('.scrolltext li:even').addClass('lieven');
function scroll(sco){
  $("div.scrolltext").myScroll({
    speed:50, //数值越大，速度越慢
    rowHeight:20 //li的高度
  });
};
scroll()



   }
   

 }
 sco.fetch("YS_objf")
//  sco.YS_listc.url = "alarm/device";
//  sco.YS_listc.params = {
//   currentPage:1,
//   pageSize:10,
//   startDate:YSfac.getNowFormatDate(0),
//   endDate:YSfac.getNowFormatDate(1)
// };
//  sco.YS_listc.done=function(re,sco){
//   sco.YS_lista.data.list.map(function(elem,index){

//         if(elem.topologyNodes.length>0){
//           elem.topologyNodes.map(function(e){
//             if(e.deviceStatus == 0&&re.data.nameMap[e.toDeviceId]){
//               e.deviceStatus = 2;
//             }
//           })
//         }
//   })
//  }
//  sco.fetch("YS_listc")





// setTimeout(function(){


// var link= sco.link;
// var device= sco.device;
// var task= sco.task;


// },1000)










// 线性图



var myCircle1 = Circles.create({
  id:                  'cpup',
  radius:              26,
  value:               80,
  maxValue:            100,
  width:               5,
  text:                function(value){return value + '%';},
  colors:              ['#2b374d', '#ff0200'],
  duration:            400,
  textClass:           'clt',
  styleText:           true
});
var myCircle2 = Circles.create({
  id:                  'ypp',
  radius:              26,
  value:               60,
  maxValue:            100,
  width:               5,
  text:                function(value){return value + '%';},
  colors:              ['#2b374d', '#0de1b1'],
  duration:            400,
  textClass:           'clt',
  styleText:           true
});
var myCircle3 = Circles.create({
  id:                  'ncp',
  radius:              26,
  value:               40,
  maxValue:            100,
  width:               5,
  text:                function(value){return value + '%';},
  colors:              ['#2b374d', '#0de1b1'],
  duration:            400,
  textClass:           'clt',
  styleText:           true
});

var myCircle4 = Circles.create({
  id:                  'wlp',
  radius:              26,
  value:               20,
  maxValue:            100,
  width:               5,
  text:                function(value){return value + '%';},
  colors:              ['#2b374d', '#0de1b1'],
  duration:            400,
  textClass:           'clt',
  styleText:           true
});






function dx(sco){
 var i = 1;
   setInterval(function(){
    if(i==21){i=1};
    var imgsrc0 = '../images/dz00'+ i + '.png';
    var imgsrc1 = '../images/dz01'+ i + '.png';
    var imgsrc2 = '../images/dz02'+ i + '.png';
    var imgsrc3 = '../images/dz10'+ i + '.png';
    var imgsrc4 = '../images/dz11'+ i + '.png';
    var imgsrc5 = '../images/dz12'+ i + '.png';
    var imgsrc6 = '../images/pdz'+ i + '.png';
      $('.dz00').attr("src",imgsrc0);
      $('.dz01').attr("src",imgsrc1);
      $('.dz02').attr("src",imgsrc2);
      $('.dz10').attr("src",imgsrc3);
      $('.dz11').attr("src",imgsrc4);
      $('.dz12').attr("src",imgsrc5);
      $('.pdz').attr("src",imgsrc6);
      i++
   },120)

}
dx()

// function bjxz(sco){
//  var i = 1;
//    setInterval(function(){
//     if(i==41){i=1};
//     var imgsrc = '../images/bjxz'+ i + '.png';
//       $('.kdbg').attr("src",imgsrc);
//       i++
//    },120)

// }
// // bjxz()
// function tjdx(sco){
//  var i = 0;
//    setInterval(function(){
//     if(i==33){i=1};
//     var imgsrc1 = '../images/wjj'+ i + '.png';
//     var imgsrc2 = '../images/sj'+ i + '.png';
//     var imgsrc3 = '../images/ll'+ i + '.png';
//       $('.wjimg').attr("src",imgsrc1);
//       $('.sjimg').attr("src",imgsrc2);
//       $('.llimg').attr("src",imgsrc3);
//       i++
//    },50)

// }
// tjdx()

function sbflag(sco){
  var i = 1;
  setInterval(function(){
    if(i==4){i=1};
    var imgsrc1 = '/images/sarrup'+ i + '.png';
    var imgsrc2 = '/images/sarrdown'+ i +'.png';
    $('.sarrup').attr("src",imgsrc1);
    $('.sarrdown').attr("src",imgsrc2);
    i++
  },500)
}
sbflag()





},


//链路监控模块
linkmonitor:function(sco) {},

// ssdh开关模块
sshdconf:function(sco){
  // 1获取sshd开关
  // 2设置sshd开关

  // 1获取sshd开关
  sco.YS_obja.url="config/sshd/get";
  sco.YS_obja.done=function(re,sco){
    if(re.code==0){
      if(!re.data){
        return false;
      }else{
      sco.YS_objb.params.open =JSON.parse(re.data.configData).open;
    }
    }
  }
  sco.fetch('YS_obja');

  // 2设置sshd开关
  sco.YS_objb.url="config/sshd/set";
  sco.YS_objb.params.open = '';
  sco.YS_objb.done=function(re,sco){
    if(sco.YS_objb.params.open == true){
      layer.msg('开关成功打开！');
    }else{
      layer.msg('开关成功关闭！');
    }
  }
  
},

//一键体验模块
experience:function(sco) {
  sco.templist = [];
  sco.taskarr = [];
  sco.examine = [];
  sco.nowimg = '../images/p0.png';
  sco.nowbaifen = '0%';
  sco.zzjc = 0;
  sco.linkCheck = 0;
  sco.deviceCheck = 0;
  sco.taskCheck = 0;
  sco.tapshow = 0;
  sco.qian =0;
  sco.retest = 0;
  sco.maxStep = 0;
  sco.isyj = 0;


  //获取体检id
  sco.YS_obja.url="examine/allInOne";
  sco.YS_obja.done=function(re,sco){
    if(re.code==0){
      sco.isyj = 1;
      sco.YS_objb.params.checkId = re.data.checkId;
      sco.YS_objb.params.index = 0;
      sco.examine = [];
      sco.fetch('YS_objb');
    }
  }  
  

  //获取一键体检结果
  sco.YS_objb.url="examine/checkResult";
  sco.YS_objb.done=function(re,sco){

    if(re.code==0){
      re.data.checkItems.map(function(elem){
        sco.examine.push(elem);
      });
    }
  }  



  //设备体检
  sco.YS_objc.url="examine/device";
  sco.YS_objc.done=function(re,sco){
    sco.isyj= 0;
    if(re.code==0){
      sco.tapshow = 1;
      // sco.examine = re.data.checkItems;
      sco.deviceCheck = 1;
      sco.YS_objb.params.checkId = re.data.checkId;
      sco.retest = 2;
      if(sco.YS_objb.params.index)delete sco.YS_objb.params.index;
      sco.examine = [];
      sco.fetch('YS_objb');
    }
  }  


  //链路体检
  sco.YS_objd.url="examine/link";
  sco.YS_objd.done=function(re,sco){
    sco.isyj= 0;
    if(re.code==0){
      sco.tapshow = 1;
      sco.examine = re.data.checkItems;
      sco.linkCheck = 1;
      sco.YS_objb.params.checkId = re.data.checkId;
      sco.retest = 1;
      if(sco.YS_objb.params.index)delete sco.YS_objb.params.index;
      sco.examine = [];
      sco.fetch('YS_objb');


    }
  }  


  //任务体检
  sco.YS_obje.url="examine/task";




  sco.YS_obje.done=function(re,sco){
    sco.isyj= 0;
    if(re.code==0){
      sco.tapshow = 1;
      sco.examine = re.data.checkItems;
      sco.taskCheck = 1;
      sco.YS_objb.params.checkId = re.data.checkId;
      sco.retest = 3;
      if(sco.YS_objb.params.index)delete sco.YS_objb.params.index;
      sco.examine = [];
      sco.fetch('YS_objb');
    }
  }  

  sco.YS_objf.url="user/loginUserInfo";

  //获取链路信息接口
  sco.YS_lista.url="link/queryAll";
  sco.YS_lista.done = function(re, sco) {
      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_lista');
console.log(sco.YS_lista);

  //获取设备接口
  sco.YS_listb.url="device/query8Page";
  sco.YS_listb.params = {currentPage:1,pageSize:9999};
  sco.YS_listb.done = function(re, sco) {
  }
  sco.fetch('YS_listb');

  //获取任务接口
  sco.YS_listc.url="task/queryAll";
  sco.YS_listc.params = {currentPage:1,pageSize:9999}
  sco.YS_listc.done = function(re, sco) {
          setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_listc');


  // layer.open({
  //         type: 1,
  //         title: false, //不显示标题
  //         closeBtn: 0,
  //         shadeClose:false, 
  //         shade:0.5, 
  //         area: ['auto', 'auto'], //宽高
  //         content: $('#zsk')
  //       });
  //       
  //       
  function qian(sco){
  var i = 0;
   var int = setInterval(function(){
    if(i==13){i=0};
    var imgsrc = '../images/qian'+ i + '.png';
      $('.bf-qian').attr("src",imgsrc);
      i++
   },150)
  return int;

}
  sco.qian = qian();


function spline(sco){
  var i = 1;
  var int = setInterval(function(){
    if(i==9){i=1};
    var imgsrc = '../images/spline'+ i + '.png';
      $('.spline').attr("src",imgsrc);
      i++
   },120)
  return int;
}
sco.spline = spline

function xping(sco){
  var i = 1;
  var int = setInterval(function(){
    if(i==26){i=1};
    var imgsrc = '../images/xp'+ i + '.png';
      $('.xping').attr("src",imgsrc);
      i++
   },120)
  return int;
}
  sco.xping = xping()
},

// 任务监控模块
taskmonitor:function(sco) {
  sco.tapshow=0;
  sco.alarmlevel=["系统不可用","必须马上采取行动的事件","关键的事件","错误事件","警告事件"," 普通但重要的事件","有用的信息","调试信息"]
  // 1.获取数据交互数据
  // 2.获取数据导入数据


  // 1.获取数据交互数据
  sco.YS_plista.url="/task/monitor";



  sco.YS_plista.done = function(re, sco) {
  };


  // 2.获取链路名称
  sco.YS_lista.url="link/queryAll";
  sco.YS_lista.done = function(re, sco) {
    if (re.code==0) {
          sco.YS_plista.params.linkId = re.data.list[0].linkId;
            sco.fetch('YS_plista');
    }
      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_lista');

},

//连接报警模块
linkalarm:function(sco) {
  sco.uid=YSfac.getstore("uid");
  sco.alarmtype='';
  sco.tapshow=0;
  sco.haszan = 0;
  sco.paged=false;
  sco.alarmlevel=["系统不可用","必须马上采取行动的事件","关键的事件","错误事件","警告事件"," 普通但重要的事件","有用的信息","调试信息"]
  // 1.链接报警列表
  // 2.搜索连接报警

  // 1.链接报警列表
  sco.YS_plista.url="alarm/link";
  sco.YS_plista.method="POST";
  sco.YS_plista.before=function(sco){
  // if(!sco.YS_plista.params.name&&!sco.YS_plista.params.type&&!sco.YS_plista.params.key&&!sco.YS_plista.params.startDate&&!sco.YS_plista.params.endDate){
  //     layer.msg("搜索条件不能为空！");return false
  //   };
   YSfac.load2(1,"加载中...");
  }
  sco.YS_plista.done = function(re,sco){
    if(re.code==0){
      layer.closeAll();
        re.data.list.map(function(el){
        var time =   YSfac.numtosecond(el.createTime);
        el.createTime =time;
        el.name=re.data.nameMap[el.bid];
            sco.newList.map(function(elem) {
              if (el.type == elem.type) {
                el.typename = elem.name;
              } else {
                return;
              }
            });
         
            
      })
      }

      sco.YS_plista.data = re.data
    }



  // 获取链路信息
  sco.YS_lista.url="link/queryAll";

  sco.YS_lista.done = function(re, sco) {
      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_lista');


    // 获取报警类型
    sco.YS_listb.url="alarm/getAllAlarmType";
    sco.YS_listb.method="POST"; 
    sco.YS_listb.done = function(re, sco) {
    sco.newList = [];
re.data.list.forEach(function(v,k){ 
        for (var v2 in v) {
          if( v.hasOwnProperty( v2 ) ) {          
           var o = {type:v2,name:v[v2]}
           sco.newList.push(o)
          } 
        }
 })




  sco.fetch('YS_plista');


      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_listb');




 sco.YS_plistb.url="knowledge/query8AlarmType";
 sco.YS_plistb.method="POST";
 sco.YS_plistb.params={currentPage:1,pageSize:3};
 sco.YS_plistb.done=function(re,sco){
   if(re.code==0&&re.data.list.length>0){
    if(re.data.total>4){sco.paged=true};
      sco.jylist=re.data.list

   }
  }

 sco.YS_obja.url="knowledge/insert";
 sco.YS_obja.method="POST";
 sco.YS_obja.done=function(re,sco){
  if(re.code==0){
    layer.msg("新增成功！")
  }else{
    layer.msg("新增失败！")
  }
 }
 sco.YS_objb.url="knowledge/up";




},
//设备报警模块
equipalarm:function(sco) {
  sco.haszan = 0;
  sco.uid=YSfac.getstore("uid");
  sco.alarmtype='';
  sco.tapshow=0;
  sco.paged=false;
  sco.alarmlevel=["系统不可用","必须马上采取行动的事件","关键的事件","错误事件","警告事件"," 普通但重要的事件","有用的信息","调试信息"]
  
  // 1.设备报警列表
  // 2.搜索设备报警
  
  

  sco.currentDeviceId = YSfac.getstore('currentDeviceId');
  sco.currentLevel = YSfac.getstore('currentLevel');




  // 1.设备报警列表
  sco.YS_plista.url="alarm/device";
    sco.YS_plista.before=function(sco){
  // if(!sco.YS_plista.params.name&&!sco.YS_plista.params.type&&!sco.YS_plista.params.key&&!sco.YS_plista.params.startDate&&!sco.YS_plista.params.endDate){
  //     layer.msg("搜索条件不能为空！");return false
  //   };
    sco.currentDeviceId?sco.YS_plista.params.bid = sco.currentDeviceId:"";
    sco.currentLevel?sco.YS_plista.params.level = sco.currentLevel:"";
  }
  sco.YS_plista.done = function(re,sco){
    if(re.code==0){
        YSfac.setstore('currentDeviceId',"");
        YSfac.setstore('currentLevel',"");
        sco.currentDeviceId = "";
        sco.currentLevel = "";
        re.data.list.map(function(el){
        var time =   YSfac.numtosecond(el.createTime);
        el.createTime =time;
        el.name=re.data.nameMap[el.bid];
        sco.newList.map(function(elem) {
              if (el.type == elem.type) {
                el.typename = elem.name;
              } else {
                return;
              }
            });
      })
      sco.YS_plista.data = re.data;
            setTimeout(function(){
           layui.use('form', function(){
             layui.form.render();
        }); 
      },500)

    }
  }


  // 获取设备信息
 sco.YS_listb.url="device/query8Page";
  sco.YS_listb.params ={currentPage:1,pageSize:9999};
  
  sco.YS_listb.done =function(re, sco) {
      setTimeout(function(){
           layui.use('form', function(){
             layui.form.render();
        }); 
      },500)
  }
  
  sco.fetch('YS_listb');



    sco.YS_listc.url="alarm/getAllAlarmType";
    sco.YS_listc.method="POST"; 
    sco.YS_listc.done = function(re, sco) {
    sco.newList = [];
re.data.list.forEach(function(v,k){ 
        for (var v2 in v) {
          if( v.hasOwnProperty( v2 ) ) {          
           var o = {type:v2,name:v[v2]}
           sco.newList.push(o)
          } 
        }
 })


  sco.fetch('YS_plista');
      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_listc');




















 sco.YS_plistb.url="knowledge/query8AlarmType";
 sco.YS_plistb.method="POST";
 sco.YS_plistb.params={currentPage:1,pageSize:9999};
 sco.YS_plistb.done=function(re,sco){
   if(re.code==0&&re.data.list.length>0){
    if(re.data.total>4){sco.paged=true};
      sco.jylist=re.data.list
   }
  }

 sco.YS_obja.url="knowledge/insert";
 sco.YS_obja.method="POST";
 sco.YS_obja.done=function(re,sco){
  if(re.code==0){
    layer.msg("新增成功！")
  }else{
    layer.msg("新增失败！")
  }
 }
 sco.YS_objb.url="knowledge/up";


   
},

// 任务报警模块
taskalarm:function(sco) {
  sco.uid=YSfac.getstore("uid");
  sco.haszan = 0;
  sco.alarmtype='';
  sco.paged=false;
    // 1.任务报警列表
  // 2.搜索任务报警
  sco.tapshow=0;
  sco.currentData = YSfac.getstore('currentData');
  sco.currentTaskId = "";
  sco.currentLevel = "";
  if(sco.currentData){
    sco.currentTaskId = sco.currentData.taskId;
    sco.currentLevel = sco.currentData.level;
  }



  // 1.任务报警列表
  sco.YS_plista.url="alarm/task";
  sco.YS_plista.before = function(sco){
    if(sco.currentData){
      sco.YS_plista.params.bid = sco.currentData.taskId;
      sco.YS_plista.params.level = sco.currentData.level;
    }
  }
  sco.YS_plista.done = function(re,sco){
    YSfac.setstore('currentData',"");
    sco.currentData = "";
    if(re.code==0){
        re.data.list.map(function(el){
        var time =   YSfac.numtosecond(el.createTime);
        el.createTime =time;
        el.name=re.data.nameMap[el.bid]
sco.newList.map(function(elem) {
              if (el.type == elem.type) {
                el.typename = elem.name;
              } else {
                return;
              }
            });

      })
      sco.YS_plista.data = re.data
    }
  }

  

 // 获取任务列表
  sco.YS_listc.url="task/queryAll";
  sco.YS_listc.done = function(re, sco) {
    console.log(sco.currentData);
      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_listc');


 sco.YS_plistb.url="knowledge/query8AlarmType";
 sco.YS_plistb.method="POST";
 sco.YS_plistb.params={currentPage:1,pageSize:9999};
 sco.YS_plistb.done=function(re,sco){
   if(re.code==0&&re.data.list.length>0){
    if(re.data.total>4){sco.paged=true};
      sco.jylist=re.data.list
   }
  }


    sco.YS_listb.url="alarm/getAllAlarmType";
    sco.YS_listb.method="POST"; 
    sco.YS_listb.done = function(re, sco) {
    sco.newList = [];
re.data.list.forEach(function(v,k){ 
        for (var v2 in v) {
          if( v.hasOwnProperty( v2 ) ) {          
           var o = {type:v2,name:v[v2]}
           sco.newList.push(o)
          } 
        }
 })


  sco.fetch('YS_plista');
      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_listb');








 sco.YS_obja.url="knowledge/insert";
 sco.YS_obja.method="POST";
 sco.YS_obja.done=function(re,sco){
  if(re.code==0){
    layer.msg("新增成功！")
  }else{
    layer.msg("新增失败！")
  }
 }
 sco.YS_objb.url="knowledge/up";


  // sco.YS_obja.before=function(sco){
  //   if(!sco.YS_obja.params.name&&!sco.YS_obja.params.jibie&&!sco.YS_obja.params.type&&!sco.YS_obja.params.key&&!sco.YS_obja.params.bgtime&&!sco.YS_obja.params.edtime){
  //     layer.msg("搜索条件不能为空！");return false
  //   };
   
},
//报警配置模块
alarmconf:function(sco) {
  sco.dxbj=1;
  sco.yjbj=1;
  sco.YS_obja.url="config/alarm/set";
  // sco.YS_obja.method="POST";
  sco.YS_obja.before=function(sco){
    if(!sco.YS_obja.params.baseConfig.alarmtime){layer.tips("请输入报警间隔时间！","#alarmtime",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!(/^\+?[1-9][0-9]*$/).test(sco.YS_obja.params.baseConfig.alarmtime)){layer.tips("请输入正确的报警间隔时间！","#alarmtime",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!sco.YS_obja.params.baseConfig.maxnum){layer.tips("请输入报警最大条数！","#maxnum",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!(/^\+?[1-9][0-9]*$/).test(sco.YS_obja.params.baseConfig.maxnum)){layer.tips("请输入合法的报警最大条数！","#maxnum",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!sco.YS_obja.params.baseConfig.timeStart){layer.tips("设置开始登录时段！","#timeStart",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!sco.YS_obja.params.baseConfig.timeEnd){layer.tips("请设置结束登录时段！","#timeEnd",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(sco.dxbj==1){
      if(!sco.YS_obja.params.smsConfig.fwzxhm){layer.tips("请输入服务中心号码！","#fwzxhm",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
      if(!sco.YS_obja.params.smsConfig.fstel){layer.tips("请输入接收手机号码！","#fstel",{tips: [3, 'rgba(0,0,0,.8)','']});return false}
      if(!(/^1[34578]\d{9}$/.test(sco.YS_obja.params.smsConfig.fstel))&&!(/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(sco.YS_obja.params.smsConfig.fstel))){layer.tips("请输入正确的手机号码！","#fstel",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  
    }
    if(sco.yjbj==1){
      if(!sco.YS_obja.params.emailConfig.fwmaildz){layer.tips("请输入邮件服务器地址！","#fwmaildz",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
      if(!sco.YS_obja.params.emailConfig.ports){layer.tips("请输入端口！","#ports",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
      if(!sco.YS_obja.params.emailConfig.emailbj){layer.tips("请输入邮箱地址！","#emailbj",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
      if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(sco.YS_obja.params.emailConfig.emailbj))){layer.tips("请输入合法的邮箱地址！","#emailbj",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
      if(!sco.YS_obja.params.emailConfig.alarmuser){layer.tips("请输入用户名！","#alarmuser",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
      if(!sco.YS_obja.params.emailConfig.alarpwd){layer.tips("请输入密码！","#alarpwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
      if(!sco.YS_obja.params.emailConfig.bjtitle){layer.tips("请输入报警标题！","#bjtitle",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    }
  //   var baseConfig = {
  //     alarmtime:sco.YS_obja.params.baseConfig.alarmtime,
  //     maxnum:sco.YS_obja.params.baseConfig.maxnum,
  //     timeStart:sco.YS_obja.params.baseConfig.timeStart,
  //     timeEnd:sco.YS_obja.params.baseConfig.timeEnd
  //   }
  //   var smsConfig ={
  //     fwzxhm:sco.YS_obja.params.smsConfig.fwzxhm,
  //     fstel:sco.YS_obja.params.smsConfig.fstel
  //   }
  //   var emailConfig ={
  //     fwmaildz:sco.YS_obja.params.emailConfig.fwmaildz,
  //     ports:sco.YS_obja.params.emailConfig.ports,
  //     emailbj:sco.YS_obja.params.emailConfig.emailbj,
  //     alarmuser:sco.YS_obja.params.emailConfig.alarmuser,
  //     alarpwd:sco.YS_obja.params.emailConfig.alarpwd,
  //     bjtitle:sco.YS_obja.params.emailConfig.bjtitle

  //   }
  //   sco.YS_obja.params.baseConfing=JSON.stringify(baseConfig);
  //   sco.YS_obja.params.smsConfig=JSON.stringify(smsConfig);
  //   sco.YS_obja.params.emailConfig=JSON.stringify(emailConfig)
  //   // if(!sco.YS_obja.params.alarmlevej && !sco.YS_obja.params.alarmlevec){layer.tips("请选择报警级别","#check1",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  //   // if(!sco.YS_obja.params.alarmwayd && !sco.YS_obja.params.alarmwayy){layer.tips("请选择接收报警方式","#dxswitch",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  //   // if(!sco.YS_obja.params.fwcenter){layer.tips("请输入服务中心号","#fwcenter",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  //   // if(!sco.YS_obja.params.phone){layer.tips("请输入手机号码！","#phone",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
  //   // if(!(/^1[34578]\d{9}$/.test(sco.YS_obja.params.phone))&&!(/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(sco.YS_obja.params.phone))){layer.tips("请输入正确的手机号码！","#phone",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  // delete sco.YS_obja.params.baseConfig.alarmtime;
  // delete sco.YS_obja.params.baseConfig.maxnum;
  // delete sco.YS_obja.params.baseConfig.timeStart;
  // delete sco.YS_obja.params.baseConfig.timeEnd;
  // delete sco.YS_obja.params.smsConfig.fwzxhm;
  // delete sco.YS_obja.params.smsConfig.fstel;
  // delete sco.YS_obja.params.emailConfig.fwmaildz;
  // delete sco.YS_obja.params.emailConfig.ports;
  // delete sco.YS_obja.params.emailConfig.emailbj;
  // delete sco.YS_obja.params.emailConfig.alarmuser;
  // delete sco.YS_obja.params.emailConfig.alarpwd;
  // delete sco.YS_obja.params.emailConfig.bjtitle;
  }
  sco.YS_obja.done=function(re,sco){
    if(re.code==0){
re.msg?layer.msg(re.msg):layer.msg('配置成功！');
  }else{
re.msg?layer.msg(re.msg):layer.msg('配置失败！');
    }
  }  



  sco.YS_objb.url="config/alarm/get";
  sco.YS_objb.done=function(re,sco){
    if(re.code==0){

     var data = JSON.parse(re.data.configData);

     sco.YS_obja.params.baseConfig=data.baseConfig;
     sco.YS_obja.params.smsConfig=data.smsConfig;
     sco.YS_obja.params.emailConfig=data.emailConfig;
     if(!sco.YS_obja.params.smsConfig.isOpen){
      sco.dxbj=0
     }else{
      sco.YS_obja.params.smsConfig=data.smsConfig;
     }
     if(!sco.YS_obja.params.emailConfig.isOpen){
      sco.yjbj=0
    }else{
      sco.YS_obja.params.emailConfig=data.emailConfig;
    }
     

     
    }
    
  }
  sco.fetch("YS_objb")

},
//任务执行统计模块
tasksta:function(sco) {
   // 1.任务统计列表
  // 2.搜索任务
  sco.todates = '';
  sco.tapshow=0;

  // 1.设备列表
  sco.YS_plista.url="/analysis/task";

  sco.YS_plista.done = function(re, sco) {
    if (re.code==0) {
      re.data.list.map(function(el){
        var time =   YSfac.numtosecond(el.createTime);
        el.createTime =time;
      })
       sco.YS_objb.params.linkId = sco.YS_plista.params.linkId;
        if (sco.YS_plista.params.startDate) { sco.YS_objb.params.startDate =sco.YS_plista.params.startDate ;} 
        if (sco.YS_plista.params.endDate) { sco.YS_objb.params.endDate =sco.YS_plista.params.endDate ;} 
       sco.fetch("YS_objb");
             setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
    }
  }

sco.istb=0;
sco.isgb=0;
sco.ismb=0;
sco.isb=0;
sco.istb2=0;
sco.isgb2=0;
sco.ismb2=0;
sco.isb2=0;


  // 查询任务统计总计 
   sco.YS_objb.url="analysis/taskCount";
   sco.YS_objb.done = function(re,sco){
      if(re.code==0){

     //小于 1M 显示B
    if (re.data.sum.successFlow<1048576){
          sco.isb=1;
        }
//大于1M 小于1G  显示M
      if (re.data.sum.successFlow>1048576&&re.data.sum.successFlow<1073741824)
        {
          re.data.sum.successFlow = YSfac.btomb(re.data.sum.successFlow);
          sco.ismb=1;
        }
//大于1G 显示G
        if (re.data.sum.successFlow>1073741824&&re.data.sum.successFlow<1099511627776) {
          re.data.sum.successFlow = YSfac.btogb(re.data.sum.successFlow);
           sco.isgb=1;
        }
//大于1T 显示T
        if (re.data.sum.successFlow>1099511627776) {
          re.data.sum.successFlow = YSfac.btotb(re.data.sum.successFlow);
           sco.istb=1;
        }


//
      if (re.data.sum.errorFlow<1048576){
          sco.isb2=1;
        }
              if (re.data.sum.errorFlow>1048576&&re.data.sum.errorFlow<1073741824){
          re.data.sum.errorFlow = YSfac.btomb(re.data.sum.errorFlow);
          sco.ismb2=1;
        }

        if (re.data.sum.errorFlow>1073741824&&re.data.sum.errorFlow<1099511627776) {
          re.data.sum.errorFlow = YSfac.btogb(re.data.sum.errorFlow);
           sco.isgb2=1;
        }

        if (re.data.sum.errorFlow>1099511627776) {
          re.data.sum.errorFlow = YSfac.btotb(re.data.sum.errorFlow);
           sco.istb2=1;
        }




        
      }
    }


  // 获取链路信息
  sco.YS_lista.url="link/queryAll";
  sco.YS_lista.done = function(re, sco) {
      sco.fetch('YS_plista');
        
    sco.YS_plista.params.linkId = re.data.list[0].linkId;
    

      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_lista');



   
},

//设备运行统计模块
equipsta:function(sco) {
   // 1.设备统计列表
  // 2.搜索设备
  sco.tapshow=0;

  // 获取链路信息
  sco.YS_lista.url="link/queryAll";
  sco.YS_lista.done = function(re, sco) {
    sco.fetch('YS_plista');
        
    sco.YS_plista.params.linkId = re.data.list[0].linkId;
    

      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_lista');

sco.todates='';
  // 1.设备日志列表
  sco.YS_plista.url="/analysis/device";

  sco.YS_plista.done = function(re, sco) {
           sco.YS_objb.params.linkId = sco.YS_plista.params.linkId;
        if (sco.YS_plista.params.startDate) { sco.YS_objb.params.startDate =sco.YS_plista.params.startDate ;} 
        if (sco.YS_plista.params.endDate) { sco.YS_objb.params.endDate =sco.YS_plista.params.endDate ;} 

       sco.fetch("YS_objb");
    if (re.code==0) {

      re.data.list.map(function(el){
           re.data.list.map(function(el){
    
           el.name=re.data.nameMap[el.bid]
      })
    
      })
              setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)



    }
  }


  // sco.YS_listb.url="device/query8Page";
  // sco.YS_listb.params = {currentPage:1,pageSize:9999};
  // sco.YS_listb.done = function(re, sco) {
  //     sco.fetch('YS_plista');

  // }







  // 查询设备运行统计
   sco.YS_objb.url="analysis/deviceCount";

   sco.YS_objb.done = function(re,sco){
      if(re.code==0){
      
      }
    }



},

//设备日志模块
equiplog:function(sco) {
  sco.alarmlevel=["系统不可用","必须马上采取行动的事件","关键的事件","错误事件","警告事件"," 普通但重要的事件","有用的信息","调试信息"]
  // 1.设备日志列表
  // 2.搜索设备日志
  
  sco.tapshow-0;
  // 1.设备日志列表
  sco.YS_plista.url="log/device";
   sco.YS_plista.done=function(re,sco){
    if(re.code == 0){
            setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
      re.data.list.map(function(el){
        var time =   YSfac.numtosecond(el.createTime);
        // time = YSfac.numtosecond(time);
        // time.toLocaleDateString().replace(/\//g, "-") + " " + time.toTimeString().substr(0, 8)
        el.createTime =time;//time
        el.deviceName=re.data.ipMap[el.host].deviceName;
        el.id=re.data.ipMap[el.host].deviceId;
        el.deviceType=re.data.ipMap[el.host].deviceType;
        
      })
      sco.YS_plista.data = re.data
    }
  }



  sco.YS_listb.url="device/query8Page";
  sco.YS_listb.params = {currentPage:1,pageSize:9999};
  sco.YS_listb.done = function(re, sco) {
      sco.fetch('YS_plista');
    sco.YS_plista.params.deviceId = re.data.list[0].deviceId;
      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_listb');


    // if(!sco.YS_obja.params.name&&!sco.YS_obja.params.jibie&&!sco.YS_obja.params.type&&!sco.YS_obja.params.key&&!sco.YS_obja.params.bgtime&&!sco.YS_obja.params.edtime){
    //   layer.msg("搜索条件不能为空！");return false
    // };

    
},

//业务日志模块
businesslog:function(sco) {
  // 1.设备日志列表
  // 2.搜索设备日志
  

  // 1.设备日志列表
  sco.YS_plista.url="/businesslog/businesslog";
  sco.YS_plista.done=function(re,sco){
    if(re.code == 0){
      re.data.list.map(function(el){
        var time =  el.createTime;
        time = YSfac.numtosecond(time);
        el.createTime = time
      })
      sco.YS_plista.data = re.data
    }
  }

  sco.fetch('YS_plista');



    // if(!sco.YS_obja.params.name&&!sco.YS_obja.params.jibie&&!sco.YS_obja.params.type&&!sco.YS_obja.params.key&&!sco.YS_obja.params.bgtime&&!sco.YS_obja.params.edtime){
    //   layer.msg("搜索条件不能为空！");return false
    // };
},

//用户日志模块
userlog:function(sco) {
  // 1.设备日志列表
  // 2.搜索设备日志
  
  sco.tapshow=0;
  // 1.设备日志列表
  sco.YS_plista.url="log/user";

  sco.YS_plista.done=function(re,sco){
    if(re.code == 0){
      re.data.list.map(function(el){
        var time =  el.createTime;
        time = YSfac.numtosecond(time);
        el.createTime = time;
        el.name=re.data.nameMap[el.uid]
      })
      sco.YS_plista.data = re.data
    }
  }
  sco.fetch('YS_plista');


  sco.YS_listb.url="user/queryAll";
  sco.YS_listb.params = {currentPage:1,pageSize:9999};
  sco.YS_listb.done = function(re, sco) {
      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_listb');

 
},
 
// 体验日志模块
explog:function(sco) { 
  sco.exptype=["","系统一键自检","设备体检","任务体检","链体检路","本系统环境自检"]
  // 1.设备日志列表
  // 2.搜索设备日志

  sco.tapshow = 0;
  // 1.设备日志列表
  sco.YS_plista.url="log/detection";
   sco.YS_plista.done=function(re,sco){
    console.log(re);
    if(re.code == 0){
      re.data.list.map(function(el){
        var time =  el.createTime;
        time = YSfac.numtosecond(time);
        el.createTime = time;

        el.name = re.data.nameMap[el.uid];

      })
      sco.YS_plista.data = re.data
    }
  }
  sco.fetch('YS_plista');


  
  sco.YS_listb.url="user/queryAll";
  sco.YS_listb.params = {currentPage:1,pageSize:9999};
  sco.YS_listb.done = function(re, sco) {

      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },500)
  }
  sco.fetch('YS_listb');


},

//设备管理模块
equipadmin:function(sco) {
  sco.deviceServiceList =[];
  sco.tapshow=0;
  sco.countnum=0;
  console.log(111);
  debugger;
  sco.iconlist=[
   {id:1,name:"GAP",status:1},{id:2,name:"路由器",status:1},{id:3,name:"防火墙",status:1},{id:4,name:"IPS",status:1},{id:5,name:"可信任安全网关",status:1},{id:6,name:"交换器",status:1},{id:7,name:"安管隔离网闸",status:1},{id:8,name:"编码器",status:1},{id:9,name:"FGAP",status:1},{id:10,name:"光交换",status:2}
  ,{id:11,name:"矩阵抽象",status:2},{id:12,name:"摄像头",status:2},{id:13,name:"3AS",status:2},{id:14,name:"ADS",status:2},{id:15,name:"AS",status:2},{id:16,name:"ASM",status:2},{id:17,name:"AWS",status:2},{id:18,name:"DTI",status:2},{id:19,name:"EWP",status:3},{id:20,name:"IWP",status:3}
  ,{id:21,name:"MOC",status:3},{id:22,name:"PAS",status:3},{id:23,name:"SS",status:3},{id:24,name:"SSE",status:3},{id:25,name:"TAS",status:3},{id:26,name:"TMS",status:3},{id:27,name:"UAS",status:3},{id:28,name:"UMS",status:4},{id:29,name:"TZ",status:4},{id:30,name:"存储陈列",status:4}
  ,{id:31,name:"磁盘陈列",status:4},{id:32,name:"摄像头",status:4},{id:33,name:"服务器",status:4},{id:34,name:"矩阵",status:4},{id:35,name:"内网管理员",status:4},{id:36,name:"入侵ids",status:4},{id:37,name:"摄像头2",status:5},{id:38,name:"数据库",status:5},{id:39,name:"云控制台",status:5},{id:40,name:"终端",status:5}
  ,{id:41,name:"主机ids",status:5},{id:42,name:"政党军机构",status:5},{id:43,name:"公安驻地外",status:5},{id:44,name:"海关",status:5},{id:45,name:"民政机构",status:5},{id:46,name:"内网",status:6},{id:47,name:"社保机构",status:6},{id:48,name:"社会企事业单位",status:6},{id:49,name:"视频内网",status:6},{id:50,name:"视频终端",status:6}
  ,{id:51,name:"外部接入单位",status:6},{id:52,name:"外网",status:6},{id:53,name:"操作台",status:6},{id:60,name:"文件夹",status:6}];
  sco.devicetype=["","文件","探针","防火墙","可信边界安全网关","入侵检测系统","安全隔离网闸","网络防毒设备","入侵防御系统","集控系统","路由器","交换机","web应用防火墙","安全单向隔离光闸","导入前置机","导入服务器","FTP库服务器","数据库服务器","信任端数据交换系统","非信任端数据交换系统","视频接入认证服务器","视频用户认证服务器","终端","其他类型服务器","视频平台服务器","IP音视频终端","应用服务器","其他终端设备"];
  sco.area=["","路由接入区","边界保护区","应用服务器","安全隔离区","安全检测与管理区"]
  // 1.设备列表
  // 2.添加设备
  // 3.编辑设备
  

  // 1.设备列表
  sco.YS_plista.url="device/query8Page";

  sco.YS_plista.method='POST';
  sco.YS_plista.done= function(re,sco){
   if(re.code==0){
     sco.YS_plista.data=re.data
   }
  
  }
  sco.fetch('YS_plista');
  
  // 2.添加设备
  sco.YS_obja.url="device/insert";
  sco.YS_obja.method='POST';
  sco.YS_obja.params={deviceName:"",area:"",netArea:"",icon:"",deviceType:"",ip:"",deviceServiceList:[]}
  sco.YS_obja.before= function(sco){
    // sco.YS_obja.params.icon = "1123";
    if(!sco.YS_obja.params.deviceName){
      layer.tips("请输入设备名称！","#name",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };
    if(!sco.YS_obja.params.deviceType){
      layer.tips("请选择设备类型！","#type",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };
    if(!sco.YS_obja.params.area){
      layer.tips("请选择所属区域！","#area",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };
    if(!sco.YS_obja.params.netArea){
     layer.tips("请选择所属网络！","#net",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };
     if(!sco.YS_obja.params.ip){
     layer.tips("请输入IP地址！","#ip",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };
    if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_obja.params.ip)){layer.tips("请输入合法的IP地址！","#ip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
 


    
    if(sco.deviceServiceList){
      sco.deviceServiceList.map(function(elem,index){
        sco.YS_obja.params['deviceServiceList['+index+'].serviceName'] = elem.serviceName;
        sco.YS_obja.params['deviceServiceList['+index+'].serviceType'] = elem.serviceType;
        sco.YS_obja.params['deviceServiceList['+index+'].ip'] = elem.ip;

        sco.YS_obja.params['deviceServiceList['+index+'].configDataStr'] = JSON.stringify(elem.configData);
        // sco.YS_obja.params['deviceServiceList['+index+'].configData.name'] = elem.configData.name;
        // sco.YS_obja.params['deviceServiceList['+index+'].configData.pwd'] = elem.configData.pwd;
        // sco.YS_obja.params['deviceServiceList['+index+'].configData.port'] = elem.configData.port;
        // sco.YS_obja.params['deviceServiceList['+index+'].configData.rootPath'] = elem.configData.rootPath;
        // sco.YS_obja.params['deviceServiceList['+index+'].configData.community'] = elem.configData.community;
        // sco.YS_obja.params['deviceServiceList['+index+'].configData.version'] = elem.configData.version;

      })
      
    }else{
      delete sco.YS_obja.params.deviceServiceList;
    }


    // delete sco.YS_obja.params.deviceServiceList;
    // sco.YS_obja.params.deviceServiceList = [];
  }
  sco.YS_obja.done= function(re,sco){
    if(re.code==0){
 // re.msg?layer.msg(re.msg):layer.msg("添加成功！");
    re.msg?YSfac.load(1,re.msg):YSfac.load(1,"添加成功");
      setTimeout(function(){location.reload()},1000)

        sco.fetch('YS_plista')
        sco.YS_obja.params={};
   
      
    }else{
  //re.msg?layer.msg(re.msg):layer.msg("添加失败！");
  re.msg?YSfac.load(1,re.msg):YSfac.load(1,"添加失败");
    }
  }
  // 3.编辑设备
  sco.YS_objb.url="device/update";
  sco.YS_objb.params={deviceName:"",area:"",netArea:"",icon:"",deviceType:"",ip:"",deviceServiceList:[]};
  sco.YS_objb.method='POST';
  sco.YS_objb.before= function(sco){
 
    
    if(sco.deviceServiceList){
      sco.deviceServiceList.map(function(elem,index){
        sco.YS_objb.params['deviceServiceList['+index+'].serviceName'] = elem.serviceName;
        sco.YS_objb.params['deviceServiceList['+index+'].serviceType'] = elem.serviceType;
        sco.YS_objb.params['deviceServiceList['+index+'].ip'] = elem.ip;


        sco.YS_objb.params['deviceServiceList['+index+'].configDataStr'] = JSON.stringify(elem.configData);

        // sco.YS_objb.params['deviceServiceList['+index+'].configData.name'] = elem.configData.name;
        // sco.YS_objb.params['deviceServiceList['+index+'].configData.pwd'] = elem.configData.pwd;
        // sco.YS_objb.params['deviceServiceList['+index+'].configData.port'] = elem.configData.port;
        // sco.YS_objb.params['deviceServiceList['+index+'].configData.rootPath'] = elem.configData.rootPath;
        // sco.YS_objb.params['deviceServiceList['+index+'].configData.community'] = elem.configData.community;
        // sco.YS_objb.params['deviceServiceList['+index+'].configData.version'] = elem.configData.version;

      })
      
    }


    delete sco.YS_objb.params.deviceServiceList;
      }
  sco.YS_objb.done= function(re,sco){
    if(re.code==0){
          re.msg?YSfac.load(1,re.msg):YSfac.load(1,"保存成功");
      setTimeout(function(){
        sco.tapshow=0;
        sco.fetch('YS_plista')
      },1000)
      
    }else{
 re.msg?YSfac.load(1,re.msg):YSfac.load(1,"保存失败");
    }
  }

  // 4.删除设备
  sco.YS_objc.url="device/delete";
  sco.YS_objc.done= function(re,sco){
    if(re.code==0){
      layer.closeAll();
      layer.msg("删除成功！");
      
      setTimeout(function(){
        location.reload();
        // layer.closeAll();
        // sco.fetch('YS_plista')
      },1000)
      
    }else{
      layer.msg("删除失败！");
    }
  }



  // 5查询设备消耗资源
  sco.YS_objd.url="device/queryExplorer";
  sco.YS_objd.params={deviceId:""}
  sco.YS_objd.done=function(re,sco){
    if(re.code==0&&re.data&&re.data['deviceExplorer']){
  var cpu = re.data.deviceExplorer.cpu.useRateStr;
  var disk = re.data.deviceExplorer.disk.useRateStr;
  var mem = re.data.deviceExplorer.mem.useRateStr;
  var ecpu = "ecpu" + sco.YS_objd.params.deviceId;
  var eyp = "eyp" + sco.YS_objd.params.deviceId;
  var enc = "enc" + sco.YS_objd.params.deviceId;
  cpu = Number(cpu.replace(/\%/g,''));
  disk = Number(disk.replace(/\%/g,''));
  mem = Number(mem.replace(/\%/g,''))
  
         var myCircle1 = Circles.create({
  id:                  ecpu,
  radius:              32,
  value:               cpu,
  maxValue:            100,
  width:               6,
  text:                function(value){return value + '%' + "<br>cpu";},
  colors:              ['#868db6', '#ffb71a'],
  duration:            400,
  textClass:           'clt',
  styleText:           true
});
  var myCircle2 = Circles.create({
  id:                  eyp,
  radius:              32,
  value:               disk,
  maxValue:            100,
  width:               6,
  text:                function(value){return value + '%' + "<br>硬盘";},
  colors:              ['#868db6', '#ffb71a'],
  duration:            400,
  textClass:           'clt',
  styleText:           true
});
  var myCircle3 = Circles.create({
  id:                  enc,
  radius:              32,
  value:               mem,
  maxValue:            100,
  width:               6,
  text:                function(value){return value + '%' + "<br>内存";},
  colors:              ['#868db6', '#ffb71a'],
  duration:            400,
  textClass:           'clt',
  styleText:           true
});
//   var myCircle4 = Circles.create({
//   id:                  'ewl',
//   radius:              32,
//   value:               10,
//   maxValue:            100,
//   width:               6,
//   text:                function(value){return value + '%' + "<br>网络";},
//   colors:              ['#868db6', '#ffb71a'],
//   duration:            400,
//   textClass:           'clt',
//   styleText:           true
// });
    }
    if(re.code==0&&!re.data){

      var ecpu = "ecpu"+ sco.YS_objd.params.deviceId;
      var eyp = "eyp" +sco.YS_objd.params.deviceId;
      var enc = "enc" +sco.YS_objd.params.deviceId;
            var myCircle1 = Circles.create({
  id:                  ecpu,
  radius:              32,
  value:               0,
  maxValue:            100,
  width:               6,
  text:                function(value){return "cpu";},
  colors:              ['#868db6', '#ffb71a'],
  duration:            400,
  textClass:           'clt2',
  styleText:           true
});
  var myCircle2 = Circles.create({
  id:                  eyp,
  radius:              32,
  value:               0,
  maxValue:            100,
  width:               6,
  text:                function(value){return "硬盘";},
  colors:              ['#868db6', '#ffb71a'],
  duration:            400,
  textClass:           'clt2',
  styleText:           true
});
  var myCircle3 = Circles.create({
  id:                  enc,
  radius:              32,
  value:               0,
  maxValue:            100,
  width:               6,
  text:                function(value){return "内存";},
  colors:              ['#868db6', '#ffb71a'],
  duration:            400,
  textClass:           'clt2',
  styleText:           true
});

    }
  }

  // 6查询设备
  sco.YS_obje.url="device/query";
  sco.YS_obje.params.linlId = '';
  sco.YS_obje.done=function(re,sco){
    if(re.code == 0){
      sco.YS_objb.params = re.data;
      sco.deviceServiceList = re.data.deviceServiceList;

      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },300)

    }
  }

},






//链路管理模块
linkadmin:function(sco) {
  $("#leftlink").empty()
  sco.epage=0;
  sco.fileisnone=0;
  sco.e_toplist=[];
  sco.e_ctoplist=[];
  sco.showstatus=1;
  sco.filelist=[];
  sco.tapshow=0;
  sco.edtp = "";
  console.log('2');
  debugger;
  sco.timestamp = Date.parse(new Date());
  // sco.iconlist=[
  //  {id:1,name:"GAP",status:1},{id:2,name:"路由器",status:1},{id:3,name:"防火墙",status:1},{id:4,name:"IPS",status:1},{id:5,name:"可信任安全网关",status:1},{id:6,name:"交换器",status:1},{id:7,name:"安管隔离网闸",status:1},{id:8,name:"编码器",status:1},{id:9,name:"FGAP",status:1},{id:10,name:"光交换",status:2}
  // ,{id:11,name:"矩阵抽象",status:2},{id:12,name:"摄像头",status:2},{id:13,name:"3AS",status:2},{id:14,name:"ADS",status:2},{id:15,name:"AS",status:2},{id:16,name:"ASM",status:2},{id:17,name:"AWS",status:2},{id:18,name:"DTI",status:2},{id:19,name:"EWP",status:3},{id:20,name:"IWP",status:3}
  // ,{id:21,name:"MOC",status:3},{id:22,name:"PAS",status:3},{id:23,name:"SS",status:3},{id:24,name:"SSE",status:3},{id:25,name:"TAS",status:3},{id:26,name:"TMS",status:3},{id:27,name:"UAS",status:3},{id:28,name:"UMS",status:4},{id:29,name:"TZ",status:4},{id:30,name:"存储陈列",status:4}
  // ,{id:31,name:"磁盘陈列",status:4},{id:32,name:"摄像头",status:4},{id:33,name:"服务器",status:4},{id:34,name:"矩阵",status:4},{id:35,name:"内网管理员",status:4},{id:36,name:"入侵ids",status:4},{id:37,name:"摄像头2",status:5},{id:38,name:"数据库",status:5},{id:39,name:"云控制台",status:5},{id:40,name:"终端",status:5}
  // ,{id:41,name:"主机ids",status:5},{id:42,name:"政党军机构",status:5},{id:43,name:"公安驻地外",status:5},{id:44,name:"海关",status:5},{id:45,name:"民政机构",status:5},{id:46,name:"内网",status:6},{id:47,name:"社保机构",status:6},{id:48,name:"社会企事业单位",status:6},{id:49,name:"视频内网",status:6},{id:50,name:"视频终端",status:6}
  // ,{id:51,name:"外部接入单位",status:6},{id:52,name:"外网",status:6},{id:53,name:"操作台",status:6}];
// 1获取链路列表
// 2.查看编辑链路信息
// 3.创建填写基本链路信息
// 4制作拓扑图


// 1获取链路列表
sco.YS_plista.url="link/queryAll";
sco.YS_plista.params={pageSize:10,currentPage:1};
sco.YS_plista.method='POST';
sco.YS_plista.done= function(re,sco){
 if(re.code==0){
   sco.YS_plista.data=re.data
}
}
sco.fetch('YS_plista');




//获取设备信息
sco.YS_plistb.url="device/query8Page";
sco.YS_plistb.params={pageSize:999999,currentPage:1};
sco.YS_plistb.method='POST';
sco.YS_plistb.done= function(re,sco){
 if(re.code==0){
  var i = 1
  var j = 9
   re.data.list.map(function(el,idx){
    if(idx<i*j){
      el.status=i

     }else{
      i++
      el.status=i
     }
     // for(idx<=i*j;i++){
     //  el.status=i;
      
     // }
  
   })



   sco.epage=Math.ceil(re.data.list.length/9)
   sco.iconlist=re.data.list
   console.log(sco.iconlist)
}


}
sco.fetch('YS_plistb');







// 2.查看编辑链路信息
sco.YS_objb.url="link/update";
sco.YS_objb.params={linkName:"",linkType:"",dbConfigData:{dbName:"",ip:"",port:"",name:"",pwd:""}};
sco.YS_objb.method="POST";
sco.YS_objb.before=function(sco){
  if(!sco.YS_objb.params.linkName){
    layer.tips("请输入链路名称！","#xlinkName",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!sco.YS_objb.params['dbConfigData.dbName']){
    layer.tips("请输入数据库名！","#xdbName",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!sco.YS_objb.params['dbConfigData.ip']){
    layer.tips("请输入ip地址！","#xip",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_objb.params['dbConfigData.ip'])){layer.tips("请输入合法的IP地址！","#ip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  if(!sco.YS_objb.params['dbConfigData.port']){
    layer.tips("请输入端口号！","#xport",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!sco.YS_objb.params['dbConfigData.name']){
    layer.tips("请输入用户名！","#xname",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!sco.YS_objb.params['dbConfigData.pwd']){
    layer.tips("请输入密码！","#xpwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
 
  // if(sco.YS_objb.params.topologyNodes){
  //  sco.YS_objb.params.topologyNodes.map(function(elem,index){
  //     sco.YS_objb.params['topologyNodes['+index+'].toDeviceId'] = elem.toDeviceId;
  //     sco.YS_objb.params['topologyNodes['+index+'].fromDeviceId'] = elem.fromDeviceId;
  //     sco.YS_objb.params['topologyNodes['+index+'].direction'] = elem.direction;
  //   })
  // }
  // 
var topologyNodes = YSfac.getstore("topologyNodes");
if(topologyNodes.length==0){layer.msg("请绘制拓扑图！");return false};
topologyNodes.map(function(elem,index){
  
   sco.YS_objb.params['topologyNodes['+index+'].direction'] = elem.direction;
   sco.YS_objb.params['topologyNodes['+index+'].fromDeviceId'] = elem.fromDeviceId;
   sco.YS_objb.params['topologyNodes['+index+'].toDeviceId'] = elem.toDeviceId;
   if(elem.child.length>0){
    elem.child.map(function(elem2,index2){
      sco.YS_objb.params['topologyNodes['+index+'].child['+index2+'].fromDeviceId'] = elem.child[index2].fromDeviceId;
      sco.YS_objb.params['topologyNodes['+index+'].child['+index2+'].toDeviceId'] = elem.child[index2].toDeviceId;
      // sco.YS_objb.params['topologyNodes['+index+'].child['+index2+'].deviceIds'] = elem.child[index2].deviceIds;

      if(elem.child[index2].deviceIds){
        elem.child[index2].deviceIds.map(function(el2,idx2){
          sco.YS_objb.params['topologyNodes['+index+'].child['+index2+'].deviceIds['+idx2+']'] = el2; 
        })
        
      }

    })
   }

   if(elem.deviceIds){
    elem.deviceIds.map(function(el,idx){
      sco.YS_objb.params['topologyNodes['+index+'].deviceIds['+idx+']'] = el; 
    })
    sco.fileisnone=0
    
   }

   if(elem.deviceIds&&elem.deviceIds.length==0){
    sco.fileisnone=1
   }



})

if(sco.fileisnone==1){
  layer.msg("文件夹不能为空！");
  return false
}



  delete sco.YS_objb.params.topologyNodes;
  delete sco.YS_objb.params.dbConfigData;
  delete sco.YS_objb.params.deviceIds;
};
sco.YS_objb.done=function(re,sco){
  if(re.code==0){
  //  layer.msg("修改成功！")
      re.msg?YSfac.load(1,re.msg):YSfac.load(1,"修改成功");
    setTimeout(function(){
      location.reload()
      //  sco.tapshow=0;
      // sco.fetch('YS_plista')
  },1000)
  }
  else{
   //  re.msg?layer.msg(re.msg):layer.msg("修改失败！")
       re.msg?YSfac.load(1,re.msg):YSfac.load(1,"修改失败");
  }
    
}

// 3.创建填写基本链路信息
sco.YS_obja.url="link/insert";
sco.YS_obja.params={linkName:"",linkType:"","dbConfigData.dbName":"","dbConfigData.ip":"",
"dbConfigData.port":"","dbConfigData.name":"","dbConfigData.pwd":"",

};


sco.YS_obja.method="POST";
sco.YS_obja.before=function(sco){
  if(!sco.YS_obja.params.linkName){
    layer.tips("请输入链路名称！","#xlinkName",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!sco.YS_obja.params['dbConfigData.dbName']){
    layer.tips("请输入数据库名！","#xdbName",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!sco.YS_obja.params['dbConfigData.ip']){
    layer.tips("请输入ip地址！","#xip",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_obja.params['dbConfigData.ip'])){layer.tips("请输入合法的IP地址！","#ip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  if(!sco.YS_obja.params['dbConfigData.port']){
    layer.tips("请输入端口号！","#xport",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!sco.YS_obja.params['dbConfigData.name']){
    layer.tips("请输入用户名！","#xname",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  if(!sco.YS_obja.params['dbConfigData.pwd']){
    layer.tips("请输入密码！","#xpwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
  };
  delete sco.YS_obja.params.dbConfigData;
var topologyNodes = YSfac.getstore("topologyNodes");
if(topologyNodes.length==0){layer.msg("请绘制拓扑图！");return false};
topologyNodes.map(function(elem,index){
   sco.YS_obja.params['topologyNodes['+index+'].direction'] = elem.direction;
   sco.YS_obja.params['topologyNodes['+index+'].fromDeviceId'] = elem.fromDeviceId;
   sco.YS_obja.params['topologyNodes['+index+'].toDeviceId'] = elem.toDeviceId;
   if(elem.child.length>0){
    elem.child.map(function(elem2,index2){
      sco.YS_obja.params['topologyNodes['+index+'].child['+index2+'].fromDeviceId'] = elem.child[index2].fromDeviceId;
      sco.YS_obja.params['topologyNodes['+index+'].child['+index2+'].toDeviceId'] = elem.child[index2].toDeviceId;
      // sco.YS_obja.params['topologyNodes['+index+'].child['+index2+'].deviceIds'] = elem.child[index2].deviceIds;

      if(elem.child[index2].deviceIds){
        elem.child[index2].deviceIds.map(function(el2,idx2){
          sco.YS_obja.params['topologyNodes['+index+'].child['+index2+'].deviceIds['+idx2+']'] = el2; 
        })
        
      }

    })
   }
  if(elem.deviceIds){
    elem.deviceIds.map(function(el,idx){
      sco.YS_obja.params['topologyNodes['+index+'].deviceIds['+idx+']'] = el; 
     
    })
     sco.fileisnone=0
   }

   if(elem.deviceIds&&elem.deviceIds.length==0){
    sco.fileisnone=1
   }
})


if(sco.fileisnone==1){
  layer.msg("文件夹不能为空！");
  return false
}
};
sco.YS_obja.done=function(re,sco){
  if(re.code == -1){
    layer.msg(re.msg);
  };
  if(re.code==0){
   // re.msg? layer.msg(re.msg): layer.msg("新增成功！");
      re.msg?YSfac.load(1,re.msg):YSfac.load(1,"新增成功");
    setTimeout(function(){
      location.reload()
  },1000)
  }
  else{
     //re.msg? layer.msg(re.msg):layer.msg("新增失败！");
       re.msg?YSfac.load(1,re.msg):YSfac.load(1,"新增失败");
    
  }
    
}

// 获取一条链路信息
sco.YS_objc.url="link/query";
sco.YS_objc.params.linkId = '';
sco.YS_objc.done=function(re,sco){
  if(re.code == 0){
    sco.YS_objb.params = re.data;


    if(re.data.topologyNodes[0].fromDeviceId==0){
      var temp = [];
      temp.push(re.data.topologyNodes[0]);
      re.data.topologyNodes = get_child(re.data.topologyNodes,re.data.topologyNodes[0].toDeviceId,temp);
    }


    sco.e_toplist=re.data.topologyNodes;
    
    sco.e_toplist.map(function(el){
   sco.iconlist.map(function(elem1){
    if(el.toDeviceId==elem1.deviceId){
      el.icon=elem1.icon
    }
  })
    if(el.child){
      el.child.map(function(el2){
     sco.iconlist.map(function(elem2){
        if(el2.toDeviceId==elem2.deviceId){
          el2.icon=elem2.icon
        }
      })
        sco.e_ctoplist.push(el2);
        if(el2.deviceIds){
          el2.deviceIds.map(function(el3){
            var isfile='';
            sco.iconlist.map(function(elem3){
              if(el3==elem3.deviceId){
               isfile=elem3.icon
              }
            })
            // if(el3.lindex==elem.deviceId){
            //   el3.icon=elem.icon
            // }
            sco.filelist.push({lindex:el3,isfile:isfile})
          })      
        }
      })

    }
    if(el.deviceIds){
      el.deviceIds.map(function(el4){
        var isfile='';
        var name='';
        sco.iconlist.map(function(elem4){
          if(el4==elem4.deviceId){
               isfile=elem4.icon;
               name = elem4.deviceName
              }
        })
        // if(el4.lindex==elem.deviceId){
        //   el4.icon=elem.icon
        // }
        sco.filelist.push({lindex:el4,isfile:isfile,dname:name})
      })
      
    }
    
    })
 

    console.log(sco.e_toplist)
    console.log(sco.e_ctoplist)
    console.log(sco.filelist)


    sco.YS_objb.params['dbConfigData.dbName'] = re.data.dbConfigData.dbName;
    sco.YS_objb.params['dbConfigData.ip'] = re.data.dbConfigData.ip;
    sco.YS_objb.params['dbConfigData.port'] = re.data.dbConfigData.port;
    sco.YS_objb.params['dbConfigData.name'] = re.data.dbConfigData.name;
    sco.YS_objb.params['dbConfigData.pwd'] = re.data.dbConfigData.pwd;

      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },300)

  }
}


sco.YS_objd.url="link/delete";

sco.YS_objd.before=function(sco){
  layer.close(sco.dellayer);
};
sco.YS_objd.done=function(re,sco){
  if(re.code == 0){
    setTimeout(function(){
        layer.msg('删除成功');
      },1000)
        setTimeout(function(){
        sco.fetch("YS_plista")
      },4000)


  }else{
        setTimeout(function(){
        layer.msg('删除失败');
       
      },2000)
  }
}


sco.YS_objf.url="tools/testDB";

sco.YS_objf.before=function(sco){
sco.YS_objf.params.netArea=1;
sco.YS_objf.params.dbName = sco.YS_obja.params['dbConfigData.dbName'];
sco.YS_objf.params.ip = sco.YS_obja.params['dbConfigData.ip'];
sco.YS_objf.params.port = sco.YS_obja.params['dbConfigData.port'];
sco.YS_objf.params.name = sco.YS_obja.params['dbConfigData.name'];
sco.YS_objf.params.pwd = sco.YS_obja.params['dbConfigData.pwd']

};

sco.YS_obje.url="tools/testDB";

sco.YS_obje.before=function(sco){
sco.YS_obje.params.netArea=1;
sco.YS_obje.params.dbName = sco.YS_objb.params['dbConfigData.dbName'];
sco.YS_obje.params.ip = sco.YS_objb.params['dbConfigData.ip'];
sco.YS_obje.params.port = sco.YS_objb.params['dbConfigData.port'];
sco.YS_obje.params.name = sco.YS_objb.params['dbConfigData.name'];
sco.YS_obje.params.pwd = sco.YS_objb.params['dbConfigData.pwd']

};






setTimeout(function(){



YS("jsPlumb",function(){

function deepCopy(p, c) {  //克隆对象
          var c = c || {};  
          for (var i in p) {  
              if(! p.hasOwnProperty(i)){  
                  continue;  
              }  
              if (typeof p[i] === 'object') {  
                  c[i] = (p[i].constructor === Array) ? [] : {};  
                  deepCopy(p[i], c[i]);  
              } else {  
                  c[i] = p[i];  
              }  
          }  
          return c;  
      } 
      function clear(){
        var alllist=YSfac.getstore("alllist");
        var e_toplist = sco.e_toplist;
        var e_ctoplist = sco.e_ctoplist;
        var filelist= sco.filelist;
        $("#main").empty()
        $("#main_e").empty()
        alllist.map(function(elem){
                      $("#left .item").each(function(){
                          if($(this).attr("data-index")==elem.allindex){
                            $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                          }
                        });
                     })
        alllist.map(function(elem){
                      $("#left_e .item").each(function(){
                          if($(this).attr("data-index")==elem.allindex){
                            $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                          }
                        });
                     })
        e_toplist.map(function(elem){
                      $("#left_e .item").each(function(){
                          if($(this).attr("data-index")==elem.toDeviceId){
                            $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                          }
                        });
                     })
        e_ctoplist.map(function(elem){
                      $("#left_e .item").each(function(){
                          if($(this).attr("data-index")==elem.toDeviceId){
                            $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                          }
                        });
                     })
        filelist.map(function(elem){
                      $("#left_e .item").each(function(){
                          if($(this).attr("data-index")==elem.lindex){
                            $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                          }
                        });
                     })
        sco.filelist=[];
        sco.e_toplist=[];
        sco.e_ctoplist=[];
        jsPlumb.deleteEveryEndpoint()
        console.log(jsPlumb.getAllConnections())
      }
      sco.clear = clear;
      function save() {
        $(".filebox").addClass("dpn");
              var connects = [];//存储连线的数组
              var mainArr=[];//存储元素的数组
              var topologyNodes=[];
              var filelist=sco.filelist;
              $.each(jsPlumb.getAllConnections(), function (idx, connection) {
                var index = $(connection.source).attr("data-index");
                var isfile = $(connection.target).attr("data-icon");
                var cfid = $(connection.source).attr("data-index");
                var ctid = $(connection.target).attr("data-index");
                var sign=0;
                var sat;

                if(idx==0){
                  topologyNodes.push({direction:1,fromDeviceId:0,toDeviceId:index,child:[]});
                }


                  // if(!check_main(topologyNodes,cfid)){

                    topologyNodes.map(function(el,index){
                      sat=0
                      if(el.fromDeviceId==cfid){
                        topologyNodes[index-1].child.push({fromDeviceId:cfid,toDeviceId:ctid})
                        sign=1
                      }

                      if(el.child.length>0&&check_main(topologyNodes,cfid)){

                        el.child.map(function(el2,index2){
                          if(el2.toDeviceId==cfid){

                              el.child.push({fromDeviceId:cfid,toDeviceId:ctid});

                            sign=1;
                          }
                        })
                      }
                   
                      // topologyNodes.map(function(elem,index){
                      //     if(cfid==el.fromDeviceId){
                      //       sat=1
                      //     }
                      //  })
                      //  if(sat==1){
                      //   topologyNodes[index-1].child.push({fromDeviceId:cfid,toDeviceId:ctid})
                      //   sign=1
                      // }
                    })
                  // }



                  if(sign==0){
                    if(isfile=="60"){
                      topologyNodes.push({
                      direction:1,
                      fromDeviceId:$(connection.source).attr("data-index"),
                      toDeviceId:$(connection.target).attr("data-index"),
                      child:[], 
                      deviceIds:[]
                      });
                    }else{
                      topologyNodes.push({
                      direction:1,
                      fromDeviceId:$(connection.source).attr("data-index"),
                      toDeviceId:$(connection.target).attr("data-index"),
                      child:[],
                      });
                    }
                   
                  }
                   if(sign==1){
                    
                      topologyNodes.map(function(el){
                        el.child.map(function(el2){
                         if(isfile=="60"){ 
                          el2.deviceIds=[]
                          }else{ }
                        })
                      })             
                  }

           
              
              });
                topologyNodes.map(function(elem){
                   
                    if(elem.deviceIds){
                      filelist.map(function(el){
                         elem.deviceIds.push(el.lindex)
                      })
                     
                    }

                    if(elem.child.length>0){
                      elem.child.map(function(e2){
                        if(e2.deviceIds){
                         
                          filelist.map(function(el2){
                             e2.deviceIds.push(el2.lindex)
                          })
                         
                        }                        
                      })
                    }
                  })
              $("#main .item").each(function(){
                // mainArr.push({
                //   // offset:$(this).position(),
                //   // text:$(this).text(),
                //   // index:$(this).attr("data-index"),
                //   // sign:$(this).attr("data-sign"),
                // });
              });
              console.log(topologyNodes);
              // console.log(mainArr);
       YSfac.setstore('topologyNodes',topologyNodes)
      }



      //查询新增的线属于主路还是子  0--主路  1--子
      function check_main(arr,fromDeviceId){
        var bool = 1;
        arr.map(function(elem,index){
          if(elem.toDeviceId==fromDeviceId){
            bool = 0;
          }
        })
        return bool;
      }


     sco.elemt = '';

      sco.save = save;
      jsPlumb.ready(function() {//一定要加,否则你妹的老报错
                      

        var connectorPaintStyle = {//基本连接线样式
                  lineWidth: 2,
                  strokeStyle: "#0E5069",
                  joinstyle: "round",
                  outlineColor: "#0E5069",
                  outlineWidth: 0
              };
      var connectorHoverStyle = {// 鼠标悬浮在连接线上的样式
                  lineWidth: 3,
                  strokeStyle: "#216477",
                  outlineColor: "white",
                  outlineWidth: 0,
              };
        var origin = {//起点
                  endpoint: ["Dot", { radius: 8 }],  //端点的形状
                  connectorStyle: connectorPaintStyle,//连接线的颜色，大小样式
                  connectorHoverStyle: connectorHoverStyle,
                  paintStyle: {
                      strokeStyle: "#03D6FC",
                      fillStyle: "transparent",
                      radius: 4,
                      lineWidth: 2
                  },        //端点的颜色样式
                  //anchor: "AutoDefault",
                  isSource: true,    //是否可以拖动（作为连线起点）
                  connector: ["Straight", { stub: [0, 0], gap: 0, cornerRadius: 0, alwaysRespectStubs: false }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
                  isTarget: false,    //是否可以放置（连线终点）
                  maxConnections:-1,    // 设置连接点最多可以连接几条线,-1表示无限大
                  connectorOverlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
              };
      var destination = {//终点
                  endpoint: ["Dot", { radius: 5 }],  //端点的形状
                  connectorStyle: connectorPaintStyle,//连接线的颜色，大小样式
                  connectorHoverStyle: connectorHoverStyle,
                  paintStyle: {fillStyle: "#03D6FC",},        //端点的颜色样式
                  isSource: false,    //是否可以拖动（作为连线起点）
                  connector: ["Straight", { stub: [0, 0], gap: 0, cornerRadius: 0, alwaysRespectStubs: false }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
                  isTarget: true,    //是否可以放置（连线终点）
                  maxConnections:-1,    // 设置连接点最多可以连接几条线,-1表示无限大
                  connectorOverlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
              };
         
        $("#left").children().draggable({
                  helper: "clone",
                  scope: "ss",
                  revert: 'invalid',//放置不到位自动恢复原位
                  stop:function(event,ui){
          var alllist = []
              $('#main').children().each(function(){
                  alllist.push({allindex:$(this).attr("data-index")})
                  if($(this).attr("data-icon")=='60'){
                    $(this).addClass("file")
                    // $(this).append('<div class="filebox dpn"></div>')
                  }
            YSfac.setstore("alllist",alllist)      
              });
          },
              });
          
              var elementSign=0;//标志元素唯一性
              $("#main").droppable({
                  scope: "ss",
           
                  drop: function (event, ui) {//在目标(target)容器上释放鼠标 ,ui.draggable[0]为开始拖拽的元素
                    elementSign++;
                      var left = parseInt(ui.offset.left - $(this).offset().left);
                      var top = parseInt(ui.offset.top - $(this).offset().top);
                      var ele=$('<div class="item" data-sign="'+elementSign+'" data-index="'+$(ui.draggable[0]).attr("data-index")+'" data-icon="'+$(ui.draggable[0]).attr("data-icon")+'" data-name="'+$(ui.draggable[0]).attr("data-name")+'" >' + $(ui.helper).html() + '</div>');//$(ui.helper).html()取第一个JQ元素的文本
                        ele.css({"left":left,"top":top,position: "absolute",margin:0});
                        $(this).append(ele);
                        jsPlumb.addEndpoint(ele, { anchors: "BottomRight" }, origin);//起点
                        jsPlumb.addEndpoint(ele, { anchors: "BottomLeft" }, destination);//终点
                        jsPlumb.draggable(ele,{containment: "parent"});//端点可以拖动设置,并且将端点限制在父级内
                        $(ele).draggable({ //设置拖动到main区域中的元素还可以拖拽
                         
                          containment: "parent" //限制拖动不超过父级边框
                        });
                       
                        //禁止重复拖拽
                        $(ui.draggable[0]).addClass("dpn").draggable("disable"); // 禁止其拖动功能
                  }

                  
              });
              var leftlist=[]
              $("#main").on("click", ".item", function () {

                if($(this).attr("data-icon")=="60"){
                  // $(this).addClass('file')
                  $(this).find(".filebox").toggleClass("dpn");

                  // console.log(123)
                }
               });
              $("#main").on("mouseup", ".item", function () {
                var offset = []
                // var parent=$("<div class='filebox'><div>")
                $("#main .item").each(function(){
             
              var r1 = $(this).position().left+$(this).context.offsetWidth;
              var l1 = $(this).position().left;
              var b1 = $(this).position().top+$(this).context.offsetHeight;
              var t1 = $(this).position().top;
              var L = $(this)[0].offsetLeft;
              var T = $(this)[0].offsetTop;
              var uid= $(this).context.id;
              var lef = $(this).position().left+100;
              var rig = $(this).position().left-100;
              var index = $(this).attr("data-index");
              var isfile = $(this).attr("data-icon");
              var dname = $(this).attr("data-name");
                offset.map(function(el){

                   if(r1<el.left||b1<el.top||l1>el.right||t1>el.bottom){
                           // layer.msg("moom")
                      }else {
                        if(isfile!="60"&&el.isfile!="60"){
                          // jsPlumb.animate(el.uid,{left:lef},{duration:350,easing:'easeOutBack'});
                          // jsPlumb.animate(uid,{left:rig},{duration:350,easing:'easeOutBack'});
                        }else{
                          if(isfile=="60"){
                            jsPlumb.removeAllEndpoints(el.uid);
                          $("#"+el.uid).remove();

$(".filebox").append("<div class='fileimg' id='"+el.index+"fimg'><img src='../images/c"+el.isfile+"0.png' /><img class='fdben' id='fdbens' fimgdel src='../images/pla-delete.png' data-id='"+el.index+"'><div class='finame' title='"+el.dname+"'><span>"+el.dname+"</span></div></div>");
                          leftlist.push({
                          lindex : el.index,
                          filimg : el.isfile
                          })

                          // sco.$apply();
                          }
                          if(el.isfile=="60"){
                            jsPlumb.removeAllEndpoints(uid);
                          $("#"+uid).remove();  
                      $(".filebox").append("<div class='fileimg' id='"+index+"fimg'><img src='../images/c"+isfile+"0.png' /><img class='fdben' id='fdbens' fimgdel src='../images/pla-delete.png' data-id='"+index+"'><div class='finame' title='"+dname+"'><span>"+dname+"</span></div></div>");
                          leftlist.push({
                          lindex : index,
                          filimg : isfile
                          })
                          // sco.$apply();
                          }                    
                        }
                        // console.log("<img class='fileimg' src='../images/c"+el.index+"0.png' />")
                      }
                      sco.filelist=leftlist
                })
  
                offset.push({
                 
                  uid:uid,
                  left : $(this).context.offsetLeft,
                  right : $(this).context.offsetLeft+$(this)[0].offsetWidth,
                  top : $(this).context.offsetTop,
                  bottom : $(this).context.offsetTop+$(this)[0].offsetHeight,
                  index: $(this).attr("data-index"),
                  isfile:$(this).attr("data-icon"),
                  dname:$(this).attr("data-name"),
                });
                // console.log($(this))
              });
              })
              
              $("#main").on("mouseenter", ".item", function () {
                var ele=$('<img src="../images/pla-delete.png" class="spans">');
                ele.css({position:"absolute",left:"90px",top:"-15px"});
                  $(this).append(ele);
              }).on("mouseleave", ".item", function () {
                  $(".spans").remove();
              }).on("click", ".spans",function () {
//新建时 删除文件夹 
sco.removetbadd = layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#removetbadd')
          }); 
    sco.addtbparent = $(this).parent();
    sco.addtbdel =$(this);
                  // if (confirm("确定删除吗?")) {
                  //   if($(this).parent().attr("data-icon")=='60'){
                  //    $(".filebox").empty();
                  //    leftlist.map(function(elem){
                  //     $("#left .item").each(function(){
                  //         if($(this).attr("data-index")==elem.lindex){
                  //           $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                  //         }
                  //       });
                  //    })
                  //   leftlist=[]
                  //   }
                  //     jsPlumb.removeAllEndpoints($(this).parent().attr("id"));
                  //     var index=$(this).parent().attr("data-index");
                  //     $(this).parent().remove();
                  //     $("#left .item").each(function(){
                  //         if($(this).attr("data-index")==index){
                  //           $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                  //         }
                  //       });
                  // }
              });
 $("#main").on("click", "#fdbens", function (event) {
//$(document).on('click','.fdben', function(event){
  event.stopPropagation();

  var key  = $(this).attr("data-id");
 var that = $(this);
    leftlist.map(function(elem,index){
     if (elem.lindex == key) {
     leftlist.splice(index,1);
     that.parent().addClass("dpn");
      //删除时要在顶部显示
        $("#left .item").each(function(){
        if($(this).attr("data-index")==elem.lindex){
          $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
        }
      });
     } else {
      return;
     }
        });
    sco.$apply(); 
})



$('#sctbadd').on('click',  function(event) {
                layer.close(sco.removetbadd);
                    if(sco.addtbparent.attr("data-icon")=='60'){
                     $(".filebox").empty();
                     leftlist.map(function(elem){
                      $("#left .item").each(function(){
                          if($(this).attr("data-index")==elem.lindex){
                            $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                          }
                        });
                     })
                    leftlist=[]
                    }

                      jsPlumb.removeAllEndpoints(sco.addtbparent.attr("id"));
                      var index=sco.addtbparent.attr("data-index");
                      sco.addtbparent.remove();
                      $("#left .item").each(function(){
                          if($(this).attr("data-index")==index){
                            $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                          }
                        });




    });




              jsPlumb.bind("click", function (conn, originalEvent) {//点击线段删除
               // if (confirm("确定删除吗?"))   jsPlumb.detach(conn);
      // hsl的代码 不要删除
        sco.elemt = conn;
        sco.aaa = layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#removext')
          }); 
                        
       });
       // hsl的代码 不要删除
    $('#scxt').on('click',  function(event) {
                layer.close(sco.aaa);
               jsPlumb.detach(sco.elemt);
    });
              jsPlumb.bind("connection", function (connInfo, originalEvent) {//自己连自己管控 

                if (connInfo.connection.sourceId == connInfo.connection.targetId) {      
                    layer.msg("不能连自己！")     
                    setTimeout(function(){
                    jsPlumb.detach(connInfo.connection);      
                  },100);
                }   
            });
              // if(sessionStorage.getItem("flowsheet")){//判断是否有保存过
              //   var flowsheet=JSON.parse(sessionStorage.getItem("flowsheet"));
              //   var mainHTML=""
              //   for(var i=0;i<flowsheet.mainArr.length;i++){
              //     if(elementSign<flowsheet.mainArr[i].sign){//如果已经保存过,即将标记更新
              //       elementSign=flowsheet.mainArr[i].sign;
              //     }
              //     mainHTML+='<div class="item" data-sign="'+flowsheet.mainArr[i].sign+'"  data-index="'+flowsheet.mainArr[i].index+'" style="left:'+flowsheet.mainArr[i].offset.left+'px;top:'+flowsheet.mainArr[i].offset.top+'px;position:absolute;margin:0" >' + flowsheet.mainArr[i].text + '</div>';
              //   };
              //   $("#main").append(mainHTML);
              //   $("#main .item").each(function(){

              //     jsPlumb.addEndpoint(this, { anchors: "BottomRight" }, deepCopy(origin, {uuid:$(this).attr("data-sign")+"origin"}));//起点
              //     jsPlumb.addEndpoint(this, { anchors: "BottomLeft" },  deepCopy(destination, {uuid:$(this).attr("data-sign")+"destination"}));//终点
              //     jsPlumb.draggable(this,{containment: "parent"},function(){console.log(this)});//端点可以拖动设置,并且将端点限制在父级内
              //     $(this).draggable({  //设置拖动到main区域中的元素还可以拖拽
              //       containment: "parent" //限制拖动不超过父级边框

              //     });
              //   });
              //   //固定连线
              //   for(var i=0;i<flowsheet.connects.length;i++){
              //     jsPlumb.connect({uuids:[flowsheet.connects[i].originSign+"origin",flowsheet.connects[i].destinationSign+"destination"]});
              //   };
              // }

      });





 jsPlumb.ready(function() {
                      

      var connectorPaintStyle = {//基本连接线样式
                  lineWidth: 2,
                  strokeStyle: "#0E5069",
                  joinstyle: "round",
                  outlineColor: "#0E5069",
                  outlineWidth: 0
              };
      var connectorHoverStyle = {// 鼠标悬浮在连接线上的样式
                  lineWidth: 3,
                  strokeStyle: "#216477",
                  outlineColor: "white",
                  outlineWidth: 0,
              };
        var origin = {//起点
                  endpoint: ["Dot", { radius: 8 }],  //端点的形状
                  connectorStyle: connectorPaintStyle,//连接线的颜色，大小样式
                  connectorHoverStyle: connectorHoverStyle,
                  paintStyle: {
                      strokeStyle: "#03D6FC",
                      fillStyle: "transparent",
                      radius: 4,
                      lineWidth: 2
                  },        //端点的颜色样式
                  //anchor: "AutoDefault",
                  isSource: true,    //是否可以拖动（作为连线起点）
                  connector: ["Straight", { stub: [0, 0], gap: 0, cornerRadius: 0, alwaysRespectStubs: false }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
                  isTarget: false,    //是否可以放置（连线终点）
                  maxConnections:-1,    // 设置连接点最多可以连接几条线,-1表示无限大
                  connectorOverlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
              };
      var destination = {//终点
                  endpoint: ["Dot", { radius: 5 }],  //端点的形状
                  connectorStyle: connectorPaintStyle,//连接线的颜色，大小样式
                  connectorHoverStyle: connectorHoverStyle,
                  paintStyle: {fillStyle: "#03D6FC",},        //端点的颜色样式
                  isSource: false,    //是否可以拖动（作为连线起点）
                  connector: ["Straight", { stub: [0, 0], gap: 0, cornerRadius: 0, alwaysRespectStubs: false }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
                  isTarget: true,    //是否可以放置（连线终点）
                  maxConnections:-1,    // 设置连接点最多可以连接几条线,-1表示无限大
                  connectorOverlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
              };
         
        $("#left_e").children().draggable({
                  helper: "clone",
                  scope: "ss",
                  stop:function(event,ui){
         
          var alllist = []
              $('#main_e').children().each(function(){
                  alllist.push({allindex:$(this).attr("data-index")})
                  if($(this).attr("data-icon")=='60'){
                    $(this).addClass("file")
                    // $(this).append('<div class="filebox dpn"></div>')
                  }
            YSfac.setstore("alllist",alllist)      
      
              });
          },
              });
          
              var elementSign=0;//标志元素唯一性
              $("#main_e").droppable({
                  scope: "ss",
           
                  drop: function (event, ui) {//在目标(target)容器上释放鼠标 ,ui.draggable[0]为开始拖拽的元素
                    elementSign++;
                      var left = parseInt(ui.offset.left - $(this).offset().left);
                      var top = parseInt(ui.offset.top - $(this).offset().top);
                      var ele=$('<div class="item e_item" data-sign="'+elementSign+'" data-index="'+$(ui.draggable[0]).attr("data-index")+'" data-icon="'+$(ui.draggable[0]).attr("data-icon")+'" data-name="'+$(ui.draggable[0]).attr("data-name")+'" >' + $(ui.helper).html() + '</div>');//$(ui.helper).html()取第一个JQ元素的文本
                        ele.css({"left":left,"top":top,position: "absolute",margin:0});
                        $(this).append(ele);
                        jsPlumb.addEndpoint(ele, { anchors: "BottomRight" }, origin);//起点
                        jsPlumb.addEndpoint(ele, { anchors: "BottomLeft" }, destination);//终点
                        jsPlumb.draggable(ele,{containment: "parent"});//端点可以拖动设置,并且将端点限制在父级内
                        $(ele).draggable({ //设置拖动到main区域中的元素还可以拖拽
                         
                          containment: "parent" //限制拖动不超过父级边框
                        });
                        $(ui.draggable[0]).addClass("dpn").draggable("disable"); // 禁止其拖动功能
                  }

                  
              });
              
              var leftlist;
               $("#main_e").on("click", ".e_item", function () {
                
                if($(this).attr("data-icon")=="60"){
                  // $(this).addClass('file')
                  $(this).find(".filebox").toggleClass("dpn")
                   // console.log(123)
                }
               });
              $("#main_e").on("mouseup", ".e_item", function () {
                leftlist=sco.filelist
                var offset = []

                $("#main_e .e_item").each(function(){
              
              var r1 = $(this).position().left+$(this).context.offsetWidth;
              var l1 = $(this).position().left;
              var b1 = $(this).position().top+$(this).context.offsetHeight;
              var t1 = $(this).position().top;
              var L = $(this)[0].offsetLeft;
              var T = $(this)[0].offsetTop;
              var uid= $(this).context.id;
              var lef = $(this).position().left+100;
              var rig = $(this).position().left-100;
              var index = $(this).attr("data-index");
              var isfile = $(this).attr("data-icon");
              var dname = $(this).attr("data-name");
                offset.map(function(el){
                   if(r1<el.left||b1<el.top||l1>el.right||t1>el.bottom){
                           // layer.msg("moom")
                      }else {
                        if(isfile!="60"&&el.isfile!="60"){
                          // jsPlumb.animate(el.uid,{left:lef},{duration:350,easing:'easeOutBack'});
                          // jsPlumb.animate(uid,{left:rig},{duration:350,easing:'easeOutBack'});
                        }else{
                          if(isfile=="60"){
                            console.log(el)

                            jsPlumb.removeAllEndpoints(el.uid);
                          $("#"+el.uid).remove();
                          $(".filebox").append("<div class='fileimg  888'><img src='../images/c"+el.isfile+"0.png' /><div class='finame' title='"+el.dname+"'><span>"+el.dname+"</span></div></div>");
                          leftlist.push({
                          lindex : el.index,
                          isfile : el.isfile,
                          dname:el.dname,
                          })
                          // console.log(leftlist)
                          // sco.$apply();
                       
                          }
                          if(el.isfile=="60"){
                            console.log(el)
                            jsPlumb.removeAllEndpoints(uid);
                          $("#"+uid).remove();
//$(".filebox").append("<div class='fileimg  99'><img src='../images/c"+isfile+"0.png' /><div class='finame' title='"+dname+"'><span>"+dname+"</span></div></div>");
$(".filebox").append("<div class='fileimg' id='"+index+"fimg'><img src='../images/c"+isfile+"0.png' /><img class='fdben' id='fdbenedit' fimgdel src='../images/pla-delete.png' data-id='"+index+"'><div class='finame' title='"+dname+"'><span>"+dname+"</span></div></div>");                         
                          leftlist.push({
                          lindex : index,
                          isfile : isfile,
                          dname:dname,

                          })
                          // console.log(leftlist)
                          // sco.$apply();
                          }                    
                        }
                      }
                      if(leftlist.length>0){
                       sco.filelist=leftlist
                      }
                })
               
                offset.push({
                 
                  uid:uid,
                  left : $(this).context.offsetLeft,
                  right : $(this).context.offsetLeft+$(this)[0].offsetWidth,
                  top : $(this).context.offsetTop,
                  bottom : $(this).context.offsetTop+$(this)[0].offsetHeight,
                  index: $(this).attr("data-index"),
                  isfile: $(this).attr("data-icon"),
                  dname:$(this).attr("data-name"),
                });
                // console.log($(this))
              });
              })

              
              $("#main_e").on("mouseenter", ".e_item", function () {
                var ele=$('<img src="../images/pla-delete.png" class="spans">');
                ele.css({position:"absolute",left:"90px",top:"-15px"});
                  $(this).append(ele);
              }).on("mouseleave", ".e_item", function () {
                  $(".spans").remove();
              }).on("click", ".spans",function () {
//删除文件  hsl新增弹窗 
        sco.removetb = layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#removetb')
          }); 
    sco.tbparent = $(this).parent();
    sco.tbdel =$(this);

                  // if (confirm("确定删除吗?")) {
                  //   if($(this).parent().attr("data-icon")=='60'){
                  //    $(".filebox").empty();
                  //    sco.filelist.map(function(elem){
                  //     $("#left_e .item").each(function(){
                  //         if($(this).attr("data-index")==elem.lindex){
                  //           $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                  //         }
                  //       });
                  //    })
                  //    sco.filelist=[]
                  //   }
                  //     jsPlumb.removeAllEndpoints($(this).parent().attr("id"));
                  //     var index=$(this).parent().attr("data-index");
                  //     $(this).parent().remove();
                  //     $("#left_e .item").each(function(){
                  //         if($(this).attr("data-index")==index){
                  //           $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                  //         }
                  //       });
                  // }
              });
//点击确定 删除文件 参数从上边的点击时间带过来    sco.tbparent = $(this).parent();  sco.tbdel =$(this);
$('#sctb').on('click',  function(event) {
                layer.close(sco.removetb);
                    if(sco.tbparent.attr("data-icon")=='60'){
                     $(".filebox").empty();
                     sco.filelist.map(function(elem){
                      $("#left_e .item").each(function(){
                          if($(this).attr("data-index")==elem.lindex){
                            $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                          }
                        });
                     })
                     sco.filelist=[]
                    }
                      jsPlumb.removeAllEndpoints(sco.tbparent.attr("id"));
                      var index=sco.tbparent.attr("data-index");
                      sco.tbparent.remove();

                      $("#left_e .item").each(function(){
                          if($(this).attr("data-index")==index){
                            $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
                          }
                        });





    });

 $("#main_e").on("click", "#fdbenedit", function (event) {
//$(document).on('click','.fdben', function(event){
  event.stopPropagation();

  var key  = $(this).attr("data-id");
 var that = $(this);
    leftlist.map(function(elem,index){
     if (elem.lindex == key) {
     leftlist.splice(index,1);
     that.parent().addClass("dpn");
      //删除时要在顶部显示
        $("#left_e .item").each(function(){
        if($(this).attr("data-index")==elem.lindex){
          $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
        }
      });
     } else {
      return;
     }
        });
    sco.$apply(); 
})














      });


function edtp(){


  var connectorPaintStyle = {//基本连接线样式
                  lineWidth: 2,
                  strokeStyle: "#0E5069",
                  joinstyle: "round",
                  outlineColor: "#0E5069",
                  outlineWidth: 0
              };
      var connectorHoverStyle = {// 鼠标悬浮在连接线上的样式
                  lineWidth: 3,
                  strokeStyle: "#216477",
                  outlineColor: "white",
                  outlineWidth: 0,
              };
        var origin = {//起点
                  endpoint: ["Dot", { radius: 8 }],  //端点的形状
                  connectorStyle: connectorPaintStyle,//连接线的颜色，大小样式
                  connectorHoverStyle: connectorHoverStyle,
                  paintStyle: {
                      strokeStyle: "#03D6FC",
                      fillStyle: "transparent",
                      radius: 4,
                      lineWidth: 2
                  },        //端点的颜色样式
                  //anchor: "AutoDefault",
                  isSource: true,    //是否可以拖动（作为连线起点）
                  connector: ["Straight", { stub: [0, 0], gap: 0, cornerRadius: 0, alwaysRespectStubs: false }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
                  isTarget: false,    //是否可以放置（连线终点）
                  maxConnections:-1,    // 设置连接点最多可以连接几条线,-1表示无限大
                  connectorOverlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
              };
      var destination = {//终点
                  endpoint: ["Dot", { radius: 5 }],  //端点的形状
                  connectorStyle: connectorPaintStyle,//连接线的颜色，大小样式
                  connectorHoverStyle: connectorHoverStyle,
                  paintStyle: {fillStyle: "#03D6FC",},        //端点的颜色样式
                  isSource: false,    //是否可以拖动（作为连线起点）
                  connector: ["Straight", { stub: [0, 0], gap: 0, cornerRadius: 0, alwaysRespectStubs: false }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
                  isTarget: true,    //是否可以放置（连线终点）
                  maxConnections:-1,    // 设置连接点最多可以连接几条线,-1表示无限大
                  connectorOverlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
              };
      var common ={
        endpoint: ["Dot", { radius: 5 }],
        connectorStyle: connectorPaintStyle,
        connector: ["Straight", { stub: [0, 0], gap: 0, cornerRadius: 0, alwaysRespectStubs: false }],
        connectorOverlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
      }



  var pl=100;
  var cl=100;
  var eplist = sco.e_toplist;
  var eclist = sco.e_ctoplist
  var filelist = sco.filelist

  
// 父节点消失
  eplist.map(function(el){
    $("#left_e .item").each(function(){
     var ddlid=$(this).attr("data-index")
    if(ddlid==el.toDeviceId){
     $(this).addClass("dpn")
    }
    })
  })
// 子节点消失
  eclist.map(function(el){
    $("#left_e .item").each(function(){
     var ddlid=$(this).attr("data-index")
    if(ddlid==el.toDeviceId){
     $(this).addClass("dpn")
    }
    })
  })
 //文件夹内容消失
 filelist.map(function(el){
    $("#left_e .item").each(function(){
     var ddlid=$(this).attr("data-index")
    if(ddlid==el.lindex){
     $(this).addClass("dpn")
    }
    })
  })

  // eplist.shift();



  jsPlumb.setContainer('main_e')

   $("#main_e .e_item").each(function(){
    var left1=$(this).position().left+pl;
    var left2=$(this).position().left+cl;
    // debugger
        jsPlumb.addEndpoint(this, { anchors: "BottomRight" }, deepCopy(origin, {uuid:$(this).attr("data-index")+"origin"}));//起点
        jsPlumb.addEndpoint(this, { anchors: "BottomLeft" },  deepCopy(destination, {uuid:$(this).attr("data-index")+"destination"}));//终点
        jsPlumb.draggable(this,{containment: "parent"});//端点可以拖动设置,并且将端点限制在父级内
        $(this).draggable({  //设置拖动到main区域中的元素还可以拖拽
          containment: "parent" //限制拖动不超过父级边框
        });
    if($(this).attr("ischild")=="true"){
      // console.log($(this).attr("data-index"))
      jsPlumb.animate($(this),{top:250,left:left2},{duration:350,easing:'easeOutBack'});
      cl+=200
    }else{
      jsPlumb.animate($(this),{top:100,left:left1},{duration:350,easing:'easeOutBack'});
    } 
    pl+=200;

   });
  for(var i=1;i<eplist.length;i++){

          jsPlumb.connect({uuids:[eplist[i].fromDeviceId+"origin",eplist[i].toDeviceId+"destination"]});
   };
   for(var j=0;j<eclist.length;j++){
          
          jsPlumb.connect({uuids:[eclist[j].fromDeviceId+"origin",eclist[j].toDeviceId+"destination"]});
          if(eclist[j].returnToId){
             jsPlumb.connect({uuids:[eclist[j].toDeviceId+"origin",eclist[j].returnToId+"destination"]});
          }
   };


   // eclist.map(function(el){

   // })


}
sco.edtp=edtp
















    


  });
          },500);   

    // //获取子级
    // function get_child(arr,id,re){
    //   var re1 = JSON.parse(JSON.stringify(re));
    //   arr.map(function(e){
    //     if(e.fromDeviceId==id){
    //       re1.push(e);
    //       if(has_child(arr,e.toDeviceId)){
    //         re1 = get_child(arr,e.toDeviceId,re1);
    //       }
    //     }
    //   })
    //     return re1;
    // }


    // //查看是否存在子级
    // function has_child(arr,id){
    //   var bool = 0;

    //   for (var i = 0; i < arr.length; i++) {
    //     if(arr[i].fromDeviceId==id){
    //       bool = 1;break;
    //     }
    //   }

    //   // arr.map(function(e){
    //   //   if(e.fromid==id){
    //   //     bool = 1;
    //   //   }
    //   // })
    //   return bool;
    // }





    //获取子级
    function get_child(arr,id,re){
      var re1 = JSON.parse(JSON.stringify(re));

      if(re1[0].child&&re1[0].child.length>0){
        re1[0].child = check_return(arr,re1[0].child);
      }

      arr.map(function(e,index){
        
        if(e.child&&e.child.length>0){
          e.child = check_return(arr,e.child);
        }

        if(e.fromDeviceId==id){
          re1.push(e);
          if(has_child(arr,e.toDeviceId)){
            re1 = get_child(arr,e.toDeviceId,re1);
          }
        }



      })
        return re1;
    }


    //查看是否存在子级
    function has_child(arr,id){
      var bool = 0;

      for (var i = 0; i < arr.length; i++) {
        if(arr[i].fromDeviceId==id){
          bool = 1;break;
        }
      }

      // arr.map(function(e){
      //   if(e.fromid==id){
      //     bool = 1;
      //   }
      // })
      return bool;
    }


    //检查分支是否有回路
    function check_return(arr,child){
      var last = child[child.length-1];
      arr.map(function(e,index){
        if(e.toDeviceId==last.toDeviceId){

          if(child.length>1){
            child[child.length-2].returnToId = e.toDeviceId;
            child.splice(child.length-1,1);
          }else{
            child[child.length-1].returnToId = e.toDeviceId;
          }
        }
      })

      return child;
    }




              
  // layer.open({
  //         type: 1,
  //         title: false, //不显示标题
  //         closeBtn: 0,
  //         shadeClose:false, 
  //         shade:0.5, 
  //         area: ['auto', 'auto'], //宽高
  //         content: $('#sqlfail')
  //       });
},

// 平台注册模块
register:function(sco) {
  sco.tapshow=0;
  // 1.注册表单提交
  sco.YS_obja.url="config/systemInfo/set";
  sco.YS_obja.method='POST';
  sco.YS_obja.before=function(sco){
    if(!sco.YS_obja.params.systemName){layer.tips("请输入平台名称！","#regpt",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!sco.YS_obja.params.managerName){layer.tips("请输入负责人姓名！","#regxm",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!sco.YS_obja.params.managerPhone){layer.tips("请输入负责人联系电话！","#regphone",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!(/^1[34578]\d{9}$/.test(sco.YS_obja.params.managerPhone))&&!(/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(sco.YS_obja.params.managerPhone))){layer.tips("请输入正确的电话号码！","#regphone",{tips: [3, 'rgba(0,0,0,.8)','']});return false}
    if(!sco.YS_obja.params.managerEmail){layer.tips("请输入负责人公安网邮箱地址！","#regemail",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(sco.YS_obja.params.managerEmail))){layer.tips("请输入正确的负责人公安网邮箱地址！","#regemail",{tips: [3, 'rgba(0,0,0,.8)','']});return false}
  }
  sco.YS_obja.done=function(re,sco){
    if(re.code == 0){
      layer.msg(re.msg);
       layer.open({
        type: 1,
        title: false, //不显示标题
        closeBtn: 0,
        shadeClose:false, 
        shade:0.5, 
        area: ['auto', 'auto'], //宽高
        content: $('#regsucc')
      });
     sco.fetch('YS_objb'); 
  }else{
      re.msg?layer.msg(re.msg):layer.msg('注册失败')
    }
  }
sco.has=0;
  sco.YS_objb.url="config/systemInfo/get";
  sco.YS_objb.method='POST';
  sco.YS_objb.done=function(re,sco){
    if(re.code == 0){
    sco.YS_obja.params=JSON.parse(re.data.configData);
               debugger;
      if (sco.YS_obja.params){
        sco.has=1;
      } 
  }else{
     
    }
  }
sco.fetch('YS_objb');









},

// syslog设置日志
syslog:function(sco) {
  sco.tapshow=0;
  // 1.注册表单提交
  sco.YS_obja.url="config/syslog2other/set";
  sco.YS_obja.method='POST';
  sco.YS_obja.before=function(sco){
   if(!sco.YS_obja.params.ip){
     layer.tips("请输入IP地址！","#syslogip",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };
    if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_obja.params.ip)){layer.tips("请输入合法的IP地址！","#syslogip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  
   if(!sco.YS_obja.params.port){
     layer.tips("请输入端口！","#sysport",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };



  }
  sco.YS_obja.done=function(re,sco){
    if(re.code == 0){
    //  layer.msg(re.msg);
       layer.open({
        type: 1,
        title: false, //不显示标题
        closeBtn: 0,
        shadeClose:false, 
        shade:0.5, 
        area: ['auto', 'auto'], //宽高
        content: $('#regsucc')
      });
  }else{
     re.msg?YSfac.load(1,re.msg):YSfac.load(1,'设置失败');
    }
  }

  sco.YS_objb.url="config/syslog2other/get";
  sco.YS_objb.method='POST';
  sco.YS_objb.done=function(re,sco){
    if(re.code == 0){
sco.YS_obja.params=JSON.parse(re.data.configData);
               debugger;
  }else{
     
    }
  }
sco.fetch('YS_objb');

/*日志保留设置*/


sco.YS_objc.url="config/logOverDay/get";
  sco.YS_objc.done = function(re, sco) {
    if(re.code==0){
      if(!re.data){
        return false;
      }else{
      sco.YS_objd.params.logOverDay =JSON.parse(re.data.configData).LogOverDay;
    }
    }
  };
sco.fetch("YS_objc")


sco.YS_objd.url="config/logOverDay/set";
sco.YS_objd.params.logOverDay = '';
  sco.YS_objd.before= function(sco){
if (sco.YS_objd.params.logOverDay==''){
  layer.tips("请输入保留天数！","#plarz",{tips: [3, 'rgba(0,0,0,.8)','']});return false
}
  };

  sco.YS_objd.done = function(re, sco) {
    if(re.code==0){
      //re.msg?YSfac.load(1,re.msg):YSfac.load(1,'设置成功');
      sco.fetch('YS_obja');
    }else{
    re.msg?YSfac.load(1,re.msg):YSfac.load(1,'设置失败');
    }
  };















},

//平台管理模块
platformadmin:function(sco) {
  console.log(sco.path)
  // 1保存表单验证
 
  // 4获取日志过期天数
  // 5设置日志过期天数

},

// 网络配置
networkconf:function(sco){
  sco.tapshow=0;

  sco.YS_objb.url="/system/netWorkGet";
  sco.YS_objb.done=function(re,sco){
    if(re.code==0){
        if(re.data){
          sco.YS_obja.params = JSON.parse(re.data.configData);
          console.log(sco.YS_objb);
        }
  }
  }  
sco.fetch('YS_objb');



  sco.YS_obja.url="/system/netWorkSet";
  // sco.YS_obja.params={ip:'192.168.125.1',zwym:'255.255.255.0',wg:'192.168.0.1',sxdns:'255.6.6.6',bydns:'255.5.5.5',tjlx:'内科',tjsj:[{time1:'10:01:01'},{time2:'11:10:00'}],rzts:'30'};
  sco.YS_obja.before=function(sco){
    if(!sco.YS_obja.params.localIp){layer.tips("请输入IP地址！","#plaip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_obja.params.localIp)){layer.tips("请输入合法的IP地址！","#plaip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};

    if(!sco.YS_obja.params.subnetMask){layer.tips("请输入子网掩码！","#playm",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!(/^(254|252|248|240|224|192|128|0)\.0\.0\.0$|^(255\.(254|252|248|240|224|192|128|0)\.0\.0)$|^(255\.255\.(254|252|248|240|224|192|128|0)\.0)$|^(255\.255\.255\.(254|252|248|240|224|192|128|0))$/).test(sco.YS_obja.params.subnetMask)){layer.tips("请输入合法的子网掩码！","#playm",{tips: [3, 'rgba(0,0,0,.8)','']});return false};

    if(!sco.YS_obja.params.gateway){layer.tips("请输入网关！","#plawg",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!(/(2(5[0-5]{1}|[0-4]\d{1})|[0-1]?\d{1,2})(\.(2(5[0-5]{1}|[0-4]\d{1})|[0-1]?\d{1,2})){3}/).test(sco.YS_obja.params.gateway)){layer.tips("请输入合法的网关！","#plawg",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    
    if(!sco.YS_obja.params.dns){layer.tips("请输入首选DNS服务器！","#plasxdns",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!sco.YS_obja.params.dnsBak){layer.tips("请输入备用DNS服务器！","#plabydns",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  }
  sco.YS_obja.done=function(re,sco){
    if(re.code==0){
      re.msg?YSfac.load(1,re.msg):YSfac.load(1,'提交成功');

  }else{
          re.msg?YSfac.load(1,re.msg):YSfac.load(1,'提交失败');
    }
  }  
},
// 体检配置
physicalconf:function(sco){
  sco.tapshow=0;
  // 1获取自动体检时间
  // 2设置自动体检时间

sco.timearr = [];
sco.temptime = '';
  // 1获取自动体检时间
  sco.YS_obja.url="config/autoCheckTimes/get";
  sco.YS_obja.done=function(re,sco){
    if(re.code==0){
      var rec = JSON.parse(re.data.configData);
      sco.timearr = rec.checkTimes;
      sco.YS_objb.params.checkTimes = rec.checkTimes;
      // sco.YS_objb.params.checkTimes = ["9:22","10:11","12:13"];
    }
  }
  sco.fetch('YS_obja');
  



  // 1设置自动体检时间
  sco.YS_objb.url="config/autoCheckTimes/set";
  sco.YS_objb.done=function(re,sco){
    if(re.code==0){
      YSfac.load(1,'保存成功');
    }else{
       YSfac.load(1,'保存成功');
    }
    
  }
  






},

// 日志配置
logconf:function(sco){
    // 1获取日志过期天数
    // 2设置日志过期天数
 sco.tapshow=0;
    // 1获取日志过期天数
sco.YS_obja.url="config/logOverDay/get";
  sco.YS_obja.done = function(re, sco) {
    if(re.code==0){
      if(!re.data){
        return false;
      }else{
      sco.YS_objb.params.logOverDay =JSON.parse(re.data.configData).LogOverDay;
    }
    }
  };
sco.fetch("YS_obja")


sco.YS_objb.url="config/logOverDay/set";
sco.YS_objb.params.logOverDay = '';

  sco.YS_objb.before = function(sco) {
if (sco.YS_objb.params.logOverDay = '') {
  YSfac.load(1,'天数不能为空')
} 
  };
  sco.YS_objb.done = function(re, sco) {
    if(re.code==0){
      re.msg?YSfac.load(1,re.msg):YSfac.load(1,'设置成功');
    }else{
       re.msg?YSfac.load(1,re.msg):YSfac.load(1,'设置失败');
    }
  };






},



//系统自检模块
sysexam:function(sco) {

sco.tapshow=0;
sco.zzjc = -1;
sco.templist = [];
sco.examine = [];
  // 本系统检查
sco.YS_obja.url="system/check";
sco.YS_obja.before = function(sco){
  if(sco.zzjc==1){
    return false;
  }
}

  sco.YS_obja.done = function(re, sco) {
    if(re.code==0){



      sco.YS_objb.params.checkId = re.data.checkId;
      sco.YS_objb.params.index = 0;
      sco.examine = [];
      // YSfac.setstore("checkid",re.data.checkId);
      // console.log(YSfac.getstore("checkid"))
      // setTimeout(function(){
        sco.fetch("YS_objb");
      // },2000)
    }else{
     
    }
  };

// 获取检查信息
sco.YS_objb.url="system/checkInfo";
sco.YS_objb.method="POST"
// sco.YS_objb.params={checkId:YSfac.getstore("checkid"),index:0}
sco.YS_objb.data=[];

  sco.YS_objb.done = function(re) {
    if(re.code==0){
      re.data.checkItems.map(function(elem){
        sco.examine.push(elem);
      });
    }
      // sco.YS_objb.data =re.data.checkItems;
      // sco.$apply();
  };

  function qian(sco){

  var i = 0;
   var int = setInterval(function(){
    if(i==13){i=0};
    var imgsrc = '../images/qian'+ i + '.png';
      $('.bf-qian').attr("src",imgsrc);
      i++
   },150)
  return int;

}
  sco.qian = qian();





 
  function xping(sco){
    var i = 1;
    var time =120;
    var int = setInterval(function(){
      if(i==26){i=1};
      var imgsrc = '../images/xp'+ i + '.png';
        $('.xping').attr("src",imgsrc);
        i++;
        time += 50;
     },time)
    return int;
  }
    sco.xping = xping()
},

// 恢复备份模块
restoreback:function(sco) {
  sco.tapshow=0;
  sco.rooturl = '';
  // 1恢复出厂设置
  // 2备份系统
  // 3恢复系统

  // 1恢复出厂设置
  sco.YS_obja.url="system/reset";
  sco.YS_obja.method="POST";
  // 2备份系统
  sco.YS_objb.url="system/bakupConfig";
  // 3恢复系统
  sco.YS_objc.url="system/reloadConfig";
  sco.YS_obja.method="POST";

       // layer.open({
       //    type: 1,
       //    title: false, //不显示标题
       //    closeBtn: 0,
       //    shadeClose:false, 
       //    shade:0.5, 
       //    area: ['auto', 'auto'], //宽高
       //    content: $('#resucc')
       //  });
},

//电源管理模块
power:function(sco) {
  sco.tapshow=0;
  // 1系统重启
  // 2系统关机
  // 3获取sshd开关
  // 4设置sshd开关
  // 1系统重启

 sco.YS_obja.url="system/shutDown";
 sco.YS_obja.params.reboot=true;
 sco.YS_obja.method="POST"

  sco.YS_obja.done=function(re,sco){
      if (re.code==0) {
        re.msg&&re.msg!=''?layer.msg(re.msg):layer.msg('操作成功')
      } else {
        re.msg&&re.msg!=''?layer.msg(re.msg):layer.msg('操作失败')
      }
  }  



  // 2系统关机
  sco.YS_objb.url="system/shutDown";
 sco.YS_objb.params.reboot=false;
 sco.YS_objb.method="POST"

  sco.YS_objb.done=function(re,sco){
      if (re.code==0) {
        re.msg&&re.msg!=''?layer.msg(re.msg):layer.msg('操作成功')
      } else {
        re.msg&&re.msg!=''?layer.msg(re.msg):layer.msg('操作失败')
      }
  }  


  // 3获取sshd开关
  sco.YS_objc.url="config/sshd/get";
  sco.YS_objc.done=function(re,sco){
    if(re.code==0){
      if(!re.data){
        return false;
      }else{
      sco.YS_objd.params.open =JSON.parse(re.data.configData).open;
    }
    }
  }
  sco.fetch('YS_objc');

  // 4设置sshd开关
  sco.YS_objd.url="config/sshd/set";
  sco.YS_objd.params.open = '';
  sco.YS_objd.done=function(re,sco){
    if(sco.YS_objd.params.open == true){
      layer.msg('已打开！');
    }else{
      layer.msg('已关闭！');
    }
  }









},

//licenses授权模块
licenses:function(sco) {
  sco.tapshow=0;
  sco.licenseKey = '';
  sco.filename = "";

  sco.YS_obja.url="license/key";
  sco.YS_obja.before=function(sco){
  }  
  sco.YS_obja.done=function(re,sco){
    sco.licenseKey = re.data.licenseKey;
  }  
  sco.fetch('YS_obja');



  sco.YS_objb.url="license/check";
  sco.YS_objb.before=function(sco){
  }  
  sco.YS_objb.done=function(re,sco){
  }  
  sco.fetch('YS_objb');



},

//工具箱模块
tools:function(sco) {
  sco.mesagea=[];
  sco.mesageb=[];
  sco.mesagec=[];
  sco.mesaged=[];

  sco.tapshow=0;
  //ping接口
  sco.YS_obja.url="/tools/ping";
  sco.YS_obja.method="POST";
  sco.YS_obja.params.deviceId=sco.YS_obje.params.deviceId;
  sco.YS_obja.before=function(sco){
   if(!sco.YS_obja.params.ip){
     layer.tips("请输入IP地址！","#toolip",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };
    if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_obja.params.ip)){layer.tips("请输入合法的IP地址！","#toolip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  
   if(!sco.YS_obja.params.netArea){
     layer.tips("请选择网段！","#toolwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };

if (sco.YS_obja.params.netArea==2&&!sco.YS_obja.params.deviceId) {
     layer.tips("请选择探针！","#tooltz",{tips: [3, 'rgba(0,0,0,.8)','']});return false


}
  // sco.mesage.push('检测中，请稍后......');
    YSfac.load2(1,"检测中...");
  }  
  sco.YS_obja.done=function(re,sco){
     layer.closeAll();
      sco.mesagea=[];
   if(re.code==0){     

    if(re.data.msg){
      re.data.msg = re.data.msg.split(':');

      re.data.msg.map(function(elem){
        sco.mesagea.push(elem);
      })
    }else{
      sco.mesagea.push('数据为空');
    }
        
}else{

   if (re.msg&&re.msg!='') {
   layer.msg(re.msg);
      sco.mesagea.push(re.msg);
   }else{
    sco.mesagea.push('检测失败');
   }
  
}

  } 



  //telnet接口
  sco.YS_objb.url="/tools/telnet";
  sco.YS_objb.method="POST";
  sco.YS_objb.params.deviceId=sco.YS_obje.params.deviceId;
  sco.YS_objb.before=function(sco){
   if(!sco.YS_objb.params.ip){
     layer.tips("请输入IP地址！","#telnetip",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };
    if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_objb.params.ip)){layer.tips("请输入合法的IP地址！","#telnetip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  


   if(!sco.YS_objb.params.port){
     layer.tips("请输入端口！","#telnetport",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };


   if(!sco.YS_objb.params.netArea){
     layer.tips("请选择网段！","#telnetwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };

if (sco.YS_objb.params.netArea==2&&!sco.YS_objb.params.deviceId) {
     layer.tips("请选择探针！","#telnettz",{tips: [3, 'rgba(0,0,0,.8)','']});return false


}
    YSfac.load2(1,"检测中...");
  }  
  sco.YS_objb.done=function(re,sco){
             sco.mesageb=[];

    if(re.code==0){
      layer.closeAll();
      if(re.data.isConnect){
        sco.mesageb.push("网络正常，telnet成功");
      }else{
        sco.mesageb.push("网络不通，telnet失败");
      }
    }else{
        if (re.msg&&re.msg!='') {
        layer.msg(re.msg);
        sco.mesageb.push(re.msg);
        }else{
        sco.mesageb.push('检测失败');
        }
    }
  } 



//数据库 YS_objc
  sco.YS_objc.url="/tools/testDB";
  sco.YS_objc.method="POST";
  sco.YS_objc.params.deviceId=sco.YS_obje.params.deviceId;
  sco.YS_objc.before=function(sco){


   if(!sco.YS_objc.params.dbName){
     layer.tips("请输入数据库名！","#sqldkname",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };


   if(!sco.YS_objc.params.ip){
     layer.tips("请输入IP地址！","#sqlip",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };
    if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_objc.params.ip)){layer.tips("请输入合法的IP地址！","#sqlip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
  


   if(!sco.YS_objc.params.port){
     layer.tips("请输入端口！","#sqlport",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };

   if(!sco.YS_objc.params.name){
     layer.tips("请输入用户名！","#sqluname",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };

   if(!sco.YS_objc.params.pwd){
     layer.tips("请输入密码！","#sqlpwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };





   if(!sco.YS_objc.params.netArea){
     layer.tips("请选择网段！","#sqlwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };

if (sco.YS_objc.params.netArea==2&&!sco.YS_objc.params.deviceId) {
     layer.tips("请选择探针！","#sqltz",{tips: [3, 'rgba(0,0,0,.8)','']});return false


}
 YSfac.load2(1,"检测中...");
  }  
  sco.YS_objc.done=function(re,sco){
    sco.mesageb=[];
            layer.closeAll();

    if(re.code==0){
      if(re.data.isConnect){
        sco.mesagec.push("网络正常，数据库连接成功");
      }else{
        sco.mesagec.push("网络不通，数据库连接失败");
      }
    }else{
                if (re.msg&&re.msg!='') {
        layer.msg(re.msg);
        sco.mesagec.push(re.msg);
        }else{
        sco.mesagec.push('检测失败');
        }
    }
  } 



//ftp
  sco.YS_objd.url="tools/ftp";
  sco.YS_objd.method="POST";
  sco.YS_objd.params.deviceId=sco.YS_obje.params.deviceId;
  sco.YS_objd.before=function(sco){


   if(!sco.YS_objd.params.ip){
     layer.tips("请输入ftp地址！","#ftpname",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };



   if(!sco.YS_objd.params.port){
     layer.tips("请输入端口！","#ftpport",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };

   if(!sco.YS_objd.params.name){
     layer.tips("请输入用户名！","#ftpuname",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };

   if(!sco.YS_objd.params.pwd){
     layer.tips("请输入密码！","#ftppwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };





   if(!sco.YS_objd.params.netArea){
     layer.tips("请选择网段！","#ftpwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
    };

if (sco.YS_objd.params.netArea==2&&!sco.YS_objd.params.deviceId) {
     layer.tips("请选择探针！","#ftptz",{tips: [3, 'rgba(0,0,0,.8)','']});return false
}

       YSfac.load2(1,"检测中...");

  }  
  sco.YS_objd.done=function(re,sco){
    sco.mesaged=[];
      layer.closeAll();
    if(re.code==0){
           re.msg?sco.mesaged.push(re.msg):sco.mesaged.push('检测成功,FTP连接正常');
  }else{
           re.msg?sco.mesaged.push(re.msg):sco.mesaged.push('检测失败');
    }
  } 


  //获取探针接口类型
  sco.YS_obje.url='device/queryAllDetector';
  sco.YS_obje.done=function(re,sco){
    setTimeout(function(){
      layui.use('form', function(){
        layui.form.render();
      }); 
    },100)
    sco.YS_obje.params.deviceName=re.data.list.deviceName;
    sco.YS_obje.params.deviceId=re.data.list.deviceId;
  }
  // sco.fetch('YS_obje');












},

//用户列表模块
userlist:function(sco) {


  
// 获取用户列表信息
  sco.YS_plista.url="user/queryAll";

  sco.YS_plista.done = function(re, sco) {
    if(re.code==0){
       re.data.users.map(function(el){
        var time =  el.createTime;
        var sd = el.loginTimeStart;
        var ed = el.loginTimeEnd;
        time = YSfac.numtosecond(time);  
        el.createTime = time;
      });
       sco.YS_plista.data = re.data;
       console.log(sco.YS_plista)
    }
   
  };
  sco.fetch('YS_plista');


    // 解锁
  sco.YS_obja.url="user/unlock";
  sco.YS_obja.done = function(re, sco) {
    if(re.code==0){
      layer.msg("解锁成功！")
    }else{
      layer.msg("解锁失败！")
    }
  };

  // 重置密码
  sco.resetmsg='';
  sco.YS_objb.url="user/resetPwd";

    sco.YS_objb.before=function(sco){

    };
  sco.YS_objb.done = function(re, sco) {
    if(re.code==0){
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#pwdresucc')
          });

      
    }else{
      layer.msg("密码重置失败！")
    }
  };
   
  // 删除
  sco.YS_objc.url="user/del";
  sco.YS_objc.done = function(re, sco) {
        layer.closeAll();
    if(re.code==0){
      re.msg?YSfac.load(1,re.msg):YSfac.load(1,"删除成功！");
      setTimeout(function(){
       sco.fetch('YS_plista');
      },1000)
    }else{
   re.msg?YSfac.load(1,re.msg):YSfac.load(1,"删除失败！");
      setTimeout(function(){
        sco.fetch('YS_plista');
      },1000)
    }
  };
// layer.open({
//           type: 1,
//           title: false, //不显示标题
//           closeBtn: 0,
//           shadeClose:false, 
//           shade:0.5, 
//           area: ['auto', 'auto'], //宽高
//           content: $('#remove')
//         });
},

//编辑用户模块
edituser:function(sco) {
  sco.rip=false;
  sco.rtime=false;

  // 编辑用户
  sco.YS_obja.url="user/update";
  sco.YS_obja.method="POST"
  sco.YS_obja.before = function(sco) {
    // if(sco.YS_obja.params.createTime)sco.YS_obja.params.createTime/=1000;
    // if(sco.YS_obja.params.updateTime)sco.YS_obja.params.updateTime/=1000;
    sco.YS_obja.params.uName = sco.YS_obja.params.name;
    delete sco.YS_obja.params.name;
    delete sco.YS_obja.params.status;
    delete sco.YS_obja.params.createTime;
    delete sco.YS_obja.params.updateTime;
  };
  sco.YS_obja.done = function(re, sco) {
    if(re.code==0){
   YSfac.load(1,"保存成功");

      setTimeout(function(){
        window.location.href = '/#/userlist';
      },1000)
    }else{
         YSfac.load(1,"保存失败");
    }
  };
  //查询用户信息
  sco.YS_objb.url="user/query";
  sco.YS_objb.params.uid=YSfac.getstore("uid"); 
 
  sco.YS_objb.done = function(re, sco) {
    if(re.code==0){
    //  re.data.loginTimeStart = YSfac.numtohms(re.data.loginTimeStart);
    //  re.data.loginTimeEnd = YSfac.numtohms(re.data.loginTimeEnd);
      sco.YS_obja.params =re.data;


      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },300)
      // var select = 'dd[lay-value=' + re.data.auth + ']';
      // $('#auth').siblings("div.layui-form-select").find('dl').find(select).click();
    }else{
      layer.msg(re.msg)
    }
  };
  sco.fetch("YS_objb")

  // 解锁
  sco.YS_objc.url="user/unlock";
  sco.YS_objc.params.uid=YSfac.getstore("uid"); 
  sco.YS_objc.done = function(re, sco) {
    if(re.code==0){
      layer.msg("解锁成功！")
      sco.YS_obja.params.status=1;
     //s sco.apply();
    }else{
      layer.msg("解锁失败！")
    }
  };

  // 重置密码
  sco.YS_objd.url="user/resetPwd";

    sco.YS_objd.before=function(sco){

    };
  sco.YS_objd.done = function(re, sco) {
    if(re.code==0){

     layer.msg(re.msg)
      
    }else{
      layer.msg("密码重置失败！")
    }
  };
   
  // 删除
  sco.YS_obje.url="user/del";
  sco.YS_obje.params.uid=YSfac.getstore("uid"); 
  sco.YS_obje.done = function(re, sco) {
   if(re.code==0){
      layer.msg("删除成功！");
      setTimeout(function(){

        layer.closeAll();
      },1000)
    }else{
      layer.msg("删除失败！")
      setTimeout(function(){
        layer.closeAll();
      },1000)
    }
  };
   


},
//用户信息模块
userinfo:function(sco) {
  sco.YS_obja.url="user/insert";
  sco.YS_obja.method="POST";
  sco.YS_obja.before=function(sco){
    if(!sco.YS_obja.params.uName){layer.tips("请输入用户名！","#uName",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!sco.YS_obja.params.pwd){layer.tips("请输入密码！","#pwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!(/(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$/.test(sco.YS_obja.params.pwd))){layer.tips("密码需要8~20位同时包含数字和大小写字母！","#pwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!$('#pwd').val()==$('#repwd').val()){layer.tips("两次输入密码不一样！","#repwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!sco.YS_obja.params.name){layer.tips("请输入姓名！","#name",{tips:[3,'rgba(0,0,0,.8)','']});return false};
    if(!sco.YS_obja.params.phone){layer.tips("请输入手机号码！","#phone",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!(/^1[34578]\d{9}$/.test(sco.YS_obja.params.phone))&&!(/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(sco.YS_obja.params.phone))){layer.tips("请输入正确的电话号码！","#phone",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!sco.YS_obja.params.email){layer.tips("请输入电子邮箱！","#email",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(sco.YS_obja.params.email))){layer.tips("请输入正确的邮箱地址！","#email",{tips: [3, 'rgba(0,0,0,.8)','']});return false}
    if(!sco.YS_obja.params.auth){layer.tips("请设置权限！","#auth",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!sco.YS_obja.params.loginIpStart){layer.tips("请输入开始IP地址！","#loginIpStart",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_obja.params.loginIpStart)){layer.tips("请输入合法的IP地址！","#loginIpStart",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!sco.YS_obja.params.loginIpEnd){layer.tips("请输入结束IP地址！","#loginIpEnd",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(sco.YS_obja.params.loginIpEnd)){layer.tips("请输入合法的IP地址！","#loginIpEnd",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
    if(!sco.YS_obja.params.loginTimeStart){layer.tips("设置开始登录时段！","#loginTimeStart",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
    if(!sco.YS_obja.params.loginTimeEnd){layer.tips("请设置结束登录时段！","#loginTimeEnd",{tips: [3, 'rgba(0,0,0,.8)','']});return false}; 
  }  
  sco.YS_obja.done=function(re,sco){
    if(re.code==0){
     re.msg?YSfac.load(1,re.msg):YSfac.load(1,'添加成功');
              setTimeout(function(){
  window.location.href = '/#/userlist'; 
      },1000)
  }else{
    re.msg?YSfac.load(1,re.msg):YSfac.load(1,'添加失败');
     
    }
  }  
},


}; 











































//初始化控制器 ============================================================================
var app = angular.module("app", []);
//过滤html 
app.filter("htmlfil",["$sce",function($sce){ 
  return function(text){ return $sce.trustAsHtml(text);};
}]);  

 
app.controller('ctrl', ['$scope','$http',function($scope,$http){ 
$scope.tapshow = 0;
$scope.tapnav = 0;
$scope.ischk =true;
$scope.zzjc =-1;
$scope.box =false;
//初始化
$scope.conf = conf;
$scope.main = main;  
$scope.$http = $http;
$scope.value = {};//备用与增加或者编辑
$scope.ystab = 0; //定义tab切换变量
$scope.reload = true;



YS("tableExport");
YS("wangEditor");
//存放 html路径,模块路径，hash参数对象
$scope.hrefinfo = {url:'',path:'',hashobj:{}};

//初始化公共
$scope.com_del = {params:{},data:{}};//公共删除
$scope.com_add = {params:{},data:{}};//公共增加
$scope.com_edit = {params:{},data:{}};//公共增加

conf.subfix.map(function(elem) { //定义接口数据容器
 $scope['com_obj'+elem] = {params:{},data:{}};
 $scope['com_list'+elem] = {load:true,params:{},data:[]};
 $scope['com_plist'+elem]  = {load:true,page:true,params:{pageSize:10,currentPage:1},data:{datalist:[]}};

 $scope['YS_list'+elem] = {load:true,params:{},data:[]};
 $scope['YS_plist'+elem]  = {load:true,page:true,params:{pageSize:10,currentPage:1},data:{datalist:[]}};
 $scope['YS_obj'+elem] = {params:{},data:{}};
}); 

$scope.fetch = function(key){//定义公共方法：请求函数 
 YSfac.fetdata($scope,key); 
}; 

$scope.dealhash = function(str,sco){ //定义处理html路径，模块路径，hash的参数对象的方法
  var url = str.split('#/'); 
  sco.hrefinfo.url = conf.index;
  sco.hrefinfo.path = conf.indexpath;
  if(url[1]){
    var key = url[1].split('?'); 
    sco.hrefinfo.url = conf.tplpath +'/'+ key[0]+'.html?time='+YSfac.time();
    // sco.hrefinfo.url = window.location.origin+'/phtml?name='+key[0]+'&prcodeid='+YSfac.getstore('prcodeid');

    sco.hrefinfo.path = key[0];
    key[1]&&(sco.hrefinfo.hashobj = YSfac.hashToobj(key[1])); 
  } 
}
$scope.dealhash(location.href,$scope); //调用方法，设置hash，html路径，hash参数  

$scope.init = function(sco) { //初始化每一个模块的数据容器
 conf.subfix.map(function(elem) { 
 sco['YS_list'+elem] = {load:true,params:{},data:[]};
 sco['YS_plist'+elem]  = {load:true,page:true,params:{pageSize:10,currentPage:1},data:{datalist:[]}}; 
 sco['YS_obj'+elem] = {params:{},data:{}};
});  
main[sco.hrefinfo.path]&&main[sco.hrefinfo.path](sco);//找到相关的模块，传入$scope,执行这个函数
}
$scope.ready = function(){$scope.init($scope);}// 加载模块html完成以后，初始化让$scope有这些数据 

window.onhashchange = function(){// hash变化，要执行两个方法
  $scope.dealhash(location.href,$scope); 
  $scope.$apply();
};



main.ctrl_init($scope);//初始化ctrl，执行一些方法  
console.log("=========================");
console.log($scope);
}]);

//邀请控制器
app.controller('shouquan', ['$scope','$http',function($scope,$http){ 
//初始化
$scope.conf = conf;
$scope.main = main;  
$scope.$http = $http;
$scope.value = {};//备用与增加或者编辑

//存放 html路径,模块路径，hash参数对象
$scope.hrefinfo = {url:'',path:'',hashobj:{}};   
//初始化公共
conf.subfix.map(function(elem) { //定义接口数据容器
 $scope['com_obj'+elem] = {params:{},data:{}};
 $scope['com_list'+elem] = {load:true,params:{},data:[]};
 $scope['com_plist'+elem]  = {load:true,page:true,params:{pageSize:10,currentPage:1},data:{datalist:[]}};

 $scope['YS_list'+elem] = {load:true,params:{},data:[]};
 $scope['YS_plist'+elem]  = {load:true,page:true,params:{pageSize:10,currentPage:1},data:{datalist:[]}};
 $scope['YS_obj'+elem] = {params:{},data:{}};
}); 

$scope.fetch = function(key){//定义公共方法：请求函数 
 YSfac.fetdata($scope,key); 
}; 

$scope.dealhash = function(str,sco){ //定义处理html路径，模块路径，hash的参数对象的方法
  var url = str.split('#/'); 
  sco.hrefinfo.url = conf.index;
  if(url[1]){
    var key = url[1].split('?'); 
    sco.hrefinfo.url = conf.tplpath +'/'+ key[0]+'.html?time='+YSfac.time();
    sco.hrefinfo.path = key[0];
    key[1]&&(sco.hrefinfo.hashobj = YSfac.hashToobj(key[1])); 
  } 
}
$scope.dealhash(location.href,$scope); //调用方法，设置hash，html路径，hash参数  

$scope.init = function(sco) { //初始化每一个模块的数据容器
 conf.subfix.map(function(elem) { 
 sco['YS_list'+elem] = {load:true,params:{},data:[]};
 sco['YS_plist'+elem]  = {load:true,page:true,params:{listnum:10,curPage:1},data:{datalist:[]}}; 
 sco['YS_obj'+elem] = {params:{},data:{}};
});  
main[sco.hrefinfo.path]&&main[sco.hrefinfo.path](sco);//找到相关的模块，传入$scope,执行这个函数
}
$scope.ready = function(){$scope.init($scope);}// 加载模块html完成以后，初始化让$scope有这些数据 

window.onhashchange = function(){// hash变化，要执行两个方法
  $scope.dealhash(location.href,$scope); 
  $scope.$apply();
};


//获取工程和邀请者信息
  $scope.YS_obja.url = 'open/program_pro_info';
  $scope.YS_obja.before = function(sco) {
  $scope.YS_obja.params.codeid = obj.u;
  };
$scope.YS_obja.done = function(re, sco) {

};


  $scope.tapshow=0;
  $scope.licenseKey = '';
  $scope.filename = "";

  $scope.YS_obja.url="license/key";
  $scope.YS_obja.before=function(sco){
  }  
  $scope.YS_obja.done=function(re,sco){
    sco.licenseKey = re.data.licenseKey;
  }  
  $scope.fetch('YS_obja');



  $scope.YS_objb.url="license/check";
  $scope.YS_objb.before=function(sco){
  }  
  $scope.YS_objb.done=function(re,sco){
  }  
  $scope.fetch('YS_objb');








}]);























//公共指令 ============================================================================


//点击，赋值 setvalue="YS_listb.params.type" run='YS_listb'
app.directive('setvalue', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){
           var ctrlsco = YSfac.getscope_byid(sco,2);
           var arr = iAttrs.setvalue.split('.'); 
           var obj = ctrlsco;
           var key = '';
           arr.map(function(el,ind){ 
            ind<arr.length-1&&(obj = obj[el]);
            ind==arr.length-1&&(key=el);
          });
           obj[key] = iAttrs.value; 

if (iAttrs.dataarr==1){
  ctrlsco.dataarr=[];
}
if (ctrlsco[arr[0]][arr[1]].curPage) {
  ctrlsco[arr[0]][arr[1]].curPage =1;
}

           iAttrs.run&&ctrlsco.fetch(iAttrs.run);
           ctrlsco.$apply(); 
        });
     }
};
}); 
//点击去请求某个接口ysenter="YS_plista"
app.directive('ysfetch', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2);
           ctrlsco.fetch(iAttrs.ysfetch); 
        });
     }
};
}); 



//回车，就去请求某个接口ysenter="YS_plista"
app.directive('ysenter', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("keyup",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);

          e.keyCode==13&&ctrlsco.fetch(iAttrs.ysenter);
        });
     }
};
}); 

//checkbox 全选yschkall="YS_plista.data.datalist"
app.directive('yschkall', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2); 
           var arr = iAttrs.yschkall.split('.'); 
           var list = ctrlsco; 
           arr.map(function(el){
            list = list[el];
          }); 
           list.map(function(el){
            el.ischk = $(iElm).find('input').is(':checked');
          });
           
           ctrlsco.$apply(); 
        });
     }
};
}); 


//checkbox 单选yschk="YS_obja.params.banbe" run="YS_obja"
    app.directive('yschk', function () {
        return {
            link: function (sco, iElm, iAttrs) {
                iElm.on("click", function (e) {
                    var ctrlsco = YSfac.getscope_byid(sco, 2);
                    ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.yschk, $(iElm).find('input').is(':checked'));
                    // ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.yschk, $(iElm).is(':checked'));
                    iAttrs.run && ctrlsco.fetch(iAttrs.run);
                    ctrlsco.$apply();
                });
            }
        };
    });


//checkbox公共勾选赋值   valchk 
app.directive('valchk',function(){ 
    return { 
        link: function(sco, iElm, iAttrs) {
            iElm.on("click",function(e){ 
              var ctrlsco = YSfac.getscope_byid(sco,2); 
                   sco.value.ischk = !sco.value.ischk; 
                    ctrlsco.$apply(); 
                 });
        }
    };
});


//某一行的开关 valswitch变化进行更新 valswitch='ycys'
app.directive('valswitch', function () {
    return {
        link: function (sco, iElm, iAttrs) {
            iElm.on("click", function (e) {
              var _th = $(iElm); 
                var ctrlsco = YSfac.getscope_byid(sco, 2);
                var d = {codeid:sco.value.codeid};
                d[iAttrs.valswitch] = sco.value[iAttrs.valswitch]==1?0:1;
                ctrlsco.com_edit.params = d;
                ctrlsco.com_edit.done = function(re,scos){
                  YSfac.updatetd(re,_th);
                  if(re.code==1){
                    sco.value[iAttrs.valswitch] = d[iAttrs.valswitch];
                  }
                } 
                ctrlsco.fetch('com_edit');
                ctrlsco.$apply();
            });
        }
    };
});

//公共方法 td值变化失去 进行更新，ystd="com_edit.params.open" 
app.directive('ystd', function() {
return {
link: function(sco, iElm, iAttrs) {
iElm.on("blur", function() { 
    var ctrlsco = YSfac.getscope_byid(sco, 2);
    var _this = $(iElm);
    var x = $(this).text();
if(x==''&&sco.value[iAttrs.ystd]==undefined){return false; }
  if(sco.value[iAttrs.ystd]!=x){
      sco.value[iAttrs.ystd] = x; 
      ctrlsco.com_edit.params = {codeid: sco.value.codeid};
      ctrlsco.com_edit.params[iAttrs.ystd] = x;
      ctrlsco.com_edit.done = function(re, sco) {
      YSfac.updatetd(re,_this); 
      }
      ctrlsco.fetch('com_edit');   
  }
});
}
};
});



//公共关闭弹窗
app.directive('comclose', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){
              layer.closeAll();
        });
     }
};
});




//checkbox公共勾选  并更新接口 yschkupdate="com_objf.params.open"
app.directive('yschkupdate',function(){ 
    return { 
        link: function(sco, iElm, iAttrs) {
            iElm.on("click",function(e){ 
              var ctrlsco = YSfac.getscope_byid(sco,2); 
              var arr = iAttrs.yschkupdate.split('.'); 
              var a = [1,0];
              var key = iAttrs.yschk; 
            //  sco.value[key] = $(iElm).is(":checked")?1:0; 
               ctrlsco[arr[0]][arr[1]].menucodeid = sco.value.codeid;
               ctrlsco[arr[0]][arr[1]].prcodeid = YSfac.getstore('prcodeid');
               ctrlsco[arr[0]][arr[1]].prcodeid = YSfac.getstore('prcodeid');
               ctrlsco[arr[0]][arr[1]][arr[2]] = $(iElm).is(":checked")?1:0; 
               ctrlsco.fetch(arr[0]);
                    ctrlsco.$apply(); 
                 });
        }
    };
});








//公共增加，触发会调用接口   ysadd='YS_plista.data.datalist'  
app.directive('ysaddrun', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){
           var ctrlsco = YSfac.getscope_byid(sco,2); 
           var arr = iAttrs.ysaddrun.split('.'); 
           var list = ctrlsco; 
           arr.map(function(el){list = list[el];}); 
              ctrlsco.com_add.done = function(re,sco) {
                if(re.code==1){ 
                  list.unshift(re.data); 
                  layer.msg('成功'); 
                }else{ 
                  layer.msg('失败');
                }
              }
              ctrlsco.fetch('com_add'); 
        });
     }
};
});



 //hsl 公共增加，触发会调用接口   ysadd='YS_plista'  
app.directive('ysaddrun2', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){
           var ctrlsco = YSfac.getscope_byid(sco,2); 
              ctrlsco.com_add.done = function(re,sco) {
                if(re.code==1){ 
                  layer.msg('成功'); 
                  ctrlsco.fetch(iAttrs.ysaddrun2);
                }else{ 
                  layer.msg('失败'); 
                }
              }
              ctrlsco.fetch('com_add'); 
              layer.closeAll();
        });
     }
};
});





//公共编辑,写法：  ysedit='YS_obja.params'  
app.directive('ysaddedit', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){
           var ctrlsco = YSfac.getscope_byid(sco,2); 
           var arr = iAttrs.ysaddedit.split('.'); 
           var list = ctrlsco; 
           arr.map(function(el){list = list[el];});

        });
     }
};
}); 

//公共编辑,写法：  ysaddedit='YS_obja.params'  
app.directive('ysaddedit2', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){
           var ctrlsco = YSfac.getscope_byid(sco,2); 
           var v = sco.value||ctrlsco.value||"";
           ctrlsco = YSfac.yssetvalue(ctrlsco,iAttrs.ysaddedit2,v); 
           

        });
     }
};
}); 




 
//select 选中赋值并执行
app.directive('ysselrun', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("change",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2); 
           var arr = iAttrs.ysselrun.split('.'); 
           var obj = ctrlsco;
           var key = '';
           arr.map(function(el,ind){ 
            ind<arr.length-1&&(obj = obj[el]);
            ind==arr.length-1&&(key=el);
          });
           obj[key] = iElm.val();
           ctrlsco.fetch(arr[0]);
        });
     }
};
}); 

//select 选中赋值
app.directive('yssel', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("change",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2); 
           var arr = iAttrs.yssel.split('.'); 
           var obj = ctrlsco;
           var key = '';
           arr.map(function(el,ind){ 
            ind<arr.length-1&&(obj = obj[el]);
            ind==arr.length-1&&(key=el);
          });
           obj[key] = iElm.val(); 
        });
     }
};
}); 

//select 选中赋值 ysselvalue="status" run="YS_obja" 
app.directive('ysselvalue', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("change",function(e){  
           sco.value[iAttrs.ysselvalue] = iElm.val(); 
           sco.$apply();
        });
     }
};
}); 




//公共的 layui的select选择方法   <select  lay-filter="YS_objc.params.logourl" ysselect="YS_objc.params.logourl" run="YS_objd" codeid="{{value.codeid}}">
app.directive('ysselect', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        layui.use('form', function(){
        var key =  'select('+iAttrs.ysselect+')';
        layui.form.on(key, function(data){
          var ctrlsco = YSfac.getscope_byid(sco,2);
          var arr = iAttrs.ysselect.split('.');
          ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.ysselect, data.value); 
          iAttrs.codeid&&(ctrlsco[arr[0]][arr[1]].codeid = iAttrs.codeid);
          iAttrs.run&&ctrlsco.fetch(iAttrs.run);//如果存在run，则去跑对应的接口
          ctrlsco.$apply();
        }); 
        layui.form.render();
      }); 
     }
};
});


app.directive('dsselect', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        layui.use('form', function(){
        var key =  'select('+iAttrs.dsselect+')';

        layui.form.on(key, function(data){
          var ctrlsco = YSfac.getscope_byid(sco,2);
          var arr = iAttrs.dsselect.split('.');
          // if(ctrlsco.YS_objb.params.deviceServiceList.length>0)
          // ctrlsco.YS_objb.params.deviceServiceList[iAttrs.key].serviceType = data.value;
          // if(ctrlsco.YS_obja.params.deviceServiceList.length>0)
          // ctrlsco.YS_obja.params.deviceServiceList[iAttrs.key].serviceType = data.value;

          ctrlsco.deviceServiceList[iAttrs.key].serviceType = data.value;
          if(data.value==4){
            if(iAttrs.type=='add'){
              ctrlsco.deviceServiceList[iAttrs.key].ip = ctrlsco.YS_obja.params.ip;
            }else if(iAttrs.type=='edit'){
              ctrlsco.deviceServiceList[iAttrs.key].ip = ctrlsco.YS_objb.params.ip;
            }
          }

          ctrlsco.$apply();
        }); 
        layui.form.render();
      }); 
     }
};
});


 




//公共方法-radio点击选中，赋值并执行跑接口  ysradio="YS_objc.params.logourl" run="YS_objd"
app.directive('ysradio', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2);  
           ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.ysradio, iAttrs.value); 
           iAttrs.run&&ctrlsco.fetch(iAttrs.run);
           ctrlsco.$apply(); 
        });
     }
};
}); 

 

//开关点击已送未送，赋值并执行跑接口   ysswitch="YS_listb.params.classify_id"  run="YS_listb"
    // app.directive('ysswitch', function () {
    //     return {
    //         link: function (sco, iElm, iAttrs) {
    //             iElm.on("click", function (e) {
    //                 var ctrlsco = YSfac.getscope_byid(sco, 2);
    //                 var arr = iAttrs.ysswitch.split('.');
    //                 var a = $('#fffff').is(':checked');
    //                 var val = $(iElm).find('input').is(':checked')?1:0;
    //                 ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.ysswitch, val);
    //                 sco.value.codeid&&(ctrlsco[arr[0]][arr[1]].codeid = sco.value.codeid);
    //                 iAttrs.run && ctrlsco.fetch(iAttrs.run);
    //                 ctrlsco.$apply();
    //             });
    //         }
    //     };
    // });





//公共方法- 多选checkbox点击选中 
app.directive('yscheck', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
    var ctrlsco = YSfac.getscope_byid(sco,2); 
   ctrlsco.ischk = ctrlsco.ischk?false:true; 
    sco.$apply(); 
        });
     }
};
}); 
// 短信提醒开关
app.directive('dxbj', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
    var ctrlsco = YSfac.getscope_byid(sco,2); 
   ctrlsco.dxbj = ctrlsco.dxbj==1?0:1; 
   if (ctrlsco.dxbj==1) {
    ctrlsco.YS_obja.params.smsConfig.isOpen=true;
   }else{
    ctrlsco.YS_obja.params.smsConfig.isOpen=false;
   }
   
    sco.$apply(); 
        });
     }
};
}); 
// 邮件提醒开关
app.directive('yjbj', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
    var ctrlsco = YSfac.getscope_byid(sco,2); 
   ctrlsco.yjbj = ctrlsco.yjbj==1?0:1; 
      if (ctrlsco.yjbj==1) {
    ctrlsco.YS_obja.params.emailConfig.isOpen=true;
   }else{
    ctrlsco.YS_obja.params.emailConfig.isOpen=false;
   }
    sco.$apply(); 
        });
     }
};
}); 









//公共切换tab的值
app.directive('setystab', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);
            ctrlsco.ystab = iAttrs.setystab;
            ctrlsco.$apply();
        });
     }
};
}); 




//点击 标记标养 原材 导入/出时用到
app.directive('sjexcel', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);
            ctrlsco.excel = iAttrs.sjexcel;
            ctrlsco.$apply();
        });
     }
};
}); 



//公共删除事件,删除1或多条
app.directive('ysdel', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2);
           var arr = iAttrs.ysdel.split('.'); 
           var list = ctrlsco;
           arr.map(function(el){list = list[el];}); 
           var delids = [];
           list.map(function(el){el.ischk&&el.isdel==0&&delids.push(el[iAttrs.delkey]);}); 
          
           if(!delids.length){layer.msg('请选择删除项');return false;} 

                 var layercon = layer.confirm('您确定要删除么？', {
                    btn: ['确定','取消'] //按钮
                  }, function(){ 
                    ctrlsco.com_del.params[iAttrs.delkey] = delids.join(','); 
                    ctrlsco.com_del.done = function(re,sco) {
                      if(re.code==1){
                        list.map(function(el,index){
                        if (el.ischk) {el.isdel = 1;}
                        }); 
                        
                      }
                       YSfac.msg(re);
                    }
                    ctrlsco.fetch('com_del');
                    layer.close(layercon);
                  }); 
            });  
       
     }
};
}); 


//公共删除事件,删除一条   yun="com_listf"
app.directive('ysdelone', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function($event){
           $event.stopPropagation(); 
          var ctrlsco = YSfac.getscope_byid(sco,2);
            var _sco = sco;  
            var layercon = layer.confirm('您确定要删除么？', {
              btn: ['确定','取消'] //按钮
            }, function(){ 
              ctrlsco.com_del.params[iAttrs.ysdelone] = sco.value[iAttrs.ysdelone]; 
              ctrlsco.com_del.done = function(re,sco) {
                if(re.code==1){
                  _sco.value.isdel = 1;
                  layer.msg('删除成功');
              iAttrs.run&&ctrlsco.fetch(iAttrs.run);
                }else{
                  layer.msg(re.msg);
                }
              }
              ctrlsco.fetch('com_del');
              layer.close(layercon);
            }); 
            
        });
     }
};
});


//td编辑状态下面 赋值
app.directive('tdedit', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("keyup",function($event){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);
          var val = $(this).text();
          sco.value[iAttrs.tdedit] = val;
          sco.$apply();
          // ctrlsco = YSfac.yssetvalue(ctrlsco,iAttrs.tdedit,val);  
        });
     }
};
});



//结合layui的文件上传 ysupload = "YS_obja.params.imgurl"  run="YS_obja" 
app.directive('ysupload', function(){ 
return { 
link: function(sco, iElm, iAttrs) { 
var ctrlsco = YSfac.getscope_byid(sco, 2); 

var uploadInst = layui.upload.render({
    elem: $(iElm), 
    accept:'file', 
    url: conf.rooturl + 'license/up', 
    done: function(re,index, upload){ 
        if(re.code == 0){ 
          
          ctrlsco.filename = "licenseData";
          ctrlsco.$apply();
          layer.open({
              type: 1,
              title: false, //不显示标题
              closeBtn: 0,
              shadeClose:false, 
              shade:0.5, 
              area: ['auto', 'auto'], //宽高
              content: $('#sqsucc')
          });
              }else{
                layer.open({
                  type: 1,
                  title: false, //不显示标题
                  closeBtn: 0,
                  shadeClose:false, 
                  shade:0.5, 
                  area: ['auto', 'auto'], //宽高
                  content: $('#sqfail')
                });
                    // layer.msg(re.msg);
        } 
    } 
    
}); 
}
};
});







//公共方法-radio点击选中，赋值并执行跑接口  ysradio="YS_objc.params.logourl" run="YS_objd"
app.directive('ysradio', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2);  
           var arr = iAttrs.ysradio.split('.'); 
           ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.ysradio, iAttrs.value); 
           iAttrs.run&&ctrlsco.fetch(iAttrs.run);
           ctrlsco.$apply(); 
        });
     }
};
}); 

//公共方法- 多选checkbox点击选中，赋值并执行跑接口  checkbox="YS_objc.params.logourl" run="YS_objd"
app.directive('checkbox', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2); 
           ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.checkbox, iAttrs.value); 
           iAttrs.run&&ctrlsco.fetch(iAttrs.run);
           ctrlsco.$apply(); 
        });
     }
};
}); 
 

//select 选中赋值，赋值codeid, upselect="status" 
app.directive('upselect', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("change",function(e){
        var ctrlsco = YSfac.getscope_byid(sco,2);   
        var _this = $(this);
           ctrlsco.com_edit.params[iAttrs.upselect] = iElm.val();
           ctrlsco.com_edit.params.codeid = sco.value.codeid;
           ctrlsco.com_edit.done =function(re,sco){
            if(re.code==1){
                _this.addClass('ipright').removeClass('ipwrong');
               }else{
                _this.addClass('ipwrong').removeClass('ipright');
               } 
           }
           ctrlsco.fetch('com_edit');
        });
     }
};
}); 



//公共的编辑方法   comedit='byyl'
app.directive('comedit', function(){
    return {
      link: function(sco, iElm, iAttrs) {
        iElm.on("change", function(event){ 
          var _this = $(iElm);
          var ctrlsco = YSfac.getscope_byid(sco,2); 
          ctrlsco.com_edit.params.codeid = sco.value.codeid;
          ctrlsco.com_edit.params[iAttrs.comedit] = iElm.val(); 
          ctrlsco.com_edit.done = function(re,sco){ 
            YSfac.updatetd(re,_this);
            ctrlsco.com_edit.params = []; 
          }
         ctrlsco.fetch('com_edit'); 
        });
      }
    };
  });



//公共时间插件 ——单个 yslaytime = "YS_plista.params.begintime" run="YS_plista"
app.directive('yslaytime',function(){
    return {
      link: function(sco, iElm, iAttrs) { 
      var ctrlsco = YSfac.getscope_byid(sco, 2); 
      var str = iAttrs.yslaytime; 
      layui.laydate.render({

            elem:$(iElm)[0], 
            type: 'time',
            done: function(value, date, endDate){  
              if(str){
                ctrlsco = YSfac.yssetvalue(ctrlsco,str,value);
                iAttrs.run&&ctrlsco.fetch(iAttrs.run);
                ctrlsco.$apply();
              } 
            }
      }); 
      }
    };
  });


//私有时间插件 ——单个 yslaytime = "YS_plista.params.begintime" run="YS_plista"
app.directive('yslaytimeone',function(){
    return {
      link: function(sco, iElm, iAttrs) { 
      var ctrlsco = YSfac.getscope_byid(sco, 2); 
      var str = iAttrs.yslaytimeone; 
      layui.laydate.render({

            elem:$(iElm)[0], 
            type: 'time',
            done: function(value, date, endDate){  
              if(str){
                ctrlsco = YSfac.yssetvalue(ctrlsco,str,value);

               var key = iAttrs.key;
        ctrlsco.timearr.map(function(e,index){
       if (index == key) {
        ctrlsco.timearr.splice(index,1,value);
       } 


        });





               ctrlsco.YS_objb.params.checkTimes = ctrlsco.timearr;
                ctrlsco.$apply();
              } 
            }
      }); 
      }
    };
  });




//公共月份插件 ——单个 yslaymonth = "YS_plista.params.begintime" run="YS_plista"
app.directive('yslaymonth',function(){
    return {
      link: function(sco, iElm, iAttrs) { 
      var ctrlsco = YSfac.getscope_byid(sco, 2); 
      var str = iAttrs.yslaymonth; 
      layui.laydate.render({

            elem:$(iElm)[0], 
            type: 'month',
            done: function(value, date, endDate){  
              if(str){
                ctrlsco = YSfac.yssetvalue(ctrlsco,str,value);
                iAttrs.run&&ctrlsco.fetch(iAttrs.run);
                ctrlsco.$apply();
              } 
            }
      }); 
      }
    };
  });

//公共日期插件 ——单个 yslaydate = "YS_plista.params.begintime" run="YS_plista"
app.directive('yslaydate',function(){
    return {
      link: function(sco, iElm, iAttrs) { 
      var ctrlsco = YSfac.getscope_byid(sco, 2); 
      var str = iAttrs.yslaydate; 
      layui.laydate.render({
            elem:$(iElm)[0], 
            done: function(value, date, endDate){  
              if(str){
                ctrlsco = YSfac.yssetvalue(ctrlsco,str,value);
                iAttrs.run&&ctrlsco.fetch(iAttrs.run);
                ctrlsco.$apply();
              } 
            }
      }); 
      }
    };
  });

//公共日期插件 ——两个日期连续 ystwodate = "YystwS_plista.params.begintime" to="YS_plista.params.endtime"  run="YS_plista"
app.directive('ystwodate',function(){
    return {
      link: function(sco, iElm, iAttrs) { 
      var ctrlsco = YSfac.getscope_byid(sco, 2); 
      var str = iAttrs.ystwodate;
      var to = iAttrs.to; 
      
      layui.laydate.render({
        //采用click弹出 trigger: 'click',
            elem:$(iElm)[0],
            range: true, 
            // showBottom:false,
            done: function(value, date, endDate){  
              if(str){
                var valarr = value.split(' - ');
                ctrlsco = YSfac.yssetvalue(ctrlsco,str,valarr[0]);
                ctrlsco = YSfac.yssetvalue(ctrlsco,to,valarr[1]);
                iAttrs.run&&ctrlsco.fetch(iAttrs.run);
                ctrlsco.$apply();
              } 
            }
      }); 
iElm.on("mouseup", function(e) {
e.stopPropagation();
return false;
});

      }
    };
  });


//公共日期插件,统一用com_edit单个日期值变化就更新， valdate = "com_edit.params.begintime" 
app.directive('valdate',function(){
    return {
      link: function(sco, iElm, iAttrs) { 
      var ctrlsco = YSfac.getscope_byid(sco, 2);
      var _this = $(iElm);
      var str = iAttrs.valdate;
function vfun(value){
   if(str){
            ctrlsco.com_edit.params={};
            ctrlsco = YSfac.yssetvalue(ctrlsco,str,value); 
            ctrlsco.com_edit.params.codeid = sco.value.codeid;
            ctrlsco.com_edit.done=function(re,sco){
              YSfac.updatetd(re,_this);
            }
            ctrlsco.fetch('com_edit'); 

            ctrlsco.$apply();
          }
}


      layui.laydate.render({
        elem:$(iElm)[0], 
        done: function(value, date, endDate){  
          vfun(value)
        }
      });


iElm.on("change",function(e){ 
var a = YSfac.IsDate(e.target.value);
if(a){
vfun(e.target.value);
} 


 });


      }
    };
  });



//今日


//benzhou 
app.directive('todate', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);

var now = new Date(); //当前日期 
var nowDayOfWeek = now.getDay(); //今天本周的第几天 
var nowDay = now.getDate(); //当前日 
var nowMonth = now.getMonth()+1; //当前月 
var nowYear = now.getYear(); //当前年 
nowYear += (nowYear < 2000) ? 1900 : 0; // 

//获得某月的天数 
function getMonthDays(myMonth){ 
var monthStartDate = new Date(nowYear, myMonth, 1); 
var monthEndDate = new Date(nowYear, myMonth + 1, 1); 
var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24); 
return days; 
} 


if (iAttrs.todate=='t') {
    var stime = nowYear+'-'+nowMonth+'-'+nowDay;    
    var etime = nowYear+'-'+nowMonth+'-'+nowDay;
} 

if (iAttrs.todate=='w') {
    var Nowdate=new Date();     
    var WeekFirstDay=new Date(Nowdate-(Nowdate.getDay()-1)*86400000);   
        var WeekLastDay=new Date((WeekFirstDay/1000+6*86400)*1000);    
    M=Number(WeekFirstDay.getMonth())+1  
    M2=Number(WeekLastDay.getMonth())+1      
    var sdate = nowYear+"-"+M+"-"+WeekFirstDay.getDate();    
debugger;     
    
   var edate = nowYear+"-"+M2+"-"+WeekLastDay.getDate();     

/*debugger;
var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek); 
var weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek)); */
   var  stime =sdate;

   var  etime =edate;
} 

if (iAttrs.todate=='m') {
var monthStartDate = new Date(nowYear, nowMonth, 1); 
   var  stime =YSfac.formatDate2(monthStartDate);

var monthEndDate = new Date(nowYear, nowMonth,getMonthDays(nowMonth)); 
   var  etime =YSfac.formatDate2(monthEndDate);

} 
ctrlsco.YS_plista.params.startDate= stime;

ctrlsco.YS_plista.params.endDate= etime;

ctrlsco.todates = stime+' - '+etime;












          ctrlsco.$apply(); 
        });
     }
};
});










//自定义私有指令 ============================================================================
app.directive('tapshow', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);
          ctrlsco.tapshow = iAttrs.tapshow;
          // sco.tapshow = iAttrs.tapshow;
          ctrlsco.$apply(); 
        });
     }
};
});

app.directive('showload', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);
          var time = iAttrs.showload;
             YSfac.loadclose(time)
          ctrlsco.$apply(); 
        });
     }
};
});




app.directive('tapnav', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);
          ctrlsco.tapnav = iAttrs.tapnav;
          sco.tapnav = iAttrs.tapnav;
          ctrlsco.$apply(); 
        });
     }
};
});


app.directive('tapparams', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);
          ctrlsco[iAttrs.name] = iAttrs.tapparams;
          ctrlsco.$apply(); 
        });
     }
};
});

//重新检测
app.directive('retest', function(){
  return{
    link: function(sco, iElm, iAttrs) {
      iElm.on("click",function(e){
        var ctrlsco = YSfac.getscope_byid(sco,2);
        var ran = 1000;
        var index = 0;
        var times = 0;
        var int = 0;
        if(ctrlsco.retest == 1){
            ctrlsco.fetch('YS_objd');
          }
          if(ctrlsco.retest == 2){
            ctrlsco.fetch('YS_objc');
          }
          if(ctrlsco.retest == 3){
            ctrlsco.fetch('YS_obje');
          }
          if(ctrlsco.retest == 0){
            ctrlsco.fetch('YS_objb');
          }    
            if(iAttrs.cancle){
              ctrlsco.zzjc=0;
              ctrlsco.nowimg = '../images/p0.png'; 
              ctrlsco.nowbaifen = ""; 
              ctrlsco.tapshow = 0;
              ctrlsco.$apply();
              return false;
            }
           
           if(iAttrs.yjjc){
             ctrlsco.fetch(iAttrs.yjjc); 
             if(iAttrs.yjjc=='YS_objc'){
                var a = ctrlsco.YS_objc;
               if(!a.params||!a.params.deviceId){
                layer.msg('请选择设备类型');
                return false;
               }
                // a.params.deviceId = '';
             }else if(iAttrs.yjjc=='YS_objd'){
                var a = ctrlsco.YS_objd;
               if(!a.params||!a.params.linkId){
                layer.msg('请选择链路类型');
                return false;
               }
                // a.params.linkId = '';
             }else if(iAttrs.yjjc=='YS_obje'){
                var a = ctrlsco.YS_obje;
               if(!a.params||!a.params.taskId){
                layer.msg('请选择任务类型');
                return false;
               }
                // a.params.taskId = '';
             }
    
             // var a = iAttrs.yjjc;
           }
    
           ctrlsco.tapshow = 1;
           if(ctrlsco.zzjc==1){
            ctrlsco.zzjc=0;
            window.clearInterval(int);
            ctrlsco.nowimg = '../images/p0.png'; 
            ctrlsco.nowbaifen = ""; 
           }else{
            ctrlsco.templist = [];
            int = ctrlsco.spline();
            ctrlsco.zzjc=1;
            ctrlsco.nowimg = '../images/p0.png'; 
            ctrlsco.nowbaifen = "0%"; 
           }
    
    
           i=1;
           j=1;
    
    
              var ax = setInterval(function(){
                if(i==12){ctrlsco.zzjc=0;ctrlsco.$apply();};
                   if(i>12){ctrlsco.zzjc=0;ctrlsco.$apply();window.clearInterval(ax);};
    
                var imgsrc = '../images/p'+ i +'.png';
    
                if(i>12){
                  ctrlsco.nowimg = '../images/p12.png'; 
                  ctrlsco.nowbaifen = "100%";
                }else{
                  if(ctrlsco.zzjc==0){
                  ctrlsco.nowimg = '../images/p0.png'; 
                  ctrlsco.nowbaifen = ""; 
                    window.clearInterval(ax);
                    window.clearInterval(int);
                  }
                }
    
              if(ctrlsco.examine.length>0&&index<ctrlsco.examine.length){
    
    
                if(ctrlsco.examine.length<13){
    
                  i = i + Math.floor(12/ctrlsco.examine.length) - 1;
                  if(ctrlsco.zzjc==0&&i>ctrlsco.examine.length){
                    return false;
                  }
    
    
                  ctrlsco.templist.unshift(ctrlsco.examine[index]);
                  index++;
                     $('.baifen').text(Math.floor(index/ctrlsco.examine.length*100)+"%");
                     if(index==ctrlsco.examine.length){
                      imgsrc = '../images/p12.png';
                      ctrlsco.zzjc=0;ctrlsco.$apply();window.clearInterval(ax);
                      window.clearInterval(int);
                    }
                    $('.bfimg').attr("src",imgsrc);
                }else{
                  times = Math.ceil(ctrlsco.examine.length/12);
                  i = i + Math.ceil(ctrlsco.examine.length/12) - 1;
    
                  if(ctrlsco.zzjc==0&&i<ctrlsco.examine.length){
                    return false;
                  }
    
                  for (var j = 0; j<times; j++) {
                    ctrlsco.templist.unshift(ctrlsco.examine[index]);
                    index++;
                     $('.baifen').text(Math.floor(index/ctrlsco.examine.length*100)+"%");
                     if(index==ctrlsco.examine.length)imgsrc = '../images/p12.png';
                    $('.bfimg').attr("src",imgsrc);
                  }
                  // if(i>12)i=12;
                }
    
                i++;
                ctrlsco.$apply();
              }
              ran = Math.floor(Math.random()*(5000-200+1)+200);
           },ran)
    
            ctrlsco.$apply();
                })
    }
  }
});
// 恢复系统备份
app.directive('bfload', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) { 
  var ctrlsco = YSfac.getscope_byid(sco, 2); 
  
  var uploadInst = layui.upload.render({
      elem: $(iElm), 
      accept:'file', 
      url: conf.rooturl + '/system/reloadConfig', 
      done: function(re,index, upload){ 
          if(re.code == 0){ 
            ctrlsco.filename = "configData";
            ctrlsco.$apply();
            layer.msg('上传成功'); 
            layer.open({
              type: 1,
              title: false, //不显示标题
              closeBtn: 0,
              shadeClose:false, 
              shade:0.5, 
              area: ['auto', 'auto'], //宽高
              content: $('#sysrecoed')
            });
              }else{
            layer.msg(re.msg);
          } 
      },
      error: function(){
      $('i').addClass('xgico');
    }
      
  }); 
  }
  };
  });
  


app.directive('findtable', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);
          ctrlsco.YS_listb.params.pid = sco.value.pid;
          ctrlsco.fetch('YS_listb');
          ctrlsco.ystab = 2;
          ctrlsco.$apply(); 
        });
     }
};
});



//////文件上传
app.directive('import22', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("change",function(e){ 
          var ctrlsco = YSfac.getscope_byid(sco,2);
var file = $("#upfile")[0].files[0];
var prcodeid =YSfac.getstore('prcodeid');
var token =YSfac.getCookie('token');
var formdata = new FormData();
 formdata.append("file",file);
 formdata.append("prcodeid",prcodeid);
 formdata.append("token",token);

    $.ajax({
        url:  conf.rooturl + '/proauth/invite_import', 
        type : 'POST',
        async : false,
        data :formdata, 
        processData : false,
        contentType : false,
        beforeSend:function(){
        },
        success : function(re) {
            if(re.code>0){
               layer.msg(re.msg);
            }else{
                layer.msg(re.msg);
            }
        }
    });
          ctrlsco.$apply(); 
        });
     }
};
});



app.directive('import', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("change",function(){
              var ctrlsco = YSfac.getscope_byid(sco, 2); 
           var file = $("#upfile")[0].files[0];
            var prcodeid =YSfac.getstore('prcodeid');
            var token =YSfac.getCookie('token');
            var sdata = {'prcodeid':prcodeid,'token':token,'file':file};
              YS('form',function(){
                $('#formid').ajaxSubmit({
                    url:conf.rooturl + '/proauth/invite_import',
                    data:sdata,
                    success: function(re) {
                      
                     // ctrlsco.YS_obja.params.invitearr = re.data;
                     // ctrlsco.tapshow = 1;
                      ctrlsco.$apply(); 
                    }
                }); 
              }) 
                
            });
     }
};
});


//结合layui的文件上传
app.directive('ysimgupload', function(){ 
return { 
link: function(sco, iElm, iAttrs) { 
var ctrlsco = YSfac.getscope_byid(sco, 2); 

var prcodeid =YSfac.getstore('prcodeid');
var token =YSfac.getCookie('token');
var sdata = {'prcodeid':prcodeid,'token':token};

var uploadInst = layui.upload.render({
    elem: '.layupload', 
    accept:'file',
    url: conf.rooturl + '/proauth/invite_import', 
    data:sdata, 
    done: function(res){ 
        if(res.code > 0){ 
        sco.invitelist = res.data;
        sco.upload = 1;
        sco.tapshow="drall2";
        sco.YS_obja.params.imgurl = res.data.fileurl;
        sco.$apply();
        return layer.msg('上传成功'); 
        }else{
          layer.msg(res.msg);
        } 
    } 
}); 
}
};
});

//导入送检计划  
app.directive('sjupload', function(){ 
return { 
link: function(sco, iElm, iAttrs) { 
var ctrlsco = YSfac.getscope_byid(sco, 2); 
//参数 import_type yc-- by  prcodeid  工程codeid  scodeid 导入送检部位时需要
var sdata ={};
var uploadInst = layui.upload.render({
    elem: '.layupload', 
    accept:'file',
    url: conf.rooturl + '/proauth/sjtz_import', 
    data:{
          prcodeid: function(){
            return YSfac.getstore('prcodeid')
          },
          token:function(){
            return YSfac.getCookie('token')
          },
          import_type:function(){
            return ctrlsco.YS_listb.params.field;
          },
          scodeid:function(){
            return ctrlsco.YS_listb.params.codeid
          }, 
        }, 
    done: function(res){ 
        if(res.code > 0){  
         layer.msg('上传成功'); 
         ctrlsco.fetch('YS_listb');
        }else{
          layer.msg(res.msg);
        } 
    } 
}); 
}
};
});




//送检汇总查询 
  app.directive('findsj', function() {
    return {
      link: function(sco, iElm, iAttrs) {
        iElm.on("click", function(e) {
          var ctrlsco = YSfac.getscope_byid(sco, 2);
          //先校验时间大小
          if (!ctrlsco.YS_lista.params.btime||!ctrlsco.YS_lista.params.etime) {
            layer.msg('时间不能为空');
          };
          if (ctrlsco.YS_lista.params.btime > ctrlsco.YS_lista.params.etime) {
            layer.msg('结束时间必须大于开始时间！');
          };
          ctrlsco.fetch(iAttrs.findsj);
          ctrlsco.YS_lista.done = function(re, sco) {
            if (re.code > 0) {
              layer.msg('查询成功');
            } else {
              layer.msg('查询失败');
            }
          };


        });
      }
    };
  });

//七牛一张图上传   id="pickfiles" qiniuimg="com_obja.params.proimg"
//工程概况图片，子单位图片，机械图片，个人头像 都走这个
app.directive('qiniuimg', function() {
  return {
    link: function(sco, iElm, iAttrs) {
      var _this = $(iElm);
      YS('Qiniu', function() {
        var button = iAttrs.id;
        var arr = iAttrs.qiniuimg.split('.'); 
        var multi_selection = false;
/*        $.ajax({
          url: conf.rooturl + '/open/get_qiniuinfo',
          data: {
            "token": YSfac.getstore('token'),
            type: 'open'
          },
          async: false,
          success: function(qnobj) {
            setTimeout(function() {*/
           
              YSfac.qiniuupload(JSON.parse(decodeURIComponent(YSfac.getCookie('QiniuInfo'))).domain, JSON.parse(decodeURIComponent(YSfac.getCookie('QiniuInfo'))).token, button, multi_selection, function(up, res) {
                YSfac.load(0);
                var ctrlsco = YSfac.getscope_byid(sco, 2);
                ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.qiniuimg, JSON.parse(decodeURIComponent(YSfac.getCookie('QiniuInfo'))).domain + '/' + res[res.length - 1].target_name);
                var imgurl = JSON.parse(decodeURIComponent(YSfac.getCookie('QiniuInfo'))).domain + '/' + res[res.length - 1].target_name;
                //个人中心没有这个参数
                if (sco.value[arr[2]]) {sco.value[arr[2]] = imgurl;}
                
                //公共修改接口 带参数  带codeid
                ctrlsco[iAttrs.qiniuimg] = imgurl
                if (arr[2] == 'proimg') {
                ctrlsco[arr[0]][arr[1]].codeid = YSfac.getstore('prcodeid');
                }

                if (arr[2] == 'sinimg') {
                ctrlsco[arr[0]][arr[1]].codeid = YSfac.getstore('scodeid');
                }
                if (arr[2] == 'jximg') {
                ctrlsco[arr[0]][arr[1]].codeid = YSfac.getstore('jxcodeid');
                }
                ctrlsco.fetch(arr[0]);
                if (arr[2] == 'proimg' || arr[2] == 'sinimg' || arr[2] == 'jximg') {
                  ctrlsco[arr[0]].done = function(re, sco) {
                    re.code == 1 ? _this.addClass('ipright').removeClass('ipwrong') : _this.addClass('ipwrong').removeClass('ipright');
                    YSfac.msg(re);
                  }
                }
                ctrlsco.$apply();
/*              });
            }, 500);
          }*/
        });
      });


    }
  };
});



//七牛一张图上传   id="pickfiles" qiniuupload="com_obja.params.proimg" 
app.directive('qiniupu', function() {
return {
  link: function(sco, iElm, iAttrs) {
    var _this = $(iElm);
    YS('Qiniu', function() {
      var button = iAttrs.id;
      var arr = iAttrs.qiniupu.split('.'); 
      var multi_selection = false;
/*      $.ajax({
        url: conf.rooturl + '/open/get_qiniuinfo',
        data: {
          "token": YSfac.getstore('token'),
          type: 'open'
        },
        async: false,
        success: function(qnobj) {*/
          setTimeout(function() {
            
            YSfac.qiniuupload(JSON.parse(decodeURIComponent(YSfac.getCookie('QiniuInfo'))).domain, JSON.parse(decodeURIComponent(YSfac.getCookie('QiniuInfo'))).token, button, multi_selection, function(up, res) {
              YSfac.load(0);
              var ctrlsco = YSfac.getscope_byid(sco, 2);
              var imgurl = JSON.parse(decodeURIComponent(YSfac.getCookie('QiniuInfo'))).domain + '/' + res[res.length - 1].target_name;
              ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.qiniupu,imgurl); 
              ctrlsco.$apply();
            });
/*          }, 500);
        }*/
      });
    });


    }
  };
});




//公共的编辑方法   comedit='byyl'
app.directive('editpro', function(){
    return {
      link: function(sco, iElm, iAttrs) {
        iElm.on("change", function(event){ 
          var _this = $(iElm);
          var ctrlsco = YSfac.getscope_byid(sco,2); 
          ctrlsco.com_objd.params.codeid = sco.value.codeid;
          ctrlsco.com_objd.params[iAttrs.editpro] = iElm.val(); 
          ctrlsco.com_objd.done = function(re,sco){ 
            YSfac.updatetd(re,_this);
          }
         ctrlsco.fetch('com_objd'); 
        });
      }
    };
  });


//提示
app.directive('showtips', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("mouseover",function(e){  
         $('.tips').removeClass('dpn')
        });
        iElm.on("mouseleave",function(e){  
         $('.tips').addClass('dpn')
        });

     }
};
});

// 用户个人中心菜单
app.directive('showumenu', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) {
    iElm.on("mouseover",function(e){  
      $('.h_umenu').slideDown(200);
      $('.h_umenu').show();
     });
     iElm.on("mouseleave",function(e){
      $('.h_umenu').hide();
     });
       }
  };
  });
   app.directive('colseinfo', function(){ 
    return { 
    link: function(sco, iElm, iAttrs) {
      iElm.on("mouseout",function(e){  
        var ctrlsco = YSfac.getscope_byid(sco,2); 
        ctrlsco.YS_lista.data.list.map(function(elem){
          elem.topologyNodes.map(function(ele){
            ele['show'] = 0;
            if(ele.child){
              ele.child.map(function(el){
                el['show'] = 0
              })
            }
          })
        })
        ctrlsco.$apply();
       });
      }
    };
    });


  app.directive('colseumenu', function(){ 
    return { 
    link: function(sco, iElm, iAttrs) {
      iElm.on("click",function(e){  
        var ctrlsco = YSfac.getscope_byid(sco,2); 
        $('.h_umenu').addClass('dpn');
       });
      }
    };
    });
  //更多

  app.directive('showmore', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  

  $(this).next().toggleClass("dpn");
   });
     }
};
});

app.directive('cfclose', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
         layer.open({
          type: 1,
          title: false, //不显示标题
          closeBtn: 0,
          shadeClose:false, 
          shade:0.5, 
          area: ['auto', 'auto'], //宽高
          content: $('#powertc')
        });
      });
     }
};
});

app.directive('cfrestar', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) {
          iElm.on("click",function(e){  
           layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose:false, 
            shade:0.5, 
            area: ['auto', 'auto'], //宽高
            content: $('#powerrestar')
          });
        });
       }
  };
  });
  
app.directive('rest', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 

 if (ctrlsco.YS_plista.params.name) {ctrlsco.YS_plista.params.name=''} 
 if (ctrlsco.YS_plista.params.type) {ctrlsco.YS_plista.params.type=''} 
 if (ctrlsco.YS_plista.params.key) {ctrlsco.YS_plista.params.key=''} 
 if (ctrlsco.YS_plista.params.level) {ctrlsco.YS_plista.params.level=''} 

 if (ctrlsco.YS_plista.params.startDate) {ctrlsco.YS_plista.params.startDate=''} 
 if (ctrlsco.YS_plista.params.endDate) {ctrlsco.YS_plista.params.endDate=''} 

 if (ctrlsco.YS_objb.params.startDate) {ctrlsco.YS_objb.params.startDate=''} 
 if (ctrlsco.YS_objb.params.endDate) {ctrlsco.YS_objb.params.endDate=''} 


 if (ctrlsco.YS_plista.params.bid) {ctrlsco.YS_plista.params.bid=''} 

 if (ctrlsco.YS_plista.params.linkId) {
   if (ctrlsco.YS_lista.data.list[0].linkId) {
   ctrlsco.YS_plista.params.linkId = ctrlsco.YS_lista.data.list[0].linkId;
   }
} 

 if (ctrlsco.YS_plista.params.deviceId) {
   if (ctrlsco.YS_listb.data.list[0].deviceId) {
   ctrlsco.YS_plista.params.deviceId = ctrlsco.YS_listb.data.list[0].deviceId;
   }

   
} 



 if (ctrlsco.YS_plista.params.keyword) {ctrlsco.YS_plista.params.keyword=''} 
 if (ctrlsco.YS_plista.params.uid) {ctrlsco.YS_plista.params.uid=''} 
if ($('.choosetime input')) {$('.choosetime input').attr("checked",false)}

       
       $(".layui-form input").val("");
      ctrlsco.$apply();
      });
     }
};
});
// 系统备份位置询问弹窗
app.directive('sysbackup', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#sysbackup')
          });
        });
      });
    }
  };
});

// 系统恢复位置查找弹窗
app.directive('sysreco', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#sysreco')
          });
        });
      });
    }
  };
});

// 系统恢复中和系统恢复完成
app.directive('sysrecoing', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#sysrecoing')
          });
        });
        setTimeout(function(){
          YS('layer', function () {
            layer.open({
              type: 1,
              title: false, //不显示标题
              closeBtn: 0,
              shadeClose: false,
              shade: 0.5,
              area: ['auto', 'auto'], //宽高
              content: $('#sysrecoed')
            });
          });
          },3000)
    });
    }
  };
});



// 恢复出厂备份弹窗
app.directive('chuchang', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#reask')
          });
        });
      });
    }
  };
});
//恢复成功弹窗
app.directive('hfcg', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
layer.closeAll();


        YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#resucc')
          });
        });
      });
    }
  };
});
// 处理建议弹窗
app.directive('dealshow', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2); 
        ctrlsco.alarmtype=iAttrs.type;
        ctrlsco.YS_plistb.params.alarmType=sco.value.type;
        ctrlsco.YS_plistb.params.typename=sco.value.typename;

        if (ctrlsco.YS_plistb.params.currentPage) {ctrlsco.YS_plistb.params.currentPage=1;} 
        
        sco.fetch("YS_plistb")
        YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#dealtip')
          });
        });
      });
    }
  };
});

// 新增知识库
app.directive('addknow', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2);
        if(ctrlsco.YS_obja.params.knowledgeDesc){
          var desc=ctrlsco.YS_obja.params.knowledgeDesc;
          ctrlsco.YS_obja.params.alarmType=ctrlsco.alarmtype;
          sco.fetch("YS_obja");
          ctrlsco.jylist.unshift({knowledgeDesc:desc,upNum:1});
          ctrlsco.$apply(); 
        }else{
          layer.closeAll()
        }

      });
    }
  };
});
// 建议点赞
app.directive('upknow', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2);
        ctrlsco.YS_objb.params.knowledgeId=iAttrs.id;
        sco.fetch("YS_objb")
       ctrlsco.haszan = iAttrs.id;
        sco.value.upNum+=1;
        ctrlsco.$apply(); 
        // setTimeout(function(){
        //   sco.fetch("YS_plistb")
          
        // },500)
        
      });
    }
  };
});

app.directive('lempty', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        sco.reload = false;
        location.reload()
         // $("#main_e").empty()
      });
    }
  };
});


app.directive('mempty', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       // debugger
         // $("#leftlink").empty()
        jsPlumb.deleteEveryEndpoint();
        jsPlumb.detachEveryConnection();
      });
    }
  };
});
//用户列表删除
app.directive('ulistdel', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.YS_objc.params.uid=sco.value.uid;
        YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#userremove')
          });
        });
      });
    }
  };
});

// 设备删除
app.directive('elistdel', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.YS_objc.params.deviceId=sco.value.deviceId?sco.value.deviceId:iAttrs.elistdel;
        YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#remove')
          });
        });
      });
    }
  };
});


// 用户解锁
app.directive('userjs', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.YS_obja.params.uid = sco.value.uid;
      sco.fetch("YS_obja")
      });
     }
};
});

//scwjs 
app.directive('scwjs', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
       var _this = $(iElm);
           e.stopPropagation();
       var ctrlsco = YSfac.getscope_byid(sco,2); 
        var key  = iAttrs.index;
        ctrlsco.filelist.map(function(elem,index){
     if (elem.lindex == key) {
      ctrlsco.filelist.splice(index,1);
      //删除时要在顶部显示
        $("#left_e .item").each(function(){
        if($(this).attr("data-index")==elem.lindex){
          $(this).removeClass("dpn").draggable("enable"); // 激活其拖动功能
        }
      });
     } else {
      return;
     }
        });
  ctrlsco.$apply(); 
      });
     }
};
});


app.directive('sbinfo', function () {
  return {
    link: function (sco, iElm, iAttrs) {

      iElm.on("mouseover", function (e) {
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.YS_objd.params.deviceId = sco.value.deviceId; 
       sco.fetch("YS_objd")

    });
  }
};
});

//重新注册 rezc
app.directive('rezc', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
       var ctrlsco = YSfac.getscope_byid(sco,2); 
             debugger;
  var oldinfo = JSON.parse(ctrlsco.YS_objb.data.configData)




if (ctrlsco.YS_obja.params.systemName==oldinfo.systemName&&ctrlsco.YS_obja.params.managerName==oldinfo.managerName
  &&ctrlsco.YS_obja.params.managerPhone==oldinfo.managerPhone&&ctrlsco.YS_obja.params.managerEmail==oldinfo.managerEmail)
   {
    YSfac.load(1,'请勿重复提交');return false;

} else {

   sco.fetch('YS_obja');


}

        });
     }
};
});

// 选择图标
app.directive('chicon', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
       layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#chicon')
          });
        });
     }
};
});

app.directive('sicon', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){  
        var _this = $(iElm);
        var ctrlsco = YSfac.getscope_byid(sco,2); 
        ctrlsco.YS_obja.params.icon = sco.value.id;  
        ctrlsco.YS_objb.params.icon = sco.value.id;  
        ctrlsco.$apply(); 
      });
     }
};
});





























//修改密码 

app.directive('xgpwd', function () {
  return {
    link: function (sco, iElm, iAttrs) {
 var ctrlsco = YSfac.getscope_byid(sco,2); 
      iElm.on("click", function (e) {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#repwd2')
          });
      });

    }
  };
});









// 重置密码
app.directive('repwd', function () {
  return {
    link: function (sco, iElm, iAttrs) {
 var ctrlsco = YSfac.getscope_byid(sco,2); 
      iElm.on("click", function (e) {

                    ctrlsco.YS_objb.params.uid = sco.value.uid; 

                    ctrlsco.fetch('YS_objb');
    

      });
    }
  };
});


// 重置密码
app.directive('repwd2', function () {
  return {
    link: function (sco, iElm, iAttrs) {
 var ctrlsco = YSfac.getscope_byid(sco,2); 
      iElm.on("click", function (e) {

                    ctrlsco.YS_objd.params.uid =  ctrlsco.YS_objb.params.uid

                    ctrlsco.fetch('YS_objd');
    

      });
    }
  };
});




// 导航栏用户名忘记密码选项
app.directive('fgpwd', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) {
          iElm.on("click",function(e){
          layer.open({
          type: 1,
          title: false, //不显示标题
          closeBtn: 0,
          shadeClose:false, 
          shade:0.5, 
          area: ['auto', 'auto'], //宽高
          content: $('#repwd2')
        });
      });
       }
  };
  });

//编辑用户时关闭弹窗，重置表单值
app.directive('closenull', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) {
          iElm.on("click",function(e){
            layer.closeAll();
          });
       }
  };
  });
  









// 查看/编辑设备
app.directive('editep', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.YS_objb.params.deviceId=sco.value.deviceId;
       ctrlsco.YS_objb.params.deviceName=sco.value.deviceName;
       ctrlsco.YS_objb.params.area=sco.value.area;
       ctrlsco.YS_objb.params.netArea=sco.value.netArea;
       ctrlsco.YS_objb.params.deviceType=sco.value.deviceType;
       ctrlsco.YS_objb.params.ip=sco.value.ip;
       ctrlsco.YS_objb.params.icon=sco.value.icon;
       // $("#sbtype option[value='2']").attr('selected','selected');
       // $("#sbarea option[value='2']").attr('selected',"selected");
       // $("#netarea option[value='sco.value.netArea']").attr('selected','selected');
       ctrlsco.tapshow = 1;
       sco.tapshow = 1;
       ctrlsco.YS_obje.params.deviceId = sco.value.deviceId;
       ctrlsco.fetch('YS_obje');
      setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },300)

       ctrlsco.$apply(); 
      });
    }
  };
});

//切换的时候render下拉框
app.directive('renderselect', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
if (ctrlsco.YS_obje.params.deviceId) {
  ctrlsco.YS_obje.params.deviceId='';
}
       setTimeout(function(){
        layui.use('form', function(){
          layui.form.render();
        }); 
      },300)

       ctrlsco.$apply(); 
      });
    }
  };
});



// 链路管理查看编辑
app.directive('editlink', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.YS_objc.params.linkId=sco.value.linkId;
       sco.fetch("YS_objc");
       ctrlsco.tapshow = 3;
       ctrlsco.filelist = [];
       ctrlsco.$apply(); 
      });
    }
  };
});
// 编辑用户
app.directive('edituser', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       YSfac.setstore('uid',sco.value.uid)     
       ctrlsco.$apply(); 
      });
    }
  };
});
// 
app.directive('linknext', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       if(!ctrlsco.YS_obja.params.linkName){
        layer.tips("请输入链路名称！","#xlinkName",{tips: [3, 'rgba(0,0,0,.8)','']});return false
       };
       if(!ctrlsco.YS_obja.params.linkType){
        layer.tips("请选择链路类型！","#linktype",{tips: [3, 'rgba(0,0,0,.8)','']});return false
       };
       if(!ctrlsco.YS_obja.params['dbConfigData.dbName']){
       layer.tips("请输入数据库名！","#xdbName",{tips: [3, 'rgba(0,0,0,.8)','']});return false
      };
       if(!ctrlsco.YS_obja.params['dbConfigData.ip']){
       layer.tips("请输入ip地址！","#xip",{tips: [3, 'rgba(0,0,0,.8)','']});return false
      };
     if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(ctrlsco.YS_obja.params['dbConfigData.ip'])){layer.tips("请输入合法的IP地址！","#xip",{tips: [3, 'rgba(0,0,0,.8)','']});return false};
     if(!ctrlsco.YS_obja.params['dbConfigData.port']){
      layer.tips("请输入端口号！","#xport",{tips: [3, 'rgba(0,0,0,.8)','']});return false
     };
     if(!ctrlsco.YS_obja.params['dbConfigData.name']){
     layer.tips("请输入用户名！","#xname",{tips: [3, 'rgba(0,0,0,.8)','']});return false
     };
      if(!ctrlsco.YS_obja.params['dbConfigData.pwd']){
     layer.tips("请输入密码！","#xpwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
     };

       sco.fetch("YS_objf")

      ctrlsco.YS_objf.done = function(re, sco) {
        if (re.code == 0) {

          if (re.data.isConnect) {
        var msgs = re.msg ? re.msg : '数据库检测中';
              YSfac.load2(1, msgs);
            setTimeout(function() {
              layer.closeAll();
             ctrlsco.tapshow=2;  
             ctrlsco.$apply();
            },1000);
          } else {
             var msgs = re.msg ? re.msg : '数据库检测失败';
                         setTimeout(function() {
                       layer.msg(msgs); 
            },500);
          }
        } else {
            var msgs = re.msg ? re.msg : '数据库检测失败';
         setTimeout(function() {
                       layer.msg(msgs); 
            },500);
        }
      }
       ctrlsco.$apply();

      });
    }
  };
});

// 添加链路拓扑图
app.directive('savelink', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.save()
       setTimeout(function(){
        sco.fetch("YS_obja")
       },1000)
       
      });
    }
  };
});

// 编辑链路拓扑图
app.directive('etoplog', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.save()
       setTimeout(function(){
        sco.fetch("YS_objb")
       },1000)
       
      });
    }
  };
});



app.directive('showfile', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       if(iAttrs.key=='true'){
        $(".filebox").toggleClass("dpn")
       }
      });
    }
  };
});



app.directive('tijian', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       var ran = 1000;
      var index = 0;
      var times = 0;
      var int = 0;
      var currentStep = 0;
      var waitTime = 0;
      var ran = 1000;
      var maxCheckId = 0;
   window.clearInterval(ctrlsco.ax);

        if(iAttrs.cancle){
          ctrlsco.zzjc=0;
          ctrlsco.nowimg = '../images/p0.png'; 
          ctrlsco.nowbaifen = ""; 
          ctrlsco.tapshow = 0;
          ctrlsco.$apply();
          return false;
        }


       
       if(iAttrs.tijian){
         ctrlsco.fetch(iAttrs.tijian); 
         ctrlsco.currentTijian = iAttrs.tijian;
         if(iAttrs.tijian=='YS_objc'){
            var a = ctrlsco.YS_objc;
           if(!a.params||!a.params.deviceId){
            layer.msg('请选择设备类型');
            return false;
           }
            // a.params.deviceId = '';
         }else if(iAttrs.tijian=='YS_objd'){
            var a = ctrlsco.YS_objd;
           if(!a.params||!a.params.linkId){
            layer.msg('请选择链路类型');
            return false;
           }
            // a.params.linkId = '';
         }else if(iAttrs.tijian=='YS_obje'){
            var a = ctrlsco.YS_obje;
            if (!ctrlsco.YS_objf.params.linkId) {
               layer.msg('请先选择任务所属链路'); return false;
            } 
           if(!a.params||!a.params.taskId){
            layer.msg('请选择任务类型');
            return false;
           }
         }
         // var a = iAttrs.tijian;
       }


       if(iAttrs.reset){
        ctrlsco.fetch(ctrlsco.currentTijian);
       }



       ctrlsco.tapshow = 1;
       if(ctrlsco.zzjc==1){
        ctrlsco.zzjc=0;
        window.clearInterval(int);
        ctrlsco.nowimg = '../images/p0.png'; 
        ctrlsco.nowbaifen = 0; 
       }else{
        ctrlsco.templist = [];
        int = ctrlsco.spline();
        ctrlsco.zzjc=1;
        ctrlsco.nowimg = '../images/p0.png'; 
        ctrlsco.nowbaifen = 0; 
       }


       i=1;
       j=1;


          ctrlsco.ax = setInterval(function(){

            //等待时间超过一分钟直接显示100%，并结束
            if(waitTime > 60000){
                ctrlsco.nowimg = '../images/p12.png'; 
                ctrlsco.nowbaifen = 100;
                ctrlsco.zzjc=0;
                ctrlsco.$apply();
                window.clearInterval(ctrlsco.ax);
                return;
            }


            //无数据
            if(ctrlsco.YS_objb.data.checkItems.length==0){

                waitTime += 1000;
                ctrlsco.fetch("YS_objb");
                return false;
            }



          //当有数据时并且不是最后一步
          // if(ctrlsco.maxStep>0&&currentStep<ctrlsco.maxStep){
          if(ctrlsco.examine.length>0){

              //插入数组展示
              ctrlsco.templist.unshift(ctrlsco.examine[currentStep]);
              //每次覆盖总数
              ctrlsco.maxStep = ctrlsco.examine[currentStep].totalStep;
              //赋值当前itemId
              if(maxCheckId<ctrlsco.examine[currentStep].itemId){
                maxCheckId = ctrlsco.examine[currentStep].itemId;
              }
              ctrlsco.YS_objb.params.index = maxCheckId;
              //淡入效果
              //step+1
              currentStep++;

              ran = 600;

              // if(ctrlsco.maxStep<13){
              //   i = Math.floor(i + ctrlsco.maxStep/12);
              // }else{
              //   i = i + Math.ceil(currentStep/ctrlsco.maxStep);
              // }
              


              var imgsrc = '../images/p'+ i +'.png';
              var progress = Math.floor(currentStep/ctrlsco.maxStep*100);

              //当百分比变大时才变化
              if(ctrlsco.nowbaifen<progress&&progress<100){

                ctrlsco.nowbaifen = progress;
                i = Math.floor(progress/8.33)<1?1:Math.floor(progress/8.33);
                ctrlsco.nowimg = '../images/p'+i+'.png';
              }
 

            //执行到最后一步
            if(currentStep==ctrlsco.maxStep&&currentStep==ctrlsco.examine.length){
              ctrlsco.nowimg = '../images/p12.png'; 
              ctrlsco.nowbaifen = "100";
              ctrlsco.zzjc=0;
              window.clearInterval(ctrlsco.ax);
            }else if(currentStep<ctrlsco.maxStep&&currentStep==ctrlsco.examine.length){//当前数组遍历完，拿最大checkid重新跑接口
              ctrlsco.fetch("YS_objb");
            }

            // if(currentStep==maxStep){
            //   ctrlsco.zzjc=0;ctrlsco.$apply();window.clearInterval(ax);
            // }

            ctrlsco.$apply();
          }
          //Math.floor(Math.random()*(5000-200+1)+200);
       },ran)

        ctrlsco.$apply();
      });
    }
  };
});





app.directive('yjjc', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       var ran = 1000;
      var index = 0;
      var times = 0;
      var int = 0;

        if(iAttrs.cancle){
          ctrlsco.zzjc=0;
          ctrlsco.nowimg = '../images/p0.png'; 
          ctrlsco.nowbaifen = ""; 
          ctrlsco.tapshow = 0;
          ctrlsco.$apply();
          return false;
        }
       
       if(iAttrs.yjjc){
         ctrlsco.fetch(iAttrs.yjjc); 
         if(iAttrs.yjjc=='YS_objc'){
            var a = ctrlsco.YS_objc;
           if(!a.params||!a.params.deviceId){
            layer.msg('请选择设备类型');
            return false;
           }
            // a.params.deviceId = '';
         }else if(iAttrs.yjjc=='YS_objd'){
            var a = ctrlsco.YS_objd;
           if(!a.params||!a.params.linkId){
            layer.msg('请选择链路类型');
            return false;
           }
            // a.params.linkId = '';
         }else if(iAttrs.yjjc=='YS_obje'){
            var a = ctrlsco.YS_obje;
           if(!a.params||!a.params.taskId){
            layer.msg('请选择任务类型');
            return false;
           }
            // a.params.taskId = '';
         }

         // var a = iAttrs.yjjc;
       }

       ctrlsco.tapshow = 1;
       if(ctrlsco.zzjc==1){
        ctrlsco.zzjc=0;
        window.clearInterval(int);
        ctrlsco.nowimg = '../images/p0.png'; 
        ctrlsco.nowbaifen = ""; 
       }else{
        ctrlsco.templist = [];
        int = ctrlsco.spline();
        ctrlsco.zzjc=1;
        ctrlsco.nowimg = '../images/p0.png'; 
        ctrlsco.nowbaifen = "0%"; 
       }


       i=1;
       j=1;


          var ax = setInterval(function(){
            if(i==12){ctrlsco.zzjc=0;ctrlsco.$apply();};
               if(i>12){ctrlsco.zzjc=0;ctrlsco.$apply();window.clearInterval(ax);};

            var imgsrc = '../images/p'+ i +'.png';

            if(i>12){
              ctrlsco.nowimg = '../images/p12.png'; 
              ctrlsco.nowbaifen = "100%";
            }else{
              if(ctrlsco.zzjc==0){
              ctrlsco.nowimg = '../images/p0.png'; 
              ctrlsco.nowbaifen = ""; 
                window.clearInterval(ax);
                window.clearInterval(int);
              }
            }

          if(ctrlsco.examine.length>0&&index<ctrlsco.examine.length){


            if(ctrlsco.examine.length<13){

              i = i + Math.floor(12/ctrlsco.examine.length) - 1;
              if(ctrlsco.zzjc==0&&i>ctrlsco.examine.length){
                return false;
              }


              ctrlsco.templist.unshift(ctrlsco.examine[index]);
              index++;
                 $('.baifen').text(Math.floor(index/ctrlsco.examine.length*100)+"%");
                 if(index==ctrlsco.examine.length){
                  imgsrc = '../images/p12.png';
                  ctrlsco.zzjc=0;ctrlsco.$apply();window.clearInterval(ax);
                  window.clearInterval(int);
                }
                $('.bfimg').attr("src",imgsrc);
            }else{
              times = Math.ceil(ctrlsco.examine.length/12);
              i = i + Math.ceil(ctrlsco.examine.length/12) - 1;

              if(ctrlsco.zzjc==0&&i<ctrlsco.examine.length){
                return false;
              }

              for (var j = 0; j<times; j++) {
                ctrlsco.templist.unshift(ctrlsco.examine[index]);
                index++;
                 $('.baifen').text(Math.floor(index/ctrlsco.examine.length*100)+"%");
                 if(index==ctrlsco.examine.length)imgsrc = '../images/p12.png';
                $('.bfimg').attr("src",imgsrc);
              }
              // if(i>12)i=12;
            }

            i++;
            ctrlsco.$apply();
          }
          ran = Math.floor(Math.random()*(5000-200+1)+200);
       },ran)

        ctrlsco.$apply();
      });
    }
  };
});
app.directive('addtjsj', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.deviceServiceList.push({time:ctrlsco.YS_obja.params.tjsj})
       ctrlsco.$apply();
      });
    }
  };
});

app.directive('deltjsj', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.deviceServiceList.pop();
       ctrlsco.$apply();
      });
    }
  };
});


app.directive('addsbs', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
      //  if(ctrlsco.deviceServiceList.length == 0){
      //     ctrlsco.countnum = ctrlsco.deviceServiceList.length;
      //  }
       var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
      //  ctrlsco.countnum += 1;
      ctrlsco.countnum = ctrlsco.deviceServiceList.length+1;
       var count;
       var obj = {serviceName:"",serviceType:"",serviceType:"",ip:"",configData:{dbName:"",ip:"",name:"",pwd:"",port:""},count:ctrlsco.countnum}
       // if(!ctrlsco.YS_obja.params.deviceServiceList)ctrlsco.YS_obja.params.deviceServiceList = [];
       // ctrlsco.YS_obja.params.deviceServiceList.push(obj);
       // ctrlsco.YS_objb.params.deviceServiceList.push(obj);
       ctrlsco.deviceServiceList.push(obj);
       ctrlsco.$apply();
      });
    }
  };
});

app.directive('delsbs', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2); 
        ctrlsco.deviceServiceList.splice(iAttrs.delsbs,1);
        ctrlsco.$apply();
      });
    }
  };
});

// 平台注册联系人电话验证
app.directive('checkphone',function(){
  return{
    link:function(sco, iElm, iAttrs) {
      iElm.on("blur",function(obj){
        var phone = $('#regphone').val();
        var check_phone = document.getElementById('check_phone');
        var regphone = document.getElementById('regphone');
        if (phone.trim().length == 0) {
          check_phone.innerHTML = "电话号码不能为空";
          regphone.setAttribute ("style","border:1px solid red !important;");
          return;
      }
      if(!(/^1[34578]\d{9}$/.test(phone))&&!(/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(sco.YS_obja.params.managerPhone))){ 
        check_phone.innerHTML = "请输入正确的电话号码";
        regphone.setAttribute ("style","border:1px solid red !important;");
        return;
      }
        else {
          check_phone.innerHTML = "";
          regphone.setAttribute("style","border:1px solid #0b4c63 !important;")
      }
          })
    }
  }
});
// 平台注册邮箱地址验证
app.directive('checkemail',function(){
  return{
    link:function(sco,iElm,iAttrs){
      iElm.on("blur",function(re){
        var email = $('#regemail').val();
        var check_email = document.getElementById('check_email');
        var regemail = document.getElementById('regemail');
        if(email.trim().length == 0) {
          check_email.innerHTML = "负责人公安网邮箱地址不能为空";
          regemail.setAttribute ("style","border:1px solid red !important;");
          return;
        }
        if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email))){
          check_email.innerHTML = "请输入正确的负责人公安网邮箱地址";
          regemail.setAttribute("style","border:1px solid red !important;");
          return;         
        }
        else{
          check_email.innerHTML = "";
          regemail.setAttribute("style","border:1px solid #0b4c63 !important;")
        }
      })
    }
  }
});

// 校验报警配置邮箱
app.directive('checkacemail',function(){
  return{
    link:function(sco,iElm,iAttrs){
      iElm.on("blur",function(re){
       var ctrlsco = YSfac.getscope_byid(sco,2); 
        var emailbj ="";
        if(iAttrs.checkacemail=='YS_obja'){
          emailbj = ctrlsco.YS_obja.params.emailConfig.emailbj;
   if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(emailbj)))
    {layer.tips("请输入合法的邮箱地址！","#"+iAttrs.id,{tips: [3, 'rgba(0,0,0,.8)','']});return false};
        }

      })
    }
  }
});
// 验证手机号码
app.directive('chkbjphone',function(){
  return{
    link:function(sco,iElm,iAttrs){
      iElm.on("blur",function(re){
       var ctrlsco = YSfac.getscope_byid(sco,2); 
        var phone ="";
        if(iAttrs.chkbjphone=='YS_obja'){
          phone = ctrlsco.YS_obja.params.smsConfig.fstel;
   if(!(/^1[34578]\d{9}$/.test(phone))&&!(/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(phone)))
    {layer.tips("请输入合法的手机号码！","#"+iAttrs.id,{tips: [3, 'rgba(0,0,0,.8)','']});return false};
        }

      })
    }
  }
});
// 校验IP
app.directive('checkip',function(){
  return{
    link:function(sco,iElm,iAttrs){
      iElm.on("blur",function(re){
       var ctrlsco = YSfac.getscope_byid(sco,2); 
        var ip ="";
        if(iAttrs.checkip=='YS_obja'){
          ip = ctrlsco.YS_obja.params.ip;
if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(ip))
  {layer.tips("请输入合法的IP地址！","#"+iAttrs.id,{tips: [3, 'rgba(0,0,0,.8)','']});return false};
        }else if(iAttrs.checkip=='YS_objb'){
          ip = ctrlsco.YS_objb.params.ip;
if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(ip))
  {layer.tips("请输入合法的IP地址！","#"+iAttrs.id,{tips: [3, 'rgba(0,0,0,.8)','']});return false};
        }else if(iAttrs.checkip=="YS_obja.params['dbConfigData.ip']"){
          ip = ctrlsco.YS_obja.params['dbConfigData.ip'];
if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(ip))
  {layer.tips("请输入合法的IP地址！","#"+iAttrs.id,{tips: [3, 'rgba(0,0,0,.8)','']});return false};
        }else if(iAttrs.checkip=="YS_obja.params.localIp"){
          ip = ctrlsco.YS_obja.params.localIp;
if(!(/^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))$/).test(ip))
  {layer.tips("请输入合法的IP地址！","#"+iAttrs.id,{tips: [3, 'rgba(0,0,0,.8)','']});return false};
        }else if(iAttrs.checkip=="YS_obja.params.subnetMask"){
          ip = ctrlsco.YS_obja.params.subnetMask;
if(!(/^(254|252|248|240|224|192|128|0)\.0\.0\.0$|^(255\.(254|252|248|240|224|192|128|0)\.0\.0)$|^(255\.255\.(254|252|248|240|224|192|128|0)\.0)$|^(255\.255\.255\.(254|252|248|240|224|192|128|0))$/).test(ip))
  {layer.tips("请输入合法的子网掩码","#"+iAttrs.id,{tips: [3, 'rgba(0,0,0,.8)','']});return false};
        }else if(iAttrs.checkip=="YS_obja.params.gateway"){
          ip = ctrlsco.YS_obja.params.gateway;
if(!(/(2(5[0-5]{1}|[0-4]\d{1})|[0-1]?\d{1,2})(\.(2(5[0-5]{1}|[0-4]\d{1})|[0-1]?\d{1,2})){3}/).test(ip))
  {layer.tips("请输入合法的网关","#"+iAttrs.id,{tips: [3, 'rgba(0,0,0,.8)','']});return false};

        }else if(iAttrs.checkip=="YS_obja.params.dns"){
          ip = ctrlsco.YS_obja.params.dns;
          
        }else if(iAttrs.checkip=="YS_obja.params.dnsBak"){
          ip = ctrlsco.YS_obja.params.dnsBak;
        }


        if(ip==""){
          layer.tips("请输入信息！","#"+iAttrs.id,{tips: [3, 'rgba(0,0,0,.8)','']});return false
        }





      })
    }
  }
});

































// 编辑链路
app.directive('etuopu', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2); 



        if(ctrlsco.YS_objb.params.linkName==""||!ctrlsco.YS_objb.params.linkName){
          layer.tips("链路名称不能为空","#blinkName",{tips: [3, 'rgba(0,0,0,.8)','']});return false
          return false;
        }

        if(ctrlsco.YS_objb.params.linkType==""||!ctrlsco.YS_objb.params.linkType){
          layer.tips("请选择链路类型","#linkType",{tips: [3, 'rgba(0,0,0,.8)','']});return false
          return false;
        }



        if(ctrlsco.YS_objb.params['dbConfigData.dbName']==""||!ctrlsco.YS_objb.params['dbConfigData.dbName']){
          layer.tips("数据库名不能为空","#bdbName",{tips: [3, 'rgba(0,0,0,.8)','']});return false
          return false;
        }

        if(ctrlsco.YS_objb.params['dbConfigData.ip']==""||!ctrlsco.YS_objb.params['dbConfigData.ip']){
          layer.tips("ip不能为空","#bip",{tips: [3, 'rgba(0,0,0,.8)','']});return false
          return false;
        }

        if(ctrlsco.YS_objb.params['dbConfigData.port']==""||!ctrlsco.YS_objb.params['dbConfigData.port']){
          layer.tips("端口不能为空","#bport",{tips: [3, 'rgba(0,0,0,.8)','']});return false
          return false;
        }

        if(ctrlsco.YS_objb.params['dbConfigData.name']==""||!ctrlsco.YS_objb.params['dbConfigData.name']){
          layer.tips("用户名不能为空","#bname",{tips: [3, 'rgba(0,0,0,.8)','']});return false
          return false;
        }


        if(ctrlsco.YS_objb.params['dbConfigData.pwd']==""||!ctrlsco.YS_objb.params['dbConfigData.pwd']){
          layer.tips("密码不能为空","#bpwd",{tips: [3, 'rgba(0,0,0,.8)','']});return false
          return false;
        }
       sco.fetch("YS_obje")

      ctrlsco.YS_obje.done = function(re, sco) {
        if (re.code == 0) {

          if (re.data.isConnect) {
        var msgs = re.msg ? re.msg : '数据库检测成功';
              YSfac.load(1, msgs);
     ctrlsco.tapshow=4;

              
           
          } else {
             var msgs = '数据库检测失败';
     
                       layer.msg(msgs); 

          }
        } else {
            var msgs = '数据库检测失败';

                       layer.msg(msgs); 

        }
      }



        
      
        ctrlsco.edtp()
        console.log(ctrlsco.filelist)
        ctrlsco.$apply();
        

      });
    }
  };
});



// 首页隐藏展开导航栏
app.directive('indexnav', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2); 
        $('.nav').slideToggle(400);
          ctrlsco.navup =!ctrlsco.navup
          ctrlsco.$apply();
      });
    }
  };
});

// 首页隐藏展开报警总览
app.directive('symenus1', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2); 
        $('.allalarm').animate({width:'toggle'},350);
          ctrlsco.syxmenu1 =!ctrlsco.syxmenu1
          ctrlsco.$apply();
      });
    }
  };
});
// 首页隐藏展开报警排行榜
app.directive('symenus2', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2); 
        $('.alarmph').animate({width:'toggle'},350);
          ctrlsco.syxmenu2 =!ctrlsco.syxmenu2
          ctrlsco.$apply();
      });
    }
  };
});
// 
app.directive('iright', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2); 
        // if(ctrlsco.YS_plistb.params.currentPage<ctrlsco.YS_plistb.data.totalPage){
        //   ctrlsco.YS_plistb.params.currentPage ++;
        //   ctrlsco.fetch("YS_plistb");
        // }else{
        //   return false
        // }

        if(ctrlsco.showstatus<sco.epage){
          ctrlsco.showstatus=ctrlsco.showstatus+1
        }else{
          return false
        }
       ctrlsco.$apply();

      });
    }
  };
});



app.directive('ileft', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var ctrlsco = YSfac.getscope_byid(sco,2); 


        // if(ctrlsco.YS_plistb.params.currentPage>1){
        //   ctrlsco.YS_plistb.params.currentPage --;
        //   ctrlsco.fetch("YS_plistb");
        // }else{
        //   return false
        // }




        if(ctrlsco.showstatus>1){
          ctrlsco.showstatus=  ctrlsco.showstatus-1
         }else{
          return false
         }
          ctrlsco.$apply();
      });
    }
  };
});


app.directive('bxip',function(){
  return{
    link:function(sco,iElm,iAttrs){
      iElm.on("click",function(re){

       var ctrlsco = YSfac.getscope_byid(sco,2); 

       ctrlsco.YS_obja.params.loginIpStart ="0.0.0.0";
       ctrlsco.YS_obja.params.loginIpEnd ="255.255.255.255";
       ctrlsco.rip = !ctrlsco.rip;
       
       ctrlsco.$apply();
      })
    }
  }
});

app.directive('bxtime',function(){
  return{
    link:function(sco,iElm,iAttrs){
      iElm.on("click",function(re){

       var ctrlsco = YSfac.getscope_byid(sco,2); 

       ctrlsco.YS_obja.params.loginTimeStart ="00:00:00";
       ctrlsco.YS_obja.params.loginTimeEnd ="23:59:59";
      // ctrlsco.rtime = !ctrlsco.rtime;
       
       ctrlsco.$apply();

      })
    }
  }
});



// 体检添加时间
app.directive('addnewtime',function(){
  return{
    link:function(sco,iElm,iAttrs){
      iElm.on("click",function(re){

       var ctrlsco = YSfac.getscope_byid(sco,2); 
       if(!ctrlsco.temptime){
        layer.msg("时间不能为空！");return false;
       }
       ctrlsco.timearr.push(ctrlsco.temptime);
       // ctrlsco.YS_objb.params.checkTimes.push(ctrlsco.temptime);
       ctrlsco.$apply();
      })
    }
  }
});

app.directive('reload',function(){
  return{
    link:function(sco,iElm,iAttrs){
      iElm.on("click",function(re){
        location.reload()
      })
    }
  }
});


// 体检删除时间
app.directive('delnewtime',function(){
  return{
    link:function(sco,iElm,iAttrs){
      iElm.on("click",function(re){

       var ctrlsco = YSfac.getscope_byid(sco,2); 

       ctrlsco.timearr.splice(iAttrs.delnewtime,1);
       
       ctrlsco.$apply();

      })
    }
  }
});
// 设备删除
app.directive('linkdel', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       ctrlsco.YS_objd.params.linkId=sco.value.linkId;
        YS('layer', function () {
       ctrlsco.dellayer =  layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#remove')
          });
        });
      });
    }
  };
});


// 设置时间段
app.directive('setfwtime', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
            var date = new Date();
            var Y = date.getFullYear() + '-';
            var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            var D = date.getDate() + ' ';
            var ctrlsco = YSfac.getscope_byid(sco,2); 
            var today = Y+M+D;

        if(iAttrs.setfwtime=='now'){
          ctrlsco.fwtime = today +' - ' +today;
        }else if(iAttrs.setfwtime=='weak'){
          console.log(2);
        }else if(iAttrs.setfwtime=='month'){
          console.log(3);
        }

        ctrlsco.$apply();

      });
    }
  };
});





app.directive('xitongzijian', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
       var ctrlsco = YSfac.getscope_byid(sco,2); 
       var ran = 1000;
      var index = 0;
      var times = 0;
      var int = 0;
      var currentStep = 0;
      var waitTime = 0;
      var ran = 1000;


        if(iAttrs.cancle){
          ctrlsco.zzjc=0;
          ctrlsco.nowimg = '../images/p0.png'; 
          ctrlsco.nowbaifen = ""; 
          ctrlsco.tapshow = 0;
          ctrlsco.$apply();
          return false;
        }


       
       // if(iAttrs.tijian){
       ctrlsco.fetch(iAttrs.xitongzijian); 
       //   ctrlsco.currentTijian = iAttrs.tijian;
       //   if(iAttrs.tijian=='YS_objc'){
       //      var a = ctrlsco.YS_objc;
       //     if(!a.params||!a.params.deviceId){
       //      layer.msg('请选择设备类型');
       //      return false;
       //     }
       //      // a.params.deviceId = '';
       //   }else if(iAttrs.tijian=='YS_objd'){
       //      var a = ctrlsco.YS_objd;
       //     if(!a.params||!a.params.linkId){
       //      layer.msg('请选择链路类型');
       //      return false;
       //     }
       //      // a.params.linkId = '';
       //   }else if(iAttrs.tijian=='YS_obje'){
       //      var a = ctrlsco.YS_obje;
       //     if(!a.params||!a.params.taskId){
       //      layer.msg('请选择任务类型');
       //      return false;
       //     }
       //   }
       //   // var a = iAttrs.tijian;
       // }


       // if(iAttrs.reset){
       //  ctrlsco.fetch(ctrlsco.currentTijian);
       // }



       ctrlsco.tapshow = 1;
       if(ctrlsco.zzjc==1){
        ctrlsco.zzjc=0;
        window.clearInterval(int);
        ctrlsco.nowimg = '../images/p0.png'; 
        ctrlsco.nowbaifen = 0; 
       }else{
        ctrlsco.templist = [];
        // int = ctrlsco.spline();
        ctrlsco.zzjc=1;
        ctrlsco.nowimg = '../images/p0.png'; 
        ctrlsco.nowbaifen = 0; 
       }


       i=1;
       j=1;


          var ax = setInterval(function(){

            //等待时间超过一分钟直接显示100%，并结束
            if(waitTime > 60000){
                ctrlsco.nowimg = '../images/p12.png'; 
                ctrlsco.nowbaifen = 100;
                ctrlsco.zzjc=0;
                ctrlsco.$apply();
                window.clearInterval(ax);
                return;
            }


            //无数据
            if(ctrlsco.YS_objb.data.checkItems.length==0){

                waitTime += 1000;
                ctrlsco.fetch("YS_objb");
                return false;
            }



          //当有数据时并且不是最后一步
          // if(ctrlsco.maxStep>0&&currentStep<ctrlsco.maxStep){
          if(ctrlsco.examine.length>0){

              //插入数组展示
              ctrlsco.templist.unshift(ctrlsco.examine[currentStep]);
              //每次覆盖总数
              ctrlsco.maxStep = ctrlsco.examine[currentStep].totalStep;
              //赋值当前itemId
              ctrlsco.YS_objb.params.index = ctrlsco.examine[currentStep].itemId;
              //淡入效果
              //step+1
              currentStep++;

              ran = 600;

              // if(ctrlsco.maxStep<13){
              //   i = Math.floor(i + ctrlsco.maxStep/12);
              // }else{
              //   i = i + Math.ceil(currentStep/ctrlsco.maxStep);
              // }
              

              var imgsrc = '../images/p'+ i +'.png';
              var progress = Math.floor(currentStep/ctrlsco.maxStep*100);

              //当百分比变大时才变化
              if(ctrlsco.nowbaifen<progress){

                if(progress==100){
                  progress=99;
                }
                ctrlsco.nowbaifen = progress;
                i = Math.floor(progress/8.33)<1?1:Math.floor(progress/8.33);
                ctrlsco.nowimg = '../images/p'+i+'.png';
              }





            //执行到最后一步
            if(currentStep==ctrlsco.maxStep&&currentStep==ctrlsco.examine.length){
              ctrlsco.nowimg = '../images/p12.png'; 
              ctrlsco.nowbaifen = 100;
              ctrlsco.zzjc=0;
              window.clearInterval(ax);
            }else if(currentStep<ctrlsco.maxStep&&currentStep==ctrlsco.examine.length){//当前数组遍历完，拿最大checkid重新跑接口
              ctrlsco.fetch("YS_objb");
            }

            // if(currentStep==maxStep){
            //   ctrlsco.zzjc=0;ctrlsco.$apply();window.clearInterval(ax);
            // }

            ctrlsco.$apply();
          }
          //Math.floor(Math.random()*(5000-200+1)+200);
       },ran)

        ctrlsco.$apply();
      });
    }
  };
});


app.directive('sysjc', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
        var _this = $(iElm);
       var ctrlsco = YSfac.getscope_byid(sco,2); 
            var i = 0;
            var arr = ["0%","12%","21%","29%","37%","46%","54%","62%","70%","79%","90%","99%","100%"]
            var ran = 1000;
            var index = 0;
            var div = document.getElementById("exambox");
            var times = 0;

        if(iAttrs.sysjc=='cancle'){
          ctrlsco.zzjc=0;
          $('.bfimg').attr("src",'../images/p0.png');
          $('.baifen').text("");
          ctrlsco.tapshow=0;
          ctrlsco.$apply();
          return false;
        }

        ctrlsco.tapshow=1;
       if(ctrlsco.zzjc==1){
        ctrlsco.zzjc=0;
        $('.bfimg').attr("src",'../images/p0.png');
        $('.baifen').text("");
        ctrlsco.$apply();
       }else{
        ctrlsco.templist = [];
        ctrlsco.zzjc=1;
        $('.bfimg').attr("src",'../images/p0.png');
        $('.baifen').text("0%");
       }
       i=1;

      var ax = setInterval(function(){
        if(i==12){ctrlsco.zzjc=0;ctrlsco.$apply();};
           if(i>12){ctrlsco.zzjc=0;ctrlsco.$apply();window.clearInterval(ax);};

        var imgsrc = '../images/p'+ i +'.png';

        if(i>12){
          $('.bfimg').attr("src",'../images/p12.png');
           $('.baifen').text("100%");
        }else{
          if(ctrlsco.zzjc==0){
            $('.bfimg').attr("src",'../images/p0.png');
            $('.baifen').text("0%");
            window.clearInterval(ax);
          }else{

            // $('.bfimg').attr("src",imgsrc);
             // $('.baifen').text(arr[i]);
          }
        }

      if(ctrlsco.YS_objb.data.length>0&&index<ctrlsco.YS_objb.data.length){


        if(ctrlsco.YS_objb.data.length<13){

          i = i + Math.floor(12/ctrlsco.YS_objb.data.length) - 1;
          if(ctrlsco.zzjc==0&&i>ctrlsco.YS_objb.data.length){
            return false;
          }


          ctrlsco.templist.unshift(ctrlsco.YS_objb.data[index]);

          index++;             $('.baifen').text(Math.floor(index/ctrlsco.YS_objb.data.length*100)+"%");
             if(index==ctrlsco.YS_objb.data.length)imgsrc = '../images/p12.png';
            $('.bfimg').attr("src",imgsrc);
        }else{
          times = Math.ceil(ctrlsco.YS_objb.data.length/12);
          i = i + Math.ceil(ctrlsco.YS_objb.data.length/12) - 1;

          if(ctrlsco.zzjc==0&&i<ctrlsco.YS_objb.data.length){
            return false;
          }

          for (var j = 0; j<times; j++) {
            ctrlsco.templist.unshift(ctrlsco.YS_objb.data[index]);

            index++;             $('.baifen').text(Math.floor(index/ctrlsco.YS_objb.data.length*100)+"%");
             if(index==ctrlsco.YS_objb.data.length)imgsrc = '../images/p12.png';
            $('.bfimg').attr("src",imgsrc);
          }
          // if(i>12)i=12;
        }

        i++;
        ctrlsco.$apply();
      }
      div.scrollTop = div.scrollHeight;
      ran = Math.floor(Math.random()*(5000-200+1)+200);
   },ran)
      });
    }
  };
});




app.directive('cfinfo', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("mouseout", function (e) {
     var ctrlsco = YSfac.getscope_byid(sco,2);
     // setTimeout(function(){
       ctrlsco.showf=0
        ctrlsco.$apply();
     // },500)
    

    })
   }
 }
})

app.directive('showfinfo', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("mouseover", function (e) {
         var ctrlsco = YSfac.getscope_byid(sco,2);
          if(iAttrs.status&&iAttrs.status==1){
         return false;
         } else{

        ctrlsco.showf=iAttrs.showfinfo;
        var infoid="finfo"+iAttrs.listkey+iAttrs.showfinfo;
        ctrlsco.YS_obja.params.deviceId = iAttrs.showfinfo;
        ctrlsco.listkey = iAttrs.listkey;

        ctrlsco.fetch('YS_obja');
        e.stopPropagation();
        // ctrlsco.showf=ctrlsco.showf!=0?0:iAttrs.showfinfo
        // console.log(infoid)
        // $("#"+infoid).removeClass("dpn")
        // }
         }

    })
   
   }
 }
})


// 显示信息
app.directive('showinfo', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("mouseover", function (e) {
         var ctrlsco = YSfac.getscope_byid(sco,2); 

    if(iAttrs.status&&iAttrs.status==1){
      return false;
    }
var listkey=iAttrs.listkey;
if(iAttrs.file=='false'||!iAttrs.file||iAttrs.file=='undefined'){
        ctrlsco.YS_obja.params.deviceId = iAttrs.showinfo;
        ctrlsco.listkey = iAttrs.listkey;//iAttrs.showinfo;
        ctrlsco.YS_lista.data.list.map(function(elem){
          elem.topologyNodes.map(function(ele){
            ele['show'] = 0;
            if(ele.child){
              ele.child.map(function(el){
                el['show'] = 0
              })
            }
          })
        })
        var show = sco.val.show ? 0:1;
       
        if(iAttrs.childkey){
          sco.val.child[iAttrs.childkey].show=show;
        }else{
          sco.val.show =show;
        }
        ctrlsco.fetch('YS_obja');

        e.stopPropagation();
}else{
      ctrlsco.filelist = [];
      if(iAttrs.ischild){
        // debugger
        if(iAttrs.childkey==0){
           ctrlsco.filelist =sco.val.child?sco.val.child[0].deviceIdsElem:[];
        }else{
           ctrlsco.filelist = sco.v?sco.v.deviceIdsElem:[];
        } 
      }else{
        ctrlsco.filelist = sco.val?sco.val.deviceIdsElem?sco.val.deviceIdsElem:[] :[];
      }
     
      if(ctrlsco.filelist.length>0){
        console.log(ctrlsco.filelist)
        ctrlsco.filenamelist = [];

        ctrlsco.filelist.map(function(elem,index){
          var name = "";
          var icon = "";
          var ip = "";
          if(ctrlsco.YS_lista.data.devices[elem.toDeviceId]){

            deviceId = elem.toDeviceId;
            status = elem.deviceStatus;
            name = ctrlsco.YS_lista.data.devices[elem.toDeviceId].deviceName;
            // ctrlsco.filelist[index] = ctrlsco.YS_lista.data.devices[elem].icon;
            icon = ctrlsco.YS_lista.data.devices[elem.toDeviceId].icon;
            ip = ctrlsco.YS_lista.data.devices[elem.toDeviceId].ip;

          }
                // ctrlsco.iconlist.map(function(elem2){
                //   if(elem2.id==elem){
                //     temp = elem2.deviceName;
                //   }
                // })

          ctrlsco.filenamelist.push({name:name,icon:icon,deviceId:deviceId,status:status,listkey:listkey,ip:ip});
        })
      }


     YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#filebox')
          });
        });
}
        ctrlsco.$apply();
         
      });
    }
  };
});

//文件夹点击
app.directive('showinfo2', function () {
  return {
    link: function (sco, iElm, iAttrs) {
      iElm.on("click", function (e) {
         var ctrlsco = YSfac.getscope_byid(sco,2); 

    if(iAttrs.status&&iAttrs.status==1){
      return false;
    }
var listkey=iAttrs.listkey;
if(iAttrs.file=='false'||!iAttrs.file||iAttrs.file=='undefined'){
        ctrlsco.YS_obja.params.deviceId = iAttrs.showinfo2;
        ctrlsco.listkey = iAttrs.listkey;//iAttrs.showinfo2;
        ctrlsco.YS_lista.data.list.map(function(elem){
          elem.topologyNodes.map(function(ele){
            ele['show'] = 0;
            if(ele.child){
              ele.child.map(function(el){
                el['show'] = 0
              })
            }
          })
        })
        var show = sco.val.show ? 0:1;
       
        if(iAttrs.childkey){
          sco.val.child[iAttrs.childkey].show=show;
        }else{
          sco.val.show =show;
        }
        ctrlsco.fetch('YS_obja');

        e.stopPropagation();
}else{
      ctrlsco.filelist = [];
      if(iAttrs.ischild){
        // debugger
        if(iAttrs.childkey==0){
           ctrlsco.filelist =sco.val.child?sco.val.child[0].deviceIdsElem:[];
        }else{
           ctrlsco.filelist = sco.v?sco.v.deviceIdsElem:[];
        } 
      }else{
        ctrlsco.filelist = sco.val?sco.val.deviceIdsElem?sco.val.deviceIdsElem:[] :[];
      }
     
      if(ctrlsco.filelist.length>0){
        console.log(ctrlsco.filelist)
        ctrlsco.filenamelist = [];

        ctrlsco.filelist.map(function(elem,index){
          var name = "";
          var icon = "";
          var ip = "";
          if(ctrlsco.YS_lista.data.devices[elem.toDeviceId]){

            deviceId = elem.toDeviceId;
            status = elem.deviceStatus;
            name = ctrlsco.YS_lista.data.devices[elem.toDeviceId].deviceName;
            // ctrlsco.filelist[index] = ctrlsco.YS_lista.data.devices[elem].icon;
            icon = ctrlsco.YS_lista.data.devices[elem.toDeviceId].icon;
            ip = ctrlsco.YS_lista.data.devices[elem.toDeviceId].ip;

          }
          ctrlsco.filenamelist.push({name:name,icon:icon,deviceId:deviceId,status:status,listkey:listkey,ip:ip});
        })
      }


     YS('layer', function () {
          layer.open({
            type: 1,
            title: false, //不显示标题
            closeBtn: 0,
            shadeClose: false,
            shade: 0.5,
            area: ['auto', 'auto'], //宽高
            content: $('#filebox')
          });
        });
}
        ctrlsco.$apply();
         
      });
    }
  };
});



app.directive('clearicon', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2);

           ctrlsco.clear(); 
        });
     }
};
}); 

// app.directive('fimgdel', function(){ 
// return { 
// link: function(sco, iElm, iAttrs) {
//         iElm.on("click",function(e){ 
//            var ctrlsco = YSfac.getscope_byid(sco,2);

//            var id = iAttrs.id+'fimg';
//            $('#'+id).hide()
//         });
//      }
// };
// }); 


//搜索接口search="todatetodate"
app.directive('searchinter', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2);
           ctrlsco[iAttrs.searchinter].params.currentPage = 1;
           ctrlsco.fetch(iAttrs.searchinter); 
        });
     }
};
}); 

app.directive('alarmnav', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) {
          iElm.on("click",function(e){ 
            if(iAttrs.key=='true'){
              window.location.href="#/taskalarm";
              // console.log(sco.val);
              YSfac.setstore('currentData',sco.value);
            }
            else{
              return false;
            }
          });
       }
  };
  }); 

app.directive('devicewrong', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) {
          iElm.on("click",function(e){ 
              window.location.href="#/equipalarm";
              // console.log(sco.val);
              YSfac.setstore('currentDeviceId',sco.val.toDeviceId);
              YSfac.setstore('currentLevel',3);

          });
       }
  };
  }); 

app.directive('devicealarm', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) {
          iElm.on("click",function(e){ 
              window.location.href="#/equipalarm";
              // console.log(sco.val);
              YSfac.setstore('currentDeviceId',sco.val.toDeviceId);
              YSfac.setstore('currentLevel',2);

          });
       }
  };
  }); 



// license授权上传
app.directive('licensesupload', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) { 
  var ctrlsco = YSfac.getscope_byid(sco, 2); 
  
  var uploadInst = layui.upload.render({
      elem: $(iElm), 
      accept:'file', 
      url: conf.rooturl + 'license/up', 
      done: function(re,index, upload){ 
          if(re.code == 0){ 
            
            ctrlsco.filename = "licenseData";
            ctrlsco.tapshow = 1;
            ctrlsco.$apply();
            layer.open({
              type: 1,
              title: false, //不显示标题
              closeBtn: 0,
              shadeClose: false,
              shade: 0.5,
              area: ['auto', 'auto'], //宽高
              content: $('#sqsucc')
            });
  
          }else{
            ctrlsco.tapshow = 2;
            ctrlsco.$apply();
                layer.open({
                  type: 1,
                  title: false, //不显示标题
                  closeBtn: 0,
                  shadeClose:false, 
                  shade:0.5, 
                  area: ['auto', 'auto'], //宽高
                  content: $('#sqfail')
                });
          } 
      } ,
      error: function(){
      $('i').addClass('xgico');
    }
      
  }); 
  }
  };
  });
  
app.directive('licensesupload2', function(){ 
return { 
link: function(sco, iElm, iAttrs) { 
var ctrlsco = YSfac.getscope_byid(sco, 2); 
var uploadInst = layui.upload.render({
    elem: $(iElm), 
    accept:'file', 
    url: conf.rooturl + 'license/up', 
    done: function(re,index, upload){ 

        if(re.code == 0){ 
           ctrlsco.filename = "licenseData";
            ctrlsco.tapshow = 1;
            ctrlsco.$apply();
           layer.msg('授权成功,即将跳转到登录页');
          setTimeout(function(){
             window.location.href = '/login.html';
          },2000);


              }else{
                  
        layer.msg('授权失败');


        } 
    } 
    
}); 
}
};
});




//
app.directive('setzzjc', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) {
          iElm.on("click",function(e){
            var ctrlsco = YSfac.getscope_byid(sco, 2); 
            ctrlsco.zzjc = -1;
            ctrlsco.templist = [];
            window.clearInterval(ctrlsco.qian);
          });
       }
  };
  }); 
app.directive('delinput', function(){ 
  return { 
  link: function(sco, iElm, iAttrs) {
          iElm.on("click",function(e){
            var ctrlsco = YSfac.getscope_byid(sco, 2); 
         debugger;
            ctrlsco[iAttrs.delinput] ='';
             ctrlsco.$apply();
          });
       }
  };
  }); 


app.directive('fetrinking', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2);
           ctrlsco.YS_objc.params.type = iAttrs.fetrinking;
           ctrlsco.fetch("YS_objc"); 
           ctrlsco.$apply();
        });
     }
};
}); 


app.directive('test', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2);

           console.log(sco.value);
        });
     }
};
}); 
app.directive('tonei', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        iElm.on("click",function(e){ 
           var ctrlsco = YSfac.getscope_byid(sco,2);
  
           delete  ctrlsco.YS_obja.params.deviceId;
           ctrlsco.$apply();
        });
     }
};
}); 





app.directive('llselect', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        layui.use('form', function(){
        var key =  'select('+iAttrs.llselect+')';
        layui.form.on(key, function(data){
          var ctrlsco = YSfac.getscope_byid(sco,2);
          var arr = iAttrs.llselect.split('.');
          ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.llselect, data.value); 
          iAttrs.codeid&&(ctrlsco[arr[0]][arr[1]].codeid = iAttrs.codeid);
          iAttrs.run&&ctrlsco.fetch(iAttrs.run);//如果存在run，则去跑对应的接口


          ctrlsco.YS_listc.params.linkId = ctrlsco.YS_objd.params.linkId;


          ctrlsco.$apply();
        }); 
        layui.form.render();
      }); 
     }
};
});


app.directive('toselecttask', function(){ 
return { 
link: function(sco, iElm, iAttrs) {
        layui.use('form', function(){
        var key =  'select('+iAttrs.toselecttask+')';
        layui.form.on(key, function(data){
          var ctrlsco = YSfac.getscope_byid(sco,2);
          var arr = iAttrs.toselecttask.split('.');
          ctrlsco = YSfac.yssetvalue(ctrlsco, iAttrs.toselecttask, data.value); 

//选择链路后，带所选的id来循环任务接口,把所属的任务都堆到数组里边
    var linkid =ctrlsco.YS_objf.params.linkId;
    if (ctrlsco.YS_obje.params.taskId) {
      ctrlsco.YS_obje.params.taskId=='';
    } 
    ctrlsco.taskarr=[];
//YS_listc.data.list 任务接口返回的数据 
          if (ctrlsco.YS_listc.data.list){
                ctrlsco.YS_listc.data.list.map(function(e){
                      if (e.linkId==linkid){
                         ctrlsco.taskarr.push(e); 
                      }else{
                        return;
                      }

                });


          } 
$('#xlu').removeAttr("disabled");
          ctrlsco.$apply();
        setTimeout(function(){
          layui.form.render();
        },500);



        }); 
      }); 
     }
};
});


























//绑定dom ============================================================================
 angular.element(document).ready(function() {angular.bootstrap(document, ['app']);}); 
})(window);
 



/*! YS v1.0.0 | by YSTeam | Copyright (c) 2013-2017 http://www.ysframe.com All rights reserved. | Licensed under MIT | 2017-12-22T12:00:00+0800 */
// 公共方法函数
(function(window, undefined) {
    var YSfac = {
        chklogin: function(da) {
            if (da.code == -200) {
                layer.msg('登陆过期，请重新登陆！');
                setTimeout(function() {
                    window.location.href = "./login.html";
                }, 1000);
            }

            if (da.code == -2) {
             
            }

        },
        //把地址栏的参数转成对象  a=1&c=3 > {a:1,c:3}
        hashToobj: function(str) {
            var obj = {};
            if (!str) return obj;
            if (str.indexOf('&') > 0) {
                var arr = str.split('&');
                arr.map(function(el) {
                    var t = el.split('=');
                    var a = t.length > 1 ? obj[t[0]] = t[1] : '';
                });
            } else {
                var t = str.split('=');
                var a = t.length > 1 ? obj[t[0]] = t[1] : '';
            }
            return obj;
        },
        setarr: function(str, num) {
            var arr = [];
            if (!str) { return arr; }
            var a = str.split(',');
            a.map(function(em) {
                if (num) {
                    var rate = parseInt(em) / parseInt(num) * 100;
                    rate = rate.toFixed(1);
                    var clas = 'qdyes';
                    if (rate < 100 || (parseInt(em) - parseInt(num)) > 15) { clas = 'qdno'; }
                    arr.push({ val: em, rate: rate ? (rate + '%') : '', clas: clas });

                } else {
                    arr.push({ val: em });
                }
            });
            return arr;
        },
        findpower: function(str, arr) {
            if (!arr.length) return false;
            var has = false;
            arr.map(function(el) {
                el.child && el.child.map(function(elem) {
                    if (elem.url == str && elem.has == 1) { has = true; }
                })
            });
            return has;
        },

        updatetd: function(re, _this) {
            if (re.code > 0) {
                _this.addClass('ipright').removeClass('ipwrong');
            } else {
                _this.addClass('ipwrong').removeClass('ipright')
            }
            YSfac.msg(re);
        },
        yssetvalue: function(ctrlsco, str, val) {
            var arr = str.split('.');
            var obj = ctrlsco;
            var key = '';
            arr.map(function(el, ind) {
                ind < arr.length - 1 && (obj = obj[el]);
                ind == arr.length - 1 && (key = el);
            });
            obj[key] = val;
            return ctrlsco;
        },
        //获取地址栏参数
        GetQueryString: function(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r !== null) { return decodeURI(r[2]); }
            return '';
        },
        time: function(str) {
            //返回时间戳；str 为空则返回当前时间戳；
            var timestamp = !str ? Date.parse(new Date()) : Date.parse(new Date(str));
            return timestamp;
        },
        numtotime: function(time) {
            //数字转换成时间格式
            var retime = '';
            if (parseInt(time) > 0) {
                var date = new Date(time);
                var year = date.getYear() + 1900;
                var month = date.getMonth() + 1;
                month = month < 10 ? '0' + month : month;
                var datev = date.getDate();
                datev = datev < 10 ? '0' + datev : datev;
                return year + '-' + month + '-' + datev;
            } else {
                return retime;
            }

        },
        getNowFormatDate: function(day) {
            var date = new Date();
            var seperator1 = "-";
            var seperator2 = ":";
            var month = date.getMonth() + 1;
            var strDate = date.getDate()+day;
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
            return currentdate;
        },

dateFormatConversion:function(inputTime) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d;
},


        btomb: function(limit) {
            var size = "";
           
        size = (limit/(1024 * 1024)).toFixed(2)

    var sizeStr = size + "";                        
     var index = sizeStr.indexOf(".");                  
    var dou = sizeStr.substr(index + 1 ,2)           
     if(dou == "00"){                                  
         return sizeStr.substring(0, index) + sizeStr.substr(index + 3, 2)
    }
     return size;
        },

          btogb: function(limit) {
            var size = "";
        size = (limit/(1024 * 1024 * 1024)).toFixed(2) 
       var sizeStr = size + "";                        
       var index = sizeStr.indexOf(".");                  
       var dou = sizeStr.substr(index + 1 ,2)           
     if(dou == "00"){                                  
         return sizeStr.substring(0, index) + sizeStr.substr(index + 3, 2)
    }
     return size;
        },
          btotb: function(limit) {
            var size = "";
        size = (limit/(1024 * 1024 * 1024* 1024)).toFixed(2) 
       var sizeStr = size + "";                        
       var index = sizeStr.indexOf(".");                  
       var dou = sizeStr.substr(index + 1 ,2)           
     if(dou == "00"){                                  
         return sizeStr.substring(0, index) + sizeStr.substr(index + 3, 2)
    }
     return size;
        },



        numtosecond: function(time) {
            var date = new Date(time);
            Y = date.getFullYear() + '-';
            M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            D = date.getDate() + ' ';
            h = date.getHours() + ':';
            m = date.getMinutes() + ':';
            s = date.getSeconds(); 
            return (Y+M+D+h+m+s); 
        },
        numtohms: function(time) {
            var date = new Date(time);
            h = date.getHours() + ':';
            m = date.getMinutes() + ':';
            s = date.getSeconds(); 
            return (h+m+s); 
        },
        IsDate: function(mystring) {
            var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
            var str = mystring;
            var arr = reg.exec(str);
            if (str == "") return false;
            if (!reg.test(str) && RegExp.$2 <= 12 && RegExp.$3 <= 31) {
                layer.msg('日期格式必须是yyyy-mm-dd');
                return false;
            }
            return true;
        },

formatDate:function(date) { 
var myyear = date.getFullYear(); 
var mymonth = date.getMonth()+1; 
var myweekday = date.getDate(); 

if(mymonth < 10){ 
mymonth = "0" + mymonth; 
} 
if(myweekday < 10){ 
myweekday = "0" + myweekday; 
} 
return (myyear+"-"+mymonth + "-" + myweekday); 
},


formatDate2:function(date) { 
var myyear = date.getFullYear(); 
var mymonth = date.getMonth(); 
var myweekday = date.getDate(); 

if(mymonth < 10){ 
mymonth = "0" + mymonth; 
} 
if(myweekday < 10){ 
myweekday = "0" + myweekday; 
} 
return (myyear+"-"+mymonth + "-" + myweekday); 
},




//获得某月的天数 
 getMonthDays:function(myMonth){ 
var monthStartDate = new Date(nowYear, myMonth, 1); 
var monthEndDate = new Date(nowYear, myMonth + 1, 1); 
var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24); 
return days; 
},




        donere: function(re) {
            // YS('layer',function(){layer.msg(re.msg,{icon:da.code});});
        },
        load: function(num, str) {
            if (num) {
                if (window.ysload == null) {
                    window.ysload = layer.msg(str ? str : '正在加载中……', { icon: 16, time: 2000, shade: 0.08 }, function() { window.ysload = null; });
                }
            } else {
                setTimeout(function() {
                    layer.close(ysload);
                    window.ysload = null;
                }, 400);
            }
        },

                load2: function(num, str) {
            if (num) {
                if (window.ysload == null) {
                    window.ysload = layer.msg(str ? str : '正在加载中……', { icon: 16, time: 20000, shade: 0.08 }, function() { window.ysload = null; });
                }
            } else {
                setTimeout(function() {
                    layer.close(ysload);
                    window.ysload = null;
                }, 400);
            }
        },



   loadclose:function(time){
    var index = layer.load(1, {
  time:time,
  shade: [0.1,'#fff'] //0.1透明度的白色背景
});
   },

        ystip: function() {
            YS('poshytip', function() {
                setTimeout(function() {
                    $('.poshytip').poshytip({
                        className: 'tip-skyblue',
                        showTimeout: 1,
                        bgImageFrameSize: 11,
                        offsetX: -5,
                        alignX: 'right',
                    });
                }, 1000)

            })
        },
        fetdata: function(sco, key) {
            var url, path, page, load, method = '';
            // 开启调试模式关闭调试模式
            url = sco.conf.debug ? sco.conf.jsonpath : sco.conf.rooturl;
            path = (sco[key] && sco[key].url) || (sco.conf[key] && sco.conf[key].url);
            if (!path) { console.log(key + '未配置路径信息，可conf中配置或者给sco.' + key + '.url赋值路径'); return false; }
            url += path + (sco.conf.debug ? '' : '');
            load = (sco.reload && sco[key] && sco[key].load) || (sco.reload && sco.conf[key] && sco.conf[key].load);
            page = (sco[key] && sco[key].page) || (sco.conf[key] && sco.conf[key].page);
            method = (sco[key] && sco[key].method) || (sco.conf[key] && sco.conf[key].method) || 'GET';
            method = sco.conf.debug ? 'GET' : method;
            //运行前 ，执行的函数；
            if (typeof(sco[key].before) === "function") { var v = sco[key].before(sco); if (v === false) { return false; } }
            if (load) { YSfac.load(1); }
            //执行请求 
            var scon = {
                method: method,
                url: url,
                withCredentials: true,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded'
                ,"X-Requested-With": "XMLHttpRequest"
                 }
            };
            var params = sco[key].params || {};
            // params.token = YSfac.getstore('token');
            method == 'GET' ? scon.params = params : scon.data = $.param(params);

            sco.$http(scon).success(function(re) {
                sco[key] && !sco[key].nologin && YSfac.chklogin(re);
                //调试模式打印路径信息
                sco.conf.console ? console.log('接口调试信息，url:' + url, '参数params:', sco[key].params, '返回re:', re) : '';
                sco[key].data = {};
                if (re.code == 0) {
                	
                    sco[key].data = re.data;
                    sco[key].ext = re.ext;
                    //接口如果需要分页的话，会调用
                    page && YS('laypage', function() {
                        var id = '#' + key;
                        if ($(id).length == 0) { console.log('未设置分页的id="' + key + '"的div容器'); return false; }
layui.laypage.render({
      elem: key
      ,count: re.data.total
      ,curr: sco[key].params.currentPage || 1 //当前页
      ,limit:sco[key].params.pageSize
      ,limits:[10,20,30,40,50]
      ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
      ,prev: '<'
      ,next: '>'
      ,pages: re.data.totalPage //总页数
      ,jump: function(obj, first) { //触发分页后的回调
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    sco[key].params.currentPage = obj.curr;
                                    sco[key].params.pageSize = obj.limit;
                                    sco.fetch(key);
                                    sco.$apply();
                                }
                            }
    });




                        // laypage({ 
                        //     cont: key, //容器。值支持id名、原生dom对象，jquery对象,
                        //     pages: re.data.totalPage, //总页数
                        //     skin: 'molv', //皮肤
                        //     first: 1, //将首页显示为数字1,。若不显示，设置false即可
                        //     //last: 11, 将尾页显示为总页数。若不显示，设置false即可
                        //     curr: sco[key].params.currentPage || 1, //当前页
                        //     prev: '<', //若不显示，设置false即可
                        //     next: '>', //若不显示，设置false即可
                        //     layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                        //     jump: function(obj, first) { //触发分页后的回调
                        //         if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                        //             sco[key].params.currentPage = obj.curr;
                        //             sco.fetch(key);
                        //             sco.$apply();
                        //         }
                        //     }
                        // });
                    })
                }
                //运行后拿到数据，执行函数
                if (typeof(sco[key].done) === "function") { sco[key].done(re, sco); }
                if (load) { YSfac.load(0); }
            }).
            error(function(re) {
                debugger;
                sco.conf.console ? console.log('模块：' + key + '的接口' + url, '参数params:', sco[key].params, '返回err:', re) : '';
                // layer.msg('模块：'+key+'的接口'+url+'网络超时！',{icon:0,time: 2000});
            });
        },
        setstore: function(key, value) {
            value = typeof(value) === 'object' ? JSON.stringify(value) : value;
            localStorage.setItem(key, value);
            return true;
        },
        isArr: function(arr) {
            return Object.prototype.toString.call(arr) === '[object Array]';
        },
        getstore: function(key) {
            var tem = '';
            try {
                tem = localStorage.getItem(key);
                tem = JSON.parse(tem);
            } catch (e) { tem = localStorage.getItem(key); }
            return tem;
        },
        unsetstore: function(key) { localStorage.removeItem(key); return true; },
        setsestore: function(key, value) {
            value = typeof(value) === 'object' ? JSON.stringify(value) : value;
            sessionStorage.setItem(key, value);
            return true;
        },
        getsestore: function(key) {
            var tem = '';
            try {
                tem = sessionStorage.getItem(key);
                tem = JSON.parse(tem);
            } catch (e) { tem = sessionStorage.getItem(key); }
            return tem;
        },
        unsetsestore: function(key) { sessionStorage.removeItem(key); return true; },
        setCookie: function(name, value) {
            //写cookies 
            var d = new Date();
            d.setTime(d.getTime() + (7 * 24 * 60 * 60 * 1000));
            var expires = "expires=" + d.toUTCString();
            document.cookie = name + "=" + value + "; " + expires;
        },
        getCookie: function(cname) {
            //读取cookies 
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') c = c.substring(1);
                if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
            }
            return "";
        },
        delCookie: function(name) {
            //删除cookies 
            f.setCookie(name, "", -1);
        },
        getscope_byid: function(sc, id) {
            /*递归，找到$id为 num 的scope.并返回；*/
            var sp = sc.$id == id ? sc : this.getscope_byid(sc.$parent, id);
            return sp;
        },
        //验证手机号码是否正确，正确返回true  错误返回false；
        testphone: function(num) {
            var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|145|147|159|153)+\d{8})$/;
            if (!myreg.test(num)) {
                return false;
            } else {
                return true;
            }
        },
        //发送短信
        sendphone: function(obj) {
            var i = 59;
            obj.attr("disabled", "true").css("opacity", 0.7);
            var int = setInterval(function() {
                if (i === 0) {
                    obj.val('发送验证码');
                    obj.removeAttr("disabled").css("opacity", 1);
                    clearInterval(int);
                    return false;
                }
                obj.val('' + i + '秒重发');
                i--;
            }, 1000);
        },

        hidemission: function() {
            $('#missionson').offCanvas('close');
            $('#mission').offCanvas('close');
        },
        qiniuupload: function(bucketurl, token, button, multi_selection, UploadComplete, FilesAdded, BeforeUpload, UploadProgress, FileUploaded) {
            // 引用方法  YSfac.qiniuupload(bucketurl,token,browse_button,)
            var uploader = Qiniu.uploader({
                runtimes: 'html5,flash,html4', //上传模式,依次退化
                browse_button: button, //上传选择的点选按钮，**必需**
                uptoken: token,
                // putExtra:putExtra,
                multi_selection: multi_selection,
                // uptoken_url: getToken(), //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
                // uptoken : '', //若未指定uptoken_url,则必须指定 uptoken ,uptoken由其他程序生成
                unique_names: true, // 默认 false，key为文件名。若开启该选项，SDK为自动生成上传成功后的key（文件名）。
                // save_key: true, // 默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK会忽略对key的处理
                domain: bucketurl, //bucket 域名，下载资源时用到，**必需**
                get_new_uptoken: true, //设置上传文件的时候是否每次都重新获取新的token
                // container: 'container', //上传区域DOM ID，默认是browser_button的父元素，
                max_file_size: '1000mb', //最大文件体积限制
                flash_swf_url: 'Moxie.swf', //引入flash,相对路径
                max_retries: 3, //上传失败最大重试次数
                dragdrop: false, //开启可拖曳上传
                // drop_element: 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
                chunk_size: '4mb', //分块上传时，每片的体积
                auto_start: true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传
                init: {
                    'FilesAdded': function(up, files) {
                        typeof(FilesAdded) === "function" && FilesAdded(up, files);
                    },
                    'BeforeUpload': function(up, file) {
                        YSfac.load(1, '上传中……');
                        typeof(BeforeUpload) === "function" && BeforeUpload(up, files);
                        // 每个文件上传前,处理相关的事情
                    },
                    'UploadProgress': function(up, file) {
                        typeof(UploadProgress) === "function" && UploadProgress(up, files);

                        // 每个文件上传时,处理相关的事情
                    },
                    'FileUploaded': function(up, file, info) {
                        typeof(FileUploaded) === "function" && FileUploaded(up, file, info);

                    },
                    'UploadComplete': function(up, file) {
                        typeof(UploadComplete) === "function" && UploadComplete(up, file);
                    },
                    'Error': function(up, err, errTip) {
                        //上传出错时,处理相关的事情
                    }
                }
            });













        },
        //建立一個可存取到該file的url
        getObjectURL: function(file) {
            var url = null;
            if (window.createObjectURL !== undefined) { // basic
                url = window.createObjectURL(file);
            } else if (window.URL !== undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file);
            } else if (window.webkitURL !== undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file);
            }
            return url;
        },
        ispc: function() {
            var userAgentInfo = navigator.userAgent;
            var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"
            ];
            var flag = true;
            for (var v = 0; v < Agents.length; v++) {
                if (userAgentInfo.indexOf(Agents[v]) > 0) {
                    flag = false;
                    break;
                }
            }
            return flag;
        },

        msg: function(re) {
            var type = re.code == 1 ? 'success' : 'error';
            YS('toastr', function() {
                toastr.options = {
                    "closeButton": true,
                    "debug": false,
                    "positionClass": "toast-bottom-right",
                    "onclick": null,
                    "showDuration": "300",
                    "hideDuration": "1000",
                    "timeOut": "2000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                };
                toastr[type](re.msg);
            });
        },
        //md5加密
        MD5: function(sMessage) {
            function RotateLeft(lValue, iShiftBits) {
                return (lValue << iShiftBits) | (lValue >>> (32 - iShiftBits));
            }

            function AddUnsigned(lX, lY) {
                var lX4, lY4, lX8, lY8, lResult;
                lX8 = (lX & 0x80000000);
                lY8 = (lY & 0x80000000);
                lX4 = (lX & 0x40000000);
                lY4 = (lY & 0x40000000);
                lResult = (lX & 0x3FFFFFFF) + (lY & 0x3FFFFFFF);
                if (lX4 & lY4) return (lResult ^ 0x80000000 ^ lX8 ^ lY8);
                if (lX4 | lY4) {
                    if (lResult & 0x40000000) return (lResult ^ 0xC0000000 ^ lX8 ^ lY8);
                    else return (lResult ^ 0x40000000 ^ lX8 ^ lY8);
                } else return (lResult ^ lX8 ^ lY8);
            }

            function F(x, y, z) {
                return (x & y) | ((~x) & z);
            }

            function G(x, y, z) {
                return (x & z) | (y & (~z));
            }

            function H(x, y, z) {
                return (x ^ y ^ z);
            }

            function I(x, y, z) {
                return (y ^ (x | (~z)));
            }

            function FF(a, b, c, d, x, s, ac) {
                a = AddUnsigned(a, AddUnsigned(AddUnsigned(F(b, c, d), x), ac));
                return AddUnsigned(RotateLeft(a, s), b);
            }

            function GG(a, b, c, d, x, s, ac) {
                a = AddUnsigned(a, AddUnsigned(AddUnsigned(G(b, c, d), x), ac));
                return AddUnsigned(RotateLeft(a, s), b);
            }

            function HH(a, b, c, d, x, s, ac) {
                a = AddUnsigned(a, AddUnsigned(AddUnsigned(H(b, c, d), x), ac));
                return AddUnsigned(RotateLeft(a, s), b);
            }

            function II(a, b, c, d, x, s, ac) {
                a = AddUnsigned(a, AddUnsigned(AddUnsigned(I(b, c, d), x), ac));
                return AddUnsigned(RotateLeft(a, s), b);
            }

            function ConvertToWordArray(sMessage) {
                var lWordCount;
                var lMessageLength = sMessage.length;
                var lNumberOfWords_temp1 = lMessageLength + 8;
                var lNumberOfWords_temp2 = (lNumberOfWords_temp1 - (lNumberOfWords_temp1 % 64)) / 64;
                var lNumberOfWords = (lNumberOfWords_temp2 + 1) * 16;
                var lWordArray = Array(lNumberOfWords - 1);
                var lBytePosition = 0;
                var lByteCount = 0;
                while (lByteCount < lMessageLength) {
                    lWordCount = (lByteCount - (lByteCount % 4)) / 4;
                    lBytePosition = (lByteCount % 4) * 8;
                    lWordArray[lWordCount] = (lWordArray[lWordCount] | (sMessage.charCodeAt(lByteCount) << lBytePosition));
                    lByteCount++;
                }
                lWordCount = (lByteCount - (lByteCount % 4)) / 4;
                lBytePosition = (lByteCount % 4) * 8;
                lWordArray[lWordCount] = lWordArray[lWordCount] | (0x80 << lBytePosition);
                lWordArray[lNumberOfWords - 2] = lMessageLength << 3;
                lWordArray[lNumberOfWords - 1] = lMessageLength >>> 29;
                return lWordArray;
            }

            function WordToHex(lValue) {
                var WordToHexValue = "",
                    WordToHexValue_temp = "",
                    lByte, lCount;
                for (lCount = 0; lCount <= 3; lCount++) {
                    lByte = (lValue >>> (lCount * 8)) & 255;
                    WordToHexValue_temp = "0" + lByte.toString(16);
                    WordToHexValue = WordToHexValue + WordToHexValue_temp.substr(WordToHexValue_temp.length - 2, 2)
                }
                return WordToHexValue;
            }
            var x = Array();
            var k, AA, BB, CC, DD, a, b, c, d
            var S11 = 7,
                S12 = 12,
                S13 = 17,
                S14 = 22;
            var S21 = 5,
                S22 = 9,
                S23 = 14,
                S24 = 20;
            var S31 = 4,
                S32 = 11,
                S33 = 16,
                S34 = 23;
            var S41 = 6,
                S42 = 10,
                S43 = 15,
                S44 = 21;
            x = ConvertToWordArray(sMessage);
            a = 0x67452301;
            b = 0xEFCDAB89;
            c = 0x98BADCFE;
            d = 0x10325476;
            for (k = 0; k < x.length; k += 16) {
                AA = a;
                BB = b;
                CC = c;
                DD = d;
                a = FF(a, b, c, d, x[k + 0], S11, 0xD76AA478);
                d = FF(d, a, b, c, x[k + 1], S12, 0xE8C7B756);
                c = FF(c, d, a, b, x[k + 2], S13, 0x242070DB);
                b = FF(b, c, d, a, x[k + 3], S14, 0xC1BDCEEE);
                a = FF(a, b, c, d, x[k + 4], S11, 0xF57C0FAF);
                d = FF(d, a, b, c, x[k + 5], S12, 0x4787C62A);
                c = FF(c, d, a, b, x[k + 6], S13, 0xA8304613);
                b = FF(b, c, d, a, x[k + 7], S14, 0xFD469501);
                a = FF(a, b, c, d, x[k + 8], S11, 0x698098D8);
                d = FF(d, a, b, c, x[k + 9], S12, 0x8B44F7AF);
                c = FF(c, d, a, b, x[k + 10], S13, 0xFFFF5BB1);
                b = FF(b, c, d, a, x[k + 11], S14, 0x895CD7BE);
                a = FF(a, b, c, d, x[k + 12], S11, 0x6B901122);
                d = FF(d, a, b, c, x[k + 13], S12, 0xFD987193);
                c = FF(c, d, a, b, x[k + 14], S13, 0xA679438E);
                b = FF(b, c, d, a, x[k + 15], S14, 0x49B40821);
                a = GG(a, b, c, d, x[k + 1], S21, 0xF61E2562);
                d = GG(d, a, b, c, x[k + 6], S22, 0xC040B340);
                c = GG(c, d, a, b, x[k + 11], S23, 0x265E5A51);
                b = GG(b, c, d, a, x[k + 0], S24, 0xE9B6C7AA);
                a = GG(a, b, c, d, x[k + 5], S21, 0xD62F105D);
                d = GG(d, a, b, c, x[k + 10], S22, 0x2441453);
                c = GG(c, d, a, b, x[k + 15], S23, 0xD8A1E681);
                b = GG(b, c, d, a, x[k + 4], S24, 0xE7D3FBC8);
                a = GG(a, b, c, d, x[k + 9], S21, 0x21E1CDE6);
                d = GG(d, a, b, c, x[k + 14], S22, 0xC33707D6);
                c = GG(c, d, a, b, x[k + 3], S23, 0xF4D50D87);
                b = GG(b, c, d, a, x[k + 8], S24, 0x455A14ED);
                a = GG(a, b, c, d, x[k + 13], S21, 0xA9E3E905);
                d = GG(d, a, b, c, x[k + 2], S22, 0xFCEFA3F8);
                c = GG(c, d, a, b, x[k + 7], S23, 0x676F02D9);
                b = GG(b, c, d, a, x[k + 12], S24, 0x8D2A4C8A);
                a = HH(a, b, c, d, x[k + 5], S31, 0xFFFA3942);
                d = HH(d, a, b, c, x[k + 8], S32, 0x8771F681);
                c = HH(c, d, a, b, x[k + 11], S33, 0x6D9D6122);
                b = HH(b, c, d, a, x[k + 14], S34, 0xFDE5380C);
                a = HH(a, b, c, d, x[k + 1], S31, 0xA4BEEA44);
                d = HH(d, a, b, c, x[k + 4], S32, 0x4BDECFA9);
                c = HH(c, d, a, b, x[k + 7], S33, 0xF6BB4B60);
                b = HH(b, c, d, a, x[k + 10], S34, 0xBEBFBC70);
                a = HH(a, b, c, d, x[k + 13], S31, 0x289B7EC6);
                d = HH(d, a, b, c, x[k + 0], S32, 0xEAA127FA);
                c = HH(c, d, a, b, x[k + 3], S33, 0xD4EF3085);
                b = HH(b, c, d, a, x[k + 6], S34, 0x4881D05);
                a = HH(a, b, c, d, x[k + 9], S31, 0xD9D4D039);
                d = HH(d, a, b, c, x[k + 12], S32, 0xE6DB99E5);
                c = HH(c, d, a, b, x[k + 15], S33, 0x1FA27CF8);
                b = HH(b, c, d, a, x[k + 2], S34, 0xC4AC5665);
                a = II(a, b, c, d, x[k + 0], S41, 0xF4292244);
                d = II(d, a, b, c, x[k + 7], S42, 0x432AFF97);
                c = II(c, d, a, b, x[k + 14], S43, 0xAB9423A7);
                b = II(b, c, d, a, x[k + 5], S44, 0xFC93A039);
                a = II(a, b, c, d, x[k + 12], S41, 0x655B59C3);
                d = II(d, a, b, c, x[k + 3], S42, 0x8F0CCC92);
                c = II(c, d, a, b, x[k + 10], S43, 0xFFEFF47D);
                b = II(b, c, d, a, x[k + 1], S44, 0x85845DD1);
                a = II(a, b, c, d, x[k + 8], S41, 0x6FA87E4F);
                d = II(d, a, b, c, x[k + 15], S42, 0xFE2CE6E0);
                c = II(c, d, a, b, x[k + 6], S43, 0xA3014314);
                b = II(b, c, d, a, x[k + 13], S44, 0x4E0811A1);
                a = II(a, b, c, d, x[k + 4], S41, 0xF7537E82);
                d = II(d, a, b, c, x[k + 11], S42, 0xBD3AF235);
                c = II(c, d, a, b, x[k + 2], S43, 0x2AD7D2BB);
                b = II(b, c, d, a, x[k + 9], S44, 0xEB86D391);
                a = AddUnsigned(a, AA);
                b = AddUnsigned(b, BB);
                c = AddUnsigned(c, CC);
                d = AddUnsigned(d, DD);
            }
            var temp = WordToHex(a) + WordToHex(b) + WordToHex(c) + WordToHex(d);
            return temp.toLowerCase();
        }





    };
    window.YSfac = YSfac;
    return YSfac;
})(window);
// Generated by CoffeeScript 1.6.3
(function() {
  var AnimatedText, AnimatedTextFactory, Bar, BaseDonut, BaseGauge, Donut, Gauge, GaugePointer, TextRenderer, ValueUpdater, addCommas, cutHex, formatNumber, mergeObjects, secondsToString, updateObjectValues, _ref, _ref1,
    __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  (function() {
    var browserRequestAnimationFrame, isCancelled, lastId, vendor, vendors, _i, _len;
    vendors = ['ms', 'moz', 'webkit', 'o'];
    for (_i = 0, _len = vendors.length; _i < _len; _i++) {
      vendor = vendors[_i];
      if (window.requestAnimationFrame) {
        break;
      }
      window.requestAnimationFrame = window[vendor + 'RequestAnimationFrame'];
      window.cancelAnimationFrame = window[vendor + 'CancelAnimationFrame'] || window[vendor + 'CancelRequestAnimationFrame'];
    }
    browserRequestAnimationFrame = null;
    lastId = 0;
    isCancelled = {};
    if (!requestAnimationFrame) {
      window.requestAnimationFrame = function(callback, element) {
        var currTime, id, lastTime, timeToCall;
        currTime = new Date().getTime();
        timeToCall = Math.max(0, 16 - (currTime - lastTime));
        id = window.setTimeout(function() {
          return callback(currTime + timeToCall);
        }, timeToCall);
        lastTime = currTime + timeToCall;
        return id;
      };
      return window.cancelAnimationFrame = function(id) {
        return clearTimeout(id);
      };
    } else if (!window.cancelAnimationFrame) {
      browserRequestAnimationFrame = window.requestAnimationFrame;
      window.requestAnimationFrame = function(callback, element) {
        var myId;
        myId = ++lastId;
        browserRequestAnimationFrame(function() {
          if (!isCancelled[myId]) {
            return callback();
          }
        }, element);
        return myId;
      };
      return window.cancelAnimationFrame = function(id) {
        return isCancelled[id] = true;
      };
    }
  })();

  String.prototype.hashCode = function() {
    var char, hash, i, _i, _ref;
    hash = 0;
    if (this.length === 0) {
      return hash;
    }
    for (i = _i = 0, _ref = this.length; 0 <= _ref ? _i < _ref : _i > _ref; i = 0 <= _ref ? ++_i : --_i) {
      char = this.charCodeAt(i);
      hash = ((hash << 5) - hash) + char;
      hash = hash & hash;
    }
    return hash;
  };

  secondsToString = function(sec) {
    var hr, min;
    hr = Math.floor(sec / 3600);
    min = Math.floor((sec - (hr * 3600)) / 60);
    sec -= (hr * 3600) + (min * 60);
    sec += '';
    min += '';
    while (min.length < 2) {
      min = '0' + min;
    }
    while (sec.length < 2) {
      sec = '0' + sec;
    }
    hr = hr ? hr + ':' : '';
    return hr + min + ':' + sec;
  };

  formatNumber = function(num) {
    return addCommas(num.toFixed(0));
  };

  updateObjectValues = function(obj1, obj2) {
    var key, val;
    for (key in obj2) {
      if (!__hasProp.call(obj2, key)) continue;
      val = obj2[key];
      obj1[key] = val;
    }
    return obj1;
  };

  mergeObjects = function(obj1, obj2) {
    var key, out, val;
    out = {};
    for (key in obj1) {
      if (!__hasProp.call(obj1, key)) continue;
      val = obj1[key];
      out[key] = val;
    }
    for (key in obj2) {
      if (!__hasProp.call(obj2, key)) continue;
      val = obj2[key];
      out[key] = val;
    }
    return out;
  };

  addCommas = function(nStr) {
    var rgx, x, x1, x2;
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = '';
    if (x.length > 1) {
      x2 = '.' + x[1];
    }
    rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
      x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
  };

  cutHex = function(nStr) {
    if (nStr.charAt(0) === "#") {
      return nStr.substring(1, 7);
    }
    return nStr;
  };

  ValueUpdater = (function() {
    ValueUpdater.prototype.animationSpeed = 32;

    function ValueUpdater(addToAnimationQueue, clear) {
      if (addToAnimationQueue == null) {
        addToAnimationQueue = true;
      }
      this.clear = clear != null ? clear : true;
      if (addToAnimationQueue) {
        AnimationUpdater.add(this);
      }
    }

    ValueUpdater.prototype.update = function(force) {
      var diff;
      if (force == null) {
        force = false;
      }
      if (force || this.displayedValue !== this.value) {
        if (this.ctx && this.clear) {
          this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        }
        diff = this.value - this.displayedValue;
        if (Math.abs(diff / this.animationSpeed) <= 0.001) {
          this.displayedValue = this.value;
        } else {
          this.displayedValue = this.displayedValue + diff / this.animationSpeed;
        }
        this.render();
        return true;
      }
      return false;
    };

    return ValueUpdater;

  })();

  BaseGauge = (function(_super) {
    __extends(BaseGauge, _super);

    function BaseGauge() {
      _ref = BaseGauge.__super__.constructor.apply(this, arguments);
      return _ref;
    }

    BaseGauge.prototype.displayScale = 1;

    BaseGauge.prototype.setTextField = function(textField) {
      return this.textField = textField instanceof TextRenderer ? textField : new TextRenderer(textField);
    };

    BaseGauge.prototype.setMinValue = function(minValue, updateStartValue) {
      var gauge, _i, _len, _ref1, _results;
      this.minValue = minValue;
      if (updateStartValue == null) {
        updateStartValue = true;
      }
      if (updateStartValue) {
        this.displayedValue = this.minValue;
        _ref1 = this.gp || [];
        _results = [];
        for (_i = 0, _len = _ref1.length; _i < _len; _i++) {
          gauge = _ref1[_i];
          _results.push(gauge.displayedValue = this.minValue);
        }
        return _results;
      }
    };

    BaseGauge.prototype.setOptions = function(options) {
      if (options == null) {
        options = null;
      }
      this.options = mergeObjects(this.options, options);
      if (this.textField) {
        this.textField.el.style.fontSize = options.fontSize + 'px';
      }
      if (this.options.angle > .5) {
        this.gauge.options.angle = .5;
      }
      this.configDisplayScale();
      return this;
    };

    BaseGauge.prototype.configDisplayScale = function() {
      var backingStorePixelRatio, devicePixelRatio, height, prevDisplayScale, width;
      prevDisplayScale = this.displayScale;
      if (this.options.highDpiSupport === false) {
        delete this.displayScale;
      } else {
        devicePixelRatio = window.devicePixelRatio || 1;
        backingStorePixelRatio = this.ctx.webkitBackingStorePixelRatio || this.ctx.mozBackingStorePixelRatio || this.ctx.msBackingStorePixelRatio || this.ctx.oBackingStorePixelRatio || this.ctx.backingStorePixelRatio || 1;
        this.displayScale = devicePixelRatio / backingStorePixelRatio;
      }
      if (this.displayScale !== prevDisplayScale) {
        width = this.canvas.G__width || this.canvas.width;
        height = this.canvas.G__height || this.canvas.height;
        this.canvas.width = width * this.displayScale;
        this.canvas.height = height * this.displayScale;
        this.canvas.style.width = "" + width + "px";
        this.canvas.style.height = "" + height + "px";
        this.canvas.G__width = width;
        this.canvas.G__height = height;
      }
      return this;
    };

    return BaseGauge;

  })(ValueUpdater);

  TextRenderer = (function() {
    function TextRenderer(el) {
      this.el = el;
    }

    TextRenderer.prototype.render = function(gauge) {
      return this.el.innerHTML = formatNumber(gauge.displayedValue);
    };

    return TextRenderer;

  })();

  AnimatedText = (function(_super) {
    __extends(AnimatedText, _super);

    AnimatedText.prototype.displayedValue = 0;

    AnimatedText.prototype.value = 0;

    AnimatedText.prototype.setVal = function(value) {
      return this.value = 1 * value;
    };

    function AnimatedText(elem, text) {
      this.elem = elem;
      this.text = text != null ? text : false;
      this.value = 1 * this.elem.innerHTML;
      if (this.text) {
        this.value = 0;
      }
    }

    AnimatedText.prototype.render = function() {
      var textVal;
      if (this.text) {
        textVal = secondsToString(this.displayedValue.toFixed(0));
      } else {
        textVal = addCommas(formatNumber(this.displayedValue));
      }
      return this.elem.innerHTML = textVal;
    };

    return AnimatedText;

  })(ValueUpdater);

  AnimatedTextFactory = {
    create: function(objList) {
      var elem, out, _i, _len;
      out = [];
      for (_i = 0, _len = objList.length; _i < _len; _i++) {
        elem = objList[_i];
        out.push(new AnimatedText(elem));
      }
      return out;
    }
  };

  GaugePointer = (function(_super) {
    __extends(GaugePointer, _super);

    GaugePointer.prototype.displayedValue = 0;

    GaugePointer.prototype.value = 0;

    GaugePointer.prototype.options = {
      strokeWidth: 0.035,
      length: 0.1,
      color: "#000000"
    };

    function GaugePointer(gauge) {
      this.gauge = gauge;
      this.ctx = this.gauge.ctx;
      this.canvas = this.gauge.canvas;
      GaugePointer.__super__.constructor.call(this, false, false);
      this.setOptions();
    }

    GaugePointer.prototype.setOptions = function(options) {
      if (options == null) {
        options = null;
      }
      updateObjectValues(this.options, options);
      this.length = this.canvas.height * this.options.length;
      this.strokeWidth = this.canvas.height * this.options.strokeWidth;
      this.maxValue = this.gauge.maxValue;
      this.minValue = this.gauge.minValue;
      this.animationSpeed = this.gauge.animationSpeed;
      return this.options.angle = this.gauge.options.angle;
    };

    GaugePointer.prototype.render = function() {
      var angle, centerX, centerY, endX, endY, startX, startY, x, y;
      angle = this.gauge.getAngle.call(this, this.displayedValue);
      centerX = this.canvas.width / 2;
      centerY = this.canvas.height * 0.9;
      x = Math.round(centerX + this.length * Math.cos(angle));
      y = Math.round(centerY + this.length * Math.sin(angle));
      startX = Math.round(centerX + this.strokeWidth * Math.cos(angle - Math.PI / 2));
      startY = Math.round(centerY + this.strokeWidth * Math.sin(angle - Math.PI / 2));
      endX = Math.round(centerX + this.strokeWidth * Math.cos(angle + Math.PI / 2));
      endY = Math.round(centerY + this.strokeWidth * Math.sin(angle + Math.PI / 2));
      this.ctx.fillStyle = this.options.color;
      this.ctx.beginPath();
      this.ctx.arc(centerX, centerY, this.strokeWidth, 0, Math.PI * 2, true);
      this.ctx.fill();
      this.ctx.beginPath();
      this.ctx.moveTo(startX, startY);
      this.ctx.lineTo(x, y);
      this.ctx.lineTo(endX, endY);
      return this.ctx.fill();
    };

    return GaugePointer;

  })(ValueUpdater);

  Bar = (function() {
    function Bar(elem) {
      this.elem = elem;
    }

    Bar.prototype.updateValues = function(arrValues) {
      this.value = arrValues[0];
      this.maxValue = arrValues[1];
      this.avgValue = arrValues[2];
      return this.render();
    };

    Bar.prototype.render = function() {
      var avgPercent, valPercent;
      if (this.textField) {
        this.textField.text(formatNumber(this.value));
      }
      if (this.maxValue === 0) {
        this.maxValue = this.avgValue * 2;
      }
      valPercent = (this.value / this.maxValue) * 100;
      avgPercent = (this.avgValue / this.maxValue) * 100;
      $(".bar-value", this.elem).css({
        "width": valPercent + "%"
      });
      return $(".typical-value", this.elem).css({
        "width": avgPercent + "%"
      });
    };

    return Bar;

  })();

  Gauge = (function(_super) {
    __extends(Gauge, _super);

    Gauge.prototype.elem = null;

    Gauge.prototype.value = [20];

    Gauge.prototype.maxValue = 80;

    Gauge.prototype.minValue = 0;

    Gauge.prototype.displayedAngle = 0;

    Gauge.prototype.displayedValue = 0;

    Gauge.prototype.lineWidth = 40;

    Gauge.prototype.paddingBottom = 0.1;

    Gauge.prototype.percentColors = null;

    Gauge.prototype.options = {
      colorStart: "#6fadcf",
      colorStop: void 0,
      gradientType: 0,
      strokeColor: "#e0e0e0",
      pointer: {
        length: 0.8,
        strokeWidth: 0.035
      },
      angle: 0.15,
      lineWidth: 0.44,
      fontSize: 40,
      limitMax: false
    };

    function Gauge(canvas) {
      this.canvas = canvas;
      Gauge.__super__.constructor.call(this);
      this.percentColors = null;
      if (typeof G_vmlCanvasManager !== 'undefined') {
        this.canvas = window.G_vmlCanvasManager.initElement(this.canvas);
      }
      this.ctx = this.canvas.getContext('2d');
      this.gp = [new GaugePointer(this)];
      this.setOptions();
      this.render();
    }

    Gauge.prototype.setOptions = function(options) {
      var gauge, _i, _len, _ref1;
      if (options == null) {
        options = null;
      }
      Gauge.__super__.setOptions.call(this, options);
      this.configPercentColors();
      this.lineWidth = this.canvas.height * (1 - this.paddingBottom) * this.options.lineWidth;
      this.radius = this.canvas.height * (1 - this.paddingBottom) - this.lineWidth;
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
      this.render();
      _ref1 = this.gp;
      for (_i = 0, _len = _ref1.length; _i < _len; _i++) {
        gauge = _ref1[_i];
        gauge.setOptions(this.options.pointer);
        gauge.render();
      }
      return this;
    };

    Gauge.prototype.configPercentColors = function() {
      var bval, gval, i, rval, _i, _ref1, _results;
      this.percentColors = null;
      if (this.options.percentColors !== void 0) {
        this.percentColors = new Array();
        _results = [];
        for (i = _i = 0, _ref1 = this.options.percentColors.length - 1; 0 <= _ref1 ? _i <= _ref1 : _i >= _ref1; i = 0 <= _ref1 ? ++_i : --_i) {
          rval = parseInt((cutHex(this.options.percentColors[i][1])).substring(0, 2), 16);
          gval = parseInt((cutHex(this.options.percentColors[i][1])).substring(2, 4), 16);
          bval = parseInt((cutHex(this.options.percentColors[i][1])).substring(4, 6), 16);
          _results.push(this.percentColors[i] = {
            pct: this.options.percentColors[i][0],
            color: {
              r: rval,
              g: gval,
              b: bval
            }
          });
        }
        return _results;
      }
    };

    Gauge.prototype.set = function(value) {
      var i, max_hit, val, _i, _j, _len, _ref1;
      if (!(value instanceof Array)) {
        value = [value];
      }
      if (value.length > this.gp.length) {
        for (i = _i = 0, _ref1 = value.length - this.gp.length; 0 <= _ref1 ? _i < _ref1 : _i > _ref1; i = 0 <= _ref1 ? ++_i : --_i) {
          this.gp.push(new GaugePointer(this));
        }
      }
      i = 0;
      max_hit = false;
      for (_j = 0, _len = value.length; _j < _len; _j++) {
        val = value[_j];
        if (val > this.maxValue) {
          this.maxValue = this.value * 1.1;
          max_hit = true;
        }
        this.gp[i].value = val;
        this.gp[i++].setOptions({
          maxValue: this.maxValue,
          angle: this.options.angle
        });
      }
      this.value = value[value.length - 1];
      if (max_hit) {
        if (!this.options.limitMax) {
          return AnimationUpdater.run();
        }
      } else {
        return AnimationUpdater.run();
      }
    };

    Gauge.prototype.getAngle = function(value) {
      return (1 + this.options.angle) * Math.PI + ((value - this.minValue) / (this.maxValue - this.minValue)) * (1 - this.options.angle * 2) * Math.PI;
    };

    Gauge.prototype.getColorForPercentage = function(pct, grad) {
      var color, endColor, i, rangePct, startColor, _i, _ref1;
      if (pct === 0) {
        color = this.percentColors[0].color;
      } else {
        color = this.percentColors[this.percentColors.length - 1].color;
        for (i = _i = 0, _ref1 = this.percentColors.length - 1; 0 <= _ref1 ? _i <= _ref1 : _i >= _ref1; i = 0 <= _ref1 ? ++_i : --_i) {
          if (pct <= this.percentColors[i].pct) {
            if (grad === true) {
              startColor = this.percentColors[i - 1];
              endColor = this.percentColors[i];
              rangePct = (pct - startColor.pct) / (endColor.pct - startColor.pct);
              color = {
                r: Math.floor(startColor.color.r * (1 - rangePct) + endColor.color.r * rangePct),
                g: Math.floor(startColor.color.g * (1 - rangePct) + endColor.color.g * rangePct),
                b: Math.floor(startColor.color.b * (1 - rangePct) + endColor.color.b * rangePct)
              };
            } else {
              color = this.percentColors[i].color;
            }
            break;
          }
        }
      }
      return 'rgb(' + [color.r, color.g, color.b].join(',') + ')';
    };

    Gauge.prototype.getColorForValue = function(val, grad) {
      var pct;
      pct = (val - this.minValue) / (this.maxValue - this.minValue);
      return this.getColorForPercentage(pct, grad);
    };

    Gauge.prototype.render = function() {
      var displayedAngle, fillStyle, gauge, h, w, _i, _len, _ref1, _results;
      w = this.canvas.width / 2;
      h = this.canvas.height * (1 - this.paddingBottom);
      displayedAngle = this.getAngle(this.displayedValue);
      if (this.textField) {
        this.textField.render(this);
      }
      this.ctx.lineCap = "butt";
      if (this.options.customFillStyle !== void 0) {
        fillStyle = this.options.customFillStyle(this);
      } else if (this.percentColors !== null) {
        fillStyle = this.getColorForValue(this.displayedValue, true);
      } else if (this.options.colorStop !== void 0) {
        if (this.options.gradientType === 0) {
          fillStyle = this.ctx.createRadialGradient(w, h, 9, w, h, 70);
        } else {
          fillStyle = this.ctx.createLinearGradient(0, 0, w, 0);
        }
        fillStyle.addColorStop(0, this.options.colorStart);
        fillStyle.addColorStop(1, this.options.colorStop);
      } else {
        fillStyle = this.options.colorStart;
      }
      this.ctx.strokeStyle = fillStyle;
      this.ctx.beginPath();
      this.ctx.arc(w, h, this.radius, (1 + this.options.angle) * Math.PI, displayedAngle, false);
      this.ctx.lineWidth = this.lineWidth;
      this.ctx.stroke();
      this.ctx.strokeStyle = this.options.strokeColor;
      this.ctx.beginPath();
      this.ctx.arc(w, h, this.radius, displayedAngle, (2 - this.options.angle) * Math.PI, false);
      this.ctx.stroke();
      _ref1 = this.gp;
      _results = [];
      for (_i = 0, _len = _ref1.length; _i < _len; _i++) {
        gauge = _ref1[_i];
        _results.push(gauge.update(true));
      }
      return _results;
    };

    return Gauge;

  })(BaseGauge);

  BaseDonut = (function(_super) {
    __extends(BaseDonut, _super);

    BaseDonut.prototype.lineWidth = 15;

    BaseDonut.prototype.displayedValue = 0;

    BaseDonut.prototype.value = 33;

    BaseDonut.prototype.maxValue = 80;

    BaseDonut.prototype.minValue = 0;

    BaseDonut.prototype.options = {
      lineWidth: 0.10,
      colorStart: "#6f6ea0",
      colorStop: "#c0c0db",
      strokeColor: "#eeeeee",
      shadowColor: "#d5d5d5",
      angle: 0.35
    };

    function BaseDonut(canvas) {
      this.canvas = canvas;
      BaseDonut.__super__.constructor.call(this);
      if (typeof G_vmlCanvasManager !== 'undefined') {
        this.canvas = window.G_vmlCanvasManager.initElement(this.canvas);
      }
      this.ctx = this.canvas.getContext('2d');
      this.setOptions();
      this.render();
    }

    BaseDonut.prototype.getAngle = function(value) {
      return (1 - this.options.angle) * Math.PI + ((value - this.minValue) / (this.maxValue - this.minValue)) * ((2 + this.options.angle) - (1 - this.options.angle)) * Math.PI;
    };

    BaseDonut.prototype.setOptions = function(options) {
      if (options == null) {
        options = null;
      }
      BaseDonut.__super__.setOptions.call(this, options);
      this.lineWidth = this.canvas.height * this.options.lineWidth;
      this.radius = this.canvas.height / 2 - this.lineWidth / 2;
      return this;
    };

    BaseDonut.prototype.set = function(value) {
      this.value = value;
      if (this.value > this.maxValue) {
        this.maxValue = this.value * 1.1;
      }
      return AnimationUpdater.run();
    };

    BaseDonut.prototype.render = function() {
      var displayedAngle, grdFill, h, start, stop, w;
      displayedAngle = this.getAngle(this.displayedValue);
      w = this.canvas.width / 2;
      h = this.canvas.height / 2;
      if (this.textField) {
        this.textField.render(this);
      }
      grdFill = this.ctx.createRadialGradient(w, h, 39, w, h, 70);
      grdFill.addColorStop(0, this.options.colorStart);
      grdFill.addColorStop(1, this.options.colorStop);
      start = this.radius - this.lineWidth / 2;
      stop = this.radius + this.lineWidth / 2;
      this.ctx.strokeStyle = this.options.strokeColor;
      this.ctx.beginPath();
      this.ctx.arc(w, h, this.radius, (1 - this.options.angle) * Math.PI, (2 + this.options.angle) * Math.PI, false);
      this.ctx.lineWidth = this.lineWidth;
      this.ctx.lineCap = "round";
      this.ctx.stroke();
      this.ctx.strokeStyle = grdFill;
      this.ctx.beginPath();
      this.ctx.arc(w, h, this.radius, (1 - this.options.angle) * Math.PI, displayedAngle, false);
      return this.ctx.stroke();
    };

    return BaseDonut;

  })(BaseGauge);

  Donut = (function(_super) {
    __extends(Donut, _super);

    function Donut() {
      _ref1 = Donut.__super__.constructor.apply(this, arguments);
      return _ref1;
    }

    Donut.prototype.strokeGradient = function(w, h, start, stop) {
      var grd;
      grd = this.ctx.createRadialGradient(w, h, start, w, h, stop);
      grd.addColorStop(0, this.options.shadowColor);
      grd.addColorStop(0.12, this.options._orgStrokeColor);
      grd.addColorStop(0.88, this.options._orgStrokeColor);
      grd.addColorStop(1, this.options.shadowColor);
      return grd;
    };

    Donut.prototype.setOptions = function(options) {
      var h, start, stop, w;
      if (options == null) {
        options = null;
      }
      Donut.__super__.setOptions.call(this, options);
      w = this.canvas.width / 2;
      h = this.canvas.height / 2;
      start = this.radius - this.lineWidth / 2;
      stop = this.radius + this.lineWidth / 2;
      this.options._orgStrokeColor = this.options.strokeColor;
      this.options.strokeColor = this.strokeGradient(w, h, start, stop);
      return this;
    };

    return Donut;

  })(BaseDonut);

  window.AnimationUpdater = {
    elements: [],
    animId: null,
    addAll: function(list) {
      var elem, _i, _len, _results;
      _results = [];
      for (_i = 0, _len = list.length; _i < _len; _i++) {
        elem = list[_i];
        _results.push(AnimationUpdater.elements.push(elem));
      }
      return _results;
    },
    add: function(object) {
      return AnimationUpdater.elements.push(object);
    },
    run: function() {
      var animationFinished, elem, _i, _len, _ref2;
      animationFinished = true;
      _ref2 = AnimationUpdater.elements;
      for (_i = 0, _len = _ref2.length; _i < _len; _i++) {
        elem = _ref2[_i];
        if (elem.update()) {
          animationFinished = false;
        }
      }
      if (!animationFinished) {
        return AnimationUpdater.animId = requestAnimationFrame(AnimationUpdater.run);
      } else {
        return cancelAnimationFrame(AnimationUpdater.animId);
      }
    }
  };

  window.Gauge = Gauge;

  window.Donut = Donut;

  window.BaseDonut = BaseDonut;

  window.TextRenderer = TextRenderer;

}).call(this);
