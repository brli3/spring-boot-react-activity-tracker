import { Button } from "antd";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Modal } from "antd";
import { ExclamationCircleOutlined } from "@ant-design/icons";
import { reqWeather } from "../../api";
import { menuList } from "../../config/menuList";
import { formatDate } from "../../utils/dateUtils";
import storageUtils from "../../utils/storageUtils";
import "./index.css";

const username = storageUtils.getUser().username;

function Header() {
  const [temperature, setTemperature] = useState(20);
  const [city] = useState("London,uk"); // TODO: set current location
  const [weatherIcon, setWeatherIcon] = useState("02d");
  const [weatherImageUrl, setWeatherImageUrl] = useState(
    "http://openweathermap.org/img/wn/02d@2x.png"
  );
  const [currentTime, setCurrentTime] = useState(formatDate(Date.now()));
  const [pageTitle, setPageTitle] = useState("");

  const location = useLocation();

  const navigate = useNavigate();

  const logout = () => {
    Modal.confirm({
      title: "Do you want to log out?",
      icon: <ExclamationCircleOutlined />,
      content: 'You will be redirected to the login page',

      onOk() {
        console.log('User logging out');
        storageUtils.removeUser();
        navigate("/login");
      },

      onCancel() {},
    });
  };

  const getPageTile = () => {
    const path = location.pathname;
    let title;
    menuList.forEach((item) => {
      if (item.key === path) {
        title = item.label.props.children;
      } else if (item.children) {
        const childItem = item.children.find(
          (childItem) => childItem.key === path
        );
        if (childItem) {
          title = childItem.label.props.children;
        }
      }
    });
    return title;
  };

  // Ajax request to get weather info
  const getWeather = async (city) => {
    const response = await reqWeather(city);
    if (Number(response.cod) !== 200) return;
    setTemperature(Math.round(response.main.temp));
    setWeatherIcon(response.weather[0].icon);
    setWeatherImageUrl(
      `http://openweathermap.org/img/wn/${weatherIcon}@2x.png`
    );
  };

  // Get weather info after first render
  useEffect(() => {
    getWeather(city); //eslint-disable-next-line
  }, []);

  // Set a timer to update the current time after first render
  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentTime(formatDate(Date.now()));
    }, 1000);

    // returned function will be called on component unmount 
    return () => {
      clearInterval(intervalId);
    };
  }, []);

  // Get page title when path changes
  useEffect(() => {
    setPageTitle(getPageTile());  //eslint-disable-next-line
  }, [location.pathname]);

  return (
    <div className="header">
      <div className="header-top">
        <span>Welcome, {username}</span>
        <Button
          type="link"
          size="small"
          style={{ padding: 1 }}
          onClick={logout}
        >
          Log out
        </Button>
      </div>
      <div className="header-bottom">
        <div className="header-bottom-left">{pageTitle}</div>
        <div className="header-bottom-right">
          <span>{currentTime}</span>
          <img src={weatherImageUrl} alt="weather" style={{ margin: 0 }} />
          <span>{temperature}&#8451;</span>
        </div>
      </div>
    </div>
  );
}

export default Header;
