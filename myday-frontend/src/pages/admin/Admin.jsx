import { useState } from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import { Layout } from "antd";
import { GithubOutlined } from "@ant-design/icons";
import LeftNav from "../../components/left-nav";
import Header from "../../components/header";
import storageUtils from "../../utils/storageUtils";
import Home from "../home/Home";
import Category from "../exercise/category/Category";
import ExerciseDB from "../exercise/database/ExerciseDB";
import Role from "../role/Role";
import User from "../user/User";
import Bar from "../charts/Bar";
import Line from "../charts/Line";
import Nutrition from "../meal/nutrition/Nutrition";
import MealDB from "../meal/database/MealDB";
import CreateActivity from "../activity/create/CreateActivity";
import EditExercise from "../exercise/database/EditExercise";
import Calories from "../exercise/calories/Calories";
import EditMeal from "../meal/database/EditMeal";
import Activity from "../activity/Activity";

const { Footer, Sider, Content } = Layout;

function Admin() {
  const [collapsed, setCollapsed] = useState(false);

  const user = storageUtils.getUser();

  if (!user || Object.keys(user).length === 0) {
    return <Navigate to={"/login"} />;
  }

  return (
    <Layout style={{ height: "100%" }}>
      <Sider
        collapsible
        collapsed={collapsed}
        onCollapse={(value) => setCollapsed(value)}
        style={{
          overflow: "auto",
          height: "100vh",
          position: "fixed",
          left: 0,
          top: 0,
          bottom: 0,
        }}
      >
        <LeftNav collapsed={collapsed} />
      </Sider>
      <Layout
        style={{
          marginLeft: collapsed ? 80 : 200,
        }}
      >
        <Header>Header</Header>
        <Content style={{ margin: "20px", backgroundColor: "#fff" }}>
          <Routes>
            <Route path="/home" element={<Home />} />
            <Route path="/exercise/category" element={<Category />} />
            <Route path="/exercise/database" element={<ExerciseDB />} />
            <Route path="/exercise/edit" element={<EditExercise />} />
            <Route path="/exercise/calories" element={<Calories />} />
            <Route path="/meal/nutrition" element={<Nutrition />} />
            <Route path="/meal/database" element={<MealDB />} />
            <Route path="/meal/edit" element={<EditMeal />} />
            <Route path="/activity" element={<Activity />} />
            <Route path="/activity/create" element={<CreateActivity />} />
            <Route path="/charts/bar" element={<Bar />} />
            <Route path="/charts/line" element={<Line />} />
            <Route path="/role" element={<Role />} />
            <Route path="/user" element={<User />} />
            <Route path="/*" element={<Home />} />
          </Routes>
        </Content>
        <Footer
          style={{ textAlign: "center", color: "#cccccc", maxHeight: "10px" }}
        >
          MyDay &copy;{new Date(Date.now()).getFullYear()} Brandon
          <a href="https://github.com/brli3" target="_blank" rel="noreferrer">
            <GithubOutlined style={{ margin: "2px" }} />
          </a>
        </Footer>
      </Layout>
    </Layout>
  );
}

export default Admin;
