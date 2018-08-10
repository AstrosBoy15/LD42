#version 150

in vec2 position;

out vec2 blurTextureCoords[11];

uniform float targetHeight;

void main(void){

	gl_Position = vec4(position, 0.0, 1.0);
	vec2 centerTexCoords = vec2((position.x +1.0)/2.0, 1 - (position.y +1.0)/2.0);
	float pixelSize = 1.0 / targetHeight;
	
	for(int i = -5; i <= 5; i++) {
		blurTextureCoords[i + 5] = centerTexCoords + vec2(0.0, pixelSize * i);
	}

}
