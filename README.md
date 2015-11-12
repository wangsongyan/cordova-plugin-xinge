# cordova-plugin-xinge
this is a tencent xinge plugin for cordova. include register,unregister and getToken method


register()

unregister()

getToken()


#instruction
I only use this plugin for android platform.so the plugin only include this .java file and .js file.

1.you must create a cordova project.  
install Node.js  
install cordova  

cordova create projectName pakageName AppName.  
cordova platform add android.  
cordova plugin add https://github.com/wangsongyan/cordova-plugin-xinge.git(you must install git tool)  

2.import your android project into Androud Stuido or Eclipse.  

3.Modify your AndroidManifest.xml.you can refer to http://developer.xg.qq.com/index.php/Android_SDK%E5%BF%AB%E9%80%9F%E6%8C%87%E5%8D%97  

4.add the libararis and .so file into android project.you can download the zip file from http://pan.baidu.com/s/1bnC1ypx or get the libs from http://xg.qq.com/xg/ctr_index/download

5.run the android project on your android devices or simulator.  

6.you can call the register,unregister,getToken in your js file. and must confirm when the 'deviceready' called.  
include your cordova.js first.  
then  
	document.addEventListener('deviceready', function(){  
		var xinge = cordova.require('cordova-plugin-xinge.XinGe');  
		xinge.getToken(  
			function(msg){  
				alert('Token:'+msg);  
			},  
			function(msg){  
				alert('error:'+msg);  
			}  
		);  
	});  

	