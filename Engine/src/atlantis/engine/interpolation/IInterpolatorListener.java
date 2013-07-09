package atlantis.engine.interpolation;

public interface IInterpolatorListener {
	void onStart();
	void onFinish();
	void onRestart();
}
