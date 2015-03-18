package com.example.victwo;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class EnhancedMovementMethod extends ArrowKeyMovementMethod {

	private static EnhancedMovementMethod sInstance;

	public static MovementMethod getInstance() {
		if (sInstance == null) {
			sInstance = new EnhancedMovementMethod();
		}
		return sInstance;
	}

	public boolean onTouchEvent(TextView widget, Spannable buffer,
			MotionEvent event) {
		int action = event.getAction();
		
		if (action == MotionEvent.ACTION_UP
				|| action == MotionEvent.ACTION_DOWN) {
			int x = (int) event.getX();
			int y = (int) event.getY();

			x -= widget.getTotalPaddingLeft();
			y -= widget.getTotalPaddingTop();

			x += widget.getScrollX();
			y += widget.getScrollY();

			Layout layout = widget.getLayout();
			int line = layout.getLineForVertical(y);
			int off = layout.getOffsetForHorizontal(line, x);

			ClickableSpan[] link = buffer.getSpans(off, off,
					ClickableSpan.class);

			if (link.length != 0) {
				if (action == MotionEvent.ACTION_UP) {
					link[0].onClick(widget);
					Selection.removeSelection(buffer);
					Log.d("LINK", link[0].toString());
					Log.d("LINK", "CLICKED");
					return true;
				} else if (action == MotionEvent.ACTION_DOWN) {
					Selection.setSelection(buffer,
							buffer.getSpanStart(link[0]),
							buffer.getSpanEnd(link[0]));
				}

				return true;
			}
			/*
			 * else { Selection.removeSelection(buffer); }
			 */
		}

		return super.onTouchEvent(widget, buffer, event);
	}

}
