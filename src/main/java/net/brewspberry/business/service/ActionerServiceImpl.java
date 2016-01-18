package net.brewspberry.business.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import net.brewspberry.adapter.RelayAdapter;
import net.brewspberry.business.IGenericDao;
import net.brewspberry.business.IGenericService;
import net.brewspberry.business.ISpecificActionerDao;
import net.brewspberry.business.ISpecificActionerService;
import net.brewspberry.business.beans.Actioner;
import net.brewspberry.business.beans.Brassin;
import net.brewspberry.business.beans.Etape;
import net.brewspberry.business.exceptions.NotAppropriateStatusException;
import net.brewspberry.dao.ActionerDaoImpl;
import net.brewspberry.exceptions.DAOException;
import net.brewspberry.exceptions.ServiceException;
import net.brewspberry.util.ConfigLoader;
import net.brewspberry.util.Constants;
import net.brewspberry.util.LogManager;

public class ActionerServiceImpl implements IGenericService<Actioner>,
ISpecificActionerService
		 {

	public static final Logger logger = LogManager.getInstance(ActionerServiceImpl.class.toString());

	String commandLineRegexp = "/home/pi/batches/bchrectemp.py [0-9]{0,5} [0-9]{0,5}";

	Pattern pattern = Pattern.compile(commandLineRegexp);

	IGenericDao<Actioner> actionerDao = new ActionerDaoImpl();
	ISpecificActionerDao actionerSpecDao = new ActionerDaoImpl();

	String getTemperatureRunningGrep = "ps -ef | grep bchrectemp.py";

	final GpioController gpioController = GpioFactory.getInstance();
	RelayAdapter relayAdapter = RelayAdapter.getInstance();

	public ActionerServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Actioner save(Actioner arg0) throws DAOException {

		return actionerDao.save(arg0);
	}

	@Override
	public Actioner update(Actioner arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actioner getElementById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actioner> getAllElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteElement(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteElement(Actioner arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Actioner> getAllDistinctElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actioner> getActionerByBrassin(Brassin brassin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actioner> getActionnerByEtape(Etape etape) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Actioner> getRealTimeActionersByName(List<String> which,
			Boolean uuid, Brassin brassin, Etape etape) throws IOException,
			ServiceException {

		/*
		 * command format : BCHRECTEMP.py ActionerName ActionerUUID
		 */

		List<Actioner> result = new ArrayList<Actioner>();
		Process commandResult = null;

		for (String actName : which) {
			try {
				commandResult = Runtime.getRuntime().exec(
						getTemperatureRunningGrep + "| grep " + which);
			} catch (IOException e) {
				System.out.println(getTemperatureRunningGrep + "| grep "
						+ which + " threw Exception");
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					commandResult.getInputStream()));
			String s = "";
			while ((s = reader.readLine()) != null) {

				Matcher matcher = pattern.matcher(s);
				Actioner curAct = new Actioner();
				String[] actionerCommand = new String[3];
				String actionerFileScriptPath = "";
				String actionerProbeName = "";
				String actionerProbeUUID = "";
				Actioner actionerDoesExists = new Actioner();

				if (matcher.find()) {

					if (matcher.group(0).split(" ").length == 3) {
						actionerCommand = matcher.group(0).split(" ");

						actionerFileScriptPath = actionerCommand[0];
						actionerProbeName = actionerCommand[1];
						actionerProbeUUID = actionerCommand[2];

						curAct.setAct_type(Constants.ACT_DS18B20);
						curAct.setAct_nom(actionerProbeName);
						curAct.setAct_uuid(actionerProbeUUID);
						curAct.setAct_brassin(brassin);
						curAct.setAct_etape(etape);

						actionerDoesExists = actionerSpecDao
								.getActionerByFullCharacteristics(curAct);

						if (actionerDoesExists.getAct_date_debut() != null) {

							curAct = actionerDoesExists;
							result.add(curAct);
						} else {
							throw new ServiceException(
									"Actioner does not exist ! "
											+ curAct.toString());
						}

					}
				}
			}
		}

		return result;
	}

	public List<Actioner> getAllRealTimeActioners() {
		List<Actioner> result = new ArrayList<Actioner>();

		return result;
	}

	private Actioner startActionInDatabase(Actioner arg0)
			throws ServiceException, NotAppropriateStatusException {

		/*
		 * Starts the action and saves it in database.
		 */

		Actioner result = null;
		if (arg0.getAct_date_debut() == null) {
			arg0.setAct_date_debut(new Date());
			arg0.setAct_status(Constants.ACT_RUNNING);
		}

		if (arg0.getAct_activated() == false && arg0.getAct_used() == false) {

			arg0.setAct_activated(true);
		} else
			throw new NotAppropriateStatusException();
		try {
			result = this.save(arg0);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Couldn't save Actioner "
					+ arg0.toString());
		}
		return result;
	}

	private Actioner stopActionInDatabase(Actioner arg0)
			throws ServiceException, NotAppropriateStatusException {

		/*
		 * When clicking stop for example, adds end date and saves the action
		 * setting Actioner status to STOPPED
		 */

		Actioner result = null;

		if (arg0.getAct_id() == 0) {
			// Searching by uuid, brassin, etape, and type
			arg0 = actionerSpecDao.getActionerByFullCharacteristics(arg0);
		}

		if (arg0.getAct_date_fin() == null) {
			arg0.setAct_date_fin(new Date());
			arg0.setAct_status(Constants.ACT_STOPPED);
		}
		if (arg0.getAct_activated() == true && arg0.getAct_used() == false) {

			arg0.setAct_used(true);

		} else
			throw new NotAppropriateStatusException();

		try {
			result = actionerDao.update(arg0);
		} catch (Exception e) {
			throw new ServiceException("Couldn't update Actioner :"
					+ arg0.toString());
		}
		return result;
	}

	/**
	 * startAction starts device for selected Actioner - ds18b20 : launches
	 * Python script that collects and stores temperatures measured by ds18b20s
	 * - relays : starting action means setting PinState to HIGH thus running
	 * the device
	 * 
	 * It also stores the Actioner in database
	 */
	@Override
	public Actioner startAction(Actioner actioner) throws Exception {

		if (actioner != null) {

			switch (actioner.getAct_type()) {

			case "1":
				// DS18B20

				/*
				 * When loading ds18b20, it executes python script that collects
				 * temperature data
				 * 
				 * Requires act_type, act_brassin, act_etape, name, uuid
				 */

				logger.info("It's an DS18B20 :");
				File f = new File(ConfigLoader.getConfigByKey(
						Constants.CONFIG_PROPERTIES,
						"files.measurement.python.script"));

				if (f.exists() && !f.isDirectory()) {

					try {

						// Starts python job and saves action in DB
						actioner = this.startActionInDatabase(actioner);

						Runtime.getRuntime().exec(
								"/usr/bin/python " + f.getAbsolutePath() + " "
										+ actioner.getAct_brassin().getBra_id()
										+ " "
										+ actioner.getAct_etape().getEtp_id()
										+ " " + actioner.getAct_id());

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					throw new Exception("File " + f.getAbsolutePath()
							+ " not found");
				}

				break;

			case "2":

				// Relay
				logger.info("It's an relay :");

				logger.info("Provisionning pin " + actioner.getAct_raspi_pin()
						+ " "
						+ Constants.BREW_GPIO.get(actioner.getAct_raspi_pin()));
				relayAdapter.changePinState(Constants.BREW_GPIO
								.get(actioner.getAct_raspi_pin()),PinState.HIGH);
				actioner.setAct_status(Constants.ACT_RUNNING);
				actioner.setAct_date_debut(new Date());
				
				
				if (relayAdapter.getStateAsString(Constants.BREW_GPIO
						.get(actioner.getAct_raspi_pin())) != "HIGH") {

					throw new Exception(
							"PinState not high, State change failed !");
				}

				break;

			}

		}
		return actioner;

	}

	/**
	 * Whenever user stops an actioner (for example switch off a relay), this
	 * method must be called !
	 * 
	 * It stops configured devices. for the moment : - relays (type 2) - ds18b20
	 * temperature sensors (type 1). For these ones in fact it stops the job
	 * collecting temperatures)
	 * 
	 * @param Actioner
	 *            that has to be stopped. Actioner must have ID
	 * @return
	 * @throws NotAppropriateStatusException
	 * 
	 */
	@Override
	public Actioner stopAction(Actioner actioner) throws IOException,
			ServiceException, NotAppropriateStatusException {
		if (actioner != null) {

			switch (actioner.getAct_type()) {

			case "1":

				// ds18b20

				Process proc = null;
				try {
					proc = Runtime.getRuntime().exec(getTemperatureRunningGrep);
				} catch (IOException e) {
					e.printStackTrace();
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
						proc.getInputStream()));

				String line = null;

				
				int counter = 0;
				while ((line = br.readLine()) != null) {

					if (line.matches(commandLineRegexp)) {

						System.out.println("Matching process found !");

						String pid = this.getPIDFromPs("bchrectemp.py "
								+ actioner.getAct_brassin().getBra_id() + " "
								+ actioner.getAct_etape().getEtp_id());

						if (pid != "") {
							Runtime.getRuntime().exec("kill -SIGTERM " + pid);
							counter++;
						}

						actioner = this.stopActionInDatabase(actioner);
					}
				}

				break;

			case "2":

				// Relay

				if (actioner.getAct_raspi_pin() != null) {

					GpioPinDigitalOutput gpio = gpioController
							.provisionDigitalOutputPin(Constants.BREW_GPIO
									.get(actioner.getAct_raspi_pin()));

					if (gpio.getState() == PinState.HIGH) {

						gpio.setState(PinState.LOW);

						actioner = this.stopActionInDatabase(actioner);
					}

				}
				break;

			}

		}
		return actioner;

	}

	public String getPIDFromPs(String line) throws IOException {

		String result = null;
		Process proc = Runtime.getRuntime().exec(
				"/bin/ps -fe | grep \"" + line
						+ "\" | grep -v \"grep\" | awk \'{print $2}\'");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				proc.getInputStream()));
		String grepLine = null;

		if ((grepLine = br.readLine()) != null) {

			result = line;

		} else {
			result = new String();
			System.out.println("No results found !!");
		}

		return result;
	}
}
