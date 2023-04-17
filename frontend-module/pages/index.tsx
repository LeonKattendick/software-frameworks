import { useGetAllPlayers } from '@/util/hooks/useGetAllPlayers';
import { Player } from '@/util/interfaces/Player';
import { Table } from 'antd';
import Link from 'next/link';

const Home = () => {
  const { players } = useGetAllPlayers();

  return (
    <Table dataSource={players}>
      <Table.Column title="ID" render={(_, record: Player) => <Link href={`/player/${record.player_id}`}>{record.player_id}</Link>} />
      <Table.Column dataIndex="name" title="Name" />
      <Table.Column dataIndex="game_type" title="Spiel" />
      <Table.Column dataIndex="global_kda" title="KDA" />
    </Table>
  );
};

export default Home;
