//package me.Yaacob.Environment.Components3D;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.lwjgl.util.vector.Vector2f;
//import org.lwjgl.util.vector.Vector3f;
//
//import me.Yaacob.Core.Y;
//
//public class OBJLoader {
//	static ArrayList<Material> materials=new ArrayList<>();
//	public static void loadOBJ(File f, Model m){
//		String[] content =Y.readFile(f).split("\n");
//		
//		ArrayList<VertexNM> vertices = new ArrayList<VertexNM>();
//        ArrayList<Vector2f> textures = new ArrayList<Vector2f>();
//        ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
//        ArrayList<Integer> indices = new ArrayList<Integer>();
//        
//        for(int i=0;i!=content.length;i++){
//        	String line=content[i];
//        	String[] c=line.split(" ");
//        	if(line.startsWith("v ")){
//        		Vector3f vertex=new Vector3f((float)Float.parseFloat(c[1]),(float)Float.parseFloat(c[2]),(float)Float.parseFloat(c[3]));
//        		VertexNM nv=new VertexNM(vertices.size(),vertex);
//        		vertices.add(nv);
//        	}else if(line.startsWith("vt ")){
//        		Vector2f tex=new Vector2f((float)Float.parseFloat(c[1]),(float)Float.parseFloat(c[2]));
//        		textures.add(tex);
//        	} else if(line.startsWith("vn ")){
//        		Vector3f normal=new Vector3f((float)Float.parseFloat(c[1]),(float)Float.parseFloat(c[2]),(float)Float.parseFloat(c[3]));
//        		normals.add(normal);
//        	} else if(line.startsWith("f ")){
//        		break;
//        	} else if(line.startsWith("mtllib ")){
//        		String[] mat=Y.readFile(new File(line.replace("mtllib ", ""))).split("\n");
//        		String nam="";
//        		String t="";
//        		String n="";
//        		for(int j=0;j!=mat.length;j++){
//        			
//        	
//        			if(mat[j].startsWith("newmtl "))nam=mat[j].replace("newmtl ", "");
//        			if(mat[j].startsWith("map_Kd "))t=mat[j].replace("map_Kd ", "");
//        			if(j+1<mat.length){
//        				if(mat[j+1].startsWith("map_Bump "))n=mat[j+1].replace("map_Bump ", "");
//        			}
//        			if(nam!="" && t!=""){
//        	
//        				materials.add(new Material(nam,t,n));
//        				nam="";
//        				t="";
//        				n="";
//        			}
//        			
//        		}
//        		
//        		
//        	} else if(line.startsWith("usemtl ")){
//        		for(Material mat:materials){
//        			if(mat.name.equals(line.replace("usemtl ", ""))){
//        				if(mat.norm!="")m.tang=true;
//        				if(mat.tex!=""){
//        					m.tex=true;
//        					m.m=mat;
//        				}
//        			}
//        		}
//        	}
//        	
//        }
//        for(int i=0;i!=content.length;i++){
//        	
//        	if(content[i].startsWith("f ")){
//        	String line=content[i];
//            String[] currentLine = line.split(" ");
//            String[] vertex1 = currentLine[1].split("/");
//            String[] vertex2 = currentLine[2].split("/");
//            String[] vertex3 = currentLine[3].split("/");
//            VertexNM v0 = processVertex(vertex1, vertices, indices  , textures);
//            VertexNM v1 = processVertex(vertex2, vertices, indices, textures);
//            VertexNM v2 = processVertex(vertex3, vertices, indices, textures);
//            if(textures.size()!=0)calculateTangents(v0, v1, v2, textures);
//        	}else{
//        		continue;
//        	}
//        }
//        removeUnusedVertices(vertices);
//        float[] verticesArray = new float[vertices.size() * 3];
//        float[] texturesArray = new float[vertices.size() * 2];
//        float[] normalsArray = new float[vertices.size() * 3];
//        float[] tangentsArray = new float[vertices.size() * 3];
//        float furthest = convertDataToArrays(vertices, textures, normals, verticesArray,
//                texturesArray, normalsArray, tangentsArray);
//        int[] indicesArray = convertIndicesListToArray(indices);
//        
//        
//        m.fpoint=furthest;
//        m.indices=indicesArray;
//        m.normals=normalsArray;
//        m.tangents=tangentsArray;
//        m.texcoord=texturesArray;
//        m.vertices=verticesArray;
// 
//        
//        }
//		
//        private static void calculateTangents(VertexNM v0, VertexNM v1, VertexNM v2,
//                List<Vector2f> textures) {
//            Vector3f delatPos1 = Vector3f.sub(v1.getPosition(), v0.getPosition(), null);
//            Vector3f delatPos2 = Vector3f.sub(v2.getPosition(), v0.getPosition(), null);
//            Vector2f uv0 = textures.get(v0.getTextureIndex());
//            Vector2f uv1 = textures.get(v1.getTextureIndex());
//            Vector2f uv2 = textures.get(v2.getTextureIndex());
//            Vector2f deltaUv1 = Vector2f.sub(uv1, uv0, null);
//            Vector2f deltaUv2 = Vector2f.sub(uv2, uv0, null);
//     
//            float r = 1.0f / (deltaUv1.x * deltaUv2.y - deltaUv1.y * deltaUv2.x);
//            delatPos1.scale(deltaUv2.y);
//            delatPos2.scale(deltaUv1.y);
//            Vector3f tangent = Vector3f.sub(delatPos1, delatPos2, null);
//            tangent.scale(r);
//            v0.addTangent(tangent);
//            v1.addTangent(tangent);
//            v2.addTangent(tangent);
//        }
//     
//        private static VertexNM processVertex(String[] vertex, ArrayList<VertexNM> vertices,
//                ArrayList<Integer> indices , ArrayList<Vector2f> textures) {
//        	
//            int index = Integer.parseInt(vertex[0]) - 1;
//            VertexNM currentVertex = vertices.get(index);
//            int textureIndex=0;
//            if(textures.size()!=0) textureIndex = Integer.parseInt(vertex[1]) - 1;
//            int normalIndex = Integer.parseInt(vertex[2]) - 1;
//            if (!currentVertex.isSet()) {
//            	 if(textures.size()!=0)   currentVertex.setTextureIndex(textureIndex);
//                currentVertex.setNormalIndex(normalIndex);
//                indices.add(index);
//                return currentVertex;
//            } else {
//                return dealWithAlreadyProcessedVertex(currentVertex, textureIndex, normalIndex, indices,
//                        vertices);
//            }
//        }
//     
//        private static int[] convertIndicesListToArray(List<Integer> indices) {
//            int[] indicesArray = new int[indices.size()];
//            for (int i = 0; i < indicesArray.length; i++) {
//                indicesArray[i] = indices.get(i);
//            }
//            return indicesArray;
//        }
//     
//        private static float convertDataToArrays(List<VertexNM> vertices, List<Vector2f> textures,
//                List<Vector3f> normals, float[] verticesArray, float[] texturesArray,
//                float[] normalsArray, float[] tangentsArray) {
//            float furthestPoint = 0;
//            for (int i = 0; i < vertices.size(); i++) {
//                VertexNM currentVertex = vertices.get(i);
//                if (currentVertex.getLength() > furthestPoint) {
//                    furthestPoint = currentVertex.getLength();
//                }
//                Vector3f position = currentVertex.getPosition();
//                Vector2f textureCoord=null;
//                if(textures.size()!=0){
//                	textureCoord = textures.get(currentVertex.getTextureIndex());
//                }
//                Vector3f normalVector = normals.get(currentVertex.getNormalIndex());
//                Vector3f tangent = currentVertex.getAverageTangent();
//                verticesArray[i * 3] = position.x;
//                verticesArray[i * 3 + 1] = position.y;
//                verticesArray[i * 3 + 2] = position.z;
//               if(textures.size()!=0)texturesArray[i * 2] = textureCoord.x;
//               if(textures.size()!=0)texturesArray[i * 2 + 1] = 1 - textureCoord.y;
//                normalsArray[i * 3] = normalVector.x;
//                normalsArray[i * 3 + 1] = normalVector.y;
//                normalsArray[i * 3 + 2] = normalVector.z;
//                tangentsArray[i * 3] = tangent.x;
//                tangentsArray[i * 3 + 1] = tangent.y;
//                tangentsArray[i * 3 + 2] = tangent.z;
//     
//            }
//            return furthestPoint;
//        }
//     
//        private static VertexNM dealWithAlreadyProcessedVertex(VertexNM previousVertex, int newTextureIndex,
//                int newNormalIndex, List<Integer> indices, List<VertexNM> vertices) {
//            if (previousVertex.hasSameTextureAndNormal(newTextureIndex, newNormalIndex)) {
//                indices.add(previousVertex.getIndex());
//                return previousVertex;
//            } else {
//                VertexNM anotherVertex = previousVertex.getDuplicateVertex();
//                if (anotherVertex != null) {
//                    return dealWithAlreadyProcessedVertex(anotherVertex, newTextureIndex,
//                            newNormalIndex, indices, vertices);
//                } else {
//                    VertexNM duplicateVertex = previousVertex.duplicate(vertices.size());//NEW
//                    duplicateVertex.setTextureIndex(newTextureIndex);
//                    duplicateVertex.setNormalIndex(newNormalIndex);
//                    previousVertex.setDuplicateVertex(duplicateVertex);
//                    vertices.add(duplicateVertex);
//                    indices.add(duplicateVertex.getIndex());
//                    return duplicateVertex;
//                }
//            }
//
//	}
//        private static void removeUnusedVertices(List<VertexNM> vertices) {
//            for (VertexNM vertex : vertices) {
//              vertex.averageTangents();
//                if (!vertex.isSet()) {
//                    vertex.setTextureIndex(0);
//                    vertex.setNormalIndex(0);
//                }
//            }
//        }
//	
//	
//}
