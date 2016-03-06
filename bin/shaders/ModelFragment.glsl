#version 400 core

in vec2 coord;
in vec3 tlv;
in vec3 surface;
in vec3 tcv;

out vec4 outc;

uniform sampler2D tex;
uniform sampler2D norm;


uniform int texb;
uniform int tang;

uniform vec3 lcolor;
uniform float shineDamper;
uniform float reflectivity;
uniform int lighting;

void main(void){


if(lighting==1){
if(tang==1){
vec3 uno=(2*texture(norm,coord)-1).xyz;
vec3 un=normalize(uno.rgb);
vec3 ulv=normalize(tlv);
float nd=dot(un,ulv);
float brightness=max(nd,0.1);
vec3 diffuse=brightness*lcolor;
vec3 utcv=normalize(tcv);

vec3 ldir=-un;
vec3 rldir=reflect(ldir,un);
float specular = dot(rldir,utcv);
specular=max(specular,0.0);
float damped=pow(specular,shineDamper);
vec3 final=damped*reflectivity*lcolor;

outc=vec4(diffuse,1.0)*texture(tex,coord)+vec4(final,1.0);

}else{

vec3 un=normalize(surface);
vec3 ulv=normalize(tlv);
float nd=dot(un,ulv);
float brightness=max(nd,0.1);
vec3 diffuse=brightness*lcolor;
vec3 utcv=normalize(tcv);

vec3 ldir=-un;
vec3 rldir=reflect(ldir,un);
float specular = dot(rldir,utcv);
specular=max(specular,0.0);
float damped=pow(specular,shineDamper);
vec3 final=damped*reflectivity*lcolor;

if(texb==0){

outc=vec4(diffuse,1.0)*vec4(1.0,1.0,1.0,1.0)+vec4(final,1.0);

} else{
outc=vec4(diffuse,1.0)*texture(tex,coord)+vec4(final,1.0);
}
}
}else{
if(texb==0){

outc=vec4(1.0,1.0,1.0,1.0);

} else{
outc=texture(tex,coord);
}

}

}