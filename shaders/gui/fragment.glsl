#version 400

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D guiTexture;

uniform float alpha;
uniform vec3 colour;

void main(void) {

	out_Color = texture(guiTexture, textureCoords);
	if(colour != vec3(-1, -1, -1)) {
		out_Color = vec4(colour, out_Color.w * alpha);
	}

}
