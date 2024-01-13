import { Link, useLocation } from "react-router-dom";
import { Menu } from "antd";
import "./index.css";
import logo from "../../assets/images/logo.png";
import { menuList } from "../../config/menuList";

const items = [...menuList];

function LeftNav({ collapsed }) {
  const location = useLocation();
  const path = location.pathname;
  let openKey;
  items.forEach((item) => {
    if (item.children) {
      const childItem = item.children.find((child) => child.key === path);
      if (childItem) openKey = item.key;
    }
  });
  return (
    <div className="left-nav">
      <Link to="/" className="left-nav-header">
        <img src={logo} alt="logo" />
        <h1 style={{ display: collapsed ? "none" : "" }}>MyDay</h1>
      </Link>
      <Menu
        theme="dark"
        selectedKeys={[path ?? "1"]}
        defaultOpenKeys={[openKey]}
        mode="inline"
        items={items}
      />
    </div>
  );
}

export default LeftNav;
