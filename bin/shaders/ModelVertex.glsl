#version 400 core

in vec3 position;
in vec3 normal;
in vec2 texture;
in vec3 tangents;


out vec2 coord;
out vec3 tlv;
out vec3 surface;
out vec3 tcv;

uniform mat4 transform;
uniform mat4 camera;
uniform mat4 projection;
uniform vec3 lpos;
uniform int tang;
uniform int lighting;


void main(void){
	vec4 wt=transform*vec4(position,1.0);
	gl_Position =projection*camera*wt;
	coord=texture;
	if(lighting==1){
	if(tang==1){
	
	
	surface=((transform*vec4(normal,0.0))).xyz;
	
		vec3 norma=normalize(surface);
	vec3 tang=normalize((transform*camera*vec4(tangents,1.0)).xyz);
	vec3 bitang=normalize(cross(norma,tangents));
	
	
	mat3 tts=mat3(
	tang.x,bitang.x,norma.x,
	tang.y,bitang.y,norma.y,
	tang.z,bitang.z,norma.z
	);	
tlv=tts*(lpos-wt.xyz);
	tcv=tts*(((inverse(camera)*vec4(0.0,0.0,0.0,1.0)).xyz-wt.xyz));

	
	
	}else{
		surface=((transform*vec4(normal,0.0))).xyz;
	tlv=lpos-wt.xyz;
	tcv=((inverse(camera)*vec4(0.0,0.0,0.0,1.0)).xyz-wt.xyz);
	
	}
	}else{
	coord=texture;
	}
	

}