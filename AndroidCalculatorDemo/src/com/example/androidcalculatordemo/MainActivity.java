package com.example.androidcalculatordemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7;
	private Button btn_8,btn_9,btn_plus,btn_minus,btn_multiply,btn_divide;
	private Button btn_point,btn_clear,btn_delete,btn_equal;
	EditText et_input;
	boolean clear_flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_0=(Button) findViewById(R.id.btn_0);
		btn_1=(Button) findViewById(R.id.btn_1);
		btn_2=(Button) findViewById(R.id.btn_2);
		btn_3=(Button) findViewById(R.id.btn_3);
		btn_4=(Button) findViewById(R.id.btn_4);
		btn_5=(Button) findViewById(R.id.btn_5);
		btn_6=(Button) findViewById(R.id.btn_6);
		btn_7=(Button) findViewById(R.id.btn_7);
		btn_8=(Button) findViewById(R.id.btn_8);
		btn_9=(Button) findViewById(R.id.btn_9);
		btn_plus=(Button) findViewById(R.id.plus);
		btn_minus=(Button) findViewById(R.id.minus);
		btn_multiply=(Button) findViewById(R.id.multiply);
		btn_divide=(Button) findViewById(R.id.devide);
		btn_point=(Button) findViewById(R.id.btn_point);
		btn_clear=(Button) findViewById(R.id.btn_clear);
		btn_delete=(Button) findViewById(R.id.btn_del);
		btn_equal=(Button) findViewById(R.id.equal);
		et_input=(EditText) findViewById(R.id.et_input);
		btn_0.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_plus.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_multiply.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_point.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_delete.setOnClickListener(this);
		btn_equal.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		String str=et_input.getText().toString();
		switch (arg0.getId()) {
		case R.id.btn_0:
		case R.id.btn_1:
		case R.id.btn_2:
		case R.id.btn_3:
		case R.id.btn_4:
		case R.id.btn_5:
		case R.id.btn_6:
		case R.id.btn_7:
		case R.id.btn_8:
		case R.id.btn_9:
			if(clear_flag){
				clear_flag=false;
				et_input.setText("");
				str="";
			}
			et_input.setText(str+((Button)arg0).getText());
			break;
		case R.id.btn_point:
			if(clear_flag){
				clear_flag=false;
				et_input.setText("");
				str="";
			}
			if(dealPoint(str)){
				str=et_input.getText().toString();
				et_input.setText(str+((Button)arg0).getText());
				}
			break;
		case R.id.plus:
		case R.id.minus:
		case R.id.multiply:
		case R.id.devide:
			if(clear_flag){
				clear_flag=false;
				et_input.setText("");
			}
			if(judge(str)){
				str=et_input.getText().toString();
				et_input.setText(str+" "+((Button)arg0).getText()+" ");
			}
				
			break;
		case R.id.btn_del:
			if(clear_flag){
				clear_flag=false;
				et_input.setText("");
			}
			else if(str!=null&&!str.equals("")){
				if(str.charAt(str.length()-1)==' ') 
					et_input.setText(str.subSequence(0, str.length()-3));
				else et_input.setText(str.subSequence(0, str.length()-1));
			}
			break;
		case R.id.btn_clear:
			clear_flag=false;
			et_input.setText("");
			break;
		case R.id.equal:
			getResult();
			clear_flag=true;
			break;
		default:
			break;
		}
	}
	/**
	 * 将EditText中表达式计算出来
	 */
	private void getResult(){
		String str=et_input.getText().toString();
		if(str==null||str.equals("")) return ;
		if(!str.contains(" ")) return ;
		if(clear_flag){
			clear_flag=false;
			return ;
		}
		if(str.charAt(str.length()-1)=='.') str=str.substring(0, str.length()-2);
		String frontNum=str.substring(0,str.indexOf(" "));
		String cal=str.substring(str.indexOf(" ")+1, str.indexOf(" ")+2);
		String behindNum=str.substring(str.indexOf(" ")+3);
		double result=0;
		if(!frontNum.equals("")&&!behindNum.equals("")){
			double d1=Double.parseDouble(frontNum);
			double d2=Double.parseDouble(behindNum);
			if(cal.equals("+")) 
				result=d1+d2;
			else if(cal.equals("-"))	
				result=d1-d2;
			else if(cal.equals("×"))
				result =d1*d2;
			else if(cal.equals("÷")){
				if(d2==0) result=0;
				else result=d1/d2;
			}
			if(!frontNum.contains(".")&&!behindNum.contains(".")&&!cal.equals("÷")){
				int r=(int) result;
				et_input.setText(r+"");
			}
			else et_input.setText(String.format("%.7f", result)+"");
		}
		else if(!frontNum.equals("")&&behindNum.equals("")){
			et_input.setText(frontNum);
		}
		else if(frontNum.equals("")&&!behindNum.equals("")){
			double d2=Double.parseDouble(behindNum);
			if(cal.equals("+")) 
				result=0+d2;
			else if(cal.equals("-"))	
				result=0-d2;
			else if(cal.equals("×"))
				result =0;
			else if(cal.equals("÷")){
				if(d2==0) result=0;
				else result=0;
			}
			if(!frontNum.contains(".")&&!behindNum.contains(".")){
				int r=(int) result;
				et_input.setText(r+"");
			}
			else et_input.setText(String.format("%.7f", result)+"");
		}
		else et_input.setText("");
	}
	/**
	 * 判断还能否添加符号。
	 * 若已经为完整式子则计算出再添加
	 * 若已经有符号且不完整则不能添加
	 */
	private boolean judge(String str){
		if(str.equals("")) return true;
		if(!str.contains(" ")) return true;
		String behindNum=str.substring(str.indexOf(" ")+3);
		if(!behindNum.equals("")) {
			getResult();
			return true;
		}
		else return false;
	}
	/**
	 * 
	 * 判断能不能添加.号
	 */
	private boolean dealPoint(String str){
		if(str.equals("")){
			str+="0";
			et_input.setText(str);
			return true;
		}
		if(str.charAt(str.length()-1)=='.') return false;
		if(str.charAt(str.length()-1)==' ')
			if(str.charAt(str.length()-2)=='+'||str.charAt(str.length()-2)=='-'
				||str.charAt(str.length()-2)=='×'||str.charAt(str.length()-2)=='÷'){
			str+="0";
			et_input.setText(str);
			return true;
		}
		if(str.contains("+")){
			String behindNum=str.substring(str.indexOf("+")+1);
			if(behindNum.contains(".")) return false;
			else return true;
		}
		if(str.contains("-")){
			String behindNum=str.substring(str.indexOf("-")+1);
			if(behindNum.contains(".")) return false;
			else return true;
		}
		if(str.contains("×")){
			String behindNum=str.substring(str.indexOf("×")+1);
			if(behindNum.contains(".")) return false;
			else return true;
		}
		if(str.contains("÷")){
			String behindNum=str.substring(str.indexOf("÷")+1);
			if(behindNum.contains(".")) return false;
			else return true;
		}
		if(str.contains(".")) return false;
		return true;
	}
}
