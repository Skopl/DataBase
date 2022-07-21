package db;

import java.sql.*;

public class DataBaseLaunch {
	public static final String DB_URL = "jdbc:sqlite:MyCats.db";
	public static final String DB_Driver = "org.sqlite.JDBC";
	
	public static void main(String [] args) {
		addMoreCats(3000);
	}
	
	public static void deleteType(int id) {
		String SQLcommand = "DELETE FROM typess WHERE id == " + id;
		//System.out.print(SQLcommand);
		try {
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			
			Statement stat = connection.createStatement();
			stat.executeUpdate(SQLcommand);
			
			connection.close();	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
			}
		
	}

	public static void updateType(int id,String newType) {
		String SQLcommand = "UPDATE typess SET type = '"+newType+"' WHERE id == "+id; 
		//System.out.println(SQLcommand);
		try {
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			
			Statement stat = connection.createStatement();
			stat.executeUpdate(SQLcommand);
			
			connection.close();	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
			}
	}

	public static void getType(int id) {
		String SQLcommand = "SELECT type FROM typess WHERE id == "+ id; 
		//System.out.println(SQLcommand);
		try {
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery(SQLcommand);
			
			System.out.println(result.getString("type"));
			
			connection.close();	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
			}
	}
	
	public static void getTypeWhere(String where) {
		String SQLcommand = "SELECT type FROM typess WHERE "+where+";"; 
		//System.out.println(SQLcommand);
		try {
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery(SQLcommand);
			
			while(result.next()) {
				String type = result.getString("type");
				System.out.print(type+"\n");
			}
			
			connection.close();	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
			}
	}

	public static void getAllTypes() {
		String SQLcommand = "SELECT * FROM typess"; 
		//System.out.println(SQLcommand);
		try {
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery(SQLcommand);
			
			while(result.next()) {
				int id = result.getInt("id");
				String type = result.getString("type");
				System.out.print(id + "\t" + type+"\n");
			}
			
			connection.close();	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
			}
	}

	public static void createTableCats() {
		String SQLcommand = "CREATE TABLE IF NOT EXISTS cats(id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, name VARCHAR(20) NOT NULL,type_id INTEGER NOT NULL REFERENCES typess(id), age INTEGER NOT NULL, weight DOUBLE);"; 
		//System.out.println(SQLcommand);
		try {
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			
			Statement stat = connection.createStatement();
			stat.executeUpdate(SQLcommand);
			
			connection.close();	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
			}
	}

	public static void insertCat(String name,String type,int age, Double weight) {
		String SQLcommand = "SELECT id FROM typess WHERE type = '"+type+"';"; 
		//System.out.println(SQLcommand);
		try {
			ResultSet result;
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			
			Statement stat = connection.createStatement();
			
			//Проверяем, есть ли в таблице заданное значение, при отсутствии создаем,перезапускаем )))))))
			try {
				result = stat.executeQuery(SQLcommand);	 
				SQLcommand = "INSERT INTO cats(name,type_id,age,weight) VALUES('"+name+"',"+result.getInt("id")+","+age+","+weight+")";
			}
			catch(SQLException e) {
				stat.executeUpdate("INSERT INTO typess(type) VALUES ('"+type+"');");
				insertCat(name,type,age,weight);
			}
			
			stat.executeUpdate(SQLcommand);
			
			connection.close();	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();	
			}
	}

	public static void addMoreCats(int n) {
		String[] names = {"Гарфилд","Том","Гудвин","Рокки","Ленивец","Пушок","Спорти","Бегемот","Пират","Гудини","Зорро","Саймон","Альбус","Базилио","Леопольд","Нарцисс","Атос","Каспер","Валлито","Оксфорд","Бисквит","Соня","Клеопатра","Цунами","Забияка","Матильда","Кнопка","Масяня","Царапка","Серсея","Ворсинка","Амели","Наоми","Маркиза","Изольда","Вальс","Несквик","Златан","Баскет","Изюм","Цукат","Мокко","Месси","Кокос","Адидас","Бейлиз","Тайгер","Зефир","Мохи","Валенсия","Баунти","Свити","Текила","Ириска","Карамель","Виски","Кукуруза","Гренка","Фасолька","Льдинка","Китана","Офелия","Дайкири","Брусника","Аватар","Космос","Призрак","Изумруд","Плинтус","Яндекс","Крисмас","Метеор","Оптимус","Смайлик","Цельсий","Краска","Дейзи","Пенка","Веста","Астра","Эйприл","Среда","Бусинка","Гайка","Елка","Золушка","Мята","Радость","Сиам","Оскар","Феликс","Гарри","Байрон","Чарли","Симба","Тао","Абу","Ватсон","Енисей","Измир","Кайзер","Васаби","Байкал","Багира","Айрис","Диана","Мими","Сакура","Индия","Тиффани","Скарлетт","Пикси","Лиззи","Алиса","Лило","Ямайка","Пэрис","Мальта","Аляска"};
		String[] porodas = {"Темнокажая", "Великая", "Гладкошерстная", "Каких не бывает", "Абиссинская кошка","Австралийская дымчатая","популярнa","Азиатская","Американская длинношерстная","Американская жесткошерстная","Американская короткошерстная","Бамбино","Бенгальская кошка","Бразильская короткошерстная кошкa","Бурмилла"};
		
		int numOfName;
		int numOfPoroda;
		int age;
		double weight;		
		
		for(int i=0;i<n;i++) {
			age =  1 + (int)(Math.random()*30);
			weight = 1 + (Math.random()*15); 
			numOfName = 0 + (int)(Math.random()*names.length);
			numOfPoroda = 0 + (int)(Math.random()*porodas.length);
			insertCat(names[numOfName],porodas[numOfPoroda],age,weight);
		}
	}
}
