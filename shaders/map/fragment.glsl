#version 400

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D entityTexture;

void main(void){

	out_Color = texture(entityTexture,textureCoords);

}