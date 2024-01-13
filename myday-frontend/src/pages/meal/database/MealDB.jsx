import {
  Button,
  Card,
  Input,
  Select,
  Table,
  Tooltip,
  Space,
  message,
  Modal,
  Image,
} from "antd";
import {
  PlusOutlined,
  SearchOutlined,
  ExclamationCircleOutlined,
  EditOutlined,
  DeleteOutlined,
} from "@ant-design/icons";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import {
  reqVisibleMeals,
  reqDeleteMeal,
  reqVisibleMealsBy,
} from "../../../api";

const { Option } = Select;

function MealDB() {
  // Meal list
  const [meals, setMeals] = useState([]);
  // Meal to edit
  const [loading, setLoading] = useState(false);
  const [searchType, setSearchType] = useState("name");
  const [searchWords, setSearchWords] = useState("");
  const navigate = useNavigate();

  const fetchMeals = async () => {
    setLoading(true);
    // All visibale (non-deleted) items;
    setMeals(await reqVisibleMeals());
    setLoading(false);
  };

  useEffect(() => {
    fetchMeals();
  }, []);

  const searchMeals = async () => {
    const result = await reqVisibleMealsBy(searchType, searchWords);
    setMeals(result);
  };

  const deleteMeal = (id) => {
    const deleteReq = async (id) => {
      try {
        const response = await reqDeleteMeal(id);
        console.log("Delete category resopnse: ", response);
        fetchMeals();
      } catch (err) {
        message.error(err.message || err);
      }
    };
    Modal.confirm({
      title: "Do you want to delete this meal?",
      icon: <ExclamationCircleOutlined />,
      onOk() {
        deleteReq(id);
      },
      onCancel() {},
    });
  };

  const title = (
    <span>
      <Select
        defaultValue="name"
        style={{
          width: 110,
        }}
        onChange={(value) => setSearchType(value)}
      >
        <Option value="name">Name</Option>
        <Option value="category">Category</Option>
      </Select>
      <Input
        placeholder="Keywords"
        style={{ width: 110, margin: "0 5px" }}
        onChange={(e) => setSearchWords(e.target.value)}
      />
      <Tooltip title="search">
        <Button
          onClick={searchMeals}
          shape="circle"
          icon={<SearchOutlined />}
        />
      </Tooltip>
    </span>
  );

  const extra = (
    <Button
      type="primary"
      style={{ margin: 0 }}
      onClick={() => navigate("/meal/edit")}
    >
      <PlusOutlined />
      Add
    </Button>
  );

  const columns = [
    {
      title: "Cover",
      dataIndex: "avatar",
      key: "avatar",
      render: (text) => <Image width={50} src={text} alt="category" />,
    },
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      sorter: (a, b) => a.name.localeCompare(b.name),
    },
    {
      title: "Category",
      dataIndex: "category",
      key: "category",
      sorter: (a, b) => a.category.localeCompare(b.category),
    },
    {
      title: "Description",
      dataIndex: "desc",
      key: "desc",
      sorter: (a, b) => a.desc.length - b.desc.length,
      responsive: ["lg"],
    },
    {
      title: "Calories",
      dataIndex: "calPerServing",
      key: "calPerServing",
      sorter: (a, b) => a.calPerServing - b.calPerServing,
    },
    {
      title: "Action",
      key: "action",
      render: (_, meal) => (
        <Space size="small">
          <EditOutlined
            onClick={() => navigate("/meal/edit/", { state: { meal } })}
            style={{ margin: 0, padding: 0 }}
          />
          <DeleteOutlined
            onClick={() => deleteMeal(meal.id)}
            style={{ margin: 0, padding: 0 }}
          />
        </Space>
      ),
    },
  ];

  return (
    <Card title={title} extra={extra}>
      <Table
        boarderd
        rowKey="id"
        loading={loading}
        dataSource={meals}
        columns={columns}
        pagination={{ defaultPageSize: 5, showQuickJumper: true }}
      />
    </Card>
  );
}

export default MealDB;
