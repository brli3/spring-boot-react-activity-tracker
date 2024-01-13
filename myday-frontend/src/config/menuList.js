import { Link } from "react-router-dom";
import {
  HomeOutlined,
  FireOutlined,
  CoffeeOutlined,
  UserOutlined,
  SafetyCertificateOutlined,
  AreaChartOutlined,
  UnorderedListOutlined,
} from "@ant-design/icons";

export const menuList = [
  {
    label: <Link to="/home">Home</Link>,
    key: "/home",
    icon: <HomeOutlined />,
  },
  {
    label: "Exercise",
    key: "exercise",
    icon: <FireOutlined />,
    children: [
      {
        label: <Link to="/exercise/category">Category</Link>,
        key: "/exercise/category",
      },
      {
        label: <Link to="/exercise/database">Database</Link>,
        key: "/exercise/database",
      },
      {
        label: <Link to="/exercise/calories">Calories</Link>,
        key: "/exercise/calories",
      },
    ],
  },
  {
    label: "Meal",
    key: "meal",
    icon: <CoffeeOutlined />,
    children: [
      {
        label: <Link to="/meal/nutrition">Nutrition</Link>,
        key: "/meal/nutrition",
      },
      {
        label: <Link to="/meal/database">Database</Link>,
        key: "/meal/database",
      },
    ],
  },
  {
    label: <Link to="/activity">Activity</Link>,
    key: "/activity",
    icon: <UnorderedListOutlined />,
  },
  {
    label: "Charts",
    key: "charts",
    icon: <AreaChartOutlined />,
    children: [
      {
        label: <Link to="/charts/bar">Bar Charts</Link>,
        key: "/charts/bar",
      },
      {
        label: <Link to="/charts/line">Line Charts</Link>,
        key: "/charts/line",
      },
    ],
  },

  {
    label: <Link to="/user">User</Link>,
    key: "/user",
    icon: <UserOutlined />,
  },

  {
    label: <Link to="/role">Role</Link>,
    key: "/role",
    icon: <SafetyCertificateOutlined />,
  },
];
