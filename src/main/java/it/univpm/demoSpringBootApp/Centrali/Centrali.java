
package it.univpm.demoSpringBootApp.Centrali;
import java.util.List;
import java.io.Serializable;

/** Classe utilizzata per la dichiarazione degli attributi.

 * Considerata la semplicità della gerarchia di attributi presenti, abbiamo ritenuto opportuno creare una classe soltanto

 * che contiene come attributi le colonne del nostro database. *

 */
public class Centrali implements Serializable
{
	/**
	 * Attributes
	 *  Gli attributi sono di tipo string, int e float, .
	 */
	private int id_powerplant,gross_cap_ele,ini_cap_ele,gross_cap_th,no_units,exact_coordinates;

	private String name_powerplant,name_status,name_technology,name_subtechnology,start_year,name_region_L2,name_region_L1,name_country,name_continent,name_owner,name_operator,name_geothermal_area,name_turbine_type,turbine_man,NUTS2_code,NUTS1_code,max_well_depth,min_temp,max_temp,min_flow_rate,max_flow_rate;

	private float latitude,longitude;


	public Centrali(String name_powerplant,String name_status, String name_technology, String name_subtechnology,
			String start_year, String name_region_L2, String name_region_L1, String name_country,String name_continent,
			String name_owner, String name_operator,String name_geothermal_area,String name_turbine_type,String turbine_man,
			String NUTS2_code, String NUTS1_code,String max_well_depth,String min_temp,String max_temp,
			String min_flow_rate,String max_flow_rate, float latitude,float longitude, int id_powerplant,int gross_cap_ele,
			int ini_cap_ele,int gross_cap_th,int no_units,int exact_coordinates)

	{
		this.id_powerplant= id_powerplant;
		this.name_powerplant=name_powerplant;
		this.gross_cap_ele=gross_cap_ele;
		this.ini_cap_ele=ini_cap_ele;
		this.gross_cap_th=gross_cap_th;
		this.name_status=name_status;
		this.name_technology=name_technology;
		this.name_subtechnology=name_subtechnology;
		this.start_year=start_year;
		this.latitude=latitude;
		this.longitude=longitude;
		this.exact_coordinates=exact_coordinates;
		this.name_region_L2=name_region_L2;
		this.NUTS2_code=NUTS2_code;
		this.name_region_L1=name_region_L1;
		this.NUTS1_code=NUTS1_code;
		this.name_country=name_country;
		this.name_continent=name_continent;
		this.max_well_depth=max_well_depth;
		this.min_temp=min_temp;
		this.max_temp=max_temp;
		this.min_flow_rate=min_flow_rate;
		this.max_flow_rate=max_flow_rate;
		this.no_units=no_units;
		this.name_geothermal_area=name_geothermal_area;
		this.name_turbine_type=name_turbine_type;
		this.turbine_man=turbine_man;
		this.name_owner=name_owner;
		this.name_operator=name_owner;

	}

	

	public Centrali() { super();}
	public Centrali( final List<String> powerplant)  {

		this(powerplant.get(0),powerplant.get(1),powerplant.get(2),powerplant.get(3),powerplant.get(4),
				powerplant.get(5),powerplant.get(6),powerplant.get(7),powerplant.get(8),powerplant.get(9),
				powerplant.get(10),powerplant.get(11),powerplant.get(12),powerplant.get(13),powerplant.get(14),
				powerplant.get(15),powerplant.get(16),powerplant.get(17),powerplant.get(18),powerplant.get(19),
				powerplant.get(20),Float.parseFloat(powerplant.get(21)),Float.parseFloat(powerplant.get(22)),
				Integer.parseInt(powerplant.get(23)),Integer.parseInt(powerplant.get(24)),
				Integer.parseInt(powerplant.get(25)),Integer.parseInt(powerplant.get(26)),
				Integer.parseInt(powerplant.get(27)),Integer.parseInt(powerplant.get(28)));
	}
	
	
	@Override
	/*Sistema override*/

	public String toString() {return"id_powerplant:"+id_powerplant+"gross_cap_ele:"+gross_cap_ele+
			"ini_cap_ele:"+ini_cap_ele
			+ "gross_cap_th:"+gross_cap_th+"no_units:"+no_units
			+"exact_coordinates:"+exact_coordinates+"name_powerplant:"+name_powerplant+"name_status:"+name_status
			+"name_technology:"+name_technology+"name_subtechnology:"+name_subtechnology+
			"start_year:"+start_year+"name_region_L2:"+name_region_L2+"name_region_L1"+name_region_L1+
			"name_country:"+name_country+"name_continent:"+name_continent+"name_owner:"+name_owner+
			"name_operator:"+name_operator+"name_geothermal_area:"+name_geothermal_area+
			"name_turbine_type:"+name_turbine_type+"turbine_man:"+turbine_man+"NUTS2_code:"+NUTS2_code+
			"NUTS1_code:"+NUTS1_code+"max_well_depth:"+max_well_depth+"min_temp:"+min_temp+"max_temp:"+max_temp
			+"min_flow_rate:"+min_flow_rate+"max_flow_rate:"+max_flow_rate+"latitude:"+latitude+
			"longitude:"+longitude;  
	}
	
	
	/**
	 * Getters and Setters 
	 */
	public int getId_powerplant() {
		return id_powerplant;
	}

	public void setId_powerplant(int id_powerplant) {
		this.id_powerplant = id_powerplant;
	}

	public int getGross_cap_ele() {
		return gross_cap_ele;
	}

	public void setGross_cap_ele(int gross_cap_ele) {
		this.gross_cap_ele = gross_cap_ele;
	}

	public int getIni_cap_ele() {
		return ini_cap_ele;
	}

	public void setIni_cap_ele(int ini_cap_ele) {
		this.ini_cap_ele = ini_cap_ele;
	}

	public int getGross_cap_th() {
		return gross_cap_th;
	}

	public void setGross_cap_th(int gross_cap_th) {
		this.gross_cap_th = gross_cap_th;
	}

	public int getNo_units() {
		return no_units;
	}

	public void setNo_units(int no_units) {
		this.no_units = no_units;
	}

	public int getExact_coordinates() {
		return exact_coordinates;
	}

	public void setExact_coordinates(int exact_coordinates) {
		this.exact_coordinates = exact_coordinates;
	}

	public String getName_powerplant() {
		return name_powerplant;
	}

	public void setName_powerplant(String name_powerplant) {
		this.name_powerplant = name_powerplant;
	}

	public String getName_status() {
		return name_status;
	}

	public void setName_status(String name_status) {
		this.name_status = name_status;
	}

	public String getName_technology() {
		return name_technology;
	}

	public void setName_technology(String name_technology) {
		this.name_technology = name_technology;
	}

	public String getName_subtechnology() {
		return name_subtechnology;
	}

	public void setName_subtechnology(String name_subtechnology) {
		this.name_subtechnology = name_subtechnology;
	}

	public String getStart_year() {
		return start_year;
	}

	public void setStart_year(String start_year) {
		this.start_year = start_year;
	}

	public String getName_region_L2() {
		return name_region_L2;
	}

	public void setName_region_L2(String name_region_L2) {
		this.name_region_L2 = name_region_L2;
	}

	public String getName_region_L1() {
		return name_region_L1;
	}

	public void setName_region_L1(String name_region_L1) {
		this.name_region_L1 = name_region_L1;
	}

	public String getName_country() {
		return name_country;
	}

	public void setName_country(String name_country) {
		this.name_country = name_country;
	}

	public String getName_continent() {
		return name_continent;
	}

	public void setName_continent(String name_continent) {
		this.name_continent = name_continent;
	}

	public String getName_owner() {
		return name_owner;
	}

	public void setName_owner(String name_owner) {
		this.name_owner = name_owner;
	}

	public String getName_operator() {
		return name_operator;
	}

	public void setName_operator(String name_operator) {
		this.name_operator = name_operator;
	}

	public String getName_geothermal_area() {
		return name_geothermal_area;
	}

	public void setName_geothermal_area(String name_geothermal_area) {
		this.name_geothermal_area = name_geothermal_area;
	}

	public String getName_turbine_type() {
		return name_turbine_type;
	}

	public void setName_turbine_type(String name_turbine_type) {
		this.name_turbine_type = name_turbine_type;
	}

	public String getTurbine_man() {
		return turbine_man;
	}

	public void setTurbine_man(String turbine_man) {
		this.turbine_man = turbine_man;
	}

	public String getNUTS2_code() {
		return NUTS2_code;
	}

	public void setNUTS2_code(String nUTS2_code) {
		NUTS2_code = nUTS2_code;
	}

	public String getNUTS1_code() {
		return NUTS1_code;
	}

	public void setNUTS1_code(String nUTS1_code) {
		NUTS1_code = nUTS1_code;
	}

	public String getMax_well_depth() {
		return max_well_depth;
	}

	public void setMax_well_depth(String max_well_depth) {
		this.max_well_depth = max_well_depth;
	}

	public String getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(String min_temp) {
		this.min_temp = min_temp;
	}

	public String getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(String max_temp) {
		this.max_temp = max_temp;
	}

	public String getMin_flow_rate() {
		return min_flow_rate;
	}

	public void setMin_flow_rate(String min_flow_rate) {
		this.min_flow_rate = min_flow_rate;
	}

	public String getMax_flow_rate() {
		return max_flow_rate;
	}

	public void setMax_flow_rate(String max_flow_rate) {
		this.max_flow_rate = max_flow_rate;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}}
	

	