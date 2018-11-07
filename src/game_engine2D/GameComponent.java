package game_engine2D;

public abstract class GameComponent extends ProcessingEntity {

	public GameComponent(GameObject g) {
		super(g.parent);
		
		//this.GameObject = g;
		//this.Transform = this.GameObject.Transform;
	}
}
