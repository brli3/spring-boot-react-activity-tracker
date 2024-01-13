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
  reqVisibleExercises,
  reqDeleteExercise,
  reqVisibleExercisesBy,
} from "../../../api";

const { Option } = Select;

function ExerciseDB() {
  // Exercise list
  const [exercises, setExercises] = useState([]);
  // Exercise to edit
  const [loading, setLoading] = useState(false);
  const [searchType, setSearchType] = useState("name");
  const [searchWords, setSearchWords] = useState("");
  const navigate = useNavigate();

  const fetchExercises = async () => {
    setLoading(true);
    // All visibale (non-deleted) items;
    setExercises(await reqVisibleExercises());
    setLoading(false);
  };

  useEffect(() => {
    fetchExercises();
  }, []);

  const searchExercises = async () => {
    const result = await reqVisibleExercisesBy(searchType, searchWords);
    setExercises(result);
  };

  const deleteExercise = (id) => {
    const deleteReq = async (id) => {
      try {
        const response = await reqDeleteExercise(id);
        console.log("Delete category resopnse: ", response);
        fetchExercises();
      } catch (err) {
        message.error(err.message || err);
      }
    };
    Modal.confirm({
      title: "Do you want to delete this exercise?",
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
          onClick={searchExercises}
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
      onClick={() => navigate("/exercise/edit")}
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
      title: "Description",
      dataIndex: "desc",
      key: "desc",
      sorter: (a, b) => a.desc.length - b.desc.length,
    },
    {
      title: "Category",
      dataIndex: "category",
      key: "category",
      sorter: (a, b) => a.name.localeCompare(b.name),
      responsive: ["md"],
    },
    {
      title: "METs",
      dataIndex: "met",
      key: "met",
      sorter: (a, b) => a.met - b.met,
      responsive: ["lg"],
    },
    {
      title: "Action",
      key: "action",
      responsive: ["sm"],
      render: (_, exercise) => (
        <Space size="small">
          <EditOutlined 
            onClick={() => navigate("/exercise/edit/", { state: { exercise } })}
            style={{ margin: 0, padding: 0 }}
          />
          <DeleteOutlined 
            onClick={() => deleteExercise(exercise.id)}
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
        dataSource={exercises}
        columns={columns}
        pagination={{ defaultPageSize: 5, showQuickJumper: true }}
      />
    </Card>
  );
}

export default ExerciseDB;
